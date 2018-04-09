package com.example.ibrah.jukescantorandkimura;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Kimura extends Activity {

    EditText rate_transversiyon,rate_transisyon;
    TextView error,result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kimura);

        rate_transversiyon = findViewById(R.id.onKimura_editText_tranversiyon_rate);
        rate_transisyon = findViewById(R.id.onKimura_editText_transisyon_rate);
        error = findViewById(R.id.onKimura_textView_error);
        result = findViewById(R.id.onKimura_textView_result);
    }


    @SuppressLint("DefaultLocale")
    public void onKimura_Hesapla_onclick(View view) {

        boolean is_rate_double = false;

        try {
            Double.parseDouble(String.valueOf(rate_transversiyon.getText()));
            Double.parseDouble(String.valueOf(rate_transisyon.getText()));
            is_rate_double = true;
        }catch (NumberFormatException ignored){}

        if(is_rate_double)
            if(Double.valueOf(String.valueOf(rate_transversiyon.getText())) +
                    Double.valueOf(String.valueOf(rate_transisyon.getText()))
                    > 1) {

                error.setText(R.string.kimura_out_of_range);
                result.setText(R.string.error);
            }
            else{

                double kimura_value = Kimura(Double.valueOf(String.valueOf(rate_transversiyon.getText())),
                        Double.valueOf(String.valueOf(rate_transisyon.getText())));

                if(Double.isNaN(kimura_value))
                    error.setText(R.string.kimura_calculate_error);
                else
                    error.setText(R.string.empity);

                result.setText(String.format("%.3f",kimura_value));
            }
    }

    private double Kimura(double rate_transversion, double rate_transisyon)
    {
        if(rate_transversion == 0 && rate_transisyon == 0)
            return 0;
        else
            return .5*Math.log(1.0/(1.0-2.0*rate_transisyon-rate_transversion))
                    + .25*Math.log(1.0/(1.0-2.0*rate_transversion));
    }

    //telefonu yan cevirince girilen veriler kaybolmasin diye verileri kayit ediyoruz.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("onKimura_rate_transversion", String.valueOf(rate_transversiyon.getText()));
        outState.putString("onKimura_rate_transisyon", String.valueOf(rate_transisyon.getText()));
        outState.putString("onKimura_error", String.valueOf(error.getText()));
        outState.putString("onKimura_kimura_rate", String.valueOf(result.getText()));
    }

    //telefonu yan cevirince saveInstanceState de yedekledigimiz verileri geri dolduruyoruz
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        rate_transversiyon.setText(String.valueOf(savedInstanceState.getString("onKimura_rate_transversion")));
        rate_transisyon.setText(String.valueOf(savedInstanceState.getString("onKimura_rate_transisyon")));
        error.setText(String.valueOf(savedInstanceState.getString("onKimura_error")));
        result.setText(String.valueOf(savedInstanceState.getString("onKimura_kimura_rate")));
    }
}
