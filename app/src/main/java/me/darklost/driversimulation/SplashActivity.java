package me.darklost.driversimulation;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;


/**

 *
 */
public class SplashActivity extends Activity {

    private static final String DTATA_TAG="DATA_TAG";

    private static final String FIRST_TAG="FIRST_TAG";
    private static final String TIME_TAG="TIME_TAG";
    private  boolean isExit=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        SharedPreferences mSharedPreferences = getSharedPreferences(DTATA_TAG,Activity.MODE_PRIVATE);
        boolean first=mSharedPreferences.getBoolean(FIRST_TAG,true);
        if(first){
            long time=System.currentTimeMillis();
            SharedPreferences.Editor editor=mSharedPreferences.edit();
            editor.putBoolean(FIRST_TAG,false);
            editor.putLong(TIME_TAG,time);
            editor.commit();
            Toast.makeText(this,"第一次试用",Toast.LENGTH_LONG).show();
        }else{
            long now=System.currentTimeMillis();
            long last=mSharedPreferences.getLong(TIME_TAG,now);
            if(now -last>=7*24*60*60){
                Toast.makeText(this,"超出试用期",Toast.LENGTH_LONG).show();
                isExit=true;

            }
            Toast.makeText(this,"处于试用期中",Toast.LENGTH_LONG).show();
        }


        AlphaAnimation myAnimation_Alpha;//定义动画
        myAnimation_Alpha=new AlphaAnimation(0.1f, 1.0f);//初始化动画对象 参数1 动画开始时候透明度 参数2 动画结束时候透明度
        myAnimation_Alpha.setDuration(3000);//设置时间持续时间为 5000毫秒
        myAnimation_Alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (isExit) {
                    SplashActivity.this.finish();
                    System.exit(0);
                }
                SplashActivity.this.startActivity(new Intent(SplashActivity.this
                        , MainActivity.class));
                SplashActivity.this.finish();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.splash).startAnimation(myAnimation_Alpha);
    }


}
