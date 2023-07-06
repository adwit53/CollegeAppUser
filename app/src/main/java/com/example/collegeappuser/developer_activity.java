package com.example.collegeappuser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class developer_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button linkedInButton = findViewById(R.id.linkedIn);
        linkedInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLinkedInProfile();
            }
        });
    }

    private void openLinkedInProfile() {
        // Replace "https://www.linkedin.com/in/developer-profile" with the actual LinkedIn profile URL
        String linkedInProfileUrl = "https://www.linkedin.com/in/adwit-agrawal-079a63239/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkedInProfileUrl));
        startActivity(intent);
    }
}
