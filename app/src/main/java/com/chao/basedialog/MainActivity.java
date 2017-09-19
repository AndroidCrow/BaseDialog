package com.chao.basedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.chao.basedialog.dialog.BaseDialog;

public class MainActivity extends AppCompatActivity {

    private BaseDialog baseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseDialog = new BaseDialog.Builder(this, R.layout.dialog_item)
                .setText(R.id.tv_title,"标题")
                .setText(R.id.tv_confirm,"确认")
                .setText(R.id.tv_cancle,"取消")
                .setOnclickListener(R.id.tv_confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                    }
                })
                .setOnclickListener(R.id.tv_confirm, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        baseDialog.dismiss();
                    }
                })
                //点击外部是否销毁
                .setCancelable(false)
                //全屏
                .fullWidth()
                .show();
    }
}
