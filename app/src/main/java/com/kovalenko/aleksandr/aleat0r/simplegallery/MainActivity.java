package com.kovalenko.aleksandr.aleat0r.simplegallery;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);

        GridView gridview = (GridView) findViewById(R.id.gridView);

        // Создаем и присваем адаптер GridView
        ImageGridAdapter imageGridAdapter = new ImageGridAdapter(this);
        gridview.setAdapter(imageGridAdapter);

        // создаём обработчик нажатия на пункт GridView
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // вызываем Activity с ViewPager, передавая ему номер нажатого пункта
                Intent intent = new Intent(getApplicationContext(), ImageViewPager.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }
}