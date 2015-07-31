package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.IOException;

import javax.crypto.BadPaddingException;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.LoginBL;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;
import wzhkun.securepw.ui.android.alert.WrongPasswordAlert;
import wzhkun.securepw.ui.android.alert.WrongSafeFileAlert;

public class LoginActivity extends Activity {
    File localSafe;
    LoginBL bl;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        bl= BLServiceManager.getLoginBL();
        password= (EditText) findViewById(R.id.login_password);
    }

    public void login(View view){
        try {
            bl.login(localSafe,String.valueOf(password.getText()));
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
}
