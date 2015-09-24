package com.bignerdranch.android.geoquiz;

/**
 * Implements a Question data type.
 *
 * @author Ilya Ilyankou <ilya.ilyankou@trincoll.edu>
 * @version 1.0, 09/19/2015
 */
public class Question {
    private int mTextResId;
    private boolean mAnswerTrue;

    /**
     * Constructs a Question
     * @param textResId reference to a string with the question text
     * @param answerTrue true if correct answer is true, false otherwise
     */
    public Question(int textResId, boolean answerTrue) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    /**
     * Returns the reference to a question text
     * @return question text
     */
    public int getTextResId() {
        return mTextResId;
    }

    /**
     * Returns the answer to the question
     * @return true if correct answer is true, false otherwise
     */
    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    /**
     * Sets the question text
     * @param textResId reference to a string with question text
     */
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    /**
     * Sets the correct answer
     * @param answerTrue correct answer to the question
     */
    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
