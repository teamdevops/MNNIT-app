package teamdevops.mnnit.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import teamdevops.mnnit.R;
import teamdevops.mnnit.entities.TimeTableData;

/**
 * Created by king on 24-11-2015.
 */
public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.ViewHolder> {

    static DialogCommunicator1 mDialogCommunicator1;
    public interface DialogCommunicator1 {
        public void onPositiveClick(TimeTableData data);
    }


    private static Context context;
    private ArrayList<TimeTableData> dataSet;
    private static ViewPager viewPager;
    int viewPagerPosition = -1;
    public static String DAY[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final int[] imageTimeofDay = {R.drawable.art_clear, R.drawable.art_light_clouds};
        public final TextView subject;
        public final TextView subjectCode;
        public final TextView venue;
        public final TextView instructor;
        public final TextView time;
        public final ImageView image;

        public ViewHolder(final View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.tvSubject);
            subjectCode = (TextView) itemView.findViewById(R.id.tvSubjectCode);
            venue = (TextView) itemView.findViewById(R.id.tvVenue);
            instructor = (TextView) itemView.findViewById(R.id.tvInstructorname);
            time = (TextView) itemView.findViewById(R.id.tvTime);
            image = (ImageView) itemView.findViewById(R.id.ivDay);
            mDialogCommunicator1 = (DialogCommunicator1) context;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Delete")
                            .setMessage("Do you want to Delete this ?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    TextView subject1;
                                    TextView subjectCode1;
                                    TextView venue1;
                                    TextView instructor1;
                                    TextView time1;

                                    subject1 = (TextView) v.findViewById(R.id.tvSubject);
                                    subjectCode1 = (TextView) v.findViewById(R.id.tvSubjectCode);
                                    venue1 = (TextView) v.findViewById(R.id.tvVenue);
                                    instructor1 = (TextView) v.findViewById(R.id.tvInstructorname);


                                    final TimeTableData data = new TimeTableData();
                                    data.setSubject(subject1.getText().toString());
                                    data.setCode(subjectCode1.getText().toString());
                                    data.setVenue(venue1.getText().toString());
                                    data.setInstructor(instructor1.getText().toString());

                                    mDialogCommunicator1.onPositiveClick(data);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(context, "Cancelled", Toast.LENGTH_SHORT).show();
                        }
                    });;

                    builder.create().show();
                }
            });

        }

    }

    public TimeTableAdapter(Context context, ArrayList<TimeTableData> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TimeTableAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        // create a new view
        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_timetable_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        viewHolder.subject.setText(dataSet.get(position).getSubject());
        viewHolder.subjectCode.setText(dataSet.get(position).getCode());
        viewHolder.venue.setText(dataSet.get(position).getVenue());
        viewHolder.instructor.setText(dataSet.get(position).getInstructor());

        String AMPM = "";
        int fromMin = dataSet.get(position).getFrom_time();
        long fromHours = TimeUnit.MINUTES.toHours((fromMin / 60) * 60);
        long fromRemainMinute = fromMin - TimeUnit.HOURS.toMinutes(fromHours);

        if (fromHours < 13)
            viewHolder.image.setImageResource(viewHolder.imageTimeofDay[0]);
        else
            viewHolder.image.setImageResource(viewHolder.imageTimeofDay[1]);

        if (fromHours < 12)
            AMPM = "am";
        else if (fromHours == 12)
            AMPM = "noon";
        else {
            fromHours = fromHours - 12;
            AMPM = "pm";
        }
        String fromTime = String.format("%02d:%02d" + AMPM, fromHours, fromRemainMinute);

        int toMin = dataSet.get(position).getTo_time();
        long toHours = TimeUnit.MINUTES.toHours((toMin / 60) * 60);
        long toRemainMinute = toMin - TimeUnit.HOURS.toMinutes(toHours);
        if (toHours < 12)
            AMPM = "am";
        else if (toHours == 12)
            AMPM = "noon";
        else {
            toHours = toHours - 12;
            AMPM = "pm";
        }
        String toTime = String.format("%02d:%02d" + AMPM, toHours, toRemainMinute);
        String time = fromTime + " : " + toTime;
        viewHolder.time.setText(time);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

