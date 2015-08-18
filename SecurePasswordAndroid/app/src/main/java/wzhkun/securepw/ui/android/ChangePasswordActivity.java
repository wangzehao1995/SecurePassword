package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;

public class ChangePasswordActivity extends Activity {
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        password= (EditText) findViewById(R.id.change_password_password);
    }

    public void changePassword(View view){
        try {
            BLServiceManager.getPasswordSafeBL().changePassword(password.getText().toString());
        } catch (IOException e) {
            new UnableToAccessFileAlert(this).show();
            e.printStackTrace();
        }

        cancel(null);
    }

    public void cancel(View view){
        this.finish();
    }
}
