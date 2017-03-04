package mytwistedidea.wordpress.com.dailyclass;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddAssignments extends AppCompatActivity implements View.OnClickListener {

    //TODO add subject from Routine Entry

    EditText etSubject, etAssignment;
    Button bDeadline,bAddAssignment;

    String deadline,subject,assignment;
    String dayWeek;
    int dayOfWeek;
    String weekDay;

    DatabaseHelper helpers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignments);
        initialize();
        reset();

        bAddAssignment.setOnClickListener(this);
        bDeadline.setOnClickListener(this);
    }

    private void initialize() {
        etSubject = (EditText) findViewById(R.id.et_addSubjectAs);
        etAssignment = (EditText) findViewById(R.id.et_addAssignmentDeAs);

        bDeadline = (Button) findViewById(R.id.b_addDeadlineAs);
        bAddAssignment= (Button) findViewById(R.id.b_addAssignmentAs);

        helpers = new DatabaseHelper(getApplicationContext());

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.b_addDeadlineAs:
                closeKeyboard();

                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(AddAssignments.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                        selectedmonth = selectedmonth + 1;

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(selectedday,selectedmonth,selectedyear);
                        SimpleDateFormat simpledateformat = new SimpleDateFormat("EEEE");
                        Date date = new Date(selectedyear, selectedmonth, selectedday+3);
                        dayWeek = simpledateformat.format(date);
                        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                        deadline = ("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                        bDeadline.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                        mcurrentDate.set(selectedyear,selectedmonth,selectedday);
                    }
                }, mYear, mMonth, mDay);

//                dayOfWeek = mcurrentDate.get(Calendar.DAY_OF_WEEK);


                if (Calendar.MONDAY == dayOfWeek) {
                    weekDay = "monday";
                } else if (Calendar.TUESDAY == dayOfWeek) {
                    weekDay = "tuesday";
                } else if (Calendar.WEDNESDAY == dayOfWeek) {
                    weekDay = "wednesday";
                } else if (Calendar.THURSDAY == dayOfWeek) {
                    weekDay = "thursday";
                } else if (Calendar.FRIDAY == dayOfWeek) {
                    weekDay = "friday";
                } else if (Calendar.SATURDAY == dayOfWeek) {
                    weekDay = "saturday";
                } else if (Calendar.SUNDAY == dayOfWeek) {
                    weekDay = "sunday";
                }
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

                break;
            case R.id.b_addAssignmentAs:
                Toast.makeText(this,"week: "+weekDay +" DAY: "+dayOfWeek+" new: "+dayWeek.toLowerCase(),Toast.LENGTH_SHORT).show();
                subject = etSubject.getText().toString();
                assignment = etAssignment.getText().toString();

                if(subject.equals("")){
                    Toast.makeText(this,"Enter Subject!",Toast.LENGTH_SHORT).show();
                }
                else if(assignment.equals("")){
                    Toast.makeText(this,"Enter Assignment Details!",Toast.LENGTH_SHORT).show();
                }
                else if(deadline.equals("")){
                    Toast.makeText(this,"Enter Deadline Details!",Toast.LENGTH_SHORT).show();
                }
                else{
                    closeKeyboard();
                    helpers.insertAssignment(subject,assignment,deadline,dayWeek.toLowerCase());
                    reset();
                }
                break;
        }
    }

    private void reset() {
        etAssignment.setText("");
        etSubject.setText("");

        bDeadline.setText("Deadline");
        subject="";
        assignment="";
        deadline="";
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
