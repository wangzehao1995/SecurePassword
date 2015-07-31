package wzhkun.securepw.ui.android.alert;

import android.content.Context;

/**
 * Created by wangzehao on 2015/7/30.
 */
public class WrongSafeFileAlert extends MyAlert{
    public WrongSafeFileAlert(Context context) {
        super(context);
    }

    @Override
    public void show() {
        getBuilder().setMessage("There's something wrong with storage file.\nTry \"reset\".");
    }
}
