package teamdevops.mnnit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import teamdevops.mnnit.R;
import teamdevops.mnnit.entities.Grievance;

/**
 * Created by Deepankar on 24-11-2015.
 */
public class CMSAdapter extends RecyclerView.Adapter<CMSAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Grievance> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView subject;
        public final TextView dateTimeGrievance;
        public final ImageView status;
        public final TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            subject = (TextView) itemView.findViewById(R.id.tvSubjectGreivance);
            dateTimeGrievance = (TextView) itemView.findViewById(R.id.tvDateTime);
            status = (ImageView) itemView.findViewById(R.id.ivStatus);
            type = (TextView) itemView.findViewById(R.id.tvType);
        }
    }

    public CMSAdapter(Context context, ArrayList<Grievance> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CMSAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        // create a new view
        View listItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_grievance_list_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(listItemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CMSAdapter.ViewHolder viewHolder, int position) {
        viewHolder.subject.setText(dataSet.get(position).getSubject());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        viewHolder.dateTimeGrievance.setText(df.format(dataSet.get(position).getDate()));
        if (dataSet.get(position).getStatus().equals(context.getString(R.string.status_progress)))
            viewHolder.status.setImageResource(android.R.drawable.button_onoff_indicator_on);
        else
            viewHolder.status.setImageResource(android.R.drawable.button_onoff_indicator_off);
        viewHolder.type.setText(dataSet.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}