package com.example.nguyenvanthinh.funny;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;

import edu.cmu.pocketsphinx.Assets;
import edu.cmu.pocketsphinx.Hypothesis;
import edu.cmu.pocketsphinx.RecognitionListener;
import edu.cmu.pocketsphinx.SpeechRecognizer;

public class MainActivity extends AppCompatActivity implements RecognitionListener{

    private SpeechRecognizer speechRecognizer;
    private static final String KWS_SEARCH = "moojt";
    private static final String KWS_PHONE = "hai";
    private static final String KEYPHRASE = "ba";

    private TextView tvResult;
    private TextView tvError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvResult = (TextView) findViewById(R.id.result);
        tvError = (TextView) findViewById(R.id.error);

        tvError.setText("Click button to start");
        new AsyncTask<Void,Void,Exception>(){

            @Override
            protected Exception doInBackground(Void... params) {
                try {

                    Assets assets = new Assets(MainActivity.this);
                    File assetDir = assets.syncAssets();
                    setupRecognitzer(assetDir);
                } catch (IOException e){
                    e.printStackTrace();

                    return e;

                }
                publishProgress();
                return null;
            }

            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }

            @Override
            protected void onPostExecute(Exception result) {
                super.onPostExecute(result);
                if(result != null) {
                    tvError.setText("Failed to init recognizer " + result );
                } else {
                    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                    fab.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar.make(view, "Nguyễn Văn Thịnh", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            tvError.setText("Preparing to the recognizer...");
                            startListening();


                        }
                    });
                }
            }
        }.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startListening() {
        stopListening();
        speechRecognizer.startListening(KWS_SEARCH, 10000);


    }

    private void stopListening(){
        speechRecognizer.stop();

    }
    private void setupRecognitzer(File assetsDir) throws IOException {
        // configure recognizer

        speechRecognizer = defaultSetup().setAcousticModel(new File(assetsDir,"digit"))
                .setDictionary(new File(assetsDir, "digit.dict"))
                .setRawLogDir(assetsDir)
                .setKeywordThreshold(1e-45f)
                .setBoolean("-allphone_ci",true)
                .getRecognizer();

        speechRecognizer.addListener(this);

        //try to inogre creat keywoard-activation search
        // and add phonetic search
        //speechRecognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);

        File languageModel = new File(assetsDir,"digit.arpa");
        speechRecognizer.addNgramSearch(KWS_SEARCH, languageModel);
    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onEndOfSpeech() {
        stopListening();
        tvError.setText("Starting");
        startListening();
    }

    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if(hypothesis == null)
            return;
        String text =hypothesis.getHypstr();
        tvResult.setText(text);


    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        if(hypothesis != null){
            String text = hypothesis.getHypstr();
            tvResult.setText(text);
        }

    }

    @Override
    public void onError(Exception e) {

        tvError.setText(e.getMessage());
    }

    @Override
    public void onTimeout() {
        stopListening();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        speechRecognizer.cancel();
        speechRecognizer.shutdown();
    }
}
