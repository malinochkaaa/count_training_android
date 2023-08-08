package com.alinakimova.count_training.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import java.util.*;

import com.alinakimova.count_training.App;
import com.alinakimova.count_training.R;
import com.alinakimova.count_training.db.Question;
import com.alinakimova.count_training.db.QuestionDAO;
import com.alinakimova.count_training.db.myDatabase;
import com.alinakimova.count_training.fragments.EasyFragment;
import com.alinakimova.count_training.fragments.HardFragment;
import com.alinakimova.count_training.fragments.MediumFragment;

import java.util.List;

public class GameActivity extends AppCompatActivity {

    public final static String MODE = "MODE";
    public final static String RESULT = "RESULT";

    FragmentTransaction transaction;
    myDatabase db;
    QuestionDAO questionDAO;

    public int currentQuestion;
    public int correct;
    List<Question> questions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        db = App.getInstance().getDatabase();
        questionDAO = db.question_dao();
        Intent intent = getIntent();
        int lvl = intent.getIntExtra(MODE, 1);
        loadQuestionsList(lvl);
        transaction = getSupportFragmentManager().beginTransaction();
        switch (questions.get(0).lvl) {
            case 1:
                transaction.add(R.id.fragment_container, EasyFragment.newInstance(questions.get(0)));
                break;
            case 2:
                transaction.add(R.id.fragment_container, MediumFragment.newInstance(questions.get(0)));
                break;
            case 3:
                transaction.add(R.id.fragment_container, HardFragment.newInstance(questions.get(0)));
                break;
        }
        transaction.commit();
        //newFragment(questions.get(currentQuestion));
    }

    public void nextQuestion() {
        currentQuestion++;
        try {
            Question nextQuestion = questions.get(currentQuestion);
            newFragment(nextQuestion);
        } catch (IndexOutOfBoundsException e) {
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra(RESULT, correct);
            startActivity(intent);
            finish();
        }
    }

    void loadQuestionsList(int lvl) {
        List<Question> list = questionDAO.getAllByLevel(lvl);
        Collections.shuffle(list);
        questions = list.subList(0, 5);
        currentQuestion = 0;
        correct = 0;
    }

    void newFragment(Question question) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (question.lvl) {
            case 1:
                transaction.replace(R.id.fragment_container, EasyFragment.newInstance(question));
                break;
            case 2:
                transaction.replace(R.id.fragment_container, MediumFragment.newInstance(question));
                break;
            case 3:
                transaction.replace(R.id.fragment_container, HardFragment.newInstance(question));
                break;
        }
        transaction.commit();
    }
}