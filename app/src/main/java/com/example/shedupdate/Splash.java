package com.example.shedupdate;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    ImageView railwayshedlogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        railwayshedlogo=findViewById(R.id.railwayshedlogo);
        Animation myanim= AnimationUtils.loadAnimation(Splash.this,R.anim.mytransition);
        railwayshedlogo.startAnimation(myanim);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Splash.this,Login.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}
