package ser210.quinnipiac.edu.piglatinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PigLatinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pig_latin);

        String pigs = (String) getIntent().getExtras().get("pigLatin");
        TextView view = (TextView) findViewById(R.id.pigText);
        view.setText(pigs);
    }
}
