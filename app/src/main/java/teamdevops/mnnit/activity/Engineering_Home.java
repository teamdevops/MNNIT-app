package teamdevops.mnnit.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import teamdevops.mnnit.R;

public class Engineering_Home extends AppCompatActivity {

    public String[] AllEngg;
    int[] engg_images = {R.drawable.applied_mech_icon_1,R.drawable.biotech_icon_1,R.drawable.chemical_engg_icon_1,R.drawable.civil_engg_icon_2,R.drawable.comp_science_icon_2,R.drawable.elect_engg_icon_1,R.drawable.electron_engg_icon_1,R.drawable.mech_engg_icon_1};
    ListView Engg_listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineering__home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Resources res1 = getResources();
        AllEngg = res1.getStringArray(R.array.EngineeringDept);
        Engg_listview = (ListView) findViewById(R.id.enggListView);
        SingleRowAdapter adapter = new SingleRowAdapter(this,AllEngg,engg_images);
        Engg_listview.setAdapter(adapter);


    }
    class SingleRowAdapter extends ArrayAdapter<String> {
        Context context;
        int[] img;
        String[] EnggNames;
        SingleRowAdapter(Context c, String[] Engineering,int EnggImages[]) {
            super(c,R.layout.single_listview_row_with_images,Engineering);
            this.context = c;
            this.img = EnggImages;
            this.EnggNames = Engineering;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflator.inflate(R.layout.single_listview_row_with_images,parent,false);
            ImageView myImage = (ImageView) row.findViewById(R.id.imageView);
            TextView myText  = (TextView) row.findViewById(R.id.textView);

            myImage.setImageResource(img[position]);
            myText.setText(EnggNames[position]);

            return row;
        }
    }

}
