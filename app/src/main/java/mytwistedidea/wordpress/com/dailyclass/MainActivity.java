package mytwistedidea.wordpress.com.dailyclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    View v[] = new View[10];
    TextView tv[] = new TextView[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initialize();

        onClickDoing();

    }

    private void onClickDoing() {
        v[1].setOnClickListener(this);
        v[2].setOnClickListener(this);
        v[3].setOnClickListener(this);
        v[4].setOnClickListener(this);
        v[5].setOnClickListener(this);
        v[6].setOnClickListener(this);
        v[7].setOnClickListener(this);
        v[8].setOnClickListener(this);
    }

    private void initialize() {

        v[1] = (View) findViewById(R.id.view_monday);
        v[2] = (View) findViewById(R.id.view_tuesday);
        v[3] = (View) findViewById(R.id.view_wednesday);
        v[4] = (View) findViewById(R.id.view_thrusday);
        v[5] = (View) findViewById(R.id.view_friday);
        v[6] = (View) findViewById(R.id.view_saturday);
        v[7] = (View) findViewById(R.id.view_sunday);
        v[8] = (View) findViewById(R.id.view_assignment);

        for(int i = 1;i<=8;i++){
            tv[i] = (TextView) v[i].findViewById(R.id.tv_day);
        }

        tv[1].setText("Monday");
        tv[2].setText("Tuesday");
        tv[3].setText("Wednesday");
        tv[4].setText("Thrusday");
        tv[5].setText("Friday");
        tv[6].setText("Saturday");
        tv[7].setText("Sunday");
        tv[8].setText("Assignment");

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_AddAssignment) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, AddAssignments.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.view_assignment){
            //TODO Assignment class
            Intent intent = new Intent(MainActivity.this, AssignmentViewing.class);
            startActivity(intent);
            return;
        }
        Intent intent = new Intent(MainActivity.this,Periods.class);
        switch (id){
            case R.id.view_monday:
                intent.putExtra("table","monday");
                break;
            case R.id.view_tuesday:
                intent.putExtra("table","tuesday");
                break;
            case R.id.view_wednesday:
                intent.putExtra("table","wednesday");
                break;
            case R.id.view_thrusday:
                intent.putExtra("table","thrusday");

                break;
            case R.id.view_friday:
                intent.putExtra("table","friday");

                break;
            case R.id.view_saturday:

                intent.putExtra("table","saturday");
                break;
            case R.id.view_sunday:

                intent.putExtra("table","sunday");
                break;
        }
        startActivity(intent);
    }
}
