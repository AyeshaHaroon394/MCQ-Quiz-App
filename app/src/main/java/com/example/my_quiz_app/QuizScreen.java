package com.example.my_quiz_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuizScreen extends AppCompatActivity {

    private String[] questions = {
            "In Harry Potter and the Philosopher's Stone, what object does Harry first catch in his mouth during a Quidditch match?",
            "What is the name of the Weasley family’s home?",
            "What is the name of the house-elf who serves the Malfoy family?",
            "Which of these spells is used to disarm an opponent?",
            "What type of dragon does Harry face in the Triwizard Tournament?",
            "Who is the ghost of Ravenclaw house?",
            "What is the name of the train that takes students to Hogwarts?",
            "What does Dumbledore leave to Hermione in his will?",
            "Which magical creature pulls the carriages at Hogwarts?",
            "What does the Marauder’s Map reveal?"
    };

    private String[][] options = {
            {"Remembrall", "Snitch", "Bludger", "Quaffle"},
            {"The Nest", "The Burrow", "Ottery St. Catchpole", "Hogsmeade Cottage"},
            {"Dobby", "Winky", "Kreacher", "Hokey"},
            {"Expelliarmus", "Avada Kedavra", "Alohomora", "Crucio"},
            {"Hungarian Horntail", "Norwegian Ridgeback", "Swedish Short-Snout", "Common Welsh Green"},
            {"The Grey Lady", "The Bloody Baron", "Nearly Headless Nick", "The Fat Friar"},
            {"Knight Bus", "Hogwarts Express", "The Lightning Train", "Wizarding Locomotive"},
            {"A wand", "The Tales of Beedle the Bard", "A Deluminator", "A Snitch"},
            {"Hippogriffs", "Thestrals", "Dragons", "Basilisks"},
            {"Hidden messages", "People’s thoughts", "Secret passages", "The location of everyone in Hogwarts"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.quiz_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}