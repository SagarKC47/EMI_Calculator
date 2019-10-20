package com.example.emi_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView loan, interest, no_of_month, result;
    private Button calculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loan = findViewById(R.id.loanamount);
        interest = findViewById(R.id.interestrate);
        no_of_month = findViewById(R.id.monthlyins);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEMI();
            }
        });
    }

    private void calculateEMI(){

        double loanamount = Double.parseDouble(loan.getText().toString());
        double rate = Double.parseDouble(interest.getText().toString());
        double month = Double.parseDouble(no_of_month.getText().toString());

        EMImodel emImodel = new EMImodel(loanamount,rate,month);

        double p = emImodel.getLoan();
        double r = emImodel.getInterest()/12/100;   //rate is calculated per month
        double n = emImodel.getNo_of_month();

        double resultemi = p*r*Math.pow(1+r,n)/(Math.pow(1+r,n)-1);
        String output = String.format("%.2f",resultemi);
        result.setText("RS: "+output);

    }
}
