package com.views.custom.customviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.views.custom.customviews.Views.BagHandle;

public class HandleActivity extends AppCompatActivity {

    BagHandle bagHandle;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handle);

        bagHandle = findViewById(R.id.handle);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bagHandle.startAnimate();
            }
        });
    }
}
