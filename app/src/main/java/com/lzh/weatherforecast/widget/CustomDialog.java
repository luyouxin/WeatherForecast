package com.lzh.weatherforecast.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzh.weatherforecast.R;


/**
 * Created by lzh on 2016/4/6.
 */
public class CustomDialog extends Dialog {
    private Context context;
    private Dialog dialog;
    private TextView titleView, contentTextView;
    private Button cancleBtn, okBtn;
    private OnClickListener listener;

    public CustomDialog(Context context) {
        super(context);
        this.context = context;
        dialog = new Dialog(context, R.style.dialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_layout, null);
        dialog.setContentView(view);
        titleView = (TextView) view.findViewById(R.id.title);
        cancleBtn = (Button) view.findViewById(R.id.cancle);
        okBtn = (Button) view.findViewById(R.id.ok);
        contentTextView = (TextView) view.findViewById(R.id.content);
    }

    //设置标题
    public void setTitleMessage(String message) {
        titleView.setText(message);
    }


    //设置右边Btn的text
    public void setCancleBtnText(String text) {
        cancleBtn.setText(text);
    }

    //设置左边Btn的text
    public void setOkBtnText(String text) {
        okBtn.setText(text);
    }

    //设置content的text
    public void setContentText(String text) {
        contentTextView.setText(text);
    }


    //点击监听
    public void setCancleBtnOnClickListener(final OnClickListener listener) {
        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
    }

    //点击监听
    public void setOkBtnOnClickListener(final OnClickListener listener) {
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick();
            }
        });
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public interface OnClickListener {
        void onClick();
    }
}
