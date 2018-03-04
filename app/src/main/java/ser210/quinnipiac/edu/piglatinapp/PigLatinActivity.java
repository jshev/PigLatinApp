package ser210.quinnipiac.edu.piglatinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PigLatinActivity extends AppCompatActivity {

    public String pigs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig_latin);

        if (savedInstanceState != null) {
            pigs = savedInstanceState.getString("pigs");
        }

        pigs = (String) getIntent().getExtras().get("pigLatin");
        TextView view = (TextView) findViewById(R.id.pigText);
        view.setText(pigs);
    }

    public void onClickBack (View view) {
        Intent intent = new Intent(PigLatinActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("pigs", pigs);
    }
}
