package com.bignerdranch.android.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;
    private Question[] mQuestionBank = new Question[] {
                new Question(R.string.q1, false),
                new Question(R.string.q2, true),
                new Question(R.string.q3, true),
                new Question(R.string.q4, false),
                new Question(R.string.q5, true),
                new Question(R.string.q6, false),
                new Question(R.string.q7, false),
                new Question(R.string.q8, true),
                new Question(R.string.q9, false),
                new Question(R.string.q10, false),
    };
    private int mCurrentIndex = -1;
    int question;

    private void updateQuestion(int n) {
        mCurrentIndex = (n == 1) ? (mCurrentIndex + 1) : (mCurrentIndex - 1);

        if (mCurrentIndex == mQuestionBank.length)
            mCurrentIndex = 0;

        if (mCurrentIndex == -1)
            mCurrentIndex = mQuestionBank.length - 1;

        question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    };

    private void checkAnswer(boolean userPressedTrue) {
        int messageResId;
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if ((answerIsTrue && userPressedTrue) || (!answerIsTrue && !userPressedTrue)) {
            messageResId = R.string.correct_toast;
        }
        else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        updateQuestion(1);

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                updateQuestion(1);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                updateQuestion(0);
            }
        });

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuestion(1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
