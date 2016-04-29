package teamdevops.mnnit.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import teamdevops.mnnit.R;
import teamdevops.mnnit.activity.Dept_Home;
import teamdevops.mnnit.activity.EmergencyHomeActivity;
import teamdevops.mnnit.activity.MnnitMapsActivity;

/**
 * Fragment class for the information page on the home screen
 *
 *
 */
public class InfoFragment extends Fragment {

    Intent emer_home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_info, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.info_gridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 3)
                    startActivity(new Intent(getActivity(), MnnitMapsActivity.class));
                else if (position == 5) {
                    startActivity(new Intent(getActivity(), EmergencyHomeActivity.class));
                }
                else if(position == 1){
                    startActivity(new Intent(getActivity(), Dept_Home.class));
                }
                else
                    Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private class ImageAdapter extends BaseAdapter {
        Context context;

        private Integer[] mDrawableIds = {
                R.drawable.ic_admissions, R.drawable.ic_departments_1,
                R.drawable.ic_clubs, R.drawable.ic_maps,
                R.drawable.ic_life, R.drawable.ic_emergency};

        private String[] mTextViews = {"Admissions", "Departments", "Clubs", "MNNIT Maps", "Life@MNNIT", "Emergency"};

        public ImageAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return mDrawableIds.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                item = inflater.inflate(R.layout.gird_view_item, parent, false);
            } else {
                item = convertView;
            }

            ImageView imageView = (ImageView) item.findViewById(R.id.ivGridItem);
            TextView textView = (TextView) item.findViewById(R.id.tvGridItem);

            textView.setText(mTextViews[position]);
            imageView.setImageResource(mDrawableIds[position]);

            return item;
        }
    }
}