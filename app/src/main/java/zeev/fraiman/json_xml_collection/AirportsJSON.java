package zeev.fraiman.json_xml_collection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AirportsJSON extends AppCompatActivity {

    Context context;
    ListView lv;
    Airport airport;
    ArrayList<Airport> airports;
    ArrayList<String> info;
    ArrayAdapter<String> adapter;
    Button bGo;
    ProgressBar pbAirport;
    String info_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airports_json);

        initComponents();

        bGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new fromNet().execute(info_url);
            }
        });
    }

    private void initComponents() {
        context=this;
        lv= findViewById(R.id.lv);
        bGo=findViewById(R.id.bGo);
        pbAirport=findViewById(R.id.pbAirport);
        info_url="https://api.travelpayouts.com/data/airports.json";
    }

    class fromNet extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pbAirport.setVisibility(View.VISIBLE);
        }

        String all="", temp;
        int n=0;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream is;
                int status=connection.getResponseCode();
                if (status==HttpURLConnection.HTTP_OK)  {
                    is=connection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);
                    BufferedReader br=new BufferedReader(isr);
                    while ((temp=br.readLine())!=null) {
                        n++;
                        all += temp + "\n";
                    }
                    br.close();
                }

            } catch (MalformedURLException e) {
                all=e.toString();
            } catch (IOException e) {
                all=e.toString();
            } catch (Exception e) {
                all=e.toString();
            }
            return all;
        }  //end of DoInBackground

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pbAirport.setVisibility(View.GONE);
            info=new ArrayList<>();
            airports=new ArrayList<>();
            try {
                JSONArray jsonArray=new JSONArray(all);
                String t1,t2,t3,t4;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject arrayElement = jsonArray.getJSONObject(i);
                    t1= arrayElement.getString("name") + "\n";
                    t2= arrayElement.getString("time_zone") + "\n";
                    t3=arrayElement.getJSONObject("coordinates").getString("lat");
                    t4=arrayElement.getJSONObject("coordinates").getString("lon");
                    airport=new Airport(t1,t2,t3,t4);
                    airports.add(airport);
                    info.add(t1+","+t2);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            adapter=new ArrayAdapter<String>(context,
                    android.R.layout.simple_expandable_list_item_1,
                    info);
            lv.setAdapter(adapter);
        }
    }  //end Async class

}