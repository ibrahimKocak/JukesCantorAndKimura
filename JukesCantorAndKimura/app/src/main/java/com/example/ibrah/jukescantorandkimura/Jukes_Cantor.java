package com.example.ibrah.jukescantorandkimura;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Jukes_Cantor extends Activity {

    EditText rate;
    TextView error,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jukes__cantor);

        rate = findViewById(R.id.onJukes_editText_rate);
        error = findViewById(R.id.onJukes_textView_error);
        result = findViewById(R.id.onJukes_textView_result);
    }


    @SuppressLint("DefaultLocale")
    public void onJukes_Hesapla_onclick(View view) {

        boolean is_rate_double = false;
        try {
            Double.parseDouble(String.valueOf(rate.getText()));
            is_rate_double = true;
        }catch (NumberFormatException ignored){}

        if(is_rate_double)
            if(Double.valueOf(String.valueOf(rate.getText())) >= .75) {

                error.setText(R.string.jukes_out_of_range);
                result.setText(R.string.error);
            }
            else{

                error.setText(R.string.empity);
                result.setText(String.format("%.3f",Jukes_Cantor(Double.valueOf(String.valueOf(rate.getText())))));
            }
    }

    private double Jukes_Cantor(double rate)
    {
        if(rate == 0)
            return 0;
        else
            return -.75*Math.log(1-((4.0/3.0)*rate));
    }

    //telefonu yan cevirince girilen veriler kaybolmasin diye verileri kayit ediyoruz.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("onJukes_rate", String.valueOf(rate.getText()));
        outState.putString("onJukes_error", String.valueOf(error.getText()));
        outState.putString("onJukes_jukes_rate", String.valueOf(result.getText()));
    }

    //telefonu yan cevirince saveInstanceState de yedekledigimiz verileri geri dolduruyoruz
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        rate.setText(String.valueOf(savedInstanceState.getString("onJukes_rate")));
        error.setText(String.valueOf(savedInstanceState.getString("onJukes_error")));
        result.setText(String.valueOf(savedInstanceState.getString("onJukes_jukes_rate")));
    }
}
