package e.irvingarcia.project;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchHomeOwner extends AppCompatActivity {
    EditText searchBox;
    RadioGroup radioGroup;
    ListView searchQuery;
    ArrayList<String> serviceProviderList;
    ServiceProviderList serviceList;
    boolean sFlag;
    boolean rFlag;
    boolean tFlag;
    Users owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_home_owner);
        Bundle extras=getIntent().getExtras();
        String users=extras.getString("users");
        String pass=extras.getString("pass");
        final MyDBHandler dbHandler=new MyDBHandler(this);
        owner=dbHandler.getProfile(users,pass);
        dbHandler.close();
        searchBox=(EditText)findViewById(R.id.serviceSearch);
        radioGroup=(RadioGroup)findViewById(R.id.typeSearch);
        searchQuery=(ListView)findViewById(R.id.searchService);
        radioGroup.check(R.id.radioService);
        sFlag=true;
        rFlag=false;
        tFlag=false;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                if(checkedId==R.id.radioService){
                    sFlag=true;
                    rFlag=false;
                    tFlag=false;
                }
                else if(checkedId==R.id.radioRate){
                    sFlag=false;
                    rFlag=true;
                    tFlag=false;
                }
                else{
                    sFlag=false;
                    rFlag=false;
                    tFlag=true;
                }
            }

        });
        //serviceProviderList=new ArrayList<String>();
        searchQuery.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String serviceProvider = serviceProviderList.get(i);
                System.out.println(serviceProvider);
                pickerDialog(serviceProvider);
                return true;
            }
        });
    }
    public void search(View view) {
        onStart();
    }
    @Override
    protected void onStart(){
        super.onStart();
        MyDBHandler dbHandler=new MyDBHandler(this);
        serviceProviderList=new ArrayList<String>();
        if(!searchBox.getText().toString().equals("") && sFlag) {
            ArrayList<ServiceProvider> servicesAvailable = dbHandler.searchServices(searchBox.getText().toString());
            if(servicesAvailable!=null) {
                for (int i = 0; i < servicesAvailable.size(); i++) {
                    ArrayList<String> tempList = servicesAvailable.get(i).getSchedule();
                    for (int j = 0; j < tempList.size(); j++) {
                        serviceProviderList.add(tempList.get(j)+","+servicesAvailable.get(i).getCompany()+","+servicesAvailable.get(i).getRate()
                                +","+ servicesAvailable.get(i).getUser().getUser());
                    }
                }
                serviceList = new ServiceProviderList(this, serviceProviderList);
                searchQuery.setAdapter(serviceList);
            }
            else{
                serviceList = new ServiceProviderList(this, serviceProviderList);
                searchQuery.setAdapter(serviceList);
            }
        }
        else if(!searchBox.getText().toString().equals("") && rFlag) {
            ArrayList<ServiceProvider> servicesAvailable = dbHandler.searchRate(searchBox.getText().toString());
            if(servicesAvailable!=null) {
                for (int i = 0; i < servicesAvailable.size(); i++) {
                    ArrayList<String> tempList = servicesAvailable.get(i).getSchedule();
                    //System.out.println(servicesAvailable.get(i));
                    for (int j = 0; j < tempList.size(); j++) {
                        //serviceList.setCompany(servicesAvailable.get(i).getCompany());
                        //System.out.println(tempList.size());
                        serviceProviderList.add(tempList.get(j)+","+servicesAvailable.get(i).getCompany()+","+servicesAvailable.get(i).getRate()
                        +","+ servicesAvailable.get(i).getUser().getUser());
                    }
                }
                serviceList = new ServiceProviderList(this, serviceProviderList);
                searchQuery.setAdapter(serviceList);
            }
            else{
                serviceList = new ServiceProviderList(this, serviceProviderList);
                searchQuery.setAdapter(serviceList);
            }
        }
        else if(!searchBox.getText().toString().equals("") && tFlag) {
            ArrayList<ServiceProvider> servicesAvailable = dbHandler.searchTime(searchBox.getText().toString());
            if(servicesAvailable!=null) {
                for (int i = 0; i < servicesAvailable.size(); i++) {
                    ArrayList<String> tempList = servicesAvailable.get(i).getSchedule();
                    //System.out.println(servicesAvailable.get(i));
                    for (int j = 0; j < tempList.size(); j++) {
                        //serviceList.setCompany(servicesAvailable.get(i).getCompany());
                        //System.out.println(tempList.size());
                        serviceProviderList.add(tempList.get(j)+","+servicesAvailable.get(i).getCompany()+","+servicesAvailable.get(i).getRate()
                                +","+ servicesAvailable.get(i).getUser().getUser());
                    }
                }
                serviceList = new ServiceProviderList(this, serviceProviderList);
                searchQuery.setAdapter(serviceList);
            }
            else{
                serviceList = new ServiceProviderList(this, serviceProviderList);
                searchQuery.setAdapter(serviceList);
            }
        }

        dbHandler.close();
    }
    public void pickerDialog(final String bigString){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.search_homeowner_popup, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        final MyDBHandler dbHandler=new MyDBHandler(this);
        String[] littleString=bigString.split(",");
        String user=littleString[5];
        ArrayList<Service> temp=dbHandler.getServicesProfile(dbHandler.getUserID(user));
        String[] services=new String[temp.size()];
        for(int i=0;i<temp.size();i++){
            services[i]=temp.get(i).getService();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, services);
        final Spinner dropDown=(Spinner) dialogView.findViewById(R.id.spinner2);
        final Button addButton=(Button)dialogView.findViewById(R.id.button9);
        dropDown.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(dropDown.getSelectedItem().toString());
                dbHandler.bookService(owner,bigString+","+dropDown.getSelectedItem().toString());
                b.dismiss();
                Toast.makeText(SearchHomeOwner.this, "Added Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        b.show();
    }
}
