package com.example.lend;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList cleaner_id, cleaner_name,cleaner_phonenumber;

    CustomAdapter(Activity activity, Context context, ArrayList cleaner_id, ArrayList cleaner_name, ArrayList cleaner_phonenumber) {
        this.activity = activity;
        this.context = context;
        this.cleaner_id = cleaner_id;
        this.cleaner_name = cleaner_name;
        this.cleaner_phonenumber = cleaner_phonenumber;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.cleaner_id_txt.setText(String.valueOf(cleaner_id.get(position)));
        holder.cleaner_name_txt.setText(String.valueOf(cleaner_name.get(position)));
        holder.cleaner_phonenumber_txt.setText(String.valueOf(cleaner_phonenumber.get(position)));

        //Recyclerview onClickListener



    }

    @Override
    public int getItemCount() {
        return cleaner_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cleaner_id_txt, cleaner_name_txt, cleaner_phonenumber_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cleaner_id_txt = itemView.findViewById(R.id.cleaner_id_txt);
            cleaner_name_txt = itemView.findViewById(R.id.cleaner_name_txt);
            cleaner_phonenumber_txt = itemView.findViewById(R.id.cleaner_phonenumber_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
