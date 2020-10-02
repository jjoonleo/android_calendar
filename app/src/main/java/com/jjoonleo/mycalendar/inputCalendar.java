package com.jjoonleo.mycalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class inputCalendar extends AppCompatActivity {

    private String time_start, time_end, date_start, date_end;

    private static String IP_ADDRESS = "192.168.0.7";
    private static String TAG = "phptest";

    private GregorianCalendar gregorianCalendar;
    private Button Date_start;
    private Button Date_end;
    private Button Time_start;
    private Button Time_end;
    private TextView mTextViewResult;
    private EditText editText_title;
    private DatePickerDialog.OnDateSetListener callbackMethod_date_start;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time_start;
    private DatePickerDialog.OnDateSetListener callbackMethod_date_end;
    private TimePickerDialog.OnTimeSetListener callbackMethod_time_end;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gregorianCalendar = new GregorianCalendar();
        date_start = String.format("%d-%d-%d",gregorianCalendar.get(Calendar.YEAR),gregorianCalendar.get(Calendar.MONTH)+1,gregorianCalendar.get(Calendar.DAY_OF_MONTH));
        date_end = String.format("%d-%d-%d",gregorianCalendar.get(Calendar.YEAR),gregorianCalendar.get(Calendar.MONTH)+1,gregorianCalendar.get(Calendar.DAY_OF_MONTH));
        time_start = String.format("%d:%d:00",gregorianCalendar.get(Calendar.HOUR_OF_DAY),gregorianCalendar.get(Calendar.MINUTE));
        time_end = String.format("%d:%d:00",gregorianCalendar.get(Calendar.HOUR_OF_DAY)+1,gregorianCalendar.get(Calendar.MINUTE));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_calendar);
        gregorianCalendar = new GregorianCalendar();
        Date_start = (Button) findViewById(R.id.Date_start);
        Time_start = (Button) findViewById(R.id.Time_start);
        Date_end = (Button) findViewById(R.id.Date_end);
        Time_end = (Button) findViewById(R.id.Time_end);
        editText_title = (EditText) findViewById(R.id.editText_title);
        Date_start.setText(gregorianCalendar.get(Calendar.YEAR)+"."+ (gregorianCalendar.get(Calendar.MONTH)+1)+"."+ gregorianCalendar.get(Calendar.DAY_OF_MONTH)+".");
        Date_end.setText(gregorianCalendar.get(Calendar.YEAR)+"."+ (gregorianCalendar.get(Calendar.MONTH)+1)+"."+ gregorianCalendar.get(Calendar.DAY_OF_MONTH)+".");
        Time_start.setText(gregorianCalendar.get(Calendar.HOUR_OF_DAY)+":"+ gregorianCalendar.get(Calendar.MINUTE));
        Time_end.setText((gregorianCalendar.get(Calendar.HOUR_OF_DAY)+1)+":"+ gregorianCalendar.get(Calendar.MINUTE));
        mTextViewResult = (TextView) findViewById(R.id.textView_main_result);

        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());


        Button buttonSave = (Button)findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuffer sb_start = new StringBuffer(date_start);
                sb_start.append( " " );
                sb_start.append( time_start );
                StringBuffer sb_end = new StringBuffer(date_end);
                sb_end.append( " " );
                sb_end.append( time_end );

                String name = editText_title.getText().toString();
                if(name.isEmpty()){
                    name = "No title";
                }
                String start = sb_start.toString();
                String end = sb_end.toString();

                //Date_start.setText(start);
                //Date_end.setText(end);
                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/my/insert.php",name, start, end);


            }
        });
        this.InitializeListener();
    }
    public void InitializeListener()
    {
        callbackMethod_date_start = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                date_start = String.format("%d-%d-%d ",year,monthOfYear+1,dayOfMonth);
                Date_start.setText(date_start);
            }
        };
        callbackMethod_time_start = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                time_start = String.format(" %d:%d:00",hourOfDay,minute);
                Time_start.setText(String.format("%d:%d",hourOfDay,minute));
            }
        };
        callbackMethod_date_end = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                date_end = String.format("%d-%d-%d ",year,monthOfYear+1,dayOfMonth);
                Date_end.setText(date_end);
            }
        };
        callbackMethod_time_end = new TimePickerDialog.OnTimeSetListener()
        {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
            {
                time_end = String.format(" %d:%d:00",hourOfDay,minute);
                Time_end.setText(String.format("%d:%d",hourOfDay,minute) );
            }
        };
    }

    public void OnClickDateStart(View view)
    {
        gregorianCalendar = new GregorianCalendar();
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod_date_start, gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();

    }

    public void OnClickTimeStart(View view)
    {
        gregorianCalendar = new GregorianCalendar();
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethod_time_start, gregorianCalendar.get(Calendar.HOUR_OF_DAY), gregorianCalendar.get(Calendar.MINUTE), true);

        dialog.show();
    }

    public void OnClickDateEnd(View view)
    {
        gregorianCalendar = new GregorianCalendar();
        DatePickerDialog dialog = new DatePickerDialog(this, callbackMethod_date_end, gregorianCalendar.get(Calendar.YEAR), gregorianCalendar.get(Calendar.MONTH), gregorianCalendar.get(Calendar.DAY_OF_MONTH));

        dialog.show();

    }

    public void OnClickTimeEnd(View view)
    {
        gregorianCalendar = new GregorianCalendar();
        TimePickerDialog dialog = new TimePickerDialog(this, callbackMethod_time_end, gregorianCalendar.get(Calendar.HOUR_OF_DAY)+1, gregorianCalendar.get(Calendar.MINUTE), true);

        dialog.show();
    }

    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(inputCalendar.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String name = (String)params[1];
            String start = (String)params[2];
            String end = (String)params[3];

            String serverURL = (String)params[0];
            String postParameters = "name=" + name + "&start=" + start + "&end=" + end;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }
}
