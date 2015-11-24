package teamdevops.mnnit.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import teamdevops.mnnit.R;
import teamdevops.mnnit.config.AppConfig;
import teamdevops.mnnit.connection.VolleySingleton;
import teamdevops.mnnit.helper.SQLiteHandler;

public class AddGrievanceActivity extends AppCompatActivity {

    private static final int MAX_TIMEOUT_MS = 100000;
    private TextView tvName;
    private TextView tvRegNoGrievance;
    private TextView tvHostelGrievance;
    private TextView tvRoomGrievance;
    private EditText etSubject;
    private EditText etGrievance;
    private Spinner sCategory;
    private String type;
    private String regNo;
    private String subject;
    private String grievance;
    private String authority;
    private String created_at;
    int status = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grievance);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_addgrievance);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteHandler db = new SQLiteHandler(this);
        HashMap<String, String> user = db.getUserDetails();

        tvName = (TextView) findViewById(R.id.tvProfileNameGrievance);
        tvName.setText(user.get(SQLiteHandler.KEY_NAME));
        tvRegNoGrievance = (TextView) findViewById(R.id.tvRegNoGrievance);
        tvRegNoGrievance.setText(user.get(SQLiteHandler.KEY_REGNO));
        tvHostelGrievance = (TextView) findViewById(R.id.tvHostelGrievance);
        tvHostelGrievance.setText(user.get(SQLiteHandler.KEY_HOSTEL));
        tvRoomGrievance = (TextView) findViewById(R.id.tvRoomGrievance);
        tvRoomGrievance.setText(user.get(SQLiteHandler.KEY_ROOMNO));

        etSubject = (EditText) findViewById(R.id.etSubject);
        etGrievance = (EditText) findViewById(R.id.etGrievance);
        sCategory = (Spinner) findViewById(R.id.sCategory);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grievance_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapter);
        sCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type = "Others";
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_grievance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.SubmitGrievance) {

            regNo = tvRegNoGrievance.getText().toString();
            authority = tvHostelGrievance.getText().toString();
            subject = etSubject.getText().toString();
            grievance = etGrievance.getText().toString();
            status = 0;
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            created_at = df.format(c.getTime());

            StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.LOCAL_GREVIANCE_ADD, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Log.d("RESPONSE :", s);
                    if (s.compareTo("Success") == 0)
                        Toast.makeText(getApplicationContext(), "Grievance Successfully Submitted", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Grievance Submission Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getApplicationContext(), "Error connecting to the server: Submission Unsuccesful", Toast.LENGTH_SHORT).show();
                        }
                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Posting params to register url
                    Map<String, String> params = new HashMap<>();
                    params.put("tag", "login");
                    params.put("regno", regNo);
                    params.put("authority", authority);
                    params.put("type", type);
                    params.put("subject", subject);
                    params.put("grievance", grievance);
                    //params.put("status", status);
                    params.put("created_at", created_at);
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(MAX_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
