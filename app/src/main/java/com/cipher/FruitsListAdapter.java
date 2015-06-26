package com.cipher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Eduardo Gorio on 26/06/2015.
 */
public class FruitsListAdapter extends ArrayAdapter<Fruit> {

    public FruitsListAdapter(Context context, List<Fruit> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit Fruit = getItem(position);

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_fruit_list, null);

            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            holder.txtDescription = (TextView) convertView.findViewById(R.id.txtDescription);
            holder.txtValor = (TextView) convertView.findViewById(R.id.txtValor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(getContext()).load(Fruit.image).into(holder.image);
        holder.txtTitle.setText(Fruit.title);
        holder.txtDescription.setText(Fruit.description);
        holder.txtValor.setText("" + Fruit.valor);
        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView txtTitle;
        TextView txtDescription;
        TextView txtValor;
    }
}
