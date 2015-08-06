package wzhkun.securepw.ui.android.alert;

import android.content.Context;

import wzhkun.securepw.R;

/**
 * Created by wangzehao on 2015/7/30.
 */
public class WrongSafeFileAlert extends MyAlert{
    public WrongSafeFileAlert(Context context) {
        super(context);
    }

    @Override
    public void show() {
        getBuilder().setMessage(R.string.wrong_safe_file).setNeutralButton(R.string.confirm,null).show();
    }
}
