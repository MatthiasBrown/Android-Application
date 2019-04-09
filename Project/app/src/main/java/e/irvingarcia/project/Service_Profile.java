package e.irvingarcia.project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Service_Profile extends AppCompatActivity {
    TextView nameView;
    TextView companyView;
    TextView phoneView;
    TextView descriptionView;
    Users temp;
    ServiceProvider serviceProvider;
    ListView service;
    List<Service> services;
    ServiceList serviceList;

    ListView schedules;
    ScheduleList scheduleList;
    List<String> schedule;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView editDate;
    private TextView editStart;
    private TextView editEnd;
    private TimePickerDialog.OnTimeSetListener timeListener;
    private TimePickerDialog.OnTimeSetListener timeListenerEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_profile);
        Bundle extras=getIntent().getExtras();

        String users=extras.getString("users");
        String pass=extras.getString("pass");
        MyDBHandler dbHandler=new MyDBHandler(this);

        temp=dbHandler.getProfile(users,pass);
        serviceProvider=dbHandler.getServiceProvider(dbHandler.getUserID(temp));

        nameView=(TextView)findViewById(R.id.nameP);
        companyView=(TextView)findViewById(R.id.companyP);
        phoneView=(TextView)findViewById(R.id.phoneP);
        descriptionView=(TextView)findViewById(R.id.descripP);

        nameView.setText(temp.getFirst());
        companyView.setText(serviceProvider.getCompany());
        phoneView.setText(serviceProvider.getPhone());
        descriptionView.setText(serviceProvider.getDescription());

        service=(ListView)findViewById(R.id.listViewP);
        schedules=(ListView)findViewById(R.id.schdules);
        dbHandler.close();
    }

    @Override
    protected void onStart(){
        super.onStart();
        MyDBHandler dbHandler=new MyDBHandler(this);
        final ArrayList<Service> serviceTemp=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
        services=serviceTemp;
        serviceList=new ServiceList(this,services);
        service.setAdapter(serviceList);

        service.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service serv = services.get(i);
                showUpdateDeleteDialog(serv);
                return true;
            }
        });
        ArrayList<String> scheduleTemp=dbHandler.getTime(dbHandler.getUserID(temp));
        schedule=scheduleTemp;
        scheduleList=new ScheduleList(this,schedule);
        schedules.setAdapter(scheduleList);

        dbHandler.close();
    }

    public void addService (View view){
        MyDBHandler dbHandler=new MyDBHandler(this);
        Intent intent = new Intent(getApplicationContext(), Service_ADD.class);
        intent.putExtra("users", temp.getUser());
        intent.putExtra("pass", temp.getPass());
        startActivityForResult(intent, 0);
        services=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
        serviceList.notifyDataSetChanged();
        dbHandler.close();

    }

    private void showUpdateDeleteDialog(Service serv) {
        MyDBHandler dbHandler=new MyDBHandler(this);
        Service tempService=serv;
        dbHandler.deleteServicefromAccount(dbHandler.getUserID(temp),tempService);
//        services=dbHandler.getServicesProfile(dbHandler.getUserID(temp));
//        serviceList.notifyDataSetChanged();
        onStart();
        dbHandler.close();

        //dbHandler.addServicetoAccount(dbHandler.getUserID(user),tempService);
        //Toast.makeText(Service_ADD.this, "Added Successfully", Toast.LENGTH_LONG).show();
    }
    public void addSchedule(View view){
        final MyDBHandler dbHandler=new MyDBHandler(this);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.add_schedule, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        editDate=(TextView)dialogView.findViewById(R.id.dateS);
        editStart=(TextView)dialogView.findViewById(R.id.startS);
        editEnd=(TextView)dialogView.findViewById(R.id.endS);
        final Button buttonDone = (Button) dialogView.findViewById(R.id.btnDone);
        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Service_Profile.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        editStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hourOfDay= 12;
                int minute = 0;

                TimePickerDialog time=new TimePickerDialog(Service_Profile.this, timeListener,hourOfDay,minute,true);
                //dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                time.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                time.show();
            }
        });
        editEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hourOfDay= 12;
                int minute = 0;

                TimePickerDialog time=new TimePickerDialog(Service_Profile.this, timeListenerEnd,hourOfDay,minute,true);
                //dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                time.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                time.show();
            }
        });
        b.show();
        mDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                editDate.setText(date);
            }
        };
        timeListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //String time=hourOfDay+":"+minute;
                editStart.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };
        timeListenerEnd=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //String time=hourOfDay+":"+minute;
                editEnd.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };


        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
                dbHandler.addTime(dbHandler.getUserID(temp),editDate.getText().toString(),editStart.getText().toString(),editEnd.getText().toString());
                dbHandler.close();
                onStart();
            }
        });
    }


}
