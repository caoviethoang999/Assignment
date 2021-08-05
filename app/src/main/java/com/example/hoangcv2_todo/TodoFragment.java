package com.example.hoangcv2_todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class TodoFragment extends Fragment {
    EditText edttitle2,edtdes2;
    Button btnUpdate2,btnBack;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edttitle2=view.findViewById(R.id.edttitle2);
        edtdes2=view.findViewById(R.id.edtdes2);
        btnUpdate2=view.findViewById(R.id.btnUpdate2);
        btnBack=view.findViewById(R.id.btnBack);
        Bundle bundle = this.getArguments();
        String title = bundle.get("title").toString();
        String description = bundle.get("description").toString();
        edttitle2.setText(title);
        edtdes2.setText(description);
        edttitle2.setEnabled(false);
        btnUpdate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titlechange=edttitle2.getText().toString();
                String deschange=edtdes2.getText().toString();
                TodoDatabase.getInstance(v.getContext()).TodoDAO().updateTodo(deschange,titlechange);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                RecylerFragment recylerFragment = new RecylerFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false);
    }
}