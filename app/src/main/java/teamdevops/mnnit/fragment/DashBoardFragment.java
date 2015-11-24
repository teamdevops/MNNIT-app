package teamdevops.mnnit.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import teamdevops.mnnit.R;
import teamdevops.mnnit.activity.LoginActivity;
import teamdevops.mnnit.activity.ProfileActivity;
import teamdevops.mnnit.activity.TimeTableActivity;
import teamdevops.mnnit.entities.TimeTableData;
import teamdevops.mnnit.helper.SQLiteHandler;

/**
 * Fragment class for the Dashboard page on the home screen
 *
 * @author Deepankar
 */
public class DashBoardFragment extends Fragment {

    private SQLiteHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new SQLiteHandler(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dash_board, container, false);
        CardView loginCard = (CardView) rootView.findViewById(R.id.loginCard);
        CardView plannerCard = (CardView) rootView.findViewById(R.id.plannerCard);

        TextView loginHeader = (TextView) rootView.findViewById(R.id.loginCardTextHeader);
        TextView loginTextInfo = (TextView) rootView.findViewById(R.id.loginCardTextInfo);
        TextView plannerTextHeader = (TextView) rootView.findViewById(R.id.plannerCardTextHeader);
        TextView plannerTextInfo = (TextView) rootView.findViewById(R.id.plannerCardTextInfo);

        HashMap<String, String> user = db.getUserDetails();


        db.close();
        if (!user.isEmpty()) {
            loginHeader.setText("Welcome " + user.get("name") + "!");
            loginTextInfo.setText("Check out your profile");
            loginCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), ProfileActivity.class);
                    startActivity(i);
                }
            });
        }
        else {
            loginCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    startActivity(i);
                }
            });
        }

        plannerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TimeTableActivity.class));
            }
        });
        return rootView;
    }
}
