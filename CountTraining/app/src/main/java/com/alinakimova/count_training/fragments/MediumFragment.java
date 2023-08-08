package com.alinakimova.count_training.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alinakimova.count_training.R;
import com.alinakimova.count_training.activities.GameActivity;
import com.alinakimova.count_training.db.Question;

public class MediumFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (GameActivity) getActivity();
        View v = inflater.inflate(R.layout.fragment_medium, null);
        ans1 = v.findViewById(R.id.medium_ans1);
        ans1.setOnClickListener(this);
        ans1.setText(question.ans1);
        ans2 = v.findViewById(R.id.medium_ans2);
        ans2.setOnClickListener(this);
        ans2.setText(question.ans2);
        ans3 = v.findViewById(R.id.medium_ans3);
        ans3.setOnClickListener(this);
        ans3.setText(question.ans3);
        ans4 = v.findViewById(R.id.medium_ans4);
        ans4.setOnClickListener(this);
        ans4.setText(question.ans4);
        questionCount = v.findViewById(R.id.medium_question_number);
        questionCount.setText("Question " + (activity.currentQuestion + 1) + "/5");
        questionText = v.findViewById(R.id.medium_question_text);
        questionText.setText(question.txt);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        question = getArguments().getParcelable(ARG_QUESTION);
    }

    static final String ARG_QUESTION = "ARG_QUESTION";
    GameActivity activity;
    Question question;
    Button ans1, ans2, ans3, ans4;
    TextView questionCount, questionText;

    public static MediumFragment newInstance(Question question) {
        MediumFragment myFragment = new MediumFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, question);
        myFragment.setArguments(args);

        return myFragment;
    }

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
