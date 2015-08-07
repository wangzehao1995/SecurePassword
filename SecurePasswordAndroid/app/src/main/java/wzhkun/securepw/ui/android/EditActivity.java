package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.core.PasswordGenerator;
import wzhkun.securepw.core.PasswordItem;
import wzhkun.securepw.ui.android.alert.UnableToAccessFileAlert;

public class EditActivity extends Activity {
    private EditText app;
    private EditText account;
    private EditText oldPassword;
    private EditText password;
    private EditText length;
    private CheckBox upperCase;
    private CheckBox number;
    private CheckBox symbol;
    private CheckBox firstCharLetter;
    private CheckBox atLeastOneNumber;
    private CheckBox atLeastOneSymbol;

    private PasswordItem old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        app = (EditText) findViewById(R.id.edit_app);
        account = (EditText) findViewById(R.id.edit_account);
        oldPassword = (EditText) findViewById(R.id.edit_old_password);
        password = (EditText) findViewById(R.id.edit_password);
        length = (EditText) findViewById(R.id.edit_length);
        upperCase = (CheckBox) findViewById(R.id.edit_upper_case);
        number = (CheckBox) findViewById(R.id.edit_number);
        symbol = (CheckBox) findViewById(R.id.edit_symbol);
        firstCharLetter = (CheckBox) findViewById(R.id.edit_first_char_letter);
        atLeastOneNumber = (CheckBox) findViewById(R.id.edit_at_least_one_number);
        atLeastOneSymbol = (CheckBox) findViewById(R.id.edit_at_least_one_symbol);
    }

    public void setPasswordItem(PasswordItem item) {
        this.old = item;
        this.app.setText(item.getApp());
        this.account.setText(item.getAccount());
        this.oldPassword.setText(item.getPassword());
    }

    public void generate(View view) {
        try {
            int length = Integer.parseInt(this.length.getText().toString());

            PasswordGenerator pg = new PasswordGenerator();
            pg.set长度(length);
            pg.set大小写混合(upperCase.isSelected());
            pg.set字母数字混合(number.isSelected());
            pg.set字母符号混合(symbol.isSelected());
            pg.set第一位为字母(firstCharLetter.isSelected());
            pg.set至少一位为数字(atLeastOneNumber.isSelected());
            pg.set至少一位为特殊符号(atLeastOneSymbol.isSelected());

            password.setText(pg.generate());
        } catch (NumberFormatException e) {
            Toast.makeText(this, R.string.wrong_length, Toast.LENGTH_SHORT).show();
        }
    }

    public void confirm(View view) {
        try {
            PasswordItem new_ = new PasswordItem(app.getText().toString(), account.getText().toString(), password.getText().toString());
            BLServiceManager.getPasswordSafeBL().updatePasswordItem(old, new_);
            cancel(null);
        } catch (IOException e) {
            new UnableToAccessFileAlert(this).show();
        }
    }

    public void cancel(View view) {
        this.finish();
    }
}
