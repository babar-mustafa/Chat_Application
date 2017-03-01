package com.example.babarmustafa.chatapplication.GroupProfile;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.babarmustafa.chatapplication.Groups.Group_coversation;
import com.example.babarmustafa.chatapplication.Groups.MyRecyclerViewAdapter;
import com.example.babarmustafa.chatapplication.Groups.groups_create_info;
import com.example.babarmustafa.chatapplication.R;
import com.example.babarmustafa.chatapplication.Signup_Adapter;
import com.example.babarmustafa.chatapplication.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GroupProfile extends AppCompatActivity {
    ImageView imageView;
    Toolbar toolbar;
    TextView name, email, gender;
    RecyclerView group_members;
    MyRecyclerViewAdapter adapter;
    ArrayList<User> users_list;
    User data;
    String groupName;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_profile);
        users_list = new ArrayList<>();
        list = new ArrayList<>();
        imageView = (ImageView) findViewById(R.id.mainbackdropforgroup);
        toolbar = (Toolbar) findViewById(R.id.maintoolbar);
        group_members = (RecyclerView) findViewById(R.id.rvOrderList);
        adapter = new MyRecyclerViewAdapter(this, users_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        group_members.setLayoutManager(mLayoutManager);
        group_members.setItemAnimator(new DefaultItemAnimator());
        group_members.setAdapter(adapter);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        gender = (TextView) findViewById(R.id.gender);
        groupName = getIntent().getStringExtra("groupname");
        String AdminName = getIntent().getStringExtra("AdminName");
        String groupImage = getIntent().getStringExtra("groupImage");
        Glide.with(GroupProfile.this).load(groupImage).into(imageView);
        FirebaseDatabase
                .getInstance()
                .getReference()
                .child("My_Groups")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot != null) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Log.d("Testing", " Key " + dataSnapshot.getKey() + " values " + dataSnapshot.getValue().toString());
                                String s = snapshot.getKey().toString();
                                Toast.makeText(GroupProfile.this, "" + s, Toast.LENGTH_SHORT).show();
                                if (snapshot.hasChild(groupName)) {
                                    Toast.makeText(GroupProfile.this, "true", Toast.LENGTH_SHORT).show();
                                    FirebaseDatabase
                                            .getInstance()
                                            .getReference()
                                            .child("User Info")
                                            .child(s)
                                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot !=null) {
                                                        data = dataSnapshot.getValue(User.class);
                                                        list.add(data.getUID());
                                                        User email = new User(data.getUID(),data.getName(), data.getEmail(), data.getPassword(), data.getGEnder(), data.getProfile_image());
                                                        users_list.add(email);
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError databaseError) {

                                                }
                                            });
//
                                } else {
                                    Toast.makeText(GroupProfile.this, "false", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.maincollapsing);
        collapsingToolbarLayout.setTitle(groupName);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.cardview_light_background));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }
}
