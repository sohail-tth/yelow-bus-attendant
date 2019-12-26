package com.tth.yelowbus_attendant.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tth.yelowbus_attendant.R;
import com.tth.yelowbus_attendant.model.EventModel;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    private Context context;
    private List<EventModel> list = new ArrayList<>();
    EventClickListener eventClickListener;

    public EventAdapter(Context context, List<EventModel> list, EventClickListener eventClickListener) {
        this.context = context;
        this.list = list;
        this.eventClickListener = eventClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final EventModel model = list.get(position);

        holder.tvTitle.setText(model.getTitle());
        holder.tvSubTitle.setText(model.getSubTitle());
        holder.tvDate.setText(model.getDate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eventClickListener != null){
                    eventClickListener.onEventClick(model);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvSubTitle, tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvSubTitle = itemView.findViewById(R.id.tvSubTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    public interface EventClickListener{
        void onEventClick(EventModel tripModel);
    }
}
