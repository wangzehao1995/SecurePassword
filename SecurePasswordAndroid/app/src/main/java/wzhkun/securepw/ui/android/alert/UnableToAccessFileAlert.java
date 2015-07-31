package wzhkun.securepw.ui.android.alert;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by wangzehao on 2015/7/30.
 */
public class UnableToAccessFileAlert extends MyAlert{
    public UnableToAccessFileAlert(Context context){
        super(context);
    }
    @Override
    public void show() {
        getBuilder().setMessage("unable to access file.").setNeutralButton("Confirm",null).show();
    }
}
