package e.irvingarcia.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ScheduleList extends ArrayAdapter<String> {
    private Activity context;
    List<String> schedule;
    public ScheduleList(Activity context, List<String> schedule) {
        super(context, R.layout.list_layout, schedule);
        this.context = context;
        this.schedule = schedule;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.schedule_layout, null, true);

        TextView textDate = (TextView) listViewItem.findViewById(R.id.dateLayout);
        TextView textStart = (TextView) listViewItem.findViewById(R.id.startLayout);
        TextView textEnd = (TextView) listViewItem.findViewById(R.id.endLayout);

        String tempSchedule = schedule.get(position);
        String[] scheduleList=tempSchedule.split(",");

        textDate.setText(scheduleList[0]);
        textStart.setText(scheduleList[1]);
        textEnd.setText(scheduleList[2]);
        return listViewItem;
    }

}