package com.remonn.base64;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonEncode;
    //private TextView textViewOutput;
    private EditText editTextBase64;
    private Button buttonDecode;
    //private TextView textViewDecodedOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        buttonEncode = findViewById(R.id.buttonEncode);
        //textViewOutput = findViewById(R.id.textViewOutput);
        editTextBase64 = findViewById(R.id.editTextBase64);
        buttonDecode = findViewById(R.id.buttonDecode);
        //textViewDecodedOutput = findViewById(R.id.textViewDecodedOutput);

        buttonEncode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputText = editTextInput.getText().toString();
                String encodedText = encodeToBase64(inputText);
                //textViewOutput.setText(encodedText);
                showAlertDialog("Hasil Encoding", encodedText);  // Menampilkan dialog setelah encode
            }
        });

        buttonDecode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String base64Text = editTextBase64.getText().toString();
                String decodedText = decodeFromBase64(base64Text);
                //textViewDecodedOutput.setText(decodedText);
                showAlertDialog("Hasil Decoding", decodedText);  // Menampilkan dialog setelah decode
            }
        });
    }

    private String encodeToBase64(String input) {
        byte[] data = input.getBytes();
        return Base64.encodeToString(data, Base64.DEFAULT);
    }

    private String decodeFromBase64(String base64) {
        byte[] decodedBytes = Base64.decode(base64, Base64.DEFAULT);
        return new String(decodedBytes);
    }

    // Menampilkan AlertDialog untuk hasil encoding atau decoding
    private void showAlertDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton("Copy to Clipboard", (dialog, which) -> {
            // Menyalin teks ke clipboard
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Encoded/Decoded Text", message);
            if (clipboard != null) {
                clipboard.setPrimaryClip(clip);
            }
        });
        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
