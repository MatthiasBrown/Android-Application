package e.irvingarcia.project;

import android.content.Intent;
import android.support.constraint.solver.LinearSystem;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {
    TextView nameBoxH;
    TextView addressBoxH;
    TextView roleBoxH;
    ListView bookedServices;
    ArrayList<String> booked;
    ServiceProviderList bookedList;
    Users owner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bundle extras=getIntent().getExtras();
        String users=extras.getString("users");
        String pass=extras.getString("pass");

        nameBoxH=(TextView)findViewById(R.id.nameH);
        addressBoxH=(TextView)findViewById(R.id.addressH);
        roleBoxH=(TextView)findViewById(R.id.roleH);
        bookedServices=(ListView)findViewById(R.id.listViewHome);

        final MyDBHandler dbHandler=new MyDBHandler(this);
        owner=dbHandler.getProfile(users,pass);
        if(owner!=null) {
            String fullName = owner.getFirst() + " " + owner.getLast();
            nameBoxH.setText(fullName);
            String fullAddress=owner.getStreet()+" "+owner.getCity()+" "+owner.getPostal();
            addressBoxH.setText(fullAddress);
            roleBoxH.setText(owner.getRole());
        }

        bookedServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String serviceProvider = booked.get(i);
                String[] tempArray=serviceProvider.split(",");
                String company=tempArray[3];
                System.out.println(company);
                pickerDialog(company);
                //dbHandler.rateServiceProvider(company,5);

                return true;
            }
        });
        dbHandler.close();
    }
    @Override
    protected void onStart(){
        super.onStart();
        MyDBHandler dbHandler=new MyDBHandler(this);
        booked=new ArrayList<>();
        booked=dbHandler.getBookedServices(owner);
        bookedList=new ServiceProviderList(this,booked);
        bookedServices.setAdapter(bookedList);
        //System.out.println(dbHandler.getBookedServices(owner));
        dbHandler.close();
    }
    public void addService(View view){
        Intent intent = new Intent(getApplicationContext(), SearchHomeOwner.class);
        intent.putExtra("users", owner.getUser());
        intent.putExtra("pass", owner.getPass());
        startActivityForResult(intent, 0);
    }

    public void pickerDialog(final String company){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.rate_popup, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        final MyDBHandler dbHandler=new MyDBHandler(this);
        Integer[] services=new Integer[6];
        for(int i=0;i<services.length;i++){
            services[i]=i;
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, services);
        final Spinner dropDown=(Spinner) dialogView.findViewById(R.id.spinner3);
        final TextView rate=(TextView)dialogView.findViewById(R.id.textView26);
        rate.setText("Rate");
        final Button addButton=(Button)dialogView.findViewById(R.id.button10);
        dropDown.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(dropDown.getSelectedItem().toString());
                dbHandler.rateServiceProvider(company,Integer.parseInt(dropDown.getSelectedItem().toString()));
                b.dismiss();
                Toast.makeText(Welcome.this, "Thanks", Toast.LENGTH_SHORT).show();
            }
        });
        b.show();
    }

}
