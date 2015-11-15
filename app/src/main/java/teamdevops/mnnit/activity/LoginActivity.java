package teamdevops.mnnit.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import teamdevops.mnnit.R;
import teamdevops.mnnit.config.AppConfig;
import teamdevops.mnnit.connection.VolleySingleton;
import teamdevops.mnnit.helper.SQLiteHandler;
import teamdevops.mnnit.helper.SessionManager;

/**
 * This activity is for login screen of Dean Academics of MNNIT.
 *
 * @author Deepankar
 */

public class LoginActivity extends ActionBarActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText regNo;
    private EditText password;
    private Button login;
    private Button skippoffline;
    private ProgressDialog progressDialog;
    private AlertDialog alertDialog;
    private JSONObject jsonObject;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onResume() {
        super.onResume();
        session = SessionManager.getInstance(getApplicationContext());
        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this,
                    HomeActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        alertDialog = new AlertDialog.Builder(this).create();
        regNo = (EditText) findViewById(R.id.etRegNo);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.bLogin);
        skippoffline = (Button) findViewById(R.id.bSkipOffline);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        skippoffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                LoginActivity.this.finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String reg = regNo.getText().toString();
                String pass = password.getText().toString();
                try {
                    showDialog();
                    login(reg, pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void login(final String regno, final String password) throws JSONException {

        // POST parameters
        Map<String, String> params = new HashMap<String, String>();
        params.put("tag", "login");
        params.put("regno" , regno);
        params.put("password",password);

        JSONObject jsonObject = new JSONObject(params);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN , new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                try {
                    hideDialog();
                    JSONObject jsonObject = new JSONObject(response);
                    Log.e( TAG, jsonObject.toString());
                    if (jsonObject.getString("error").equals("0")) {
                        // User login successful, now store the user in sqlite and shared preference
                        session.setLogin(true);
                        JSONObject user = jsonObject.getJSONObject("user");
                        String name = user.getString("name");
                        int regno = user.getInt("regno");
                        String email = user.getString("email");
                        long phoneNo = user.getLong("phoneno");
                        db.addUser(name,email,regno,phoneNo);
                        db.close();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        i.putExtra("name", user.getString("name"));
                        startActivity(i);
                    } else if (jsonObject.getString("error").equals("1")) {
                        hideDialog();
                        alertDialog.setTitle("Login Error");
                        alertDialog.setMessage(jsonObject.getString("error_msg"));
                        alertDialog.show();
                    }
                    else if (jsonObject.getString("error").equals("2")) {
                        hideDialog();
                        alertDialog.setTitle("Login Error");
                        alertDialog.setMessage(jsonObject.getString("error_msg"));
                        alertDialog.show();
                    }
                    else {
                        hideDialog();
                        alertDialog.setTitle("Login Error");
                        alertDialog.setMessage(jsonObject.getString("error_msg"));
                        alertDialog.show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.hide();
                Log.d(TAG , volleyError.toString());
                Toast.makeText(getApplicationContext(), "Login Error : Could not connect to server. Please check active internet connection", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "login");
                params.put("regno", regno);
                params.put("password", password);
                return params;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
