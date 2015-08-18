package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.IOStreamSupplier;
import wzhkun.securepw.bl.MyFile;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;

public class SyncActivity extends Activity {
    EditText path;
    private static final int FILE_SELECT_CODE = 55454545;//any value is fine

    public static void setSyncFile(final Context context,String syncFile) throws IOException {
        if(context==null||syncFile==null){
            return;
        }
        final Uri fileUri;
        try {
            fileUri = Uri.parse(syncFile);
        }catch (Exception uriFormatException){
            uriFormatException.printStackTrace();
            return;
        }

        BLServiceManager.getSettingBL().setSyncFilePath(fileUri.toString());

        MyFile syncMyFile=new MyFile();
        syncMyFile.setInputStream(new IOStreamSupplier<InputStream>() {
            @Override
            public InputStream get() throws IOException {
                return context.getContentResolver().openInputStream(fileUri);
            }
        });
        syncMyFile.setOutputStream(new IOStreamSupplier<OutputStream>() {
            @Override
            public OutputStream get() throws IOException {
                return context.getContentResolver().openOutputStream(fileUri);
            }
        });

        BLServiceManager.getPasswordSafeBL().setSyncFile(syncMyFile);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        path= (EditText) findViewById(R.id.sync_path);

        String settringPath=BLServiceManager.getSettingBL().getSyncFilePath();
        path.setText(settringPath);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data==null){
            return;
        }
        if (requestCode == FILE_SELECT_CODE) {
            Uri uri = data.getData();
            if (uri != null) {
                path.setText(uri.toString());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void choose(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(Intent.createChooser(intent, ""), FILE_SELECT_CODE);
    }

    public void confirm(View view){
        try {
            SyncActivity.setSyncFile(this,path.getText().toString());
            cancel(null);
        } catch (IOException e) {
            new UnableToAccessFileAlert(this);
        } catch (Exception uriFormatException){
            uriFormatException.printStackTrace();
        }
    }

    public void cancel(View view){
        this.finish();
    }
}
