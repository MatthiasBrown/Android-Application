package e.irvingarcia.project;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderList extends ArrayAdapter<String> {
    private Activity context;
    List<String> schedule;
    //private String company;
    public ServiceProviderList(Activity context, List<String> schedule) {
        super(context, R.layout.list_layout, schedule);
        this.context = context;
        this.schedule = schedule;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_provider_list, null, true);

        TextView companyBox = (TextView) listViewItem.findViewById(R.id.companyList);
        TextView dateBox = (TextView) listViewItem.findViewById(R.id.dateList);
        TextView startBox = (TextView) listViewItem.findViewById(R.id.startTimeList);
        TextView endBox = (TextView) listViewItem.findViewById(R.id.endTimeList);
        TextView rateBox=(TextView) listViewItem.findViewById(R.id.rateList);

        String tempSchedule = schedule.get(position);
        String[] scheduleIndividual=tempSchedule.split(",");


        dateBox.setText(scheduleIndividual[0]);
        startBox.setText(scheduleIndividual[1]);
        endBox.setText(scheduleIndividual[2]);
        companyBox.setText(scheduleIndividual[3]);
        rateBox.setText(scheduleIndividual[4]);
        return listViewItem;
    }



}
