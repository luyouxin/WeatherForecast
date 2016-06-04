package com.lzh.weatherforecast.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzh.weatherforecast.R;


/**
 * Created by lzh on 2016/4/12.
 */
public class TitleLayout extends RelativeLayout {
    private Context context;
    private RelativeLayout relativeLayout;
    private ImageView leftImageView, rightImageView;
    private TextView titleTextView, rightTextView;
    private TitleOnClickListener titleOnClickListener;

    public TitleLayout(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    //初始化view
    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.common_title_layout, null);
        leftImageView = (ImageView) view.findViewById(R.id.left_imageview);
        rightTextView = (TextView) view.findViewById(R.id.right_textview);
        titleTextView = (TextView) view.findViewById(R.id.title);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.titlelayout);
        rightImageView = (ImageView) view.findViewById(R.id.right_image);
        this.addView(view);
    }

    //给右边的imageView设置bg
    public void setRightImageViewBg(int resid) {
        rightImageView.setBackgroundResource(resid);
    }

    //设置右边的imageView是否可见
    public void setRightImageViewVisibility(int visibility) {
        rightImageView.setVisibility(visibility);
    }

    // 右边图片点击事件
    public void setRightImageViewListener(final TitleOnClickListener titleOnClickListener) {
        rightImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleOnClickListener.OnClick();
            }
        });
    }

    //设置标题栏背景颜色
    public void setTitleBackgroundColor(int color) {
        relativeLayout.setBackgroundColor(color);
    }

    //设置标题
    public void setTitleMessage(String title) {
        titleTextView.setText(title);
    }

    //标题文字大小
    public void setTitleSize(float size) {
        titleTextView.setTextSize(size);
    }

    //设置左边图片
    public void setLeftImage(Drawable drawable) {
        leftImageView.setImageDrawable(drawable);
    }

    //设置右边文字
    public void setRightTextMessage(String message) {
        rightTextView.setText(message);
    }

    //左边图片是否隐藏
    public void setLeftVisibility(int visibility) {
        leftImageView.setVisibility(visibility);
    }

    //右边图片是否隐藏
    public void setRightVisibility(int visibility) {
        rightTextView.setVisibility(visibility);
    }

    //标题文字是否隐藏
    public void setTitleVisibility(int visibility) {
        titleTextView.setVisibility(visibility);
    }

    //设置标题文字颜色
    public void setTitleColor(int color) {
        titleTextView.setTextColor(color);
    }

    //左边点击事件
    public void setLeftListener(final TitleOnClickListener titleOnClickListener) {
        leftImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleOnClickListener.OnClick();
            }
        });
    }

    public void setRightListener(final TitleOnClickListener titleOnClickListener) {
        rightTextView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                titleOnClickListener.OnClick();
            }
        });
    }

    public interface TitleOnClickListener {
        void OnClick();

    }
}
