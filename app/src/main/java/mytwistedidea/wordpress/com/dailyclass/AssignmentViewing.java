package mytwistedidea.wordpress.com.dailyclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssignmentViewing extends AppCompatActivity {

    DatabaseHelper MyHelpers;

    ArrayList<String> allAssignmentsAL;

    String subject[] = new String[50], assignments[] = new String[50], deadline[] = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_viewing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(AssignmentViewing.this,AddAssignments.class);
                startActivity(intent);

            }
        });
        populate();
    }

    private void populate() {
        MyHelpers = new DatabaseHelper(getApplicationContext());
        allAssignmentsAL = MyHelpers.getAllAssignment();


        int l =allAssignmentsAL.size();

        int j;
        int i;
        for(i=0,j=0;i< l;){

//            period[i] = periodsallAL.get(i++);
            subject[j] = allAssignmentsAL.get(i++);
            if(i<l)
                assignments[j] = allAssignmentsAL.get(i++);
            if(i<l)
                deadline[j++] = allAssignmentsAL.get(i++);
        }

        final List<HashMap<String,String>> aList = new ArrayList<>();

        for(i = 0;i<j;i++){

            HashMap<String,String> hashMap = new HashMap<>();
//            hashMap.put("pd","P- "+(Integer.toString(i)+1)+" ("+start[i]+"-"+end[i]+")");
            hashMap.put("st",subject[i]);
            hashMap.put("as",assignments[i]);
            hashMap.put("de",deadline[i]);
//            hashMap.put("tr","By "+teacher[i]);
            aList.add(hashMap);

        }

        String[] from = {"st","as","de"};
        int to[] = {R.id.tv_subjectV,R.id.tv_assignmentDetailsV,R.id.tv_deadlineV};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_assignment,from,to);
        final ListView listView = (ListView) findViewById(R.id.lv_assignments);
        listView.setAdapter(simpleAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }
}
