package com.example.mediaplayer;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
//import android.widget.MediaController;
import android.view.View;
import android.widget.*;


public class SystemVideoPlayer extends Activity implements View.OnClickListener {
    private VideoView videoview;
    private Uri uri;
    private LinearLayout llTop;
    private TextView tvName;
    private ImageView ivTime;
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

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2018-07-24 17:54:05 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        llTop = (LinearLayout)findViewById( R.id.ll_top );
        tvName = (TextView)findViewById( R.id.tv_name );
        ivTime = (ImageView)findViewById( R.id.iv_time );
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
        uri=getIntent().getData();
        videoview.setVideoURI(uri);

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
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
       // videoview.setMediaController(new MediaController(this));
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
