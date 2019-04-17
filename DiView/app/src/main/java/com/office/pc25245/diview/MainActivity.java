package com.office.pc25245.diview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  private DiView diView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        diView = new DiView(MainActivity.this);
        setContentView(diView);
    }
}
