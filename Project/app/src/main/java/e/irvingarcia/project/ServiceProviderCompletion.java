package e.irvingarcia.project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class ServiceProviderCompletion extends AppCompatActivity {

    EditText streetBox;
    EditText cityBox;
    EditText postalBox;
    EditText companyBox;
    EditText phoneBox;
    EditText descriptionBox;
    Button btnFinish;
    RadioGroup radGroup;
    private Users temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras=getIntent().getExtras();

        setContentView(R.layout.activity_service_provider);
        String users=extras.getString("users");
        String pass=extras.getString("pass");

        MyDBHandler dbHandler=new MyDBHandler(this);
        temp=dbHandler.getProfile(users,pass);
        if(users.equals("null")&& pass.equals("null")){
            temp=new Users();
            temp.setCity("");
            temp.setPostal("");
            temp.setStreet("");
        }

        streetBox=(EditText)findViewById(R.id.editStreetS);
        cityBox=(EditText)findViewById(R.id.editCityS);
        postalBox=(EditText)findViewById(R.id.editPostalS);
        companyBox=(EditText)findViewById(R.id.editCompanyS);
        phoneBox=(EditText)findViewById(R.id.editPhoneS);
        descriptionBox=(EditText)findViewById(R.id.editDescriptionS);
        btnFinish=(Button)findViewById(R.id.btnFinish);
        btnFinish.setEnabled(false);

        radGroup=(RadioGroup)findViewById(R.id.radioGroupS);
        radGroup.check(R.id.radioYes);

        descriptionBox.addTextChangedListener(watcher);
        phoneBox.addTextChangedListener(watcher);
        companyBox.addTextChangedListener(watcher);
        streetBox.addTextChangedListener(watcher);
        cityBox.addTextChangedListener(watcher);
        postalBox.addTextChangedListener(watcher);

        streetBox.setText(temp.getStreet());
        cityBox.setText(temp.getCity());
        postalBox.setText(temp.getPostal());



    }
    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {}
        @Override
        public void afterTextChanged(Editable s) {
            if (phoneBox.getText().toString().length() == 0 || companyBox.getText().toString().length() == 0 ||
                    streetBox.getText().toString().length() == 0 || cityBox.getText().toString().length() == 0||
                    postalBox.getText().toString().length() == 0) {
                btnFinish.setEnabled(false);
            } else {
                btnFinish.setEnabled(true);
            }
        }
    };
    public void finish(View view){
        MyDBHandler dbHandler= new MyDBHandler(this);
        ServiceProvider temp2=new ServiceProvider();
        temp2.setCompany(companyBox.getText().toString());
        temp2.setPhone(phoneBox.getText().toString());
        temp2.setDescription(descriptionBox.getText().toString());
        if(radGroup.getCheckedRadioButtonId() == R.id.radioNo){
            temp2.setLiscence(false);
        }
        else{
            temp2.setLiscence(true);
        }
        temp.setStreet(streetBox.getText().toString());
        temp.setPostal(postalBox.getText().toString());
        temp.setCity(cityBox.getText().toString());
        dbHandler.completedForm(temp,temp2);
        dbHandler.close();
        Intent intent = new Intent(getApplicationContext(), Service_Profile.class);
        intent.putExtra("users", temp.getUser());
        intent.putExtra("pass", temp.getPass());
        startActivityForResult(intent, 0);
    }

}
