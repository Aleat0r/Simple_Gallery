package com.kovalenko.aleksandr.aleat0r.simplegallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ImageGridAdapter extends BaseAdapter{

    private Context mContext;

    //   Конструктор принимает и сохраняет ссылку на переданный контекст
    public ImageGridAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return Constants.IMAGES_ID.length;
    }

    @Override
    public Object getItem(int position) {
        return Constants.IMAGES_ID[position];
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

            // Устанавливаем Padding для изображения
            imageView.setPadding(Constants.GRID_PADDING, Constants.GRID_PADDING,
                    Constants.GRID_PADDING, Constants.GRID_PADDING);

            // Создание bitmap картинки (без этого была бы ошибка java.lang.OutOfMemoryError)
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            // Указаваем коэффициент уменьшения размера изображения
            options.inSampleSize = Constants.SAMPLE_SIZE;
            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(),
                    Constants.IMAGES_ID[position],options);
            imageView.setImageBitmap(icon);
        } else {
            imageView = (ImageView) view;
        }
        return imageView;
    }
}
