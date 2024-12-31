package zeev.fraiman.json_xml_collection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class EU_parlament_members extends AppCompatActivity {

    ListView lvMembers;
    ArrayList<String> infa;
    ArrayList<Member> members;
    ArrayAdapter<String> adapter;
    Button bMembers;
    ProgressBar pbMembers;
    Context context;
    String st1,st2,st3,st4,members_url;
    Thread go_xml;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eu_parlament_members);

        initComponents();

        bMembers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goParse();
                pbMembers.setVisibility(View.VISIBLE);
                startParse();

            }
        });


    }

    private void startParse() {
        go_xml=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(members_url);
                    XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                    parser.setInput(url.openStream(),null);
                    while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
                        if (parser.getEventType() == XmlPullParser.START_TAG) {
                            switch (parser.getName()) {
                                case "fullName":
                                    parser.next();
                                    st1= parser.getText();
                                    break;
                                case "country":
                                    parser.next();
                                    st2= parser.getText();
                                    break;
                                case "politicalGroup":
                                    parser.next();
                                    st3=parser.getText();
                                    break;
                                case "nationalPoliticalGroup":
                                    parser.next();
                                    st4=parser.getText();
                                    Member member=new Member(st1,st2,st3,st4);
                                    infa.add(member.toString());
                                    break;
                                default:
                                    break;
                            } //end of switch
                        }  //end of if
                        parser.next();
                    } //end of while
                    pbMembers.setVisibility(View.INVISIBLE);

                } catch (MalformedURLException e) {
                    String temp=e.toString();
                } catch (IOException e) {
                    String temp=e.toString();
                } catch (Exception e) {
                    String temp=e.toString();
                }
            }
        });
        go_xml.start();
        try {
            go_xml.join(); // wait to end of Thread
            adapter=new ArrayAdapter<String>(context,
                    android.R.layout.simple_list_item_1, infa);
            lvMembers.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initComponents() {
        context=this;
        bMembers=findViewById(R.id.bMembers);
        lvMembers=findViewById(R.id.lvMembers);
        pbMembers=findViewById(R.id.pbMembers);
        infa=new ArrayList<>();
        members=new ArrayList<>();
        st1="";st2="";st3="";st4="";
        members_url ="https://www.europarl.europa.eu/meps/en/full-list/xml";
    }
}