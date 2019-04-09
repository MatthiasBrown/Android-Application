package e.irvingarcia.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;
    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.list_layout, services);
        this.context = context;
        this.services = services;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);

        TextView textService = (TextView) listViewItem.findViewById(R.id.layout_service);
        TextView textHourly = (TextView) listViewItem.findViewById(R.id.layout_hourly);

        Service service = services.get(position);

        textService.setText(service.getService());
        textHourly.setText(String.valueOf(service.getHour()));
        return listViewItem;
    }

}
