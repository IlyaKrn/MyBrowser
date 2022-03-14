package com.example.browser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.browser.adapters.CardPageAdapter;
import com.example.browser.adapters.OnCardPageStateClick;
import com.example.browser.objects.Page;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SEARCH_QUERY = "search_query";

    private ImageButton btSearch;
    private EditText etSearch;
    private RecyclerView rvRecentPages;
    private CardPageAdapter adapter;
    private ArrayList<Page> recentPagesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recentPagesList.add(new Page("https://www.google.com/search?q=aaaaa+wwww+e&oq=aaaaa+wwww+e&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8"));
        recentPagesList.add(new Page("https://www.google.com/search?q=aaaaa+wwww+e&oq=aaaaa+wwww+e&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8"));
        recentPagesList.add(new Page("https://www.google.com/search?q=aaaaa+wwww+e&oq=aaaaa+wwww+e&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8"));

        rvRecentPages = findViewById(R.id.rv_recent_pages);
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
        adapter = new CardPageAdapter(recentPagesList, new OnCardPageStateClick() {
            @Override
            public void onStateClick(Page page) {

            }
        });
        rvRecentPages.setLayoutManager(new GridLayoutManager(this, 2));
        rvRecentPages.setAdapter(adapter);
    }

    private String parser(String str){
        String start = "";
        for (int i = 0; i < 8; i++) {
            start += str.charAt(i);
        }

        String query = "";

        if (start.equals("https://")){
            query = str;
        }
        else {
            String[] strings = str.split(" ");
            String qData = "";
            for (int i = 0; i < strings.length; i++) {
                qData += strings[i];
                if (!(i == strings.length-1)){
                    qData += "+";
                }
            }
            query = "https://www.google.com/search?q=" + qData + "&oq=" + qData + "&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8";
        }


        return query;
        //  https://www.google.com/search?q=aaaaa+wwww+e&oq=aaaaa+wwww+e&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8
    }
}