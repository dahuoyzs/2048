package com.bigfire.game1.mygame1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class HzwActivity extends AppCompatActivity {
    private ImageView[] imageViews = new ImageView[25];
    Random random = new Random();
    int nums[][] = new int[5][5];
    private Button btn_restart;
    private TextView tv_score;
    private TextView tv_small;
    private Long score= 0l;
    private LinearLayout linemain_img;
    public Animation animation = null;
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hzw);

        initView();
        startgame();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(HzwActivity.this,
                        R.anim.anim_zhuan);
                btn_back.startAnimation(animation);
                Intent i = new Intent(HzwActivity.this,Main.class);
                startActivity(i);
                finish();
            }
        });
        btn_restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animation = AnimationUtils.loadAnimation(HzwActivity.this,
                        R.anim.anim_hat_scale);
                btn_restart.startAnimation(animation);

                startgame();
            }
        });
        linemain_img.setOnTouchListener(new View.OnTouchListener() {
            private float startX,startY,endX,endY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        startX=event.getX();
                        startY=event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX=event.getX()-startX;
                        endY=event.getY()-startY;
                        if (Math.abs(endX)>Math.abs(endY)){
                            if (endX<-5){
                                left();
                            }else if (endX>5){
                                right();
                            }
                        }else {
                            if (endY<-5){
                                up();
                            }else if (endY>5){
                                down();
                            }
                        }
                        break;
                }
                return true;
            }
        });
    }
    private void left(){
        boolean bool=false;
        for(int y=0;y<5;y++){
            for(int x=0;x<5;x++){
                for(int z=x+1;z<5;z++){
                    if (nums[z][y]>0){
                        if (nums[x][y]<=0){
                            nums[x][y]=nums[z][y];
                            nums[z][y]=(0);
                            x--;
                            bool=true;
                        }else if (nums[x][y]==nums[z][y]){
                            nums[x][y]=(nums[x][y]*2);
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_suofang);
                            imageViews[y*5+x].startAnimation(animation);
                            nums[z][y]=(0);
                            score+=nums[x][y];
                            tv_score.setText(score+"");
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_score);
                            tv_score.startAnimation(animation);
                            bool=true;
                        }
                        break;
                    }
                }
            }
        }
        if (bool)
        {
            addRandNum();
            refresh();
        }else {
            endgame();
        }
        refresh();

    }
    private void right(){
        boolean bool=false;
        for(int y=0;y<5;y++){
            for(int x=4;x>=0;x--){
                for(int z=x-1;z>=0;z--){
                    if (nums[z][y]>0){
                        if (nums[x][y]<=0){
                            nums[x][y]=nums[z][y];
                            nums[z][y]=(0);
                            x++;
                            bool=true;
                        }else if (nums[x][y]==nums[z][y]){
                            nums[x][y]=nums[x][y]*2;
                            nums[z][y]=0;
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_suofang);
                            imageViews[y*5+x].startAnimation(animation);
                            score+=nums[x][y];
                            tv_score.setText(score+"");
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_score);
                            tv_score.startAnimation(animation);
                            bool=true;
                        }
                        break;
                    }
                }
            }
        }
        if (bool)
        {
            addRandNum();
        }else {
            endgame();
        }
        refresh();
    }
    private void up(){
        boolean bool=false;
        for(int x=0;x<5;x++){
            for(int y=0;y<5;y++){
                for(int z=y+1;z<5;z++){

                    if (nums[x][z]>0){

                        if (nums[x][y]<=0){
                            nums[x][y]=(nums[x][z]);
                            nums[x][z]=(0);
                            y--;
                            bool=true;
                        }else if (nums[x][y]==nums[x][z]){
                            nums[x][y]=nums[x][y]*2;
                            nums[x][z]=(0);
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_suofang);
                            imageViews[y*5+x].startAnimation(animation);
                            score+=nums[x][y];
                            tv_score.setText(score+"");
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_score);
                            tv_score.startAnimation(animation);
                            bool=true;
                        }
                        break;
                    }

                }
            }
        }
        if (bool)
        {
            addRandNum();

        }else {
            endgame();
        }
        refresh();
    }
    private void down(){
        boolean bool=false;
        for (int x = 0; x < 5; x++) {
            for (int y = 4; y >= 0; y--) {
                for (int z = y - 1; z >= 0; z--) {

                    if (nums[x][z] > 0) {

                        if (nums[x][y] <= 0) {
                            nums[x][y]=nums[x][z];
                            nums[x][z]=0;
                            y++;
                            bool=true;
                        } else if (nums[x][y]==nums[x][z]) {
                            nums[x][y]=nums[x][y] * 2;
                            nums[x][z]=0;
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_suofang);
                            imageViews[y*5+x].startAnimation(animation);
                            score+=nums[x][y];
                            tv_score.setText(score + "");
                            animation = AnimationUtils.loadAnimation(this,
                                    R.anim.anim_score);
                            tv_score.startAnimation(animation);
                            bool=true;
                        }
                        break;
                    }
                }
            }
        }
        if (bool)
        {
            addRandNum();
        }else {
            endgame();
        }
        refresh();
    }
    private void initView()
    {
        linemain_img= (LinearLayout) findViewById(R.id.linemain_img);
        btn_restart= (Button) findViewById(R.id.btn_restart);
        btn_back= (Button) findViewById(R.id.btn_back);
        tv_score= (TextView) findViewById(R.id.hzscore);
        tv_small= (TextView) findViewById(R.id.tv_small);
        imageViews[0]= (ImageView) findViewById(R.id.img1);
        imageViews[1]= (ImageView) findViewById(R.id.img2);
        imageViews[2]= (ImageView) findViewById(R.id.img3);
        imageViews[3]= (ImageView) findViewById(R.id.img4);
        imageViews[4]= (ImageView) findViewById(R.id.img5);
        imageViews[5]= (ImageView) findViewById(R.id.img6);
        imageViews[6]= (ImageView) findViewById(R.id.img7);
        imageViews[7]= (ImageView) findViewById(R.id.img8);
        imageViews[8]= (ImageView) findViewById(R.id.img9);
        imageViews[9]= (ImageView) findViewById(R.id.img10);
        imageViews[10]= (ImageView) findViewById(R.id.img11);
        imageViews[11]= (ImageView) findViewById(R.id.img12);
        imageViews[12]= (ImageView) findViewById(R.id.img13);
        imageViews[13]= (ImageView) findViewById(R.id.img14);
        imageViews[14]= (ImageView) findViewById(R.id.img15);
        imageViews[15]= (ImageView) findViewById(R.id.img16);
        imageViews[16]= (ImageView) findViewById(R.id.img17);
        imageViews[17]= (ImageView) findViewById(R.id.img18);
        imageViews[18]= (ImageView) findViewById(R.id.img19);
        imageViews[19]= (ImageView) findViewById(R.id.img20);
        imageViews[20]= (ImageView) findViewById(R.id.img21);
        imageViews[21]= (ImageView) findViewById(R.id.img22);
        imageViews[22]= (ImageView) findViewById(R.id.img23);
        imageViews[23]= (ImageView) findViewById(R.id.img24);
        imageViews[24]= (ImageView) findViewById(R.id.img25);

    }

    public void startgame(){
        score=0l;
        for (int y=0;y<nums.length;y++){
            for (int x=0;x<nums.length;x++){
                nums[x][y]=0;
            }
        }
        if(random.nextInt(100)>1){
            addRandNum();
            addRandNum();
        }else {
            nums=new int[][]{{2,4,2,4,2},{4,2,4,2,4},{2,4,2,4,2},{4,2,4,2,4},{2,4,2,4,2}};
        }

//        nums=new int[][]{{4,2,8,4},{4,16,64,16},{16,8,32,8},{2,4,8,2}};
//        nums=new int[][]{{2,32,8,2},{4,2,64,4},{2,8,32,16},{4,32,16,4}};
//        nums=new int[][]{{2,4,2,4,2},{4,2,4,2,4},{2,4,2,4,2},{4,2,4,2,4},{2,4,2,4,2}};
        refresh();
    }

    public void addRandNum()
    {

        boolean b=hasZero();
        if (b){
            while(true){
                int y =random.nextInt(5);
                int x =random.nextInt(5);
                if (nums[x][y]==0)
                {
                    nums[x][y]= random.nextInt(100)>3 ? 2 : 4;
//                    imageViews[x*4+y]
                    animation = AnimationUtils.loadAnimation(this,
                            R.anim.anim_suofang);
                    imageViews[y*5+x].startAnimation(animation);
                    break;
                }
            }
        }else {
            boolean bool=isequally();
            if(!isequally()){
                HzwDialog myDiglog= new HzwDialog(this,HzwActivity.this,score+"");
                myDiglog.show();
            }
//                Toast.makeText(getApplicationContext(),"结束",Toast.LENGTH_SHORT).show();
        }
    }

    private void endgame()
    {
        boolean b=hasZero();
        if (b){
            while(true){
                int y =random.nextInt(5);
                int x =random.nextInt(5);
                if (nums[x][y]==0)
                {
                    nums[x][y]= random.nextInt(100)>3 ? 2 : 4;
                    break;
                }
            }
        }else {
            if(!isequally()){
                HzwDialog myDiglog= new HzwDialog(this,HzwActivity.this,score+"");
                myDiglog.show();
            }
//                Toast.makeText(getApplicationContext(),"结束",Toast.LENGTH_SHORT).show();
            refresh();
        }
    }

    private boolean hasZero(){
        for (int y=0;y<nums.length;y++){
            for (int x=0;x<nums.length;x++){
                if(nums[x][y]==0)
                    return true;
            }
        }
        return false;
    }
    private boolean isequally() {
        for (int y=0;y<nums.length;y++){
            for (int x=0;x<nums.length;x++) {
                if (x==0){
                    if (nums[x][y]==nums[x+1][y])
                        return true;
                }else if (x==4){
                    if (nums[x][y]==nums[x-1][y])
                        return true;
                }
                if (y==0){
                    if (nums[x][y]==nums[x][y+1])
                        return true;
                }else if (y==4){
                    if (nums[x][y]==nums[x][y-1])
                        return true;
                }
                if (x!=0&&y!=0&&x!=4&&y!=4){
                    if (nums[x][y]==nums[x][y+1])
                        return true;
                    if(nums[x][y]==nums[x+1][y])
                        return true;
                    if (nums[x][y]==nums[x][y-1])
                        return true;
                    if(nums[x][y]==nums[x-1][y])
                        return true;
                }

            }
        }
        return false;
    }

    private void refresh() {
        String s="";
        for (int y=0;y<nums.length;y++){
            for (int x=0;x<nums.length;x++){
                setImg(x,y,nums[x][y]);
                s=s+nums[x][y]+" ";
            }
            s+="\n";
        }
        tv_score.setText(score+"");
        tv_small.setText(s);
    }
    private void setImg(int y, int x, int i) {
        switch (i){
            case 0:
                imageViews[x*5+y].setImageResource(R.mipmap.block);
                break;
            case 2:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwba);
                break;
            case 4:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbb);
                break;
            case 8:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbc);
                break;
            case 16:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbd);
                break;
            case 32:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbe);
                break;
            case 64:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbf);
                break;
            case 128:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbg);
                break;
            case 256:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbh);
                break;
            case 512:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbi);
                break;
            case 1024:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbj);
                break;
            case 2048:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbk);
                break;
            case 4096:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbl);
                break;
            case 8192:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbm);
                break;
            case 16384:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbn);
                break;
            case 32768:
                imageViews[x*5+y].setImageResource(R.mipmap.hzwbo);
                break;
        }
    }
}
