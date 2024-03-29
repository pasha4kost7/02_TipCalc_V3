package com.missouristate.betamen.a03_tipcalc_v13;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.missouristate.betamen.a03_tipcalc_v13.TipCalculator;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private TipCalculator tipCalc;
    public NumberFormat money = NumberFormat.getCurrencyInstance( );

    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        tipCalc = new TipCalculator( 0.17f, 100.0f );
        setContentView( R.layout.activity_main );
    }

    //Called when the user clicks on the Calculate button
    public void calculate( View v ) {
        Log.w( "MainActivity", "v = " + v );

        EditText billEditText = findViewById(R.id.editText_EnterBillAmount);
        EditText tipEditText = findViewById(R.id.editText_EnterTipAmount);

        // the billEditText is a Class so we need to convert it to the String.
        String billString = billEditText.getText( ).toString( );
        String tipString = tipEditText.getText( ).toString( );

        TextView tipTextView = findViewById(R.id.textView_TipTotal);
        TextView totalTextView = findViewById(R.id.textView_TotalAmount);

        try {
            //invert Strings into floats and int
            float billAmount = Float.parseFloat( billString );
            int tipPercent = Integer.parseInt( tipString );

            //pass floats to the TipCalculator class
            tipCalc.setBill( billAmount );
            tipCalc.setTip( .01f * tipPercent );

            // ask Model to calculate tip and total amounts
            float tip = tipCalc.tipAmount( );
            float total = tipCalc.totalAmount( );

            //update view on the screen
            tipTextView.setText( money.format( tip ) );
            totalTextView.setText( money.format( total ) );
        } catch( NumberFormatException nfe ) {

        }
    }
}