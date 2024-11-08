package com.remonn.base64;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Menampilkan halaman loading selama 2 detik
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Memulai MainActivity setelah 2 detik
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Menutup SplashActivity agar tidak kembali saat menekan tombol kembali
            }
        }, 2000); // 2000ms = 2 detik
    }
}
