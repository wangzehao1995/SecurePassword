package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.IOStreamSupplier;
import wzhkun.securepw.bl.MyFile;
import wzhkun.securepw.bl.PasswordSafeBL;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;
import wzhkun.securepw.ui.android.alert.WrongPasswordAlert;
import wzhkun.securepw.ui.android.alert.WrongSafeFileAlert;

public class LoginActivity extends Activity {
    PasswordSafeBL bl;
    EditText password;
    private final MyFile localSafe=makeMyFile("password.safe");
    private final MyFile settingFile=makeMyFile("securepw.setting");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bl= BLServiceManager.getPasswordSafeBL();
        password= (EditText) findViewById(R.id.login_password);

        bl.setLocalFile(localSafe);
        BLServiceManager.getSettingBL().setSettingFile(settingFile);
    }

    public void login(View view){
        try {
            bl.login(String.valueOf(password.getText()));
            clear();
            Intent intent = new Intent();
            intent.setClass(this, MainActivity.class);
            this.startActivity(intent);
        } catch (BadPaddingException e) {
            new WrongPasswordAlert(this).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            new WrongSafeFileAlert(this).show();
            e.printStackTrace();
        } catch (IOException e) {
            new UnableToAccessFileAlert(this).show();
            e.printStackTrace();
        }
    }

    public void reset(View view){
        Intent intent = new Intent();
        intent.setClass(this, ResetActivity.class);
        this.startActivity(intent);
    }

    private void clear(){
        password.setText("");
    }

    private MyFile makeMyFile(String fileName){
        MyFile result=new MyFile();
        result.setInputStream(new IOStreamSupplier<InputStream>() {
            @Override
            public InputStream get() throws IOException {
                return openFileInput(fileName);
            }
        });
        result.setOutputStream(new IOStreamSupplier<OutputStream>() {
            @Override
            public OutputStream get() throws IOException {
                return openFileOutput(fileName,MODE_PRIVATE);
            }
        });
        return result;
    }
}
