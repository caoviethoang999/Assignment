package com.example.hoangcv2_todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import java.util.List;


public class RecylerFragment extends Fragment {
    EditText edttitle,edtdes;
    Button btnAdd,btnList;
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    SearchView searchView;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.test);
        edttitle=view.findViewById(R.id.edttitle);
        edtdes=view.findViewById(R.id.edtdes);
        btnAdd=view.findViewById(R.id.btnAdd);
        btnList=view.findViewById(R.id.btnList);
        searchView=view.findViewById(R.id.searchview);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                List<Todo> list=TodoDatabase.getInstance(getContext()).TodoDAO().search(newText);
                todoAdapter.getAll(list);
                recyclerView.setAdapter(todoAdapter);
                return true;
            }
        });
        todoAdapter=new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=edttitle.getText().toString();
                String des=edtdes.getText().toString();
                Todo todo=new Todo(title,des);
                TodoDatabase.getInstance(v.getContext()).TodoDAO().insertTodo(todo);
            }
        });
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Todo> list;
                list=TodoDatabase.getInstance(v.getContext()).TodoDAO().getListtodo();
                todoAdapter.getAll(list);
                edtdes.setText(String.valueOf(todoAdapter.getItemCount()));
                recyclerView.setAdapter(todoAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recyler, container, false);
    }
}