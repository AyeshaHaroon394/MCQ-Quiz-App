package com.example.my_quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizScreen extends AppCompatActivity {

    /*private String[] questions = {
            "In Harry Potter and the Philosopher's Stone, what object does Harry first catch in his mouth during a Quidditch match?",
            "What is the name of the Weasley family’s home?",
            "What is the name of the house-elf who serves the Malfoy family?",
            "Which of these spells is used to disarm an opponent?",
            "What type of dragon does Harry face in the Triwizard Tournament?",
            "Who is the ghost of Ravenclaw house?",
            "What is the name of the train that takes students to Hogwarts?",
            "What does Dumbledore leave to Hermione in his will?",
            "Which magical creature pulls the carriages at Hogwarts?",
            "What does the Marauder’s Map reveal ?"
    };
     */

    private String[][] options = {
            {"Remembrall", "Snitch", "Bludger", "Quaffle"},
            {"The Nest", "The Burrow", "Ottery St. Catchpole", "Hogsmeade Cottage"},
            {"Dobby", "Winky", "Kreacher", "Hokey"},
            {"Expelliarmus", "Avada Kedavra", "Alohomora", "Crucio"},
            {"Swedish Short Snout", "Norwegian Ridgeback", "Hungarian Horntail", "Common Welsh Green"},
            {"The Fat Friar", "The Bloody Baron", "Nearly Headless Nick", "The Grey Lady"},
            {"Knight Bus", "Hogwarts Express", "The Lightning Train", "Wizarding Locomotive"},
            {"A wand", "The Tales of Beedle the Bard", "A Deluminator", "A Snitch"},
            {"Hippogriffs", "House Elves", "Thestrals", "Basilisks"},
            {"Hidden messages", "People’s thoughts", "Secret Passages & location", "Snape's diary"}
    };

    private int[][] answers = {
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {1, 0, 0, 0},
            {0, 1, 0, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
    };
    private String[] questions;

    private TextView question;
    private TextView counter;
    private Button nextBtn, prevBtn;
    private RadioGroup radioGroup;
    private RadioButton opt1, opt2, opt3, opt4;
    private String username;
    private int index;
    private int score;

    private void init()
    {
        question=findViewById(R.id.question);
        counter=findViewById(R.id.counter);
        nextBtn=findViewById(R.id.nextBtn);
        prevBtn=findViewById(R.id.previousBtn);
        radioGroup=findViewById(R.id.rGrp);
        opt1=findViewById(R.id.opt1);
        opt2=findViewById(R.id.opt2);
        opt3=findViewById(R.id.opt3);
        opt4=findViewById(R.id.opt4);
        username=getIntent().getStringExtra("USERNAME");
        index=0;
        score=0;
        questions = getResources().getStringArray(R.array.questions);
    }

    private void newQuestionScreen()
    {
        question.setText(questions[index]);
        opt1.setText(options[index][0]);
        opt2.setText(options[index][1]);
        opt3.setText(options[index][2]);
        opt4.setText(options[index][3]);

        radioGroup.clearCheck();
        counter.setText((index+1)+"/10");
        prevBtn.setEnabled(index!=0);
    }

    private boolean checkAnswer()
    {
        int selectedOpt=radioGroup.getCheckedRadioButtonId();
        if(selectedOpt==-1)
        {
            return false;
        }
        int selectedIndex = -1;
        if (selectedOpt == R.id.opt1)
            selectedIndex = 0;
        else if (selectedOpt == R.id.opt2)
            selectedIndex = 1;
        else if (selectedOpt == R.id.opt3)
            selectedIndex = 2;
        else if (selectedOpt == R.id.opt4)
            selectedIndex = 3;

        return answers[index][selectedIndex] == 1;
    }

    public void updateScore()
    {
        if(checkAnswer())
        {
            score++;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.quiz_screen);
        init();
        newQuestionScreen();

        nextBtn.setOnClickListener(view -> {
            if (radioGroup.getCheckedRadioButtonId() == -1)
            {
                Toast.makeText(QuizScreen.this, "You need to select an answer!!", Toast.LENGTH_SHORT).show();
                return;
            }
            updateScore();

            //we check if we are not on the last question then we can load the next question
            if(index<10)
            {
                index++;
                newQuestionScreen();
            }
            //if we are at the last question then we go to the result screen
            else
            {
                Intent i = new Intent(QuizScreen.this, ResultScreen.class);
                i.putExtra("USERNAME", username);
                i.putExtra("SCORE", score);
                startActivity(i);
                finish();
            }
        });

        prevBtn.setOnClickListener(view -> {
            if (index > 0) {
                index--;
                newQuestionScreen();
            }
        });
    }
}