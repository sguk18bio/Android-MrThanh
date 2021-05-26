package com.example.zingtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.zingtest.Model.Playlist;
import com.example.zingtest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends ArrayAdapter<Playlist> {
    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }


    class viewHolder {
        TextView tv1;
        ImageView imgplaylist, imgiconplaylist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        viewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.dong_play_list, null);
            viewHolder = new viewHolder();
            viewHolder.tv1 = convertView.findViewById(R.id.textviewtenplaylist);
            viewHolder.imgplaylist = convertView.findViewById(R.id.imageviewplaylist);
            viewHolder.imgiconplaylist = convertView.findViewById(R.id.imageviewiconplaylist);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PlayListAdapter.viewHolder) convertView.getTag();
        }

        Playlist playList = getItem(position);
        Picasso.get().load(playList.getHinhNen()).into(viewHolder.imgplaylist);
        Picasso.get().load(playList.getHinhIcon()).into(viewHolder.imgiconplaylist);
        viewHolder.tv1.setText(playList.getTen());

        return convertView;
    }
}
