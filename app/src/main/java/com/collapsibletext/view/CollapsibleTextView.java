package com.collapsibletext.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

    //处理折叠事件
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //展开
                case 0x01:
                    tvDesc.setMaxLines(Integer.MAX_VALUE);
                    ivDescOp.setImageResource(R.mipmap.menu_pull_up1);
                    COLLAPSIBLE = false;
                    flag = true;
                    break;
                //折叠
                case 0x02:
                    tvDesc.setMaxLines(TEXT_MAX_LINE);
                    ivDescOp.setImageResource(R.mipmap.menu_pull_down1);
                    COLLAPSIBLE = true;
                    flag = true;
                    break;
            }
        }
    };

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
        //可折叠并且已经折叠
        //进行展开操作
        if(IS_COLLAPSIBLE && COLLAPSIBLE){
            System.out.println("qqqqqqqqqq  0x01");
             Message message = new Message();
             message.what = 0x01;
             handler.sendMessage(message);
        }
        //可折叠但未进行折叠
        //进行折叠操作
        if(IS_COLLAPSIBLE && !COLLAPSIBLE){
            System.out.println("qqqqqqqqqq  0x02");
            Message message = new Message();
            message.what = 0x02;
            handler.sendMessage(message);
        }
    }
}
