package wzhkun.securepw.ui.android.alert;

import android.app.AlertDialog;
import android.content.Context;

import wzhkun.securepw.R;

/**
 * Created by wangzehao on 2015/7/30.
 */
public class UnableToAccessFileAlert extends MyAlert{
    public UnableToAccessFileAlert(Context context){
        super(context);
    }
    @Override
    public void show() {
        getBuilder().setMessage(R.string.unable_to_access_file).setNeutralButton(R.string.confirm, null).show();
    }
}
