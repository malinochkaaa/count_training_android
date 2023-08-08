package com.alinakimova.count_training.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alinakimova.count_training.R;
import com.alinakimova.count_training.activities.GameActivity;
import com.alinakimova.count_training.db.Question;
import com.google.android.material.textfield.TextInputEditText;

public class HardFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (GameActivity) getActivity();
        View v = inflater.inflate(R.layout.fragment_hard, null);
        answer = v.findViewById(R.id.hard_ans);
        submit = v.findViewById(R.id.hard_submit_btn);
        submit.setOnClickListener(this);
        questionCount = v.findViewById(R.id.hard_question_number);
        questionCount.setText("Question " + (activity.currentQuestion + 1) + "/5");
        questionText = v.findViewById(R.id.hard_question_text);
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
    Button submit;
    EditText answer;
    TextView questionCount, questionText;

    public static HardFragment newInstance(Question question) {
        HardFragment myFragment = new HardFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARG_QUESTION, question);
        myFragment.setArguments(args);

        return myFragment;
    }

    public void onClick(View view) {
        if (answer.getText().toString().equals(question.right)) {
            activity.correct++;
            Toast.makeText(activity, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(activity, "Incorrect! The answer is " + question.right, Toast.LENGTH_SHORT).show();
        }
        activity.nextQuestion();
    }
}
