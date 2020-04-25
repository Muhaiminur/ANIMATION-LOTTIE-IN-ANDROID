package com.abir.lottie_animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.abir.lottie_animation.databinding.ActivitySecondBinding;

import java.util.HashMap;

public class Dialouge_Activity extends AppCompatActivity {

    ActivitySecondBinding activity_second;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        activity_second = DataBindingUtil.setContentView(this, R.layout.activity_second);
        activity_second.showDialouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog();
                progressDialog.show(getSupportFragmentManager(), "teste");
                //progressDialog.setCancelable(false);
                //show_fish_loading();
            }
        });
    }


    //second way off showing progress
    public void show_fish_loading() {
        try {
            HashMap<String, Integer> screen = getScreenRes();
            int width = screen.get(SCREEN_WIDTH);
            int height = screen.get(SCREEN_HEIGHT);
            int mywidth = (width / 10) * 9;
            final Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.setContentView(R.layout.progress_dialouge);
            LinearLayout ll = (LinearLayout) dialog.findViewById(R.id.dialog_layout_size);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll.getLayoutParams();
            params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            params.width = mywidth;
            ll.setLayoutParams(params);
            dialog.setCancelable(true);
            dialog.show();
        } catch (Exception e) {
            Log.d("Error Line Number", Log.getStackTraceString(e));
        }
    }

    public HashMap<String, Integer> getScreenRes() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) this).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        map.put(SCREEN_WIDTH, width);
        map.put(SCREEN_HEIGHT, height);
        map.put(SCREEN_DENSITY, (int) metrics.density);
        return map;
    }

    public static String SCREEN_WIDTH = "width";
    public static String SCREEN_HEIGHT = "height";
    public static String SCREEN_DENSITY = "density";
}
