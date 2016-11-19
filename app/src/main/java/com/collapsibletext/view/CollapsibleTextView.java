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

    //文本内容
    private TextView tvDesc;
    //下拉箭头布局
    private LinearLayout llDescOp;
    //下拉箭头
    private ImageView ivDescOp;

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

    public void setText(String text){
        tvDesc.setText(text);
        //重新请求布局
        requestLayout();
    }

    @Override
    public void onClick(View view) {

    }
}
