package com.example.q2_mediaplayer_siddharth;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private VideoView videoView;
    private TextView statusText;
    private ShapeableImageView albumArt;
    private static final int PICK_AUDIO_REQUEST = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        statusText = findViewById(R.id.statusText);
        albumArt = findViewById(R.id.album_art);

        videoView.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        // 1. audio from diskk
        findViewById(R.id.btnOpenFile).setOnClickListener(v -> {
            // Hide video and stop it before picking audio
            videoView.stopPlayback();
            videoView.setVisibility(View.GONE);

            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("audio/*");
            startActivityForResult(intent, PICK_AUDIO_REQUEST);
        });

        // 2.video from url
        findViewById(R.id.btnOpenURL).setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.reset();
            }

            albumArt.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);

            // url of video
            String liveUrl = "https://www.w3schools.com/html/mov_bbb.mp4";
            videoView.setVideoURI(Uri.parse(liveUrl));

            //brings the video to the black screen
            videoView.setZOrderOnTop(true);

            statusText.setText("Connecting to Stream...");

            // 5. When the video is ready, start it and update the status
            videoView.setOnPreparedListener(mp -> {
                statusText.setText("LIVE (Streaming)");
                videoView.start();
            });

            // 6. Handle Buffering if any
            videoView.setOnInfoListener((mp, what, extra) -> {
                if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START) {
                    statusText.setText("Buffering...");
                } else if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END) {
                    statusText.setText("LIVE (Streaming)");
                }
                return true;
            });

            // 7. If the URL fails, play the local video silently
            videoView.setOnErrorListener((mp, what, extra) -> {
                statusText.setText("Network Error - Loading Local");
                videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test_video));
                videoView.start();
                return true;
            });
        });
        // 3. CONTROLS buttons
        findViewById(R.id.btnPlay).setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.start();
            if (videoView.getVisibility() == View.VISIBLE) videoView.start();
            statusText.setText("Playing");
        });

        findViewById(R.id.btnPause).setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.pause();
            if (videoView.getVisibility() == View.VISIBLE) videoView.pause();
            statusText.setText("Paused");
        });

        findViewById(R.id.btnStop).setOnClickListener(v -> {
            if (mediaPlayer != null) mediaPlayer.stop();
            videoView.stopPlayback();
            statusText.setText("Stopped");
        });

        findViewById(R.id.btnRestart).setOnClickListener(v -> {
            if (mediaPlayer != null) { mediaPlayer.seekTo(0); mediaPlayer.start(); }
            if (videoView.getVisibility() == View.VISIBLE) { videoView.resume(); videoView.start(); }
            statusText.setText("Restarted");
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_AUDIO_REQUEST && resultCode == RESULT_OK && data != null) {
            // Hide video so we can see the Album Art
            videoView.setVisibility(View.GONE);
            albumArt.setVisibility(View.VISIBLE);

            Uri audioUri = data.getData();
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            try {
                mediaPlayer = MediaPlayer.create(this, audioUri);
                mediaPlayer.start();
                statusText.setText("Playing Audio from Disk");
            } catch (Exception e) {
                statusText.setText("Error loading audio");
            }
        }
    }
}