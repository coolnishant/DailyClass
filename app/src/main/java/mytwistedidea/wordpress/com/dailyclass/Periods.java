package mytwistedidea.wordpress.com.dailyclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Periods extends AppCompatActivity {

    DatabaseHelper MyHelpers;

    ArrayList<String> periodsallAL;
    ArrayList<String> assignmentsweekdayallAL;

    String period[] = new String[50];
    String subject[] = new String[50];
    String start[] = new String[50];
    String end[] = new String[50];
    String teacher[] = new String[50];

//    String period[] = new String[50];
//    String weekdays[] = new String[50];
    String subjectAss[] = new String[50];
    String deadline[] = new String[50];
    String assignments[] = new String[50];

    String tableFrom = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_periods);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        tableFrom = intent.getStringExtra("table");

        populatePeriod();
        populateAssignment();

        Log.e("a",tableFrom);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(Periods.this, AddPeriods.class);
                intent.putExtra("table",tableFrom);
                startActivity(intent);

            }
        });
    }

    private void populateAssignment() {
        MyHelpers = new DatabaseHelper(getApplicationContext());
        assignmentsweekdayallAL = MyHelpers.getAllAssignmentWeekday(tableFrom);

        int k;
        int i=0;
        int l2 = assignmentsweekdayallAL.size();

        if(i<l2){
            return;
        }

        for (i=0,k=0;i<l2;){
            subjectAss[k] = assignmentsweekdayallAL.get(i++);
            if(i<l2){
                assignments[k] = assignmentsweekdayallAL.get(i++);
            }
            if(i<l2){
                deadline[k++] = assignmentsweekdayallAL.get(i++);
            }
        }
        final List<HashMap<String,String>> aList = new ArrayList<>();

        for(i = 0;i<k;i++){

            HashMap<String,String> hashMap = new HashMap<>();

            hashMap.put("st",subjectAss[i]);
            hashMap.put("as",assignments[i]);
            hashMap.put("de",deadline[i]);

            aList.add(hashMap);

        }

        String[] from = {"st","as","de"};

        //TODO set downthings
        int to[] = {R.id.tv_subjectV,R.id.tv_assignmentDetailsV,R.id.tv_deadlineV};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_assignment,from,to);
        final ListView listView = (ListView) findViewById(R.id.lv_assignments);
        listView.setAdapter(simpleAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        populatePeriod();
    }

    private void populatePeriod() {
        MyHelpers = new DatabaseHelper(getApplicationContext());
        periodsallAL = MyHelpers.getAllPeriod(tableFrom);

        int l =periodsallAL.size();
        int j;
        int i=0;

        if(i<l)
            return;

        for(i=0,j=0;i< l;j++){

//            period[i] = periodsallAL.get(i++);
            subject[j] = periodsallAL.get(i++);
            if(i<l)
                start[j] = periodsallAL.get(i++);
            if(i<l)
                end[j] = periodsallAL.get(i++);
            if(i<l)
                teacher[j] = periodsallAL.get(i++);

        }

        final List<HashMap<String,String>> aList = new ArrayList<>();

        for(i = 0;i<j;i++){

            HashMap<String,String> hashMap = new HashMap<>();
            hashMap.put("pd","P- "+(Integer.toString(i+1))+
                    " ("+start[i]+"-"+end[i]+")");
            hashMap.put("st",subject[i]);
            hashMap.put("sa",start[i]);
            hashMap.put("ed",end[i]);
            hashMap.put("tr","By "+teacher[i]);
            aList.add(hashMap);

        }

        String[] from = {"pd","st","tr"};
        int to[] = {R.id.tv_period,R.id.tv_subject,R.id.tv_teacher};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_period,from,to);
        final ListView listView = (ListView) findViewById(R.id.lv_periods);
        listView.setAdapter(simpleAdapter);

    }


}
