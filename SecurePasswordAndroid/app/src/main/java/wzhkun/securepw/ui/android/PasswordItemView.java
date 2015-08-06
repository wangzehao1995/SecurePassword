package wzhkun.securepw.ui.android;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import wzhkun.securepw.R;
import wzhkun.securepw.core.PasswordItem;
import wzhkun.securepw.ui.android.alert.WrongPasswordAlert;

/**
 * Created by wangzehao on 2015/8/3.
 */
public class PasswordItemView {
    private ViewGroup view;
    private PasswordItem item;
    private Context context;
    public PasswordItemView(final Context context,PasswordItem item){
        LayoutInflater iInflater = LayoutInflater.from(context);

        this.view = (ViewGroup) iInflater.inflate(R.layout.password_item, null);
        this.item=item;
        this.context=context;

        Button edit= (Button) view.getChildAt(0);
        final Button delete= (Button) view.getChildAt(1);
        EditText app= (EditText) view.getChildAt(3);
        EditText account= (EditText) view.getChildAt(5);
        EditText password= (EditText) view.getChildAt(7);

        setOnClickCopy(app);
        setOnClickCopy(account);
        setOnClickCopy(password);

        app.setText(item.getApp());
        account.setText(item.getAccount());
        password.setText(item.getPassword());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WrongPasswordAlert(context).show();
                edit();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
    }

    private void edit(){
    }

    private void delete(){

    }

    public View getView(){
        return view;
    }

    private void setOnClickCopy(final EditText et){
        et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("", et.getText()));
                Toast.makeText(context, R.string.copy_to_clipboard, Toast.LENGTH_SHORT).show();
            }
        });

        et.setKeyListener(null);
    }
}
