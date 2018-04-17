package com.example.wiltontuckerhart.proyectofinal.activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wiltontuckerhart.proyectofinal.R;
import com.example.wiltontuckerhart.proyectofinal.Util.Functions;
import com.example.wiltontuckerhart.proyectofinal.Util.Session;
import com.example.wiltontuckerhart.proyectofinal.database.models.Users;
import com.example.wiltontuckerhart.proyectofinal.database.models.Users_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;

public class RegisterActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private ImageView image;

    private Session session;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        image = findViewById(R.id.imageLogin);

        session = new Session(this);
        Button registrar = findViewById(R.id.register);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    validar(username.getText().toString(), password.getText().toString());
                    goToRegistrar(username.getText().toString(), password.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        Functions.loadImage("http://reconciliasian.com/wp-content/uploads/2018/03/gallery-perfect-7-piece-wide-frame-set-modern-picture-frames-with-prepare.jpg", image, this);
    }


    private  void goToMain(){
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }

    private void validar(String username, String password) throws Exception
    {
        if (username.isEmpty())
        {
            throw new Exception("El nombre de usuario esta vacio");
        }
        if (password.isEmpty())
        {
            throw new Exception("Ingrese una contraseña");
        }
    }

    private boolean goToRegistrar ( String username, String password ) throws Exception{
        boolean isLoggedIn= SQLite.selectCountOf().from(Users.class).where(Users_Table.username.eq(username))
                .and(Users_Table.password.eq(Functions.md5(password))).hasData();

        if (isLoggedIn)
        {
            throw new Exception("El Ussuario ya existe");
        }else{
            Users user = new Users();
            user.nombre = username;
            user.username = username;
            user.password = Functions.md5(password);
            user.save();
            Session session = new Session(getApplicationContext());
            session.createLoginSession(user.id, user.nombre, user.roll);
            goToMain();
        }
        return isLoggedIn;
    }
}
