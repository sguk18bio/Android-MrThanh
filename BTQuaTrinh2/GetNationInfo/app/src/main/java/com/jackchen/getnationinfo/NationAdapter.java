package com.jackchen.getnationinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class NationAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Nation> nationList;

    public NationAdapter(Context context, int layout, ArrayList<Nation> nationList) {
        this.context = context;
        this.layout = layout;
        this.nationList = nationList;
    }

    @Override
    public int getCount() {
        return nationList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Bitmap image = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);

            holder = new ViewHolder();

            //ánh xạ view
            holder.txtName = convertView.findViewById(R.id.row_text);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

//       gán giá trị
        Nation nation = nationList.get(position);

        //Set giá trị trong list cho view
        holder.txtName.setText(nation.getName());
//        holder.img.setImageURI(Uri.parse(nation.getImageUrl()));

//        Lấy ảnh từ URL
//        try {
//            InputStream in = new URL(nation.getImageUrl()).openStream();
//            image = BitmapFactory.decodeStream(in);
//            in.close();
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        holder.img.setImageBitmap(image);

//        if (nation != null) {
//            Log.d("In nation not null", "yes");
//            try {
//                Bitmap bit = new ImageFromUrlHandler(holder.img).execute(nation.getImageUrl()).get();
////                holder.img.setImageBitmap(bit);
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            Log.d("BBB", "no");
//        }

        return convertView;
    }

    private class ViewHolder {
        TextView txtName;
    }

//    private class ImageFromUrlHandler extends AsyncTask<String, Void, Bitmap> {
//        ImageView imageView;
//
//        public ImageFromUrlHandler(ImageView imageView) {
//            this.imageView = imageView;
////            Log.d("id of imgview", imageView.getId() + "");
//        }
//
//
////        protected Bitmap doInBackground(ArrayList<Nation> nations) {
////
////        }
//
//        @Override
//        protected Bitmap doInBackground(String... urls) {
//
//            return GetImageFromUrl(urls[0]);
//        }
//
//    }
//
//    protected Bitmap GetImageFromUrl(String url) {
////        Bitmap image = null;
//
//        try {
//            Log.d("image path", url);
//            URL path = new URL(url);
//            HttpURLConnection conn = (HttpURLConnection) path.openConnection();
//            conn.setInstanceFollowRedirects(true);
//            conn.setReadTimeout(15000);
//            conn.setConnectTimeout(15000);
//            conn.setRequestMethod("GET");
//
//            int responceCode = conn.getResponseCode();
//            if (responceCode == HttpURLConnection.HTTP_OK) {
//                InputStream in = conn.getInputStream();
//                return  BitmapFactory.decodeStream(in);
//            }
//
////                Log.d("Url của ảnh", url);
////               InputStream in = new URL(url).openStream();
//
////                image = bitmap;
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        return image;
//
//        return null;
//    }
}