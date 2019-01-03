package com.mmc.chomp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class BoardViewAdapter extends BaseAdapter {

    private Context context;
    private OnChocolateChooseListener onChocolateChooseListener;
    private List<Chocolate> chocolates;

    public BoardViewAdapter(Context context, OnChocolateChooseListener onChocolateChooseListener, boolean[][] board) {
        this.context = context;
        this.onChocolateChooseListener = onChocolateChooseListener;
        chocolates = mapBooleansToChocolates(board);
    }

    @Override
    public int getCount() {
        return chocolates == null ? 0:chocolates.size();
    }

    @Override
    public Object getItem(int position) {
        return chocolates.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.chocolate_item, parent, false);
        Button btn = view.findViewById(R.id.btn);
        final Chocolate item = (Chocolate) getItem(position);
        String s = item.isTaken() ? "x" : "o";
        btn.setText(s);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChocolateChooseListener.choosed(item);
            }
        });
        return view;
    }


    void newSetOfData(boolean[][] boardState) {
        chocolates = mapBooleansToChocolates(boardState);
        notifyDataSetChanged();
    }

    private List<Chocolate> mapBooleansToChocolates(boolean[][] t) {
        List<Chocolate> list = new ArrayList<>();

        for (int i = 0; i < t.length; i++) {
            boolean[] b = t[i];
            for (int k = 0; k < b.length; k++) {
                list.add(new Chocolate(i, k, b[k]));
            }
        }

        return list;
    }
}