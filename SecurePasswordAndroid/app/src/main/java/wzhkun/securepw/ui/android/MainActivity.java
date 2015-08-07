package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import wzhkun.securepw.R;
import wzhkun.securepw.core.PasswordItem;


public class MainActivity extends Activity {
    private FrameLayout frame;
    private LinearLayout passwordBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = (FrameLayout) findViewById(R.id.main_stack_view);

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
        frame.removeAllViews();
        frame.addView(passwordBox);
        loadPasswordItems();
    }

    private void loadPasswordItems() {
        passwordBox.removeAllViews();
        TextView doubleClickToCopyLabel = new TextView(this);
        doubleClickToCopyLabel.setText(R.string.double_click_to_copy);
        passwordBox.addView(doubleClickToCopyLabel);
        PasswordItem item = new PasswordItem("app", "account", "pw");
        passwordBox.addView(new PasswordItemView(this, item).getView());
        passwordBox.addView(new PasswordItemView(this, item).getView());
    }

    public void showChangePassword(MenuItem item) {

    }

    public void showSync(MenuItem item) {

    }

    public void showImport(MenuItem item) {

    }

    public void showExport(MenuItem item) {

    }

    public void lock(MenuItem item) {
        System.exit(0);
    }
}
