package zeev.fraiman.json_xml_collection;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class JSON_Fragment extends Fragment {

    ListView lvAirports;
    ArrayList<Airport> airports;
    ArrayList<String> info;
    ArrayAdapter<String> adapter;
    String info_url;
    String all="", temp;
    Airport airport;
    TextView tvAirports;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_j_s_o_n_, container, false);
        tvAirports = view.findViewById(R.id.tvAirports);
        lvAirports = view.findViewById(R.id.lvAirports);
        airports = new ArrayList<>();
        info = new ArrayList<>();
        //info_url = "https://api.travelpayouts.com/data/airports.json";
        //info_url = "http://data.gov.il/dataset/synagogues-br7/synagogues.json";
        info_url = "https://raw.githubusercontent.com/Phylator/data/master/categories.json";
        new fromNet().execute(info_url);
        return view;
    }

    class fromNet extends AsyncTask<String, Void, String> {

        String all="", temp;
        int n=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

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
            tvAirports.setText(result);
            info=new ArrayList<>();
            airports=new ArrayList<>();
            try {
                JSONArray jsonArray=new JSONArray(all);
                String t1,t2,t3,t4;
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject arrayElement = jsonArray.getJSONObject(i);
                    t1= arrayElement.getString("name") + "\n";
                    /*
                    t2= arrayElement.getString("time_zone") + "\n";
                    t3=arrayElement.getJSONObject("coordinates").getString("lat");
                    t4=arrayElement.getJSONObject("coordinates").getString("lon");
                    airport=new Airport(t1,t2,t3,t4);
                    airports.add(airport);
                    */
                    info.add(t1);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Toast.makeText(getActivity(), "size="+info.size(), Toast.LENGTH_SHORT).show();
            adapter=new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_expandable_list_item_1,
                    info);
            lvAirports.setAdapter(adapter);

        }
    }  //end Async class

}