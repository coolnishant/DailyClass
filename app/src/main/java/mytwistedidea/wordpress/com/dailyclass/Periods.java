package mytwistedidea.wordpress.com.dailyclass;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Periods extends AppCompatActivity {

    DatabaseHelper MyHelpers;
    SimpleAdapter simpleAdapter;

    TextView tvRoutineday,tvAssignmentday;

    SimpleAdapter simpleAdapterPeriod, simpleAdapterAssignment;
    ListView listView;

    TextView tvnoRoutine, tvnoAssignment;

    ArrayList<String> periodsallAL;
    ArrayList<String> assignmentsweekdayallAL;

    String period[] = new String[50];
    String subject[] = new String[50];
    String start[] = new String[50];
    String end[] = new String[50];
    String teacher[] = new String[50];
    String assingmentno[] = new String[50];
//    String periodno[] = new String[50];

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


        initialize();

        tvRoutineday.setText("Routine of "+tableFrom.toUpperCase());
        tvAssignmentday.setText("Assignment for "+tableFrom.toUpperCase());
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

    private void initialize() {
        tvnoRoutine = (TextView) findViewById(R.id.tv_noroutine);
        tvnoAssignment = (TextView) findViewById(R.id.tv_noassignment);
        tvRoutineday = (TextView) findViewById(R.id.tv_routineday);
        tvAssignmentday = (TextView) findViewById(R.id.tv_assignmentday);
    }

    private void populateAssignment() {
        MyHelpers = new DatabaseHelper(getApplicationContext());
        assignmentsweekdayallAL = MyHelpers.getAllAssignmentWeekday(tableFrom);

        int k;
        int i=0;
        int l2 = assignmentsweekdayallAL.size();

        if(l2 == 1){
            tvnoAssignment.setVisibility(View.VISIBLE);
            return;
        }

        for (i=0,k=0;i<l2;){
            assingmentno[k] = assignmentsweekdayallAL.get(i++);
            if(i<l2)
                subjectAss[k] = assignmentsweekdayallAL.get(i++);
            if(i<l2){
                assignments[k] = assignmentsweekdayallAL.get(i++);
            }
            if(i<l2){
                deadline[k++] = assignmentsweekdayallAL.get(i++);
            }
        }
        Log.e("aaa",""+l2);
            tvnoAssignment.setVisibility(View.INVISIBLE);
        final List<HashMap<String,String>> aList = new ArrayList<>();

        for(i = 0;i<k;i++){

            HashMap<String,String> hashMap = new HashMap<>();

            hashMap.put("st","In: "+subjectAss[i]);
            hashMap.put("as",assignments[i]);
            hashMap.put("de",deadline[i]);

            aList.add(hashMap);

        }

        String[] from = {"st","as","de"};

        int to[] = {R.id.tv_subjectV,R.id.tv_assignmentDetailsV,R.id.tv_deadlineV};

        simpleAdapterAssignment = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_assignment,from,to);
        final ListView listView = (ListView) findViewById(R.id.lv_assignments);
        listView.setAdapter(simpleAdapterAssignment);

        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populatePeriod();
        populateAssignment();
    }

    private void populatePeriod() {
        MyHelpers = new DatabaseHelper(getApplicationContext());
        periodsallAL = MyHelpers.getAllPeriod(tableFrom.toString());

        int l =periodsallAL.size();
        int j;
        int i=0;
        Log.e("aa",l+"");
        if(l == 1) {
            tvnoRoutine.setVisibility(View.VISIBLE);
            return;
        }
            tvnoRoutine.setVisibility(View.INVISIBLE);
        for(i=0,j=0;i< l;){

            period[i] = periodsallAL.get(i++);
            if(i<l)
                subject[j] = periodsallAL.get(i++);
            if(i<l)
                start[j] = periodsallAL.get(i++);
            if(i<l)
                end[j] = periodsallAL.get(i++);
            if(i<l)
                teacher[j++] = periodsallAL.get(i++);

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

        simpleAdapterPeriod = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_period,from,to);
        final ListView listView = (ListView) findViewById(R.id.lv_periods);
        listView.setAdapter(simpleAdapterPeriod);

        registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");
        int id = v.getId();

        if(id == R.id.lv_assignments) {
            menu.add(0, v.getId(), 0, "Delete Assignment");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "Edit Assignment");
        }
        else if(id == R.id.lv_periods) {
            menu.add(0, v.getId(), 0, "Delete Class");//groupId, itemId, order, title
            menu.add(0, v.getId(), 0, "Edit Class");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle() == "Delete Class"){
            AdapterView.AdapterContextMenuInfo info=
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            deleteClass(info.position);
            return true;
        }
        else if(item.getTitle() == "Delete Assignment"){
            AdapterView.AdapterContextMenuInfo info=
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            deleteAssignment(info.position);
            return true;
        }
        else if(item.getTitle() == "Edit Assignment"){
            //TODO edit part

        }
        return false;
    }

    private void deleteClass(final int position) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Class Delete?")
                .setPositiveButton("Ok!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                MyHelpers.deleteAPeriod(tableFrom,period[position]);
//                                final List<HashMap<String,String>> aList = new ArrayList<>();
//                                String[] from = {"st","as","de"};
//                                int to[] = {R.id.tv_subjectV,R.id.tv_assignmentDetailsV,R.id.tv_deadlineV};
//
//                                simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_assignment, from, to);
//                                simpleAdapter.notifyDataSetChanged();
//                                listView.setAdapter(simpleAdapter);
//                                onResume();

                                Intent intent = new Intent(Periods.this,Periods.class);
                                intent.putExtra("table",tableFrom);
                                finish();
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancel!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // ignore, just dismiss
                            }
                        })
                .show();
    }


    private void deleteAssignment(final int rowId) {
//        if (rowId>0) {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Assignment Delete?")
                .setPositiveButton("Ok!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                MyHelpers.deleteAnAssignment(assingmentno[rowId]);
                                final List<HashMap<String,String>> aList = new ArrayList<>();
                                String[] from = {"st","as","de"};
                                int to[] = {R.id.tv_subjectV,R.id.tv_assignmentDetailsV,R.id.tv_deadlineV};

                                Intent intent = new Intent(Periods.this,Periods.class);
                                intent.putExtra("table",tableFrom);
                                finish();
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("Cancel!",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // ignore, just dismiss
                            }
                        })
                .show();

//        }
    }

}
