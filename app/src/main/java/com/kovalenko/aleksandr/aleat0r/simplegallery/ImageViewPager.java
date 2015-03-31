package com.kovalenko.aleksandr.aleat0r.simplegallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class ImageViewPager extends Activity {

    int position;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        // Получаем из MainActivity ID нажатого пункта
        Intent p = getIntent();
        position = p.getExtras().getInt("id");

        // Создаем и присваем адаптер ViewPager
        ImagePagerAdapter pagerAdapter = new ImagePagerAdapter(this);
        ViewPager viewpager = (ViewPager) findViewById(R.id.pager);
        viewpager.setAdapter(pagerAdapter);

        // Показать изображение с полученным ранее ID
        viewpager.setCurrentItem(position);
    }
}