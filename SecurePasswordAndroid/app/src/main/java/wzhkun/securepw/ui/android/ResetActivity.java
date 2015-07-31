package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.bl.PasswordSafeBL;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;


public class ResetActivity extends Activity {
    PasswordSafeBL bl;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        password= (EditText) findViewById(R.id.reset_password);
        bl= BLServiceManager.getPasswordSafeBL();
    }

    public void reset(View view){
        try {
            bl.reset(String.valueOf(password.getText()));
            clear();
            cancel(null);
        } catch (IOException e) {
            new UnableToAccessFileAlert(this).show();
            e.printStackTrace();
        }
    }

    public void cancel(View view){
        this.finish();
    }

    private void clear(){
        password.setText("");
    }
}
