package com.example.ibrah.jukescantorandkimura;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    Intent intent_jukes_cantor,intent_kimura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent_jukes_cantor = new Intent(this,Jukes_Cantor.class);
        intent_kimura = new Intent(this,Kimura.class);
    }

    public void onMain_jukes_onclick(View view) {

        startActivity(intent_jukes_cantor);
    }

    public void onMain_kimura_onclick(View view) {

        startActivity(intent_kimura);
    }
}
