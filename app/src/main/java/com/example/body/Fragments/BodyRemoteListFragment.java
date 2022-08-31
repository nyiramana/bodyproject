package com.example.body.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.body.Adapters.BodyDataAdapter;
import com.example.body.R;
import com.example.body.model.BodyTypesModal;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class BodyRemoteListFragment extends Fragment {

    private FirebaseFirestore db;
    RecyclerView smellDataRecyclerView;
    ArrayList<BodyTypesModal> smellDataArrayList;
    BodyDataAdapter smellDataAdapter;
    FloatingActionButton fabRemote;
    TextView noData;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.body_remote_list_fragment, viewGroup, false);
        smellDataArrayList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        smellDataRecyclerView = view.findViewById(R.id.smellrecyclerview);
        fabRemote = view.findViewById(R.id.fab_remote);
        noData = view.findViewById(R.id.no_data);
        noData.setVisibility(View.VISIBLE);

        fabRemote.setOnClickListener(v -> {
            BodyTypeForm formFrag = new BodyTypeForm(1);
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.remoteLayout, formFrag, BodyTypeForm.class.getSimpleName())
                    .commit();
        });

        smellDataArrayList.clear();
        db.collection("body")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            smellDataArrayList.add(new BodyTypesModal(Objects.requireNonNull(document.getData().get("bodycolor")).toString(), Objects.requireNonNull(document.getData().get("bodyheight")).toString(),Objects.requireNonNull(document.getData().get("bodyweight")).toString(), (boolean) document.getData().get("disability") ? 1 : 0, smellDataArrayList.size()));
                        }
                    } else {
                        System.out.println("Error getting documents: " + task.getException());
                    }
                    if (smellDataArrayList.size() < 1) {
                        noData.setVisibility(View.VISIBLE);
                    }
                    smellDataAdapter = new BodyDataAdapter(smellDataArrayList, getContext());
                    smellDataRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    smellDataRecyclerView.setVerticalScrollBarEnabled(true);
                    smellDataRecyclerView.setAdapter(smellDataAdapter);
                });
        return view;
    }

}