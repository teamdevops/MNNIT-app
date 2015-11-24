package teamdevops.mnnit.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

import teamdevops.mnnit.R;
import teamdevops.mnnit.helper.SQLiteHandler;
import teamdevops.mnnit.helper.SessionManager;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = new SQLiteHandler(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
        toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_profile);
        collapsingToolbarLayout.setTitle(user.get(SQLiteHandler.KEY_NAME));
        session = SessionManager.getInstance(getApplicationContext());

        ImageView appBarImage = (ImageView) findViewById(R.id.ivAppBar);
        byte[] imageByteArray = db.getImage();
        db.close();
        if(imageByteArray!=null) {
            Bitmap bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray.length);
            appBarImage.setImageBitmap(bmp);
        }
        else
            appBarImage.setImageResource(R.drawable.logo);

        TextView name = (TextView) findViewById(R.id.tvProfileName);
        name.setText(user.get(SQLiteHandler.KEY_NAME));
        TextView regno = (TextView) findViewById(R.id.tvRegNo);
        regno.setText(user.get(SQLiteHandler.KEY_REGNO));
        TextView fathername = (TextView) findViewById(R.id.tvFatherName);
        fathername.setText(user.get(SQLiteHandler.KEY_FATHER));
        TextView gender = (TextView) findViewById(R.id.tvGender);
        gender.setText(user.get(SQLiteHandler.KEY_GENDER));
        TextView email = (TextView) findViewById(R.id.tvEmail);
        email.setText(user.get(SQLiteHandler.KEY_EMAIL));
        TextView phoneno = (TextView) findViewById(R.id.tvPhone);
        phoneno.setText(user.get(SQLiteHandler.KEY_PHONE));
        TextView dob = (TextView) findViewById(R.id.tvDob);
        dob.setText(user.get(SQLiteHandler.KEY_DOB));
        TextView address = (TextView) findViewById(R.id.tvAddress);
        address.setText(user.get(SQLiteHandler.KEY_ADDRESS));
        TextView bloodgroup = (TextView) findViewById(R.id.tvBloodgroup);
        bloodgroup.setText(user.get(SQLiteHandler.KEY_BLOODGROUP));
        TextView hostel = (TextView) findViewById(R.id.tvHostel);
        hostel.setText(user.get(SQLiteHandler.KEY_HOSTEL));
        TextView roomno = (TextView) findViewById(R.id.tvRoom);
        roomno.setText(user.get(SQLiteHandler.KEY_ROOMNO));

        Button logout = (Button) findViewById(R.id.btnLogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteUsers();
                db.deleteGrievances();
                session.setLogin(false);
                Intent i = new Intent(ProfileActivity.this,LoginActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });

    }
}
