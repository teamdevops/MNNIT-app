package teamdevops.mnnit.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

import teamdevops.mnnit.R;
import teamdevops.mnnit.activity.TimeTableActivity;
import teamdevops.mnnit.adapter.TimeTableAdapter;
import teamdevops.mnnit.entities.TimeTableData;
import teamdevops.mnnit.helper.MyComparator;
import teamdevops.mnnit.helper.SQLiteHandler;

/**
 * Created by- Chandra Bhan Giri
 */

public class FragmentMon extends Fragment {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onResume() {
        super.onResume();
        populateRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int scrollPosition = 0;
        View view = inflater.inflate(R.layout.fragment_mon, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvMonday);
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;
    }

    public void populateRecyclerView() {
        SQLiteHandler db = new SQLiteHandler(getActivity());
        ArrayList<TimeTableData> dataSet = db.getTimeTableData(TimeTableActivity.DAY[0]);
        Collections.sort(dataSet, new MyComparator());
        mAdapter = new TimeTableAdapter(getActivity(), dataSet);
        mRecyclerView.setAdapter(mAdapter);
    }
}

