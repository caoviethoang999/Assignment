package com.example.hoangcv2_todo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListNoteFragment extends Fragment implements View.OnClickListener, SearchView.OnQueryTextListener {
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    SearchView searchView;
    FloatingActionButton floatingActionButton;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        loadData();
        searchView.setOnQueryTextListener(this);
        floatingActionButton.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_note, container, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fltbtnAdd:
                getToAddListNote();
            default:
                break;
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        searchData(newText);
        return true;
    }

    public void getToAddListNote() {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        AddListNoteFragment recylerFragment = new AddListNoteFragment();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
    }

    public void initView(View view) {
        recyclerView = view.findViewById(R.id.test);
        searchView = view.findViewById(R.id.searchview);
        floatingActionButton = view.findViewById(R.id.fltbtnAdd);
        todoAdapter = new TodoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    public void searchData(String newText) {
        List<Todo> list = TodoDatabase.getInstance(getContext()).TodoDAO().search(newText);
        todoAdapter.getAll(list);
        recyclerView.setAdapter(todoAdapter);
    }

    public void loadData() {
        List<Todo> list;
        list = TodoDatabase.getInstance(getContext()).TodoDAO().getListTodo();
        todoAdapter.getAll(list);
        recyclerView.setAdapter(todoAdapter);
    }
}