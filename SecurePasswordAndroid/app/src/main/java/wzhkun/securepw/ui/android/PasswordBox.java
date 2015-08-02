package wzhkun.securepw.ui.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import wzhkun.securepw.R;

/**
 * Created by wangzehao on 2015/8/3.
 */
public class PasswordBox extends LinearLayout {
    public PasswordBox(Context context) {
        super(context);
        LayoutInflater iInflater = LayoutInflater.from(context);
        View v = iInflater.inflate(R.layout.password_box,null);
    }

}
