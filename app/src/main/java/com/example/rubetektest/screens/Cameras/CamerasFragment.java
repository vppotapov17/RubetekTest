package com.example.rubetektest.screens.Cameras;

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
import com.example.rubetektest.adapters.CamerasAdapter;
import com.example.rubetektest.models.camera;

import java.util.ArrayList;
import java.util.List;

public class CamerasFragment extends Fragment {

    // список камер
    private List<camera> cameraList;

    private CamerasViewModel viewModel;

    public CamerasFragment(){}

    public static CamerasFragment newInstance(){
        return new CamerasFragment();
    }

    @Override
    public void onCreate(Bundle onSavedInstanceState) {
        super.onCreate(onSavedInstanceState);

        cameraList = new ArrayList<>();
        viewModel = new  ViewModelProvider(getActivity()).get(CamerasViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cameras, container, false);
    }

    @Override
    public void onResume(){
        super.onResume();

        RecyclerView recyclerView = getActivity().findViewById(R.id.camerasRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getCamerasLiveData().observe(this, cameras -> {
            cameraList.clear();
            cameraList.addAll(cameras);

            if (recyclerView.getAdapter() == null){
                CamerasAdapter adapter = new CamerasAdapter(cameraList);

                recyclerView.setAdapter(adapter);
            }
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
