package com.example.rubetektest.screens.Doors;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.rubetektest.R;
import com.example.rubetektest.adapters.DoorsAdapter;
import com.example.rubetektest.models.door;

import java.util.ArrayList;
import java.util.List;

public class DoorsFragment extends Fragment {

    private List<door> doorList;

    private DoorsViewModel viewModel;

    public DoorsFragment(){}

    public static DoorsFragment newInstance(){
        return new DoorsFragment();
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);

        doorList = new ArrayList<>();
        viewModel = new ViewModelProvider(getActivity()).get(DoorsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doors, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        RecyclerView recyclerView = getActivity().findViewById(R.id.doorsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getDoorsLiveData().observe(this, doors ->{
            doorList.clear();
            doorList.addAll(doors);

            if (recyclerView.getAdapter() == null)
                recyclerView.setAdapter(new DoorsAdapter(doorList));
            else recyclerView.getAdapter().notifyDataSetChanged();
        });

        // принудительное обновление данных с сервера
        SwipeRefreshLayout swipeRefreshLayout = getActivity().findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.getDataFromServer();
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}
