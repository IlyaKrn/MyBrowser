package com.example.browser.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.browser.objects.Page;
import com.example.browser.R;

import java.util.ArrayList;

// адаптер для списка страниц

public class CardPageAdapter extends RecyclerView.Adapter<CardPageAdapter.PageViewHolder> {

    private ArrayList<Page> pages; // список
    private OnCardPageStateClickListener onCardPageStateClick; // обработчик нажатия на элемент

    // конструктор
    public CardPageAdapter(ArrayList<Page> pages, OnCardPageStateClickListener onCardPageStateClick) {
        this.pages = pages;
        this.onCardPageStateClick = onCardPageStateClick;
    }

    // создание холдера
    @Override
    public PageViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        // создание разметки для холдера
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_page, viewGroup, false);
        // создание холдера
        PageViewHolder holder = new PageViewHolder(v);
        return holder;
    }


    // получение колличества элементов
    @Override
    public int getItemCount() {
        return pages.size();
    }

    // обновление холдера
    @Override
    public void onBindViewHolder(PageViewHolder pageViewHolder, @SuppressLint("RecyclerView") int i) {
        // обновление холдера
        pageViewHolder.bind(i);
        // установка слушателя нажатий
        pageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onCardPageStateClick != null){
                    onCardPageStateClick.onStateClick(pages.get(i));
                }
            }
        });
    }


    // холдер
    public class PageViewHolder extends RecyclerView.ViewHolder {


        // разметка
        private CardView cv;
        private TextView tvName;
        private ImageButton btClose;
        private WebView wvPage;
        // страница элемента
        private Page page;

        // конструктор
        PageViewHolder(View itemView) {
            super(itemView);
            // получение объектов разметки
            cv = itemView.findViewById(R.id.cv);
            tvName = itemView.findViewById(R.id.tv_card_page_name);
            btClose = itemView.findViewById(R.id.bt_card_page_close);
            wvPage = itemView.findViewById(R.id.wv_card_page);
            wvPage.getSettings().setJavaScriptEnabled(true);
            wvPage.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    tvName.setText(view.getTitle());
                }
            });
        }

        // обновление данных холдера
        public void bind(int index) {
            page = pages.get(index);
            wvPage.loadUrl(page.url);
            btClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pages.remove(index);
                    notifyDataSetChanged();
                }
            });


        }
    }
}
