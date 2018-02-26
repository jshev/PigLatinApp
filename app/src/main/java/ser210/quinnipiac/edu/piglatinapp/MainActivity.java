package ser210.quinnipiac.edu.piglatinapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();
    final static String url1 = "https://piglatin.p.mashape.com/piglatin.json?text=";
    final static String url2 = "&mashape-key=Ua35jWwKEMmsh16tGNbyeJ9zdat3p1mbW60jsn8Q7tn8rJw8po";
    final LatinHandler latinhandler = new LatinHandler();
    public String english;
    public String sentence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick (View view) {
        EditText text = (EditText) findViewById(R.id.englishText);
        english = text.getText().toString();

        String englishPlus = english.replaceAll("\\s+","+");
        new FetchPigLatin().execute(englishPlus);
    }

    private class FetchPigLatin extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String latin = null;
            try {
                URL url = new URL( url1 + sentence + url2);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream in = urlConnection.getInputStream();
                if (in == null) {
                    return null;
                }

                reader = new BufferedReader(new InputStreamReader(in));
                String latinJsonString = getJsonStringFromBuffer(reader);

                latin = latinhandler.getTranslation(latinJsonString);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            } finally {

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch(IOException e) {
                        return null;
                    }
                }
            }
            return latin;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                Log.d(LOG_TAG, result);
            }
            Intent intent = new Intent(MainActivity.this, PigLatinActivity.class);
            intent.putExtra("pigLatin", result);
            startActivity(intent);
        }
    }

    private String getJsonStringFromBuffer(BufferedReader br) throws Exception {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = br.readLine()) != null) {
            buffer.append(line + '\n');
        }
        if (buffer.length() == 0) {
            return null;
        } else {
            return buffer.toString();
        }
    }
}
