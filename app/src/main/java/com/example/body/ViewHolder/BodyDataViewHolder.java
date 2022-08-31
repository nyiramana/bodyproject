package com.example.body.ViewHolder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.body.R;
import com.example.body.model.BodyTypesModal;


public class BodyDataViewHolder extends RecyclerView.ViewHolder {
    TextView bodyId;
    TextView bodycolor;
    TextView bodyheight;
    TextView bodyweight;
    TextView bodydisability;
    Context context;

    public BodyDataViewHolder(@NonNull View itemView, Context context) {
        super(itemView);
        this.context = context;
        bodyId= itemView.findViewById(R.id.body_id);
        bodycolor = itemView.findViewById(R.id.bodycolor);
        bodyheight = itemView.findViewById(R.id.bodyheight);
        bodyweight = itemView.findViewById(R.id.bodyweight);
        bodydisability = itemView.findViewById(R.id.disability);
    }

    public void BindBodyData(final BodyTypesModal smellData) {
        System.out.println(smellData.getDisability());
        bodyId.setText(String.valueOf(smellData.getId()));
        bodycolor.setText(smellData.getbodycolor());
        bodyheight.setText(smellData.getBodyheight());
        bodyweight.setText(smellData.getBodyweight());
        bodydisability.setText(smellData.getDisability() ? "Yes" : "No");
    }
}
