package com.example.mediaplayer;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
//import android.widget.MediaController;
import android.view.View;
import android.widget.*;

import com.example.mediaplayer.domain.MediaItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SystemVideoPlayer extends Activity implements View.OnClickListener {
    private static final int PROGRESS=0;
    private VideoView videoview;
    private Uri uri;
    private LinearLayout llTop;
    private TextView tvName;
    private ImageView ivBattery;
    private TextView tvTime;
    private Button btnVoice;
    private SeekBar seekbarVoice;
    private Button btnSwitchPlayer;
    private LinearLayout llBottom;
    private TextView tvCurrentTime;
    private SeekBar seekbarVideo;
    private TextView tvDuration;
    private Button btnVoiceExit;
    private Button btnVoicePre;
    private Button btnVoiceStartPause;
    private Button btnVoiceNext;
    private Button btnVoiceSwitchScreen;
    private BatteryReceiver receiver;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case PROGRESS:
                    int currentPosition=videoview.getCurrentPosition();
                    seekbarVideo.setProgress(currentPosition);

                    //tvCurrentTime.setText(currentPosition);
                    tvTime.setText(getSystemTime());
                    handler.removeMessages(PROGRESS);
                    handler.sendEmptyMessageDelayed(PROGRESS,1000);
                    break;
            }
        }

    };
    private  ArrayList<MediaItem> mediaItems;
    private int position;

    private String getSystemTime() {
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }


    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-07-24 17:54:05 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        llTop = (LinearLayout)findViewById( R.id.ll_top );
        tvName = (TextView)findViewById( R.id.tv_name );
        ivBattery = (ImageView)findViewById( R.id.iv_battery );
        tvTime = (TextView)findViewById( R.id.tv_time );
        btnVoice = (Button)findViewById( R.id.btn_voice );
        seekbarVoice = (SeekBar)findViewById( R.id.seekbar_voice );
        btnSwitchPlayer = (Button)findViewById( R.id.btn_switch_player );
        llBottom = (LinearLayout)findViewById( R.id.ll_bottom );
        tvCurrentTime = (TextView)findViewById( R.id.tv_current_time );
        seekbarVideo = (SeekBar)findViewById( R.id.seekbar_video );
        tvDuration = (TextView)findViewById( R.id.tv_duration );
        btnVoiceExit = (Button)findViewById( R.id.btn_voice_exit );
        btnVoicePre = (Button)findViewById( R.id.btn_voice_pre );
        btnVoiceStartPause = (Button)findViewById( R.id.btn_voice_start_pause );
        btnVoiceNext = (Button)findViewById( R.id.btn_voice_next );
        btnVoiceSwitchScreen = (Button)findViewById( R.id.btn_voice_switch_screen );

        btnVoice.setOnClickListener(this);
        btnSwitchPlayer.setOnClickListener(this);
        btnVoiceExit.setOnClickListener(this);
        btnVoicePre.setOnClickListener(this);
        btnVoiceStartPause.setOnClickListener(this);
        btnVoiceNext.setOnClickListener(this);
        btnVoiceSwitchScreen.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2018-07-24 17:54:05 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btnVoice ) {
            // Handle clicks for btnVoice
        } else if ( v == btnSwitchPlayer ) {
            // Handle clicks for btnSwitchPlayer
        } else if ( v == btnVoiceExit ) {
            // Handle clicks for btnVoiceExit
        } else if ( v == btnVoicePre ) {
            // Handle clicks for btnVoicePre
        } else if ( v == btnVoiceStartPause ) {
            if(videoview.isPlaying()){
                videoview.pause();
                btnVoiceStartPause.setBackgroundResource(R.drawable.btn_voice_start_selector);
            }else{
                videoview.start();
                btnVoiceStartPause.setBackgroundResource(R.drawable.btn_voice_pause_selector);
            }
            // Handle clicks for btnVoiceStartPause
        } else if ( v == btnVoiceNext ) {
            // Handle clicks for btnVoiceNext
        } else if ( v == btnVoiceSwitchScreen ) {
            // Handle clicks for btnVoiceSwitchScreen
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        videoview=findViewById(R.id.videoview);
        findViews();
        getData();
        setData();
        setListener();
       // videoview.setMediaController(new MediaController(this));

        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        receiver=new BatteryReceiver();
        registerReceiver(receiver,intentFilter);
    }

    private void setData() {
        if(mediaItems!=null&&mediaItems.size()>0){
            MediaItem mediaItem=mediaItems.get(position);
            videoview.setVideoPath(mediaItem.getData());
            tvName.setText(mediaItem.getName());
        }else if(uri!=null){
            videoview.setVideoURI(uri);
            tvName.setText(uri.toString());
        }

    }

    protected void getData(){
        uri=getIntent().getData();
        mediaItems= (ArrayList<MediaItem>) getIntent().getSerializableExtra("videolist");
        position =getIntent().getIntExtra("position",0);
        //
    }
    protected void setListener() {
        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                int duration=videoview.getDuration();
                seekbarVideo.setMax(duration);
               // tvDuration.setText(duration);
                handler.sendEmptyMessage(PROGRESS);
                videoview.start();
            }
        });

        videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                Toast.makeText(SystemVideoPlayer.this,"Error",Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Toast.makeText(SystemVideoPlayer.this,"Completion",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        seekbarVideo.setOnSeekBarChangeListener(new VideoOnSeekBarChangeListener());
    }

    class VideoOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            if(b){
                videoview.seekTo(i);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    class BatteryReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int level=intent.getIntExtra("level",0);
            setBattery(level);
        }


    }
    private void setBattery(int level) {
        if(level<=0){
            ivBattery.setImageResource(R.drawable.ic_battery_0);
        }else if(level<=10){
            ivBattery.setImageResource(R.drawable.ic_battery_10);
        }else if(level<=20){
            ivBattery.setImageResource(R.drawable.ic_battery_20);
        }else if(level<=40){
            ivBattery.setImageResource(R.drawable.ic_battery_40);
        }else if(level<=60){
            ivBattery.setImageResource(R.drawable.ic_battery_60);
        }else if(level<=80){
            ivBattery.setImageResource(R.drawable.ic_battery_80);
        }else if(level<=100){
            ivBattery.setImageResource(R.drawable.ic_battery_100);
        }else{
            ivBattery.setImageResource(R.drawable.ic_battery_100);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {

        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver=null;
        }
        super.onDestroy();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Intent intent=new Intent(this,TestActivity.class);
                startActivity(intent);
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
