package ecos.uniandes.davidmtz.patientapplication;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;

import java.util.Calendar;

/**
 * Created by codeaholics on 13/06/16.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class TimeDialog extends DialogFragment {

    private TimePickerDialog.OnTimeSetListener timeListener;


    public void setOnTimeSetListener(TimePickerDialog.OnTimeSetListener timeListener){
        this.timeListener = timeListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Iniciar con el tiempo actual
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Retornar en nueva instancia del dialogo selector de tiempo
        return new TimePickerDialog(
                getActivity(),
                (TimePickerDialog.OnTimeSetListener) getActivity(),
                hour,
                minute,
                DateFormat.is24HourFormat(getActivity()));
    }

}

