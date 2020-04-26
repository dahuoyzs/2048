package com.bigfire.game1.mygame1;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Main extends AppCompatActivity {
    private Button btn_four;
    private Button btn_five;
    private Button btn_wite;
    private ImageView img_hz;
    private ObjectAnimator animator;
    private Animation animation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initView();

    }

    private void initView() {
        btn_four= (Button) findViewById(R.id.btn_four);
        btn_five= (Button) findViewById(R.id.btn_five);
        btn_wite= (Button) findViewById(R.id.btn_wite);
        img_hz= (ImageView) findViewById(R.id.img_hz);

    }

    public void four(View view){
        animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_file);
        btn_four.startAnimation(animation);
        Intent i = new Intent(Main.this,MainActivity.class);
        startActivity(i);
    }
    public void five(View view){
        animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_file);
        btn_five.startAnimation(animation);
        Intent i = new Intent(Main.this,FiveActivity.class);
        startActivity(i);
    }
    public void hz(View view){
        animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_file);
        img_hz.startAnimation(animation);
        Intent i = new Intent(Main.this,HzwActivity.class);
        startActivity(i);
    }
    public void wite(View view){
        animation = AnimationUtils.loadAnimation(this,
                R.anim.anim_file);
        btn_wite.startAnimation(animation);
//        Intent i = new Intent(Main.this,MainActivity.class);
//        startActivity(i);
    }
}
