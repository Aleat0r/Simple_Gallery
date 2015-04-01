package com.kovalenko.aleksandr.aleat0r.simplegallery.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageGridAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> filePaths = new ArrayList<>();
    private int imageWidth;

    //  Конструктор принимает и сохраняет ссылки на переданный контекст,
    // список путей к изображениям, ширину элемента
    public ImageGridAdapter(Context context, ArrayList<String> filePaths,
                            int imageWidth){
        mContext = context;
        this.filePaths = filePaths;
        this.imageWidth = imageWidth;
    }

    @Override
    public int getCount() {
        return this.filePaths.size();
    }

    @Override
    public Object getItem(int position) {
        return this.filePaths.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    //  Создаём новый ImageView для каждого элемента, на который ссылается адаптер
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ImageView imageView;

        if (view == null) {
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) view;
        }

        // Получаем Bitmap
        Bitmap image = decodeFile(filePaths.get(position), imageWidth,
                imageWidth);
        // Обрезаеем лишнее
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        // Устанавливаем высоту и ширину элемента
        imageView.setLayoutParams(new GridView.LayoutParams(imageWidth,
                imageWidth));
        // Установиваем изображение в ImageView
        imageView.setImageBitmap(image);
        return imageView;
    }

    // Метод возвращающий bitmap, который будет уменьшен до необходимых нам размеров
    public static Bitmap decodeFile(String filePath, int width, int height) {

        // Читаем с inJustDecodeBounds=true для определения размеров
        BitmapFactory.Options options1 = new BitmapFactory.Options();
        // inJustDecodeBounds – поможет нам узнать размер изображения, не занимая память
        options1.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options1);
        // Требуемые размеры
        final int REQUIRED_WIDTH = width;
        final int REQUIRED_HEIGHT = height;
        // Реальные размеры
        final int REAL_WIDTH = options1.outWidth;
        final int REAL_HEIGHT = options1.outHeight;

        // Вычисляем наибольший  коэффициент уменьшения размера изображения при чтении,
        // который будет кратным двум и оставит полученные размеры больше, чем требуемые
        int scale = 1;
        while (REAL_WIDTH / 2 / scale >= REQUIRED_WIDTH
                && REAL_HEIGHT / 2 / scale >= REQUIRED_HEIGHT)
            scale *= 2;

        BitmapFactory.Options options2 = new BitmapFactory.Options();
        options2.inPreferredConfig = Bitmap.Config.RGB_565;
        options2.inSampleSize = scale;
        return BitmapFactory.decodeFile(filePath, options2);
    }
}
