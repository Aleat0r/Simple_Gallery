package com.kovalenko.aleksandr.aleat0r.simplegallery;

import java.io.File;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

//Класс - помощник, чтоб в других классах не было много кода
public class Helper {

    private Context mContext;

    // Конструктор принимает и сохраняет ссылку на переданный контекст
    public Helper(Context context) {
        mContext = context;
    }

    // Метод возвращает список путей к файлам(изображениям) из SDCard
    public ArrayList<String> getFilePaths() {
        ArrayList<String> filePaths = new ArrayList<>();

        // Определяем путь к SDCard + /DCIM/Camera  в зависимости от версии системы (иначе бы
        // getExternalStorageDirectory при версии 4.4.2  давал путь на память устройства, а не SDCard
        File directory;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
             directory = new File(
                    System.getenv("SECONDARY_STORAGE")
                            + Constants.PHOTO_DIRECTORY);
        } else {
            directory = new File(android.os.Environment.getExternalStorageDirectory()
                    + Constants.PHOTO_DIRECTORY);
        }

        //  Проверяем, что указанный объект действительно является каталогом
        if (directory.isDirectory()) {

            // Получаем массив файлов
            File[] listFiles = directory.listFiles();

            // Проверяем не пустой ли массив
            if (listFiles.length > 0) {

                // loop through all files
                for (int i = 0; i < listFiles.length; i++) {

                    // Получаем путь к файлу
                    String filePath = listFiles[i].getAbsolutePath();
                    // Добавляем путь в список
                    filePaths.add(filePath);

                }
            } else {
                // если каталог пустой выводим всплывающее сообщение
                Toast.makeText(mContext, "В " + Constants.PHOTO_DIRECTORY + " пусто",
                        Toast.LENGTH_LONG).show();
            }
        }
        return filePaths;
    }

    // Получаем ширину экрана
    public int getScreenWidth() {
        int columnWidth;
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }
}
