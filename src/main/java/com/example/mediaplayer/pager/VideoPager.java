package com.example.mediaplayer.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mediaplayer.R;
import com.example.mediaplayer.SystemVideoPlayer;
import com.example.mediaplayer.base.BasePager;
import com.example.mediaplayer.domain.MediaItem;

import java.util.ArrayList;

public class VideoPager extends BasePager {

    private ListView lv_video_pager;
    private TextView tv_nomedia;
    private ProgressBar pb_loading;

    private ArrayList<MediaItem> mediaItems;

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        View view=View.inflate(context,R.layout.video_pager,null);
        lv_video_pager=view.findViewById(R.id.lv_video_pager);
        tv_nomedia=view.findViewById(R.id.tv_nomedia);
        pb_loading=view.findViewById(R.id.pb_loading);

        lv_video_pager.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MediaItem mediaItem=mediaItems.get(i);



                Intent intent=new Intent(context,SystemVideoPlayer.class);
               // intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
                Bundle bundle=new Bundle();
                bundle.putSerializable("videolist",mediaItems);
                intent.putExtras(bundle);
                intent.putExtra("position",i);
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        getData();
    }

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if(mediaItems!=null&&mediaItems.size()>0){
                tv_nomedia.setVisibility(View.GONE);
                pb_loading.setVisibility(View.GONE);

                lv_video_pager.setAdapter(new VideoPagerAdapter());
            }else{
                tv_nomedia.setVisibility(View.VISIBLE);
                pb_loading.setVisibility(View.GONE);
            }
        }
    };

    class VideoPagerAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mediaItems.size();
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
            ViewHolder viewHolder;
            if(view==null){
                view=View.inflate(context,R.layout.item_video_pager,null);
                viewHolder=new ViewHolder();
                viewHolder.tv_name=view.findViewById(R.id.tv_name);
                viewHolder.tv_duration=view.findViewById(R.id.tv_duration);
                viewHolder.tv_size=view.findViewById(R.id.tv_size);
                view.setTag(viewHolder);
            }else{
                viewHolder=(ViewHolder)view.getTag();
            }
            MediaItem mediaItem=mediaItems.get(i);
            viewHolder.tv_name.setText(mediaItem.getName());
            viewHolder.tv_size.setText(Formatter.formatFileSize(context,mediaItem.getSize()));
            return view;
        }
    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_duration;
        TextView tv_size;
    }

    private void getData() {
        new Thread(){
            public void run(){
                super.run();

                mediaItems=new ArrayList<MediaItem>();
                ContentResolver contentresolver= context.getContentResolver();
                Uri uri=MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objects={
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA
                };
                Cursor cursor = contentresolver.query(uri,objects,null,null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        MediaItem mediaitem=new MediaItem();
                        String name=cursor.getString(0);
                        mediaitem.setName(name);
                        long duration=cursor.getLong(1);
                        mediaitem.setDuration(duration);
                        long size=cursor.getLong(2);
                        mediaitem.setSize(size);
                        String data=cursor.getString(3);
                        mediaitem.setData(data);

                        mediaItems.add(mediaitem);
                    }
                    cursor.close();
                }
                handler.sendEmptyMessage(0);
            }
        }.start();
    }
}
