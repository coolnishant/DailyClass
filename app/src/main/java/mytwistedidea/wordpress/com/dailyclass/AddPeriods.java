package mytwistedidea.wordpress.com.dailyclass;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddPeriods extends AppCompatActivity implements View.OnClickListener {

    EditText etSubject, etFaculty;
    Button bAddStart, bAddEnd, bAddPeriod;

    DatabaseHelper helpers;
    int a = 0;

    static String subject=new String(""), faculty = new String(""), startTime = new String(""), endTime=new String("");
    static String tableFrom=new String("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_periods);

        initialize();
        Intent intent = getIntent();
        tableFrom = intent.getStringExtra("table");

        bAddStart.setOnClickListener(this);
        bAddEnd.setOnClickListener(this);
        bAddPeriod.setOnClickListener(this);

    }

    private void initialize() {
        etSubject = (EditText) findViewById(R.id.et_addSubjectAs);
        etFaculty = (EditText) findViewById(R.id.et_addAssignmentDeAs);

        bAddStart = (Button) findViewById(R.id.b_addStartTime);
        bAddEnd = (Button) findViewById(R.id.b_addEndTime);
        bAddPeriod = (Button) findViewById(R.id.b_addPeriod);

        helpers = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public void onClick(View v) {
        int id= v.getId();

        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        closeKeyboard();
        switch (id){
            case R.id.b_addStartTime:

                mTimePicker = new TimePickerDialog(AddPeriods.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        bAddStart.setText( selectedHour + ":" + selectedMinute);
                        startTime = selectedHour +":"+selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Start Time");
                mTimePicker.show();
                break;
            case R.id.b_addEndTime:

                mTimePicker = new TimePickerDialog(AddPeriods.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        bAddEnd.setText( selectedHour + ":" + selectedMinute);
                        endTime = selectedHour +":"+selectedMinute;
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("End Time");
                mTimePicker.show();
                break;
            case R.id.b_addPeriod:

                subject = etSubject.getText().toString();
                faculty = etFaculty.getText().toString();

                if(subject.equals(""))
                    Toast.makeText(this,"Enter Subject!",Toast.LENGTH_SHORT).show();
                else if(faculty.equals(""))
                    Toast.makeText(this,"Enter Faculty!",Toast.LENGTH_SHORT).show();
                else if(startTime.equals(""))
                    Toast.makeText(this,"Enter Start Time!",Toast.LENGTH_SHORT).show();
                else if(endTime.equals(""))
                    Toast.makeText(this,"Enter End Time!",Toast.LENGTH_SHORT).show();
                else{

                    Log.e("t","here above insertP AddP " +tableFrom+subject+startTime+endTime+faculty);
                    //TODO period number defination
                    helpers.insertPeriod(tableFrom.toString(), subject.toString(), startTime.toString(), endTime.toString(), faculty.toString());

                    Toast.makeText(this,"Added! "+subject+" by "+faculty,Toast.LENGTH_SHORT).show();
                    reset();
                }
                break;
        }
    }

    private void reset() {
        subject=""; faculty = ""; startTime=""; endTime="";
        etFaculty.setText("");
        etSubject.setText("");
        bAddEnd.setText("Start Time");
        bAddStart.setText("End Time");

        etSubject.requestFocus();
    }

    private void closeKeyboard() {
        //Soft Keyboard Hiding
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
