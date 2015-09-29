package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    public static final String KEY_INDEX = "index";
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final int REQUEST_CODE_CHEAT = 0;
    private boolean mIsCheater;

    private boolean[] cheated = new boolean[10];

    private Button mTrueButton;
    private Button mCheatButton;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (resultCode != REQUEST_CODE_CHEAT) {
            if (data==null) {
                return;
            }

            mIsCheater = CheatActivity.wasAnswerShown(data);
            if (mIsCheater) {
                cheated[mCurrentIndex] = true;
            }
        }

    }


    private void updateQuestion(int n) {
        mCurrentIndex = (n == 1) ? (mCurrentIndex + 1) : (mCurrentIndex - 1);

        if (mCurrentIndex == mQuestionBank.length)
            mCurrentIndex = 0;

        if (mCurrentIndex == -1)
            mCurrentIndex = mQuestionBank.length - 1;

        question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }


    private void checkAnswer(boolean userPressedTrue) {
        int messageResId;
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        if (mIsCheater || cheated[mCurrentIndex]) {
            messageResId = R.string.judgement_toast;
        }
        else {

            if ((answerIsTrue && userPressedTrue) || (!answerIsTrue && !userPressedTrue)) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate(Bundle) called");

        setContentView(R.layout.activity_quiz);

        mTrueButton = (Button) findViewById(R.id.true_button);
        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            mIsCheater = savedInstanceState.getBoolean("mIsCheater", false);
            cheated = savedInstanceState.getBooleanArray("cheated");
        }

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });

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
                mIsCheater = false;
                updateQuestion(1);
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                mIsCheater = false;
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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex-1);
        savedInstanceState.putBoolean("mIsCheater", mIsCheater);
        savedInstanceState.putBooleanArray("cheated", cheated);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
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
