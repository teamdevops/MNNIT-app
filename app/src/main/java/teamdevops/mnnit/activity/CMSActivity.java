package teamdevops.mnnit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

import teamdevops.mnnit.R;
import teamdevops.mnnit.adapter.CMSAdapter;
import teamdevops.mnnit.entities.Grievance;
import teamdevops.mnnit.helper.MyGrievanceComparator;
import teamdevops.mnnit.helper.SQLiteHandler;

public class CMSActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteHandler db;

    @Override
    public void onResume() {
        super.onResume();
        populateRecyclerView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cms);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_cms);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCMS);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddGrievanceActivity.class));
            }
        });
        int scrollPosition = 0;
        mRecyclerView = (RecyclerView) findViewById(R.id.rvGrievance);
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void populateRecyclerView() {
        db = new SQLiteHandler(getApplicationContext());
        ArrayList<Grievance> dataSet = db.getGrievanceDetails();
        Collections.sort(dataSet, new MyGrievanceComparator());
        mAdapter = new CMSAdapter(this, dataSet);
        mRecyclerView.setAdapter(mAdapter);
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refreshgrievance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.refreshGreivance) {

            // TODO Perform Volley
            refreshGrievances();
            populateDB();
            populateRecyclerView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void populateDB() {

    }

    public void refreshGrievances() {

    }

}
