package com.example.rubetektest.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.rubetektest.models.camera;
import com.example.rubetektest.repositories.retrofit.NetworkService;
import com.example.rubetektest.repositories.retrofit.POJOs.CamerasPOJO;
import com.example.rubetektest.repositories.room.DBs.camerasDB;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CamerasViewModel extends AndroidViewModel{

    // база данных Room
    camerasDB mCamerasDB;

    // livedata со списком камер
    private MutableLiveData<List<camera>> camerasLiveData;

    // список камер
    private List<camera> cameraList;

    public CamerasViewModel(Application application){
        super(application);

        camerasLiveData = new MutableLiveData<>();
        cameraList = new ArrayList<>();

        // подключение к базе данных Room
        mCamerasDB = Room.databaseBuilder(application, camerasDB.class, "Cameras").build();

        // получение данных из Room в отдельном потоке
        new Thread(new Runnable() {
            @Override
            public void run() {
                cameraList = mCamerasDB.getCamerasDao().getCameras();

                // если список в базе пуст, получаем данные с сервера
                // если нет, обновляем LiveData
                if (cameraList.size() == 0) getDataFromServer();
                else {
                    camerasLiveData.postValue(cameraList);
                    Log.d("AAA", "Данные о камерах получены из Room");
                }
            }
        }).start();
    }

    // метод для загрузки данных с сервера
    public void getDataFromServer(){
        NetworkService.getInstance().getCamerasAPI().getCameras().enqueue(new Callback<CamerasPOJO>() {
            @Override
            public void onResponse(Call<CamerasPOJO> call, Response<CamerasPOJO> response) {
                CamerasPOJO camerasPOJO = response.body();

                cameraList = camerasPOJO.getData().getCameras();

                // кэширование данных в Room

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        // очистка базы данных
                        for (camera c : mCamerasDB.getCamerasDao().getCameras()){
                            mCamerasDB.getCamerasDao().deleteCamera(c);
                        }

                        // добавление обновленных данных с сервера
                        for (camera c : cameraList)
                            mCamerasDB.getCamerasDao().addCamera(c);
                    }
                }).start();

                // обновление LiveData
                camerasLiveData.postValue(cameraList);

                Log.d("AAA", "Данные о камерах получены с сервера");
            }

            @Override
            public void onFailure(Call<CamerasPOJO> call, Throwable t) {
                Log.d("AAA", t.getMessage());
            }
        });
    }

    public MutableLiveData<List<camera>> getCamerasLiveData() {
        return camerasLiveData;
    }
}
