package com.example.mac_soong.popupwindow;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bt_Open;
    private Button bt_P,bt_O;
    private PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_Open = (Button) findViewById(R.id.bt_open);
        View view = getLayoutInflater().inflate(R.layout.mpopupwindow,null);
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT,true);

        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),(Bitmap) null));

        mPopupWindow.getContentView().setFocusable(true);
        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.setAnimationStyle(R.style.bottom_menu);

        init_bt(view);

        mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode==KeyEvent.KEYCODE_MENU && event.getRepeatCount() ==0 && event.getAction() == KeyEvent.ACTION_DOWN){
                    if (mPopupWindow!=null && mPopupWindow.isShowing()){
                        mPopupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });

        bt_Open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.showAtLocation(v, Gravity.BOTTOM,0,0);
            }
        });
    }

    private void init_bt(View view){
        bt_O = (Button) view.findViewById(R.id.bt_o);
        bt_P = (Button) view.findViewById(R.id.bt_p);

        bt_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });
        bt_O.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                mPopupWindow.dismiss();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU &&event.getRepeatCount() == 0 && event.getAction() == KeyEvent.ACTION_DOWN){
            if (mPopupWindow!=null && mPopupWindow.isShowing()){
                mPopupWindow.dismiss();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
