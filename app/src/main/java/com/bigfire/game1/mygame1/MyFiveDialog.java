package com.bigfire.game1.mygame1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dahuo on 2017/10/21.
 */

public class MyFiveDialog extends Dialog {
    private String content;
    private Button btn_restart;
    private TextView tv_score;
    private FiveActivity fiveActivity ;
    public Animation animation = null;

    public MyFiveDialog(Context context, FiveActivity fiveActivity, String content) {
        super(context);
        this.fiveActivity=fiveActivity;
        this.content=content;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.diglog_layout);
        initView();
        tv_score.setText("得分："+content);
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(getContext(),
                        R.anim.anim_btn_start);
                btn_restart.startAnimation(animation);
                fiveActivity.startgame();
                dismiss();
            }
        });
    }

    private void initView() {
        btn_restart= (Button) findViewById(R.id.btn_restart);
        tv_score= (TextView) findViewById(R.id.score);
    }
}
