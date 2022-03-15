package com.example.browser.adapters;

import com.example.browser.objects.Page;

// нажание на элемент списка страниц
public interface OnCardPageStateClickListener {
    void onStateClick(Page page);
    void onDeletePage(int index);
}
