package com.itexico.instrumentationtesting.androidjunitrunner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itexico.instrumentationtesting.R;

import java.util.List;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PARCELABLE_KEY = CalculatorActivity.class.getSimpleName();
    private static final String TAG = PARCELABLE_KEY;
    private Calculator mCalculator;
    private TextView mResultTextView;
    private EditText mOperandOneEditText;
    private EditText mOperandTwoEditText;
    private OperandsParcelable mOperandsParcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        mCalculator = new Calculator();
        mResultTextView = (TextView) findViewById(R.id.operation_result_text_view);
        mOperandOneEditText = (EditText) findViewById(R.id.operand_one_edit_text);
        mOperandTwoEditText = (EditText) findViewById(R.id.operand_two_edit_text);

        final Button additionButton = (Button) findViewById(R.id.operation_add_btn);
        final Button substractionButton = (Button) findViewById(R.id.operation_sub_btn);
        final Button multiplyButton = (Button) findViewById(R.id.operation_mul_btn);
        final Button divisionButton = (Button) findViewById(R.id.operation_div_btn);

        additionButton.setOnClickListener(this);
        substractionButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divisionButton.setOnClickListener(this);

        mOperandsParcelable = new OperandsParcelable();
        if (null != savedInstanceState) {
            Log.i(TAG, "onCreate(), null != savedInstanceState");
            // We've got a past state, apply it to the UI.
            mOperandsParcelable = savedInstanceState.getParcelable(PARCELABLE_KEY);
            List<Double> data = mOperandsParcelable.getOperands();
            double[] operands = new double[data.size()];
            mOperandOneEditText.setText(String.valueOf(operands[0]));
            mOperandOneEditText.setText(String.valueOf(operands[1]));
        }
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(PARCELABLE_KEY, mOperandsParcelable);
        Log.i(TAG, "onSaveInstanceState()");
    }

    @Override
    public void onClick(View view) {
        String operand1 = mOperandOneEditText.getText().toString();
        String operand2 = mOperandTwoEditText.getText().toString();
        if (operand1.isEmpty() || operand2.isEmpty()) {
            Toast.makeText(this, "Operand can't be empty!!", Toast.LENGTH_SHORT).show();
        }
        final double operand1Val = Double.valueOf(operand1);
        final double operand2Val = Double.valueOf(operand2);
        double result = 0;

        switch (view.getId()) {
            case R.id.operation_add_btn:
                result = mCalculator.addition(operand1Val, operand2Val);
                break;
            case R.id.operation_sub_btn:
                result = mCalculator.subtraction(operand1Val, operand2Val);
                break;
            case R.id.operation_mul_btn:
                result = mCalculator.multiply(operand1Val, operand2Val);
                break;
            case R.id.operation_div_btn:
                result = mCalculator.division(operand1Val, operand2Val);
                break;
        }

        mResultTextView.setText(String.valueOf(result));
    }
}
