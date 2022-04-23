package com.uogfyp.kidolearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.kiprotich.japheth.TextAnim;
import com.uogfyp.kidolearning.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DashboardActivity";
    private static final int DELAY = 2000;
    private ActivityMainBinding binding;
    private MediaPlayer mediaPlayer;
    private AssetFileDescriptor assetFileDescriptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
    }

    private void init() {
        // Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE);

        // Animate App Name
        binding.txtAppName
                .setWidth(16f)
                .setDelay(10)
                .setColor(Color.WHITE)
                .setConfig(TextAnim.Configuration.INTERMEDIATE)
                .setSizeFactor(26f)
                .setLetterSpacing(16f)
                .setText("KIDO  LEARNING")
                .startAnimation();

        // Play Splash Start Audio (AFTER 02 SECONDS)
        new Handler().postDelayed(() -> {
            try {
                mediaPlayer = new MediaPlayer();
                assetFileDescriptor = getAssets().openFd("splash_start.ogg");
                mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
                assetFileDescriptor.close();

                mediaPlayer.prepare();
                mediaPlayer.setVolume(1f, 1f);
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, DELAY);
    }
}