package com.atooma.plugin.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.atooma.plugin.AtoomaParams;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout);

        final Spinner spinner = (Spinner) findViewById(R.id.testSpinner);
        final String[] greetings = new String[]{"Hi", "Hello", "Good morning", "Good night", "Bye Bye"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                greetings
        );
        spinner.setAdapter(adapter);

        Button b = (Button) findViewById(R.id.button1);
        b.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String string = greetings[spinner.getSelectedItemPosition()];
                Intent intent = new Intent();
                SharedPreferences sp = getSharedPreferences("Prefs", 0);
                sp.edit().putString("AutenticatedText", string).commit();
                intent.putExtra(AtoomaParams.ACTIVITY_RESULT_KEY, string);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
