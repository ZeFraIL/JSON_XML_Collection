package zeev.fraiman.json_xml_collection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class JSON_in_fragment extends AppCompatActivity {

    Context context;
    FrameLayout FLForAirports;
    Button bAirports;
    JSON_Fragment jsonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_in_fragment);

        initComponents();

        bAirports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonFragment = new JSON_Fragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.FL_ForAirports, jsonFragment);
                ft.commit();
            }
        });
    }

    private void initComponents() {
        context=this;
        FLForAirports= findViewById(R.id.FL_ForAirports);
        bAirports= findViewById(R.id.bAirports);
    }
}