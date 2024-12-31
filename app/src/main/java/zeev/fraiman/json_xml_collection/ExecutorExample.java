package zeev.fraiman.json_xml_collection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample extends AppCompatActivity {

    Context context;
    TextView tvExecutor;
    Button bExecutor;
    private ExecutorService executor;
    private String textFileContent="";

    String ulr="https://raw.githubusercontent.com/Phylator/data/master/constants.json";
    //"https://raw.githubusercontent.com/Phylator/data/master/categories.json"


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executor_example);

        initComponents();

        bExecutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startParse();
            }
        });
    }

    private void startParse() {
        executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(ulr);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                        stringBuilder.append("\n");
                    }
                    reader.close();
                    textFileContent = stringBuilder.toString();

                    // Здесь вы можете использовать содержимое текстового файла
                    tvExecutor.setText(textFileContent);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void buildList(String textFileContent) {
        ArrayList<String> info=new ArrayList<>();

        try {
            JSONArray jsonArray=new JSONArray(textFileContent);

            String t1,t2,t3,t4;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject arrayElement = jsonArray.getJSONObject(i);
                t1= arrayElement.getString("name") + "\n";
                //t2= arrayElement.getString("time_zone") + "\n";
                //t3=arrayElement.getJSONObject("coordinates").getString("lat");
                //t4=arrayElement.getJSONObject("coordinates").getString("lon");

                info.add(t1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "size="+info.size(), Toast.LENGTH_SHORT).show();
    }

    private void initComponents() {
        context=this;
        tvExecutor= findViewById(R.id.tvExecutor);
        bExecutor= findViewById(R.id.bExecutor);
    }

    public void viewSize(View view) {
        buildList(textFileContent);
    }
}