package com.example.my_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    Button startbtn;
    EditText nameField;


    //Initializing the components
    public void init()
    {
        logo = findViewById(R.id.idlogo);
        startbtn = findViewById(R.id.startbtn);
        nameField = findViewById(R.id.username);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Making sure field is not left blank by using Toasting and getting the username from the field
        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=nameField.getText().toString();

                if(username.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Can not leave Name Field blank!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, QuizScreen.class);
                    i.putExtra("USERNAME", username);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}