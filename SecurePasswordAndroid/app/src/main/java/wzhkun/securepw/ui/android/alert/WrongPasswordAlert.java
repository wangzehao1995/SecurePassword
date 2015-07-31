package wzhkun.securepw.ui.android.alert;

import android.content.Context;

/**
 * Created by wangzehao on 2015/7/30.
 */
public class WrongPasswordAlert extends MyAlert{
    public WrongPasswordAlert(Context context) {
        super(context);
    }

    @Override
    public void show() {
        getBuilder().setMessage("Wrong Password").setNeutralButton("Confirm",null).show();
    }
}
