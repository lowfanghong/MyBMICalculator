package sg.edu.rp.c346.id_18021531.mybmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
EditText etWeight, etHeight;
Button btncal , btnreset;
TextView tvdate, tvbmi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etWeight = findViewById(R.id.editText);
        etHeight = findViewById(R.id.editText2);
        btncal = findViewById(R.id.button);
        btnreset = findViewById(R.id.button2);
        tvdate= findViewById(R.id.textViewdate);
        tvbmi = findViewById(R.id.textViewBMI);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvdate.setText("");
                tvbmi.setText("");
                SharedPreferences prefs = PreferenceManager.
                        getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                prefEdit.clear();
                prefEdit.commit();

            }
        });

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Weight = etWeight.getText().toString();
                String Height = etHeight.getText().toString();
               float W = Float.parseFloat(Weight);
               float H = Float.parseFloat(Height);
               float bmi=  W / (H*H);
                Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
                String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                        (now.get(Calendar.MONTH)+1) + "/" +
                        now.get(Calendar.YEAR) + " " +
                        now.get(Calendar.HOUR_OF_DAY) + ":" +
                        now.get(Calendar.MINUTE);
                 tvdate.setText("last calculated date is" + datetime);
                 tvbmi.setText("Last calculated BMI is" + bmi );
                 etWeight.setText("");
                 etHeight.setText("");



            }
        });



    }
    @Override
    protected void onPause() {
        super.onPause();
        float W = Float.parseFloat(etWeight.getText().toString());
        float H = Float.parseFloat(etHeight.getText().toString());
        float bmi=  W / (H*H);
        Calendar now = Calendar.getInstance();  //Create a Calendar object with current date and time
        String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH)+1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEdit = prefs.edit();
        prefEdit.putFloat("Weight", W);
        prefEdit.putFloat("Height", H);
        prefEdit.putFloat("bmi", bmi);
        prefEdit.putString("DT", datetime);



        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
         Float Weight = prefs.getFloat("Weight",0);
        Float Height = prefs.getFloat("Height",0);
        Float bmi = prefs.getFloat("bmi",0);
        String DateTime = prefs.getString("DT","");

        tvdate.setText("last calculated date is" + DateTime);
        tvbmi.setText("Last calculated BMI is " + bmi);





    }



}
