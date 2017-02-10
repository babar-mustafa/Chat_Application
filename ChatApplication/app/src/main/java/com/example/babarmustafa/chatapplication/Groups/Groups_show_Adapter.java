package com.example.babarmustafa.chatapplication.Groups;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.babarmustafa.chatapplication.R;
import com.example.babarmustafa.chatapplication.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by BabarMustafa on 2/8/2017.
 */

//public class Groups_show_Adapter {
//}

public class Groups_show_Adapter  extends BaseAdapter {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private com.example.babarmustafa.chatapplication.Signup_Adapter listapater;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    private ArrayList< groups_create_info> dataList;
    private Context context;

    public Groups_show_Adapter (ArrayList< groups_create_info> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public  groups_create_info getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        // final View inflater = LayoutInflater.from(MainView.this).inflate(R.layout.for_messages,null);
        View view = inflater.inflate(R.layout.single_group_show, null);


        TextView forname = (TextView) view.findViewById(R.id.g_v_username);
        CircularImageView pcircularImageView = (CircularImageView) view.findViewById(R.id.g_profile_view);

        final  groups_create_info data = dataList.get(position);

        String username = data.getGroup_name();
//        String iml = data.getProfile_image();
//        Toast.makeText(context, ""+iml, Toast.LENGTH_SHORT).show();



//mAuth.getCurrentUser().toString();

        //to still the condition after changes
        final  groups_create_info todoChekd = ( groups_create_info) getItem(position);
        forname.setText(dataList.get(position).getGroup_name());
//        System.out.print(""+data.getProfile_image());
        Picasso.with(context).load(data.getG_i_url()).into(pcircularImageView);


        return view;
    }
}
