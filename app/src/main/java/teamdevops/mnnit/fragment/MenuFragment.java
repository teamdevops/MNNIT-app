package teamdevops.mnnit.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import teamdevops.mnnit.activity.CMSActivity;

/**
 * Fragment class for the Menu page on the home screen
 *
 * @author Deepankar
 */

public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.menu_gridview);
        gridview.setAdapter(new ImageAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if(position == 4)
                    startActivity(new Intent(getActivity(), CMSActivity.class));
                else
                    Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private class ImageAdapter extends BaseAdapter {
        Context context;

        private Integer[] mDrawableIds = {
                R.drawable.ic_news_color, R.drawable.ic_notice_color,
                R.drawable.ic_planner_color_1, R.drawable.ic_result,
                R.drawable.ic_grievance, R.drawable.ic_mess};

        private String[] mTextViews = {"News", "Announcements", "Planner", "Results", "E-Grievance", "Mess"};

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
