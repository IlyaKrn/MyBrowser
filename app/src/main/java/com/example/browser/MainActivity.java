package com.example.browser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_QUERY = "search_query";

    private ImageButton btSearch;
    private EditText etSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSearch = findViewById(R.id.bt_search);
        etSearch = findViewById(R.id.et_search);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(SEARCH_QUERY, parser(etSearch.getText().toString()));
                startActivity(intent);
            }
        });


    }

    private String parser(String str){
        String[] strings = str.split(" ");
        String qData = "";
        for (int i = 0; i < strings.length; i++) {
            qData += strings[i];
            if (!(i == strings.length-1)){
                qData += "+";
            }
        }
        String query = "https://www.google.com/search?q=" + qData + "&oq=" + qData + "&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8";
        return query;
      //  https://www.google.com/search?q=aaaaa+wwww+e&oq=aaaaa+wwww+e&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8


    }
}