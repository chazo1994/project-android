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
    private TextView tvResultNumber;
    private TextView tvError;
    private String number[] = {"khoong","moojt","hai","ba","boosn","nawm","sasu","baary","tasm","chisn","muwowfi","muwowi",
            "moost","nghifn","trawm","linh","tuw"};
    private String numberCorrespond[] = {"không","một","hai","ba","bốn","năm","sáu","bẩy","tám","chín","mười","mươi",
            "mốt","nghìn","trăm","linh","tư"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvResult = (TextView) findViewById(R.id.result);
        tvResultNumber = (TextView) findViewById(R.id.tvResultNumber);
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
        tvResult.setText(converSequence(text));
        tvResultNumber.setText(convertSequenceToNumber(text));


    }

    @Override
    public void onResult(Hypothesis hypothesis) {
        if(hypothesis != null){
            String text = hypothesis.getHypstr();
            tvResult.setText(converSequence(text));
            tvResultNumber.setText(convertSequenceToNumber(text));

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



    //below code to convert sequence ascii to unicode and convert sequence ascii to number

    String detectNumberToString(String inputNumber) {
       // string line =""
       int i = 0, length = number.length;

        for(i=0;i<length;i++){
            if(number[i].equals(inputNumber)){
               return numberCorrespond[i];
            }
        }
        return null;
    }

    String converSequence(String stringDetect) {
        int i,j=0, length = stringDetect.length();
        String line = "";

        for(i = 0;i<length;i++) {
            if(Character.isWhitespace(stringDetect.charAt(i))){
                line = line + detectNumberToString(stringDetect.substring(j, i)) + " ";
                j = i+1;
            }
            if(i == (length-1)){
                line = line + detectNumberToString(stringDetect.substring(j, i+1));
            }
        }

        return line;
    }

    String detectNumberToNumber(String inputNumber) {

        for(int i = 0;i<number.length;i++){
            if(number[i].equals(inputNumber)) {
                if(i<10){
                    return String.valueOf(i);
                } else if(i == 13 || i == 14 ) {
                    return "";
                } else if(i == 11 || i == 15) {
                    return "0";
                } else if (i == 10 || i == 12) {
                    return "1";
                } else {
                    return "4";
                }
            }
        }

        return "";
    }

    String convertSequenceToNumber(String stringDetect) {
        int i,j=0, length = stringDetect.length();
        String line = "";

        for(i = 0;i<length;i++) {
            if(Character.isWhitespace(stringDetect.charAt(i))){
                line = line + detectNumberToNumber(stringDetect.substring(j,i));
                j = i+1;
            }
            if(i == (length-1)) {
                String temp = stringDetect.substring(j,i+1);
                if (temp.equals("muwowfi")) {
                    line = line + detectNumberToNumber(stringDetect.substring(j,i+1)) +"0";
                } else {
                    line = line +detectNumberToNumber(stringDetect.substring(j,i+1));
                }
            }
        }

        return line;
    }

}
