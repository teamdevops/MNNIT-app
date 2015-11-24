package teamdevops.mnnit.helper;

import java.util.Comparator;

import teamdevops.mnnit.entities.Grievance;

/**
 * Created by Deepankar on 24-11-2015.
 */
public class MyGrievanceComparator implements Comparator<Grievance> {

    @Override
    public int compare(Grievance g1, Grievance g2) {
        return ((g1.getDate().compareTo(g2.getDate())) <= 0 ? -1 : 1);
    }
}
