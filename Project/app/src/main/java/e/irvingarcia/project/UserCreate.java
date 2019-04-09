package e.irvingarcia.project;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Button;
import android.widget.Spinner;

public class UserCreate extends AppCompatActivity {
    EditText userBox;
    EditText passBox;
    EditText firstBox;
    EditText lastBox;
    EditText streetBox;
    EditText cityBox;
    EditText postalBox;
    Button buttonCreate;
    Spinner dropDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create);
        userBox = (EditText) findViewById(R.id.editUser);
        passBox = (EditText) findViewById(R.id.editPass);
        firstBox = (EditText) findViewById(R.id.editFirst);
        lastBox = (EditText) findViewById(R.id.editLast);
        streetBox = (EditText) findViewById(R.id.editStreet);
        cityBox = (EditText) findViewById(R.id.editCity);
        postalBox = (EditText) findViewById(R.id.editPostal);
        buttonCreate=(Button) findViewById(R.id.button3);
        userBox.addTextChangedListener(watcher);
        passBox.addTextChangedListener(watcher);
        firstBox.addTextChangedListener(watcher);
        lastBox.addTextChangedListener(watcher);
        streetBox.addTextChangedListener(watcher);
        cityBox.addTextChangedListener(watcher);
        postalBox.addTextChangedListener(watcher);
        buttonCreate.setEnabled(false);
        dropDown = findViewById(R.id.spinner);

        String[] items = new String[]{"Home Owner", "Service Provider"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        dropDown.setAdapter(adapter);
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
            if (userBox.getText().toString().length() == 0 || passBox.getText().toString().length() == 0 ||
                    firstBox.getText().toString().length() == 0|| lastBox.getText().toString().length() == 0||
                    streetBox.getText().toString().length() == 0 || cityBox.getText().toString().length() == 0||
                    postalBox.getText().toString().length() == 0) {
                buttonCreate.setEnabled(false);
            } else {
                buttonCreate.setEnabled(true);
            }
        }
    };


    public void newUser (View view) {
        String user=userBox.getText().toString();
        String pass=passBox.getText().toString();
        String first=firstBox.getText().toString();
        String last=lastBox.getText().toString();
        String street=streetBox.getText().toString();
        String city=cityBox.getText().toString();
        String postal=postalBox.getText().toString();
        String role=dropDown.getSelectedItem().toString();

        Users newUsers = new Users(user,pass,first,last,street,city,postal, role);


        MyDBHandler dbHandler= new MyDBHandler(this);
        dbHandler.addUser(newUsers);

        if(role.equals("Service Provider")){
            dbHandler.addServiceProvider(newUsers);
        }
        dbHandler.close();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);
        final AlertDialog b = dialogBuilder.create();
        b.show();
        final Button buttonBack = (Button) dialogView.findViewById(R.id.goBack);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
