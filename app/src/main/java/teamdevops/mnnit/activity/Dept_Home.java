package teamdevops.mnnit.activity;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import teamdevops.mnnit.R;

public class Dept_Home extends AppCompatActivity {

    public String[] allDept;
    int[] dept_images = {R.drawable.engg_icon3,R.drawable.scinece_icon,R.drawable.management_icon,R.drawable.faculty_icon};
    ListView Dept_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dept__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Resources res1 = getResources();
        allDept = res1.getStringArray(R.array.Departments);
        Dept_listview = (ListView) findViewById(R.id.listViewDept);
        SingleRowAdapter adapter = new SingleRowAdapter(this,allDept,dept_images);
        Dept_listview.setAdapter(adapter);

        Dept_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 0){
                    startActivity(new Intent(getApplicationContext(),Engineering_Home.class));
                }
                else if (position == 1) {
                    Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(position == 2){
                    Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                }
                else if(position == 3){
                    String url = "http://mnnit.ac.in/images/newstories/Faculty_Profile__color_.pdf";
                    Intent down = new Intent(Intent.ACTION_VIEW);
                    down.setData(Uri.parse(url));
                    startActivity(down);
                }
            }
        });
    }

    class SingleRowAdapter extends ArrayAdapter<String> {
        Context context;
        int[] img;
        String[] DeptNames;
        SingleRowAdapter(Context c, String[] Departments,int DeptImages[]) {
            super(c,R.layout.single_listview_row_with_images,Departments);
            this.context = c;
            this.img = DeptImages;
            this.DeptNames = Departments;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflator.inflate(R.layout.single_listview_row_with_images,parent,false);
            ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
            TextView myText  = (TextView) row.findViewById(R.id.textView);

            myImage.setImageResource(img[position]);
            myText.setText(DeptNames[position]);

            return row;
        }
    }

}
