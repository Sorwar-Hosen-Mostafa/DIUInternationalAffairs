package com.daffodilvarsity.diuinternationalaffairs.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.daffodilvarsity.diuinternationalaffairs.activity.EventDescriptionActivity;
import com.daffodilvarsity.diuinternationalaffairs.Model.Event;
import com.daffodilvarsity.diuinternationalaffairs.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by Jibunnisa on 12/4/2016.
 */

public class Event_Adapter extends RecyclerView.Adapter<Event_Adapter.DerpHolder> {

    List<Event> data ;
    LayoutInflater inflater;
    ClickedListener clickedListener;
    Context context;
    SpotsDialog progressDialog;
    public Event_Adapter(List<Event> data, Context context, SpotsDialog progressDialog){

        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.progressDialog=progressDialog;
    }
    public void setClickedListener(ClickedListener clickedListener){
        this.clickedListener = clickedListener;
    }

    @Override
    public DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.listspecificitem,parent,false);

        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(DerpHolder holder, int position) {

        final Event item = data.get(position);
        holder.title.setText(item.getTitle());
        Picasso.with(context)
                .load(item.getPicture())
                .into(holder.picture);
        holder.deadline.setText("Dead Line: "+item.getDeadline());

        progressDialog.dismiss();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,EventDescriptionActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("title",item.getTitle());
                i.putExtra("description",item.getDescription());
                i.putExtra("image",item.getPicture());
                i.putExtra("deadline","Dead Line: "+item.getDeadline().toString());
                context.startActivity(i);
            }
        });




    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class DerpHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView picture;
        TextView deadline;
        View view;

        public DerpHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.Specific_Event_Title);
            picture = (ImageView) itemView.findViewById(R.id.Specific_Event_Image);
            view = itemView.findViewById(R.id.count_item_root);
            deadline = (TextView) itemView.findViewById(R.id.Specific_Event_Deadline);
           

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if(clickedListener!=null){
                clickedListener.itemclick(v,getPosition());
            }
        }
    }
    public interface ClickedListener{
        public void itemclick(View view, int position);
    }
}
