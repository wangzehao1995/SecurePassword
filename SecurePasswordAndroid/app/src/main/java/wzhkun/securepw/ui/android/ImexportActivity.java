package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.IOStreamSupplier;
import wzhkun.securepw.bl.MyFile;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;
import wzhkun.securepw.ui.android.alert.WrongPasswordAlert;
import wzhkun.securepw.ui.android.alert.WrongSafeFileAlert;

/**
 * Created by wangzehao on 2015/8/8.
 */
public class ImexportActivity extends Activity {
    public static final int IMPORT = 0;
    public static final int EXPORT = 1;
    private static final int FILE_SELECT_CODE = 5454545;//any value is fine
    private int type;
    private TextView imexport_label_imexport_from;
    private Button imexport_choose;
    private EditText imexport_from;
    private TextView imexport_label_imexport_password;
    private EditText imexport_password;
    private CheckBox imexport_use_my_password;
    private Button imexport_imexport;
    private Button imexport_cancel;

    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imexport);

        Intent intent = getIntent();
        type = intent.getIntExtra("type", IMPORT);

        initCommonPart();
        if (type == IMPORT) {
            setImportText();
            setImportListener();
        } else {
            setExportText();
            setExportListener();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            if (uri != null) {
                imexport_from.setText(uri.toString());
                fileUri = uri;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void initCommonPart() {

        imexport_label_imexport_from = (TextView) findViewById(R.id.imexport_label_imexport_from);
        imexport_choose = (Button) findViewById(R.id.imexport_choose);
        imexport_from = (EditText) findViewById(R.id.imexport_from);
        imexport_label_imexport_password = (TextView) findViewById(R.id.imexport_label_imexport_password);
        imexport_password = (EditText) findViewById(R.id.imexport_password);
        imexport_use_my_password = (CheckBox) findViewById(R.id.imexport_use_my_password);
        imexport_imexport = (Button) findViewById(R.id.imexport_imexport);
        imexport_cancel = (Button) findViewById(R.id.imexport_cancel);

        setCommonListener();
    }

    private void setImportText() {
        imexport_label_imexport_from.setText(R.string.import_file);
        imexport_label_imexport_password.setText(R.string.import_file_password);
        imexport_imexport.setText(R.string.import_);
    }

    private void setExportText() {
        imexport_label_imexport_from.setText(R.string.export_file);
        imexport_label_imexport_password.setText(R.string.export_file_password);
        imexport_imexport.setText(R.string.export);
    }

    private void setImportListener() {
        imexport_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, ""), FILE_SELECT_CODE);
            }
        });
        imexport_imexport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileUri != null) {
                    try {
                        MyFile file = new MyFile();
                        file.setInputStream(new IOStreamSupplier<InputStream>() {
                            @Override
                            public InputStream get() throws IOException {
                                return getContentResolver().openInputStream(fileUri);
                            }
                        });
                        file.setOutputStream(new IOStreamSupplier<OutputStream>() {
                            @Override
                            public OutputStream get() throws IOException {
                                return getContentResolver().openOutputStream(fileUri);
                            }
                        });
                        if (type == IMPORT) {
                            BLServiceManager.getPasswordSafeBL().import_(file);
                        } else {
                            BLServiceManager.getPasswordSafeBL().export(file);
                        }
                    } catch (BadPaddingException e) {
                        new WrongPasswordAlert(ImexportActivity.this).show();
                    } catch (ClassNotFoundException e) {
                        new WrongSafeFileAlert(ImexportActivity.this).show();
                    } catch (IOException e) {
                        new UnableToAccessFileAlert(ImexportActivity.this).show();
                    }
                }
            }
        });
    }

    private void setExportListener() {

    }

    private void setCommonListener() {
        imexport_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void cancel() {
        finish();
    }
}
