package com.example.body.Fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.body.Adapters.BodyDataAdapter;
import com.example.body.body_db.DBHandler;
import com.example.body.model.BodyTypesModal;
import com.example.body.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class BodyLocalListFragment extends Fragment {
    RecyclerView userDataRecyclerView;
    FloatingActionButton fabLocal;
    ArrayList<BodyTypesModal> smellDataArrayList;
    BodyDataAdapter smellDataAdapter;
    DBHandler dbHandler;
    TextView noData;

    @SuppressLint("NotifyDataSetChanged")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.body_local_list_fragment, container, false);
        fabLocal = view.findViewById(R.id.fab_local);
        userDataRecyclerView = view.findViewById(R.id.smellrecyclerview);
        dbHandler = new DBHandler(requireContext());
        dbHandler.open();
        Cursor cursor = dbHandler.getSmellTypes();
        noData = view.findViewById(R.id.no_data);

        smellDataArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            if (smellDataArrayList != null) {
                smellDataArrayList.clear();
                noData.setVisibility(View.VISIBLE);
            }
            noData.setVisibility(View.GONE);
            smellDataArrayList.clear();
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow("body_color"));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("body_height"));
                String des = cursor.getString(cursor.getColumnIndexOrThrow("body_weight"));
                String sense = cursor.getString(cursor.getColumnIndexOrThrow("disability"));
                smellDataArrayList.add(new BodyTypesModal(title, desc, des, Integer.parseInt(sense), smellDataArrayList.size()));
            } while (cursor.moveToNext());

            smellDataAdapter = new BodyDataAdapter(smellDataArrayList, getContext());
            userDataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            userDataRecyclerView.setVerticalScrollBarEnabled(true);
            userDataRecyclerView.setAdapter(smellDataAdapter);
        }
//        dbHandler.close();

        fabLocal.setOnClickListener(v-> {
            BodyTypeForm formFrag= new BodyTypeForm(0);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.thisView, formFrag, BodyTypeForm.class.getSimpleName())
                    .commit();
        });

        return view;
    }

}
