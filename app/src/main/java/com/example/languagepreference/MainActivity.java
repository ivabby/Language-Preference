package com.example.languagepreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String TAG = "MainActivity";

    public static final String KEY = "key";

    TextView textView;


    /**
     * Creating the options menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.english:
                updateLanguage("English");
                return true;
            case R.id.spanish:
                updateLanguage("Spanish");
                return true;

            default:

                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        sharedPreferences = getSharedPreferences("com.example.languagepreference" , Context.MODE_PRIVATE);

        // Check if the app is launched for the first time
        //  If yes asked for the language
        //  If no set the last set language
        String key = "";
        try{
            key = sharedPreferences.getString(KEY , "");
        } catch (Exception e){
            Log.e(TAG, "onCreate: error " + e.getMessage());
        }

        if(key.equals("")){
            alertDialog();
        }
        else{
            updateLanguage(key);
        }
    }

    private void alertDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Choose a language")
                .setMessage("Which language would you like?")
                .setPositiveButton("English", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: clicked on English.");

                        updateLanguage("English");
                    }
                })
                .setNegativeButton("Spanish", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "onClick: clicked on Spanish.");

                        updateLanguage("Spanish");
                    }
                })
                .show();
    }

    private void updateLanguage(String language){
        sharedPreferences.edit().putString(KEY , language).apply();

        textView.setText(language);
    }
}
