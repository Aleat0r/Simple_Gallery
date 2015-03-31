package com.kovalenko.aleksandr.aleat0r.simplegallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;

    // Конструктор принимает и сохраняет ссылку на переданный контекст
    public ImagePagerAdapter(Context context){
        mContext = context;
    }

    // Показываем изображение по полученному ID
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);

        // Создание bitmap картинки (без этого была бы ошибка java.lang.OutOfMemoryError)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),
                Constants.IMAGES_ID[position], options);
        imageView.setImageBitmap(bitmap);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return Constants.IMAGES_ID.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
