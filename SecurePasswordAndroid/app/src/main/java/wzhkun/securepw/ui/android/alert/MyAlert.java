package wzhkun.securepw.ui.android.alert;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by wangzehao on 2015/7/30.
 */
public abstract class MyAlert {
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    protected AlertDialog.Builder getBuilder() {
        return new AlertDialog.Builder(context);
    }

    public abstract void show();

}
