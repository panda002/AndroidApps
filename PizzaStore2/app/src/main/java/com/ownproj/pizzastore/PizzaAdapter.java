package com.ownproj.pizzastore;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class PizzaAdapter extends BaseAdapter {

    private List<Integer> imageIds;
    private Context appContext;
    private LayoutInflater inflater;

    public PizzaAdapter(Context appContext, List<Integer> imageIds)
    {
    this.appContext = appContext;
    this.imageIds = imageIds;

        inflater = LayoutInflater.from(appContext);
    }
    @Override
    public int getCount() {
        return imageIds.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = inflater.inflate(R.layout.activity_main,null);
        ImageView img = view.findViewById(R.id.imageView);
        img.setImageResource(imageIds.get(i));
        return view1;

    }
}
