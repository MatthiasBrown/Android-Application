package e.irvingarcia.project;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceCreation extends AppCompatActivity {
    EditText addServiceBox;
    EditText addHourBox;
    EditText editServiceBox;
    EditText deleteServiceBox;
    Button addServiceButton;
    Button editServiceButton;
    Button deleteServiceButton;
    TextView viewAdd;
    TextView viewEdit;
    TextView viewDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);

        viewAdd=(TextView)findViewById(R.id.viewAdd);
        viewEdit=(TextView)findViewById(R.id.viewEdit);
        viewDelete=(TextView)findViewById(R.id.viewDelete);

        addHourBox=(EditText)findViewById(R.id.editAddHour);
        addServiceBox=(EditText)findViewById(R.id.editAddService);
        editServiceBox=(EditText)findViewById(R.id.editService);
        deleteServiceBox=(EditText)findViewById(R.id.editDeleteService);

        addServiceButton= (Button) findViewById(R.id.btnAdd);
        editServiceButton=(Button)findViewById(R.id.btnService);
        deleteServiceButton=(Button)findViewById(R.id.btnDelete);

        addHourBox.addTextChangedListener(watch);
        addServiceBox.addTextChangedListener(watch);
        editServiceBox.addTextChangedListener(watch);
        deleteServiceBox.addTextChangedListener(watch);

        addServiceButton.setEnabled(false);
        editServiceButton.setEnabled(false);
        deleteServiceButton.setEnabled(false);
    }
    private final TextWatcher watch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (addHourBox.getText().toString().length()==0 ||addServiceBox.getText().toString().length()==0) {
                addServiceButton.setEnabled(false);
            } else {
                addServiceButton.setEnabled(true);
            }
            if(editServiceBox.getText().toString().length()==0){
                editServiceButton.setEnabled(false);
            }
            else{
                editServiceButton.setEnabled(true);
            }
            if(deleteServiceBox.getText().toString().length()==0){
                deleteServiceButton.setEnabled(false);
            }
            else{
                deleteServiceButton.setEnabled(true);
            }
        }
    };

    public void newService (View view) {
        String service=addServiceBox.getText().toString();
        double hour=Double.parseDouble(addHourBox.getText().toString());

        Service newService=new Service(service,hour);

        // TODO: add to database
        MyDBHandler dbHandler= new MyDBHandler(this);
        dbHandler.addService(newService);
        dbHandler.close();
        viewAdd.setText("Added Successfully");
        addHourBox.setText("");
        addServiceBox.setText("");


    }
    public void editService(View view){
        String service=editServiceBox.getText().toString();
        final MyDBHandler dbHandler= new MyDBHandler(this);
        final Service find= dbHandler.findService(service);
        dbHandler.close();
        if(find==null){
            viewEdit.setText("Not Found");
        }
        else{
            viewEdit.setText("Found");


            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();
            final View dialogView = inflater.inflate(R.layout.edit_dialogue, null);
            dialogBuilder.setView(dialogView);
            final AlertDialog b = dialogBuilder.create();
            b.show();
            final EditText editServiceBox = (EditText) dialogView.findViewById(R.id.editServiceName);
            final EditText editHourBox=(EditText)dialogView.findViewById(R.id.editHourly);
            editServiceBox.setText(find.getService());
            editHourBox.setText(Double.toString(find.getHour()));
            final Button buttonUpdateService = (Button) dialogView.findViewById(R.id.buttonUpdate);
            buttonUpdateService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String service = editServiceBox.getText().toString();
                    double price = Double.parseDouble(editHourBox.getText().toString());
                    Service updatedService=new Service(service,price);
                    dbHandler.updateService(updatedService,find);
                    b.dismiss();
                }
            });
            dbHandler.close();
        }
    }
    public void delete(View view){
        String service=deleteServiceBox.getText().toString();
        MyDBHandler dbHandler= new MyDBHandler(this);
        boolean delete=dbHandler.deleteService(service);
        dbHandler.close();
        if(delete){
            viewDelete.setText("Deleted Successfully");
        }
        else{
            viewDelete.setText("Failed");
        }

    }
}

