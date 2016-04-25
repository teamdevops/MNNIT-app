package teamdevops.mnnit.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import teamdevops.mnnit.R;

public class EmergencyHomeActivity extends AppCompatActivity {

    ImageButton download_ib1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        download_ib1 = (ImageButton) findViewById(R.id.imageButton);
        //download_b1 = (Button) findViewById(R.id.button);


    }

    public void DownloadDutyChart(View view) {
        String url = "http://mnnit.ac.in/images/newstories/1053_Notification_regarding_Duty_Chart_of_Part_Time_Doctors_for_Health_Centre_of_the_Institute.pdf";
        Intent down = null, chooser = null;
        if(view.getId() == R.id.imageButton ){
            down = new Intent(Intent.ACTION_VIEW);
            down.setData(Uri.parse(url));
            startActivity(down);
        }

    }

    @Nullable
    @Override
    public ActionBar getActionBar() {

        return super.getActionBar();
    }
}
