package com.kovalenko.aleksandr.aleat0r.simplegallery.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kovalenko.aleksandr.aleat0r.simplegallery.R;

import java.util.ArrayList;

public class ImagePagerAdapter extends PagerAdapter {

    private Context mContext;
    private ArrayList<String> imagePaths;
    private LayoutInflater inflater;

    // Конструктор принимает и сохраняет ссылки на переданный контекст и список путей к изображениям
    public ImagePagerAdapter(Context context, ArrayList<String> imagePaths){
        mContext = context;
        this.imagePaths = imagePaths;
    }

    // Показываем изображение по полученному ID
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imgDisplay;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.fullscreen_image, container, false);
        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);

        // Создание bitmap картинки (без этого была бы ошибка java.lang.OutOfMemoryError)
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        Bitmap bitmap = BitmapFactory.decodeFile(imagePaths.get(position), options);
        imgDisplay.setImageBitmap(bitmap);
        container.addView(viewLayout);
        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return this.imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}



