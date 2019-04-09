package e.irvingarcia.project;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

public class Service_ADD extends AppCompatActivity {
    ListView add;
    List<Service> services;

    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service__add);
        add=(ListView)findViewById(R.id.listAdd);
        MyDBHandler dbHandler=new MyDBHandler(this);
        ArrayList<Service> temp=dbHandler.getServices();
        ServiceList serviceList=new ServiceList(this,dbHandler.getServices());
        Bundle extras=getIntent().getExtras();
        String users=extras.getString("users");
        String pass=extras.getString("pass");
        user=dbHandler.getProfile(users,pass);

        add.setAdapter(serviceList);

        dbHandler.close();

        services=new ArrayList<>();

        add.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Service service = services.get(i);
                showUpdateDeleteDialog(service);
                return true;
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        MyDBHandler dbHandler=new MyDBHandler(this);
        services=dbHandler.getServices();
        //System.out.print(temp.size());
        dbHandler.close();
    }

    private void showUpdateDeleteDialog(Service serv) {
        MyDBHandler dbHandler=new MyDBHandler(this);
        Service tempService=serv;
        dbHandler.addServicetoAccount(dbHandler.getUserID(user),tempService);
        Toast.makeText(Service_ADD.this, "Added Successfully", Toast.LENGTH_LONG).show();
    }

    public void buttonFinish(View view){
        finish();
    }


}
