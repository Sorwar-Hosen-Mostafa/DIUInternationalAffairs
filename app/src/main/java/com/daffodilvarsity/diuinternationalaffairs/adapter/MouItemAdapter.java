package com.daffodilvarsity.diuinternationalaffairs.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daffodilvarsity.diuinternationalaffairs.Model.MOU;
import com.daffodilvarsity.diuinternationalaffairs.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import dmax.dialog.SpotsDialog;


/**
 * Created by Jibunnisa on 5/23/2017.
 */

public class MouItemAdapter extends RecyclerView.Adapter<MouItemAdapter.DerpHolder> {


    List<MOU> mous ;
    LayoutInflater layoutInflater;
    Context context;
    SpotsDialog spotsDialog;

    public MouItemAdapter(List<MOU> mous,Context context,SpotsDialog spotsDialog){
        this.mous= mous;
        this.context=context;
        layoutInflater= LayoutInflater.from(context);
        this.spotsDialog=spotsDialog;

    }

    @Override
    public MouItemAdapter.DerpHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.listspecificmouitem,parent,false);

        return new DerpHolder(view);
    }

    @Override
    public void onBindViewHolder(MouItemAdapter.DerpHolder holder, int position) {

        holder.country.setText(mous.get(position).getCountry());
        holder.title.setText(mous.get(position).getTitle());
        holder.website.setText(mous.get(position).getWebsite());
        Picasso.with(context)
                .load(mous.get(position).getFlagimageurl())
                .into(holder.flagimage);
        spotsDialog.dismiss();
    }

    @Override
    public int getItemCount() {
        return mous.size();
    }

    class DerpHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView flagimage;
        TextView website;
        TextView country;
        View view;
        public DerpHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.mou_item_title);
            website= (TextView) itemView.findViewById(R.id.mou_item_website);
            country= (TextView) itemView.findViewById(R.id.mou_item_title_country_name);
            flagimage= (ImageView) itemView.findViewById(R.id.mou_item_title_country_flag);
            view=itemView.findViewById(R.id.count_mou_root);
        }
    }
}
