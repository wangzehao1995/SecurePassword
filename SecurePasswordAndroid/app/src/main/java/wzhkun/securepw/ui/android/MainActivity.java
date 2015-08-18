package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Set;

import wzhkun.securepw.R;
import wzhkun.securepw.bl.BLServiceManager;
import wzhkun.securepw.core.PasswordItem;


public class MainActivity extends Activity {
    public static MainActivity getMainActivity(){
        return instance;
    }
    private static MainActivity instance;

    private FrameLayout frame;
    private LinearLayout passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frame = (FrameLayout) findViewById(R.id.main_stack_view);
        instance=this;

        showSafeBox(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void add(View view) {
        Intent intent = new Intent();
        intent.setClass(this, EditActivity.class);
        this.startActivity(intent);
    }

    public void sync(View view) {

    }

    public void setting(View view) {
        openOptionsMenu();
    }

    public void showSafeBox(MenuItem item) {
        if (passwordBox == null) {
            passwordBox = PasswordBox.newBox(this);
        }
        showView(passwordBox);
        reloadPasswordItems();
    }

    public void reloadPasswordItems() {
        passwordBox.removeAllViews();
        addDoubleClickToCopyLabel();

        Set<PasswordItem> items=BLServiceManager.getPasswordSafeBL().getPasswordItems();
        for(PasswordItem item:items){
            passwordBox.addView(new PasswordItemView(this,item).getView());
        }
    }

    private void addDoubleClickToCopyLabel(){
        TextView doubleClickToCopyLabel = new TextView(this);
        doubleClickToCopyLabel.setText(R.string.double_click_to_copy);
        passwordBox.addView(doubleClickToCopyLabel);
    }

    public void showChangePassword(MenuItem item) {
        Intent intent = new Intent();
        intent.setClass(this, ChangePasswordActivity.class);
        this.startActivity(intent);
    }

    public void showSync(MenuItem item) {

    }

    public void showImport(MenuItem item) {
        Intent intent = new Intent();
        intent.setClass(this, ImexportActivity.class);
        intent.putExtra("type",ImexportActivity.IMPORT);
        this.startActivity(intent);
    }

    public void showExport(MenuItem item) {
        Intent intent = new Intent();
        intent.setClass(this, ImexportActivity.class);
        intent.putExtra("type", ImexportActivity.EXPORT);
        this.startActivity(intent);
    }

    public void lock(MenuItem item) {
        System.exit(0);
    }

    private void showView(View view){
        frame.removeAllViews();
        frame.addView(view);
    }
}
