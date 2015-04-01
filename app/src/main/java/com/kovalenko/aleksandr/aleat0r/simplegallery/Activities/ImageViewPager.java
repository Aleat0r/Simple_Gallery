package com.kovalenko.aleksandr.aleat0r.simplegallery.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.kovalenko.aleksandr.aleat0r.simplegallery.Adapters.ImagePagerAdapter;
import com.kovalenko.aleksandr.aleat0r.simplegallery.Helper;
import com.kovalenko.aleksandr.aleat0r.simplegallery.R;

public class ImageViewPager extends Activity {

    private Helper helper;
    private ImagePagerAdapter pagerAdapter;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager);

        // Получаем из MainActivity ID нажатого пункта
        Intent intent = getIntent();
        int position = intent.getExtras().getInt("id");

        helper = new Helper(getApplicationContext());

        // Создаем и присваем адаптер ViewPager
        pagerAdapter = new ImagePagerAdapter(this, helper.getFilePaths());
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);

        // Показать изображение с полученным ранее ID первым
        viewPager.setCurrentItem(position);
    }
}