package ecos.uniandes.davidmtz.patientapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.View;
import android.content.Intent;
import android.net.Uri;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.util.Log;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by codeaholics on 13/06/16.
 */
public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView txtTime;

    private TextView txtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        createListeners ();

        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("hh:mm a");
        String formattedTime = df.format(c.getTime());
        txtTime = (TextView) findViewById(R.id.time);
        if(txtTime != null){
            txtTime.setText(formattedTime);
        }

        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df1.format(c.getTime());
        txtDate = (TextView) findViewById(R.id.date);
        if(txtDate != null){
            txtDate.setText(formattedDate);
        }

    }

    public void createListeners (){
        TextView timeI = (TextView) this.findViewById(R.id.time);
        if(timeI != null){
            timeI.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    chageTime();
                }
            });
        }

        TextView dateI = (TextView) this.findViewById(R.id.date);
        if(dateI!= null){
            dateI.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    chageDate();
                }
            });
        }
    }

    @Override
    public void onTimeSet(TimePicker vista, int hour, int minute) {
        Date time = new Date();
        time.setHours(hour);
        time.setMinutes(minute);
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm a",
                Locale.getDefault());
        txtTime = (TextView) findViewById(R.id.time);
        txtTime.setText(formato.format(time));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(System.currentTimeMillis());
        calendario.set(Calendar.YEAR, year);
        calendario.set(Calendar.MONTH, month);
        calendario.set(Calendar.DAY_OF_MONTH, day);
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd",
                Locale.getDefault());
        txtDate = (TextView) findViewById(R.id.date);
        txtDate.setText(formato.format(new Date(calendario.getTimeInMillis())));
    }

    public void chageTime() {
        TimeDialog timeDialog = new TimeDialog();
        timeDialog.show(getSupportFragmentManager(), "timeSelector");
    }

    public void chageDate() {
        DateDialog dateDialog = new DateDialog();
        dateDialog.show(getSupportFragmentManager(), "dateSelector");
    }

    public void event_Click(View vw) {

        EditText medicamentET = (EditText) findViewById(R.id.medicament);
        String medicamentStr = "";
        if(medicamentET != null){
            medicamentStr = medicamentET.getText().toString();
        }

        RadioGroup activityRG = (RadioGroup) findViewById(R.id.activity);
        String activityStr = "";
        if(activityRG != null){
            RadioButton activityRB = ((RadioButton) findViewById(activityRG.getCheckedRadioButtonId()));
            if(activityRB != null){
                activityStr = activityRB.getText().toString();
            }
        }

        SeekBar seek = (SeekBar) findViewById(R.id.intensity);
        int seekValue = 0;
        if(seek != null){
            seekValue = seek.getProgress();
        }

        try {
            JSONObject report = new JSONObject();
            report.put("fecha", txtDate.getText());
            report.put("hora", txtTime.getText());
            report.put("medicament", medicamentStr);
            report.put("activity", activityStr);
            report.put("intensidad", seekValue);
            Log.d(TAG, report.toString());

            RestTask task = new RestTask(this, "episodeID", "http://10.0.2.2:4567/api/episode/create", report.toString());
            task.execute();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.exercise:
                if (checked)
                    // all of exercise
                    break;
            case R.id.repose:
                if (checked)
                    // all of repose
                    break;
            case R.id.work:
                if (checked)
                    // all of work
                    break;
        }
    }
}
