package com.collapsibletext.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.collapsibletext.R;

/**
 * @className:CollapsibleTextView
 * @classDescription：自定义可折叠的TextView
 * @author: miao
 * @createTime: 2016年11月19日
 */

public class CollapsibleTextView extends LinearLayout implements View.OnClickListener{

    //规定文本最大行数
    private int TEXT_MAX_LINE = 2;
    //是否可折叠
    private boolean IS_COLLAPSIBLE;
    //是否已经折叠
    private boolean COLLAPSIBLE;
    //第一次启动进入onLayout方法标记
    private boolean flag;

    //下拉箭头布局
    private LinearLayout llDescOp;
    //下拉箭头
    private ImageView ivDescOp;
    //文本内容
    private TextView tvDesc;

    class InnerRunnable implements Runnable{

        @Override
        public void run() {
            if(IS_COLLAPSIBLE && COLLAPSIBLE){
                //可折叠并且已经折叠--文本展开
                tvDesc.setMaxLines(Integer.MAX_VALUE);
                ivDescOp.setImageResource(R.mipmap.menu_pull_up1);
                COLLAPSIBLE = false;
                flag = true;
            }else if(IS_COLLAPSIBLE && !COLLAPSIBLE){
                //可折叠但未进行折叠--文本折叠
                tvDesc.setMaxLines(TEXT_MAX_LINE);
                ivDescOp.setImageResource(R.mipmap.menu_pull_down1);
                COLLAPSIBLE = true;
                flag = true;
            }
        }
    }

    public CollapsibleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取view的布局
        View view = inflate(context, R.layout.collapsible_textview_layout,this);
        tvDesc = (TextView) view.findViewById(R.id.desc_tv);
        llDescOp = (LinearLayout) view.findViewById(R.id.desc_op_ll);
        ivDescOp = (ImageView) findViewById(R.id.desc_op_iv);
        llDescOp.setOnClickListener(this);
    }

    public CollapsibleTextView(Context context) {
        super(context);
    }

    //设置文本的内容
    public void setText(String text){
        tvDesc.setText(text);
        requestLayout();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //初始化代码
        //flag用来标记后续操作不走以下代码
        if(!flag){
            //文本的行数大于规定值，折叠并显示下拉箭头
            if(tvDesc.getLineCount() > TEXT_MAX_LINE){
                tvDesc.setMaxLines(TEXT_MAX_LINE);
                llDescOp.setVisibility(VISIBLE);
                IS_COLLAPSIBLE = true;
                COLLAPSIBLE = true;
            }else{
                llDescOp.setVisibility(GONE);
                IS_COLLAPSIBLE = false;
            }
        }
    }

    @Override
    public void onClick(View view) {
        post(new InnerRunnable());
    }
}
