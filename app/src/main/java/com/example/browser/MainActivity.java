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
import com.example.browser.adapters.OnCardPageStateClickListener;
import com.example.browser.objects.Page;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // ключ для Intent
    public static final String SEARCH_QUERY = "search_query";
    // разметка
    private ImageButton btSearch;
    private EditText etSearch;
    private RecyclerView rvRecentPages;
    private CardPageAdapter adapter;
    private ArrayList<Page> recentPagesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // инициализация разметки
        init();



        // кнопка поиска
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(SEARCH_QUERY, getUrl(etSearch.getText().toString()));
                recentPagesList.add(new Page(getUrl(etSearch.getText().toString())));
                adapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
        // адаптер для предыдущих страниц
        adapter = new CardPageAdapter( recentPagesList, new OnCardPageStateClickListener() {
            @Override
            public void onStateClick(Page page) {
                Intent intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(SEARCH_QUERY, getUrl(page.url));
                recentPagesList.add(new Page(getUrl(etSearch.getText().toString())));
                adapter.notifyDataSetChanged();
                startActivity(intent);
            }
        });
        rvRecentPages.setLayoutManager(new GridLayoutManager(this, 1));
        rvRecentPages.setAdapter(adapter);
    }

    // инициализация разметки
    private void init(){
        rvRecentPages = findViewById(R.id.rv_recent_pages);
        btSearch = findViewById(R.id.bt_search);
        etSearch = findViewById(R.id.et_search);
    }
    // преобразлвание текста в ссылку
    private String getUrl(String str){
        String start = "";
        if (str.length() >= 8) {
            for (int i = 0; i < 8; i++) {
                start += str.charAt(i);
            }
        }

        String url = "";

        if (start.equals("https://")){
            url = str;
        }
        else {
            String[] strings = str.split(" ");
            String data = "";
            for (int i = 0; i < strings.length; i++) {
                data += strings[i];
                if (!(i == strings.length-1)){
                    data += "+";
                }
            }
            url = "https://www.google.com/search?q=" + data + "&oq=" + data + "&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8";
        }


        return url;
        //  https://www.google.com/search?q=aaaaa+wwww+e&oq=aaaaa+wwww+e&aqs=chrome..69i57.5015j0j15&sourceid=chrome&ie=UTF-8
    }
}