package wzhkun.securepw.ui.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import wzhkun.securepw.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void add(View view) {

    }

    public void sync(View view) {

    }

    public void setting(View view) {
        openOptionsMenu();
    }

    public void showSafeBox(MenuItem item) {

    }

    public void showChangePassword(MenuItem item) {

    }

    public void showSync(MenuItem item) {

    }

    public void showImport(MenuItem item) {

    }

    public void showExport(MenuItem item) {

    }

    public void lock(MenuItem item){
        System.exit(0);
    }
}
