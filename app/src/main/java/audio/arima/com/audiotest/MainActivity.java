package audio.arima.com.audiotest;

import android.media.AudioManager;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {
    private String TAG = "audiotest";
    private static final String AUDIOTEST_A1 = "Spk_nF0_A1";
    private static final String AUDIOTEST_A2 = "Spk_nF0_A2";
    private static final String AUDIOTEST_RE = "Spk_nRe";
    AudioManager am;
    private Button button01;
    private TextView textView01;
    private String testvalue_re = null;
    private String testvalue_a1 = null;
    private String testvalue_a2 = null;
    private Float a0;
    private Float a1;
    private Float a2;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button01 = (Button) findViewById(R.id.Button01);
        textView01 = (TextView) findViewById(R.id.TextView01);
        am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        button01.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                getvalueparameter();
                float test0 = StringtoValue(testvalue_re);
                //float test1 = StringtoValue(testvalue_a1);
                //float test2 = StringtoValue(testvalue_a2);
                textView01.setText("Spk_nRe = "+test0+"\n"+testvalue_a1+"\n"+testvalue_a2);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void getvalueparameter() {
        if (am == null) {
            Log.e(TAG, "audiosys is null");
            testvalue_re = "Spk_nRe = 0.0";
            testvalue_a1 = "Spk_nF0_A1 = 0.0";
            testvalue_a2 = "Spk_nF0_A2 = 0.0";
        }
       // testvalue_re = am.getParameters(AUDIOTEST_RE);
       // testvalue_a1 = am.getParameters(AUDIOTEST_A1);
       // testvalue_a2 = am.getParameters(AUDIOTEST_A2);
    }

    private float StringtoValue(String value){
        String[] spilt;
        spilt = value.split("=");
        String tmp = spilt[1];
        float a0;
        a0 = Float.valueOf(tmp);
        a0 = a0/ (float)pow(2,27);
        return a0;
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
