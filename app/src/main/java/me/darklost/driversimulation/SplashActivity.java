package me.darklost.driversimulation;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import me.darklost.driversimulation.utils.ProtectUtils;
import me.drakeet.materialdialog.MaterialDialog;


/**

 *
 */
public class SplashActivity extends Activity {

    private static final String DTATA_TAG="DATA_TAG";

    private static final String DEVICE_ID="DEVICE_ID_BASE64";
    private  boolean isExit=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        SharedPreferences mSharedPreferences = getSharedPreferences(DTATA_TAG, Activity.MODE_PRIVATE);
        String save=mSharedPreferences.getString(DEVICE_ID, "");
        String deviceId= ProtectUtils.getOblyDevicesID(this);
        System.out.println("DK DeviceID=" + deviceId);
        System.out.println("DK save=" + save);
        if(!deviceId.equals(save)){
                isExit=true;
        }
        System.out.println("DK Base64="+isExit);
        AlphaAnimation myAnimation_Alpha;//定义动画
        myAnimation_Alpha=new AlphaAnimation(0.1f, 1.0f);//初始化动画对象 参数1 动画开始时候透明度 参数2 动画结束时候透明度
        myAnimation_Alpha.setDuration(3000);//设置时间持续时间为 5000毫秒
        myAnimation_Alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                System.out.println("DK onAnimationEnd=" + isExit);
                if (isExit) {

                    final MaterialDialog dialog = new MaterialDialog(SplashActivity.this);
                    dialog.setTitle(R.string.register);
                    View view = View.inflate(SplashActivity.this, R.layout.dialog_register, null);
                    dialog.setContentView(view);
                    TextView tv = (TextView) view.findViewById(R.id.register_tv);
                    tv.setText(new String(Base64.encode(ProtectUtils.getOblyDevicesID(SplashActivity.this).getBytes(), Base64.DEFAULT)));
                    final EditText et = (EditText) view.findViewById(R.id.register_et);
                    Button bt = (Button) view.findViewById(R.id.register_bt);
                    bt.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {
                            try {
                                String str = et.getText().toString();
                                String out = new String(Base64.decode(str, Base64.DEFAULT));
                                if (ProtectUtils.getOblyDevicesID(SplashActivity.this).equals(str)) {
                                    System.out.println("DK mboolean=" + isExit);
                                    SharedPreferences mSharedPreferences = getSharedPreferences(DTATA_TAG, Activity.MODE_PRIVATE);
                                    mSharedPreferences.edit().putString(DEVICE_ID, str).commit();
                                    SplashActivity.this.startActivity(new Intent(SplashActivity.this
                                            , MainActivity.class));
                                    SplashActivity.this.finish();
                                } else {
                                    isExit = true;
                                    SplashActivity.this.finish();
                                    System.exit(0);
                                }
                            } catch (Exception ex){
                                ex.printStackTrace();
                            }


                        }
                    });
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (isExit) {

                                SplashActivity.this.finish();
                                System.exit(0);
                            }
                        }
                    });
                    dialog.show();

                } else {


                    SplashActivity.this.startActivity(new Intent(SplashActivity.this
                            , MainActivity.class));
                    SplashActivity.this.finish();
                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        findViewById(R.id.splash).startAnimation(myAnimation_Alpha);
    }


}
