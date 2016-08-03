package com.example.pernalonga.ihm_tests;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.actionbar, menu);

        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_edit1:

            /* DO EDIT */

                return true;

            case R.id.action_edit2:

            /* DO EDIT */

                return true;

            case R.id.action_edit3:

            /* DO EDIT */

                return true;

            case R.id.action_edit4:

            /* DO EDIT */

                return true;
        }

        return super.onOptionsItemSelected(item);

    }
}
