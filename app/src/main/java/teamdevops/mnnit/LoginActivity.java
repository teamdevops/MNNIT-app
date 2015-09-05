package teamdevops.mnnit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Deepankar
 */

public class LoginActivity extends ActionBarActivity {

    private EditText regNo;
    private EditText password;
    private Button login;
    private Button skippoffline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regNo = (EditText) findViewById(R.id.etRegNo);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.bLogin);
        skippoffline = (Button) findViewById(R.id.bSkipOffline);

        skippoffline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(i);
                LoginActivity.this.finish();
            }
        });

    }
}
