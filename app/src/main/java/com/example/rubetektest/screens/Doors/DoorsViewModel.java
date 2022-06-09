package com.example.rubetektest.screens.Doors;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.rubetektest.models.door;
import com.example.rubetektest.api.NetworkService;
import com.example.rubetektest.api.POJOs.DoorsPOJO;
import com.example.rubetektest.database.DBs.doorsDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoorsViewModel extends AndroidViewModel {

    // база данных Room
    doorsDB mDoorsDB;

    // livedata со списком дверей
    private MutableLiveData<List<door>> doorsLiveData;

    // список дверей
    List<door> doorList;

    public DoorsViewModel(Application application){
        super(application);

        doorsLiveData = new MutableLiveData<>();
        doorList = new ArrayList<>();

        // подключение к базе данных Room
        mDoorsDB = Room.databaseBuilder(application.getApplicationContext(), doorsDB.class, "Doors").build();

        // получаем данные из Room в отдельном потоке
        new Thread(new Runnable() {
            @Override
            public void run() {
                doorList = mDoorsDB.getDoorsDao().getDoors();

                // если список в БД пуст, получаем данные с сервера
                // если нет, обновляем LiveData
                if (doorList.size() == 0) getDataFromServer();
                else{
                    Log.d("AAA", "Данные о дверях получены из Room");
                    doorsLiveData.postValue(doorList);
                }
            }
        }).start();

    }

    public void getDataFromServer(){
        NetworkService.getInstance().getDoorsAPI().getDoors().enqueue(new Callback<DoorsPOJO>() {
            @Override
            public void onResponse(Call<DoorsPOJO> call, Response<DoorsPOJO> response) {
                DoorsPOJO doorsPOJO = response.body();
                doorList = doorsPOJO.getDoorList();

                // кэширование данных в Room

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // очистка базы данных
                        for (door d : mDoorsDB.getDoorsDao().getDoors()){
                            mDoorsDB.getDoorsDao().deleteDoor(d);
                        }

                        // добавление обновленных данных с сервера
                        for (door d : doorList)
                            mDoorsDB.getDoorsDao().addDoor(d);
                    }
                }).start();

                // обновление LiveData
                doorsLiveData.postValue(doorList);

                Log.d("AAA", "Данные о дверях получены с сервера");
            }

            @Override
            public void onFailure(Call<DoorsPOJO> call, Throwable t) {
                Log.d("AAA", t.getMessage());
            }
        });
    }

    public LiveData<List<door>> getDoorsLiveData(){
        return doorsLiveData;
    }
}
