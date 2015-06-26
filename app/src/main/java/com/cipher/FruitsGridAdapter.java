package com.cipher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by Eduardo Gorio on 26/06/2015.
 */
public class FruitsGridAdapter extends ArrayAdapter<Fruit> {

    private ImageLoader mLoader;

    public FruitsGridAdapter(Context context, List<Fruit> objects) {
        super(context, 0, objects);
        mLoader = VolleySingleton.getInstance(context).getImageLoader();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context ctx = parent.getContext();
        if (convertView == null) {
            convertView = LayoutInflater.from(ctx)
                    .inflate(R.layout.item_fruit_grid, null);
        }
        NetworkImageView img = (NetworkImageView)
                convertView.findViewById(R.id.image);
        TextView txt = (TextView)
                convertView.findViewById(R.id.txtTitle);

        Fruit Fruit = getItem(position);
        txt.setText(Fruit.title);
        img.setImageUrl(Fruit.image, mLoader);

        return convertView;
    }
}

