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

public class AssignmentViewing extends AppCompatActivity {

    DatabaseHelper MyHelpers;

    SimpleAdapter simpleAdapter;

    TextView tvnoAssignment;

    ListView listView;

    ArrayList<String> allAssignmentsAL;

    String subject[] = new String[50], assignments[] = new String[50], deadline[] = new String[50], assingmentno[] = new String[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_viewing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intialize();

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
//        onlongpress();
    }

    private void onlongpress() {

        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select The Action");
        int i = v.getId();
        menu.add(0, v.getId(), 0, "Delete");//groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Edit");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle() == "Delete"){
            AdapterView.AdapterContextMenuInfo info=
                    (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();

            delete(info.position);
            return true;
        }
        else if(item.getTitle() == "Edit"){
            //TODO edit part

        }
        return false;
    }


    private void delete(final int rowId) {
//        if (rowId>0) {
            new AlertDialog.Builder(this)
                    .setTitle("Confirm?")
                    .setPositiveButton("Ok!",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                   MyHelpers.deleteAnAssignment(assingmentno[rowId]);
                                    final List<HashMap<String,String>> aList = new ArrayList<>();
                                    String[] from = {"st","as","de"};
                                    int to[] = {R.id.tv_subjectV,R.id.tv_assignmentDetailsV,R.id.tv_deadlineV};

                                    simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_assignment, from, to);
                                    simpleAdapter.notifyDataSetChanged();
                                    listView.setAdapter(simpleAdapter);
                                    onResume();
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

    private void intialize() {
        tvnoAssignment = (TextView) findViewById(R.id.tv_noassignmentAsV);
    }

    private void populate() {
        MyHelpers = new DatabaseHelper(getApplicationContext());
        allAssignmentsAL = MyHelpers.getAllAssignment();

        int l =allAssignmentsAL.size();

        int j;
        int i=0;
        Log.e("a",l+"");
        if(l == 1){
            tvnoAssignment.setVisibility(View.VISIBLE);
            return;
        }
        tvnoAssignment.setVisibility(View.INVISIBLE);
        for(i=0,j=0;i< l;i++){

//            period[i] = periodsallAL.get(i++);
            assingmentno[i] = allAssignmentsAL.get(i++);
            if(i<l)
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

        simpleAdapter = new SimpleAdapter(getApplicationContext(),aList,R.layout.custom_listview_assignment,from,to);
        listView = (ListView) findViewById(R.id.lv_assignments);
        listView.setAdapter(simpleAdapter);
        registerForContextMenu(listView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        populate();
    }
}
