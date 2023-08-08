package com.alinakimova.count_training.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alinakimova.count_training.R;
import com.alinakimova.count_training.activities.GameActivity;
import com.alinakimova.count_training.db.Question;


public class EasyFragment extends Fragment implements View.OnClickListener {

    static final String ARG_QUESTION = "ARG_QUESTION";
    GameActivity activity;
    Question question;
    Button ans1;
    Button ans2;
    TextView questionCount;
    TextView questionText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = getArguments().getParcelable(ARG_QUESTION);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (GameActivity) getActivity();
        View v = inflater.inflate(R.layout.fragment_easy, null);
        ans1 = v.findViewById(R.id.easy_ans1);
        ans1.setOnClickListener(this);
        ans1.setText(question.ans1);
        ans2 = v.findViewById(R.id.easy_ans2);
        ans2.setOnClickListener(this);
        ans2.setText(question.ans2);
        questionCount = v.findViewById(R.id.easy_question_number);
        questionCount.setText("Question " + (activity.currentQuestion + 1) + "/5");
        questionText = v.findViewById(R.id.easy_question_text);
        questionText.setText(question.txt);
        return v;
    }

    public static EasyFragment newInstance(Question question) {
        EasyFragment myFragment = new EasyFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, question);
        myFragment.setArguments(args);

        return myFragment;
    }

    @Override
    public void onClick(View view) {
        if (((Button) view).getText().toString().equals(question.right)) {
            activity.correct++;
            Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Incorrect! The answer is " + question.right, Toast.LENGTH_SHORT).show();
        }
        activity.nextQuestion();
    }
}