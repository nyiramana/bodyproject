package com.example.body.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.body.ViewHolder.BodyDataViewHolder;
import com.example.body.model.BodyTypesModal;
import com.example.body.R;

import java.util.ArrayList;

public class BodyDataAdapter extends RecyclerView.Adapter<BodyDataViewHolder> {
    ArrayList<BodyTypesModal> userDataArrayList;
    BodyDataViewHolder smellDataViewHolder;
    Context context;

    public BodyDataAdapter(ArrayList<BodyTypesModal> smellDataList, Context context) {
        this.userDataArrayList = smellDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public BodyDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.body_type, parent, false);
        return new BodyDataViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull BodyDataViewHolder holder, int position) {
        final BodyTypesModal smellData = userDataArrayList.get(position);
        holder.BindBodyData(smellData);
        smellDataViewHolder = holder;
    }

    @Override
    public int getItemCount() {
        return userDataArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
