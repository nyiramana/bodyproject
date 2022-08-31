package com.example.body.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.body.R;
import com.example.body.body_db.DBHandler;
import com.example.body.model.BodyTypesModal;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class BodyTypeForm extends Fragment {
    private FirebaseFirestore db;
    TextInputEditText titleField, heightField,weightField;
    SwitchMaterial sense;
    Button saveButton;
    private DBHandler dbHandler;
    int flag;
    final Handler handler = new Handler();

    public BodyTypeForm(int flag) {
        this.flag = flag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_body_type_form, container, false);
        titleField = view.findViewById(R.id.titleField);
        heightField = view.findViewById(R.id.heightField);
        weightField = view.findViewById(R.id.weightField);
        sense = view.findViewById(R.id.bad_or_good);
        saveButton = view.findViewById(R.id.saveButton);
        db = FirebaseFirestore.getInstance();
        dbHandler = new DBHandler(requireContext());
        dbHandler.open();

        saveButton.setOnClickListener(v -> {
            if (Objects.requireNonNull(titleField.getText()).toString().isEmpty()) {
                titleField.setError("bodyColor is required");
                return;
            }
            if (Objects.requireNonNull(heightField.getText()).toString().isEmpty()) {
                heightField.setError("bodyHeight is required");
                return;
            }
            if (Objects.requireNonNull(weightField.getText()).toString().isEmpty()) {
                weightField.setError("bodyWeight is required");
                return;
            }

            if (flag == 1) {
                saveToRemote(titleField.getText().toString(), heightField.getText().toString(),weightField.getText().toString(), sense.isChecked(), v);
                return;
            }

            long result = dbHandler.insert(titleField.getText().toString(), heightField.getText().toString(), weightField.getText().toString(),sense.isChecked());
            if (result > 0) {
                Snackbar snackbar = Snackbar.make(v, "Body well added!!!", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.BLUE);
                snackbar.show();

                handler.postDelayed(() -> {
                    // Do something after 2s = 2000ms
                    BodyLocalListFragment formFrag = new BodyLocalListFragment();
                    requireActivity().getSupportFragmentManager().beginTransaction().detach(this).commit();
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.thisView, formFrag, BodyTypeForm.class.getSimpleName())
                            .commit();
                }, 2000);
            } else {
                Snackbar snackbar = Snackbar.make(v, "Yoo! Something went wrong", Snackbar.LENGTH_LONG);
                snackbar.setBackgroundTint(Color.RED);
                snackbar.show();
            }
        });

        return view;
    }

    private void saveToRemote(String bodycolor, String bodyheight,String bodyweght, boolean disability, View v) {

        // creating a collection reference
        // for our Firebase Firestore database.
        CollectionReference dbSmellTypes = db.collection("body");

        // adding our data to our courses object class.
        BodyTypesModal smellTypes = new BodyTypesModal(bodycolor, bodyheight,bodyweght, disability ? 1 : 0, 1);
        Toast.makeText(getContext(), "Data saved ", Toast.LENGTH_SHORT).show();
        // below method is use to add data to Firebase Firestore.
        dbSmellTypes.add(smellTypes).addOnSuccessListener(documentReference -> {
            Snackbar snackbar = Snackbar.make(v, "wow! data saved successfully", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(Color.BLUE);
            snackbar.show();

            handler.postDelayed(() -> {
                // Do something after 2s = 2000ms
                BodyRemoteListFragment formFrag = new BodyRemoteListFragment();
                requireActivity().getSupportFragmentManager().beginTransaction().detach(this).commit();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.remoteLayout, formFrag, BodyTypeForm.class.getSimpleName())
                        .commit();
            }, 2000);

        }).addOnFailureListener(e -> {
            Snackbar snackbar = Snackbar.make(v, "yoo! something went wrong", Snackbar.LENGTH_LONG);
            snackbar.setBackgroundTint(Color.RED);
            snackbar.show();
        });
    }
}
