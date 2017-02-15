package com.example.babarmustafa.chatapplication.GroupProfile;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.babarmustafa.chatapplication.R;

public class GroupProfile extends AppCompatActivity {
    ImageView imageView;
    Toolbar toolbar;
    TextView name, email, gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);
        imageView = (ImageView) findViewById(R.id.mainbackdropforgroup);
        toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        gender = (TextView) findViewById(R.id.gender);
        String groupName = getIntent().getStringExtra("groupname");
        String AdminName = getIntent().getStringExtra("AdminName");
        String groupImage = getIntent().getStringExtra("groupImage");
        Glide.with(GroupProfile.this).load(groupImage).into(imageView);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.maincollapsing);
        collapsingToolbarLayout.setTitle(groupName);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.cardview_light_background));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
}
