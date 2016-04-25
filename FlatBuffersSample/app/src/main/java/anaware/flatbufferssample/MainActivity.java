package anaware.flatbufferssample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dslplatform.json.DslJson;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import anaware.flatbufferssample.flatbuffersgen.People;
import anaware.flatbufferssample.flatbuffersgen.PeopleList;
import anaware.flatbufferssample.json.PeopleJson;
import anaware.flatbufferssample.json.PeopleListJson;

public class MainActivity extends AppCompatActivity {

    // UI Elements
    private View form;
    private ProgressBar progressBar;
    private TextView jsonStatus;
    private TextView flatbufferStatus;
    private Button jsonButton;
    private Button flatbufferButton;
    private DslJson<Object> dslJson = new DslJson<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Retrieve UI components
        form = (View)findViewById(R.id.form);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        jsonStatus = (TextView)findViewById(R.id.jsonStatus);
        flatbufferStatus = (TextView)findViewById(R.id.flatbufferStatus);
        jsonButton = (Button)findViewById(R.id.jsonButton);
        jsonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson();
            }
        });
        flatbufferButton = (Button)findViewById(R.id.flatbufferButton);
        flatbufferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseFlatBuffer();
            }
        });
    }

    private void parseFlatBuffer() {
        progressBar.setVisibility(View.VISIBLE);
        form.setVisibility(View.GONE);
        new FlatParsing().execute(readRawResource(R.raw.sample_flatbuffers));
    }

    private void parseJson() {
        progressBar.setVisibility(View.VISIBLE);
        form.setVisibility(View.GONE);
        new JsonParsing().execute(readRawResource(R.raw.sample_json));
    }

    protected byte[] readRawResource(int resId) {
        InputStream stream = null;
        byte[] buffer = null;
        try {
            stream = getResources().openRawResource(resId);
            buffer = new byte[stream.available()];
            while (stream.read(buffer) != -1);
        } catch (IOException e) {
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                }
            }
        }
        return buffer;
    }

    private class JsonParsing extends AsyncTask<Object, Void, String> {
        @Override
        protected String doInBackground(Object... params) {
            byte[] jsonInput = (byte[])params[0];
            long startTime = System.currentTimeMillis();
            try {
                PeopleListJson peopleList = dslJson.deserialize(PeopleListJson.class, jsonInput, jsonInput.length);
                return peopleList.howLong(startTime);
            }catch (IOException io) {
                return io.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            jsonStatus.setText(result);
            progressBar.setVisibility(View.GONE);
            form.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

    private class FlatParsing extends AsyncTask<Object, Void, String> {
        @Override
        protected String doInBackground(Object... params) {
            byte[] buffer = (byte[]) params[0];
            long startTime = System.currentTimeMillis();
            ByteBuffer bb = ByteBuffer.wrap(buffer);
            PeopleList peopleList = PeopleList.getRootAsPeopleList(bb);
            PeopleListJson people = new PeopleListJson(peopleList);
            return people.howLong(startTime);
        }

        @Override
        protected void onPostExecute(String result) {
            flatbufferStatus.setText(result);
            progressBar.setVisibility(View.GONE);
            form.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}
