package com.collapsibletext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.collapsibletext.view.CollapsibleTextView;

public class MainActivity extends AppCompatActivity {
    private CollapsibleTextView collapsibleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        collapsibleTextView = (CollapsibleTextView) findViewById(R.id.CollapsibleTextView);
        //设置文字会出现效果
        collapsibleTextView.setText(getResources().getString(R.string.content));
    }
}
