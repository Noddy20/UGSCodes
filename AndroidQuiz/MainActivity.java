package com.example.android.androidquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;    // for postdelay

public class MainActivity extends AppCompatActivity {

    int questNum = 1;
    int[] correctAns = {4, 1, 2, 1, 4, 3, 2, 2, 3, 4};   // array of correct answers
    int marksGot = 0;
    RadioGroup optionsRadio;        // global declaration of radio group and submit btn
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * startQuiz will execute on Start and Restart btn
     */
    public void startQuiz(View view) {
        marksGot = 0;
        questNum = 1;
             // set progress bar progress 0
        ProgressBar progressVal = (ProgressBar) findViewById(R.id.progress_bar);
        progressVal.setProgress(0);
            // set 1 Q text string on question view and set it visible
        TextView quesView = (TextView) findViewById(R.id.questionView);
        quesView.setText(getResources().getString(R.string.ques_1));
        quesView.setVisibility(View.VISIBLE);
            // set score view invisible, used when restarting the test
        TextView scoreView = (TextView) findViewById(R.id.yourScore);
        scoreView.setVisibility(View.GONE);
            // set radiogroup visible and clear default checks
        RadioGroup opRadio = (RadioGroup) findViewById(R.id.radio_grp);
        opRadio.setVisibility(View.VISIBLE);
        opRadio.clearCheck();
            // set text strings of choices for Q 1 on radio btns
        RadioButton startRad1 = (RadioButton) findViewById(R.id.radio_1);
        RadioButton startRad2 = (RadioButton) findViewById(R.id.radio_2);
        RadioButton startRad3 = (RadioButton) findViewById(R.id.radio_3);
        RadioButton startRad4 = (RadioButton) findViewById(R.id.radio_4);
        startRad1.setText(R.string.opt_1_1);
        startRad2.setText(R.string.opt_1_2);
        startRad3.setText(R.string.opt_1_3);
        startRad4.setText(R.string.opt_1_4);
            // set Start btn invisible
        Button startBtn = (Button) findViewById(R.id.play_btn);
        startBtn.setText(getResources().getString(R.string.button_start));
        startBtn.setVisibility(View.GONE);
           // set submit btn visible
        Button subBtn = (Button) findViewById(R.id.submit_btn);
        subBtn.setVisibility(View.VISIBLE);
           // set progress text and bar visible
        TextView progText = (TextView) findViewById(R.id.progress_text);
        ProgressBar progShow = (ProgressBar) findViewById(R.id.progress_bar);
        progText.setVisibility(View.VISIBLE);
        progShow.setVisibility(View.VISIBLE);
           // set Image view invisible
        ImageView startImg = (ImageView) findViewById(R.id.resultExpr);
        startImg.setVisibility(View.GONE);
           // set an onClickListener on submit btn
        addListenerOnButton();
    }
    /**
     * addListenerOnButton will execute on pressing Submit Btn
     */
    public void addListenerOnButton() {
           // objects for radio group and submit btn
        optionsRadio = (RadioGroup) findViewById(R.id.radio_grp);
        submitBtn = (Button) findViewById(R.id.submit_btn);
           // onClickListener 
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   // check if no radio btn is checked
                if (optionsRadio.getCheckedRadioButtonId() == -1)
                    Toast.makeText(MainActivity.this, "Select a Answer!!", Toast.LENGTH_SHORT).show();
                else {
                    int selectedId = optionsRadio.getCheckedRadioButtonId();
                       // get checked radio btn
                    RadioButton selectedOp = (RadioButton) findViewById(selectedId);
                    String selectedAns = selectedOp.getText().toString();
                       // get correct answer
                    String currentString = "opt_" + questNum + "_" + correctAns[questNum - 1];
                    String correctAns = getStringFromResourceName(currentString);
                       // if selected ans and correct ans are same increase marks
                    if (selectedAns.equals(correctAns)) marksGot++;
                    Toast.makeText(MainActivity.this, "Answer Submitted.", Toast.LENGTH_SHORT).show();
                    questNum++;             // set next question number
                    if (questNum <= 10) {              // if question number is small or eq to 10 then execute changeQuestion 
                        /**
                         * This handler is used to execute changeQuestion after a delay
                         */
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //Do something after 100ms
                                changeQuestion();
                            }
                        }, 500);

                    } else endQuiz();              // if quesNum is more than 10 then execute endQuiz
                }
            }
        });
    }
    /**
     * getStringFromResourceName will return string from string resource by it's name
     */
    private String getStringFromResourceName(String resourceName) {
        String packageName = getPackageName();  // get package name 
           // get resource id by resource name and package name 
        int resourceId = getResources().getIdentifier(resourceName, "string", packageName);
           // return string obtained by resource id 
        return getString(resourceId);
    }
    /**
     * changeQuestion will change the question view and radio btn options text on execution
     */
    public void changeQuestion() {
           // set radio btn clear
        TextView nextQes = (TextView) findViewById(R.id.questionView);
        RadioGroup radioClr = (RadioGroup) findViewById(R.id.radio_grp);
        radioClr.clearCheck();
           // set question and options string of next questions  
        RadioButton radioOne = (RadioButton) findViewById(R.id.radio_1);
        RadioButton radioTwo = (RadioButton) findViewById(R.id.radio_2);
        RadioButton radioThree = (RadioButton) findViewById(R.id.radio_3);
        RadioButton radioFour = (RadioButton) findViewById(R.id.radio_4);
        ProgressBar progressVal = (ProgressBar) findViewById(R.id.progress_bar);
        String quesResId = "ques_" + questNum;
        String optOne = "opt_" + questNum + "_1";
        String optTwo = "opt_" + questNum + "_2";
        String optThree = "opt_" + questNum + "_3";
        String optFour = "opt_" + questNum + "_4";
        nextQes.setText(getStringFromResourceName(quesResId));
        radioOne.setText(getStringFromResourceName(optOne));
        radioTwo.setText(getStringFromResourceName(optTwo));
        radioThree.setText(getStringFromResourceName(optThree));
        radioFour.setText(getStringFromResourceName(optFour));
           // increase progress bar value by question number 
        progressVal.setProgress(questNum);
        
        addListenerOnButton();
    }
    /**
     * endQuiz will show the final result with a emoji expression depending on the marks got
     */
    public void endQuiz() {
           // set question view invisible
        TextView quesView = (TextView) findViewById(R.id.questionView);
        quesView.setVisibility(View.GONE);
           // set score view visible and show the result 
        TextView scoreView = (TextView) findViewById(R.id.yourScore);
        String finalMarks = "You Got " + marksGot + "/10";
        scoreView.setText(finalMarks);
        scoreView.setVisibility(View.VISIBLE);
           // set radio group invisible
        RadioGroup optRadio = (RadioGroup) findViewById(R.id.radio_grp);
        optRadio.setVisibility(View.GONE);
           // set result emoji image expressions according to marks got
        ImageView resultImg = (ImageView) findViewById(R.id.resultExpr);
        resultImg.setVisibility(View.VISIBLE);
        if (marksGot < 5)
            resultImg.setImageResource(R.drawable.bad);
        else if (marksGot > 8)
            resultImg.setImageResource(R.drawable.good);
        else
            resultImg.setImageResource(R.drawable.avg);
           // set Start btn visible with set text Restart
        Button rePlayBtn = (Button) findViewById(R.id.play_btn);
        rePlayBtn.setText(getResources().getString(R.string.button_restart));
        rePlayBtn.setVisibility(View.VISIBLE);
           // set submit btn invisible
        Button subBtn = (Button) findViewById(R.id.submit_btn);
        subBtn.setVisibility(View.GONE);
    }

}
