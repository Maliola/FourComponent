package com.mahui.fourcomponent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void service(View view){
        Intent intent=new Intent(MainActivity.this,ServiceActivity.class);
        startActivity(intent);
    }
    public void broadcast(View view){
        Intent intent=new Intent(MainActivity.this,BroadcastActivity.class);
        startActivity(intent);
    }
    public void contentprovider(View view){
        Intent intent=new Intent(MainActivity.this,ContentProviderActivity.class);
        startActivity(intent);
    }
}
