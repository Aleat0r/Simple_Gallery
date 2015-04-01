package com.kovalenko.aleksandr.aleat0r.simplegallery.Activities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.view.View;

import com.kovalenko.aleksandr.aleat0r.simplegallery.Adapters.ImageGridAdapter;
import com.kovalenko.aleksandr.aleat0r.simplegallery.Constants;
import com.kovalenko.aleksandr.aleat0r.simplegallery.Helper;
import com.kovalenko.aleksandr.aleat0r.simplegallery.R;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Helper helper;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ImageGridAdapter imageGridAdapter;
    private GridView gridView;
    private int columnWidth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_view);

        gridView = (GridView) findViewById(R.id.gridView);
        helper = new Helper(this);

        // Инициализируем GridView
        InitializeGridLayout();

        // Загружаем все пути на изображения с SDCard
        imagePaths = helper.getFilePaths();

        // Создаем и присваем адаптер GridView
        imageGridAdapter = new ImageGridAdapter(this, imagePaths, columnWidth);
        gridView.setAdapter(imageGridAdapter);

        // Создаём обработчик нажатия на пункт GridView
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // Вызываем Activity с ViewPager, передавая ему номер нажатого пункта
                Intent intent = new Intent(getApplicationContext(), ImageViewPager.class);
                intent.putExtra("id", position);
                startActivity(intent);
            }
        });
    }

    // Метод инициализирует gridView, установив необходимые атрибуты
    private void InitializeGridLayout() {

        // Рассчитываем ширину столбца gridView в зависимости от ширины экрана, padding
        // и количества столбцов
        columnWidth = (helper.getScreenWidth() - ((Constants.NUM_OF_COLUMNS + 1) *
                Constants.GRID_PADDING)) / Constants.NUM_OF_COLUMNS;
        // Устанавливаем количество столбцов
        gridView.setNumColumns(Constants.NUM_OF_COLUMNS);
        // Устанавливаем количество столбцов
        gridView.setColumnWidth(columnWidth);
        // Устанавливаем padding
        gridView.setPadding(Constants.GRID_PADDING, Constants.GRID_PADDING,
                Constants.GRID_PADDING, Constants.GRID_PADDING);
        // Устанавливаем горизонтальный и вертикальный отступы между ячейками
        gridView.setHorizontalSpacing(Constants.GRID_PADDING);
        gridView.setVerticalSpacing(Constants.GRID_PADDING);
    }
}

