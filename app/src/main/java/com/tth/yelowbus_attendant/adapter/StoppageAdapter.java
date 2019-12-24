package com.tth.yelowbus_attendant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.model.StoppageModel;

import java.util.ArrayList;
import java.util.List;

public class StoppageAdapter extends RecyclerView.Adapter<StoppageAdapter.ViewHolder>{

    private Context context;
    private List<StoppageModel> modelList = new ArrayList<>();


    public StoppageAdapter(Context context, List<StoppageModel> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_stop_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StoppageModel model = modelList.get(position);

        holder.tvTime.setText(model.getTime());
        holder.tvName.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTime, tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvName = itemView.findViewById(R.id.tvName);
        }
    }
}
