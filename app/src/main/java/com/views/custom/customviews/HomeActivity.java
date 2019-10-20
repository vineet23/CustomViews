package com.views.custom.customviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.views.custom.customviews.Views.Circular;
import com.views.custom.customviews.Views.Raidar;

public class HomeActivity extends AppCompatActivity {

    TextView gold,circular,atom,raidar,detector,bagHandle,circlesheet,trianglesheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gold = findViewById(R.id.gold);
        circular = findViewById(R.id.circular);
        atom = findViewById(R.id.atom);
        raidar = findViewById(R.id.raidar);
        detector = findViewById(R.id.detector);
        bagHandle = findViewById(R.id.bagHandle);
        circlesheet = findViewById(R.id.circlesheet);
        trianglesheet = findViewById(R.id.trianglesheet);

        gold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MainActivity.class));
            }
        });

        circular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CircularActivity.class));
            }
        });

        atom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,AtomActivity.class));
            }
        });

        raidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, RaidarActivity.class));
            }
        });

        detector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,DetectorActivity.class));
            }
        });

        bagHandle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HandleActivity.class));
            }
        });

        circlesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,SheetCircleActivity.class));
            }
        });

        trianglesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,SheetTriangleActivity.class));
            }
        });
    }
}
