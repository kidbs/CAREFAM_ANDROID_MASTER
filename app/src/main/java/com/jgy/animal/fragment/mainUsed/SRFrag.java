package com.jgy.animal.fragment.mainUsed;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jgy.animal.Adapter.CustomAdapter;
import com.jgy.animal.Adapter.ViewItemData;
import com.jgy.animal.BaseUrl;
import com.jgy.animal.R;
import com.jgy.animal.interfaces.AnimalApiService;
import com.jgy.animal.Entities.AnimalEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SRFrag extends Fragment {

    public final static String KEY_INPUT_QUERY = "INPUT_QUERY";

    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;
    private ProgressBar progressBar;
    private Call<Integer> indexLengthCall;
    private Call<AnimalEntity> animalDtoCall;
    private AnimalApiService apiService;
    private int apiLength;
    private int loadedApiLength;
    private String query;
    private boolean isLoadingData;  // 데이터 로딩 중인지 여부를 나타내는 플래그 변수


    private RecyclerView.OnScrollListener scrollListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View srView = inflater.inflate(R.layout.recycler_view, container, false);
        progressBar = srView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.100.108:23468") //학원
//                .baseUrl("http://192.168.0.133:23468") // 집 wifi 서버의 기본 URL
//                .baseUrl("http://172.30.1.24:23468")
                .baseUrl(BaseUrl.url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(AnimalApiService.class);

        return srView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        onSearchRequested(bundle != null ? bundle : new Bundle());
    }

    private void loadData() {
        if (loadedApiLength >= apiLength || isLoadingData) {
            if (customAdapter.getItemCount() == 0) {
                customAdapter.clear(); // 어댑터 초기화
                TextView noResultsTextView = getView().findViewById(R.id.emptyView);
                progressBar.setVisibility(View.INVISIBLE);
                noResultsTextView.setVisibility(View.VISIBLE);
            } else {
                TextView noResultsTextView = getView().findViewById(R.id.emptyView);
                progressBar.setVisibility(View.INVISIBLE);
                noResultsTextView.setVisibility(View.GONE);
            }
            return;
        }

        isLoadingData = true;  // 데이터 로딩 중 플래그를 설정합니다.

        animalDtoCall = apiService.getSearchFacility(query, loadedApiLength);
        animalDtoCall.enqueue(new Callback<AnimalEntity>() {
            @Override
            public void onResponse(Call<AnimalEntity> call, Response<AnimalEntity> response) {
                loadedApiLength++;

                if (isRemoving()) {
                    isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
                    return;
                }
                if (!response.isSuccessful()) {
                    isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
                    return;
                }

                // 데이터 처리 로직

                isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
                AnimalEntity apiInfo = response.body();

                String placeName = apiInfo.getFacility();
                String placeAddr1 = apiInfo.getCity();
                String placeAddr2 = apiInfo.getMunicipality();
                String placeInfo = apiInfo.getCategory3();
                String placeOtherInfo = apiInfo.getParking();
                String phoneNumber = apiInfo.getPhoneNumber();
                String operatingTime = apiInfo.getOperatingTime();
                String homePage = apiInfo.getHomePage();
                String closedDays = apiInfo.getClosedDays();
                String parking =apiInfo.getParking();


                ViewItemData viewItemData = new ViewItemData(placeName, placeAddr1, placeAddr2, placeOtherInfo, placeInfo);
                viewItemData.setPhoneNumber(phoneNumber);
                viewItemData.setOperatingTime(operatingTime);
                viewItemData.setWebsite(homePage);
                viewItemData.setClosedDays(closedDays);
                viewItemData.setParking(parking);

                // 중복 데이터 확인 후 추가합니다
                if (!customAdapter.containsItem(viewItemData)) {
                    customAdapter.add(viewItemData);
                    progressBar.setVisibility(View.GONE);
                }

                // 추가된 데이터가 있으면 다시 loadData() 호출하여 다음 데이터를 가져옵니다
                loadData();
                if(customAdapter.getItemCount() ==0){

                }
            }

            @Override
            public void onFailure(Call<AnimalEntity> call, Throwable t) {
                loadedApiLength++;
                isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (customAdapter != null) {
            customAdapter.clear();
        }
        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(scrollListener);
        }
        if (animalDtoCall != null) {
            animalDtoCall.cancel();
        }
    }

    public void onSearchRequested(Bundle bundle) {
        // 이전 데이터 초기화
        if (customAdapter != null) {
            customAdapter.clear();
        }

        // 이전 검색 결과를 더 이상 사용하지 않으므로 animalDtoCall 취소
        if (animalDtoCall != null) {
            animalDtoCall.cancel();
        }

        View srView = getView();
        query = bundle.getString(KEY_INPUT_QUERY, "");
        if (query.isEmpty()) {
            // 검색어가 없는 경우 아무 동작을 수행하지 않고 종료
            return;
        }
        recyclerView = srView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        progressBar = srView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        indexLengthCall = apiService.getSearchIndexLength(query);
        indexLengthCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                apiLength = response.body();
                loadedApiLength = 0;
                loadData(); // loadData()를 한 번만 호출하도록 수정

                scrollListener = new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        loadData(); // 스크롤 이벤트 발생 시 loadData() 호출
                    }
                };
                recyclerView.addOnScrollListener(scrollListener);
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

//    public final static String KEY_INPUT_QUERY = "INPUT_QUERY";
//
//    RecyclerView recyclerView = null;
//    CustomAdapter customAdapter = null;
//
//    int apiLength;
//    int loadedApiLength;
//    String query;
//
//    private ProgressBar progressBar;
//    Call<Integer> indexLengthCall;
//    Call<AnimalEntity> callApiFacility;
//    RecyclerView.OnScrollListener scrollListener;
//
//    private AnimalApiService apiService;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Bundle bundle = getArguments();
//
//        query = bundle.getString(KEY_INPUT_QUERY, "");
//
//        View srView = inflater.inflate(R.layout.recycler_view, container, false);
//
//        progressBar = srView.findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseUrl.url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        apiService = retrofit.create(AnimalApiService.class);
//
//        indexLengthCall = apiService.getSearchIndexLength(query);
//
//        indexLengthCall.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if (isRemoving()) {
//                    return;
//                }
//                if (!response.isSuccessful()) {
//                    return;
//                }
//
//                apiLength = response.body();
//
//                recyclerView = srView.findViewById(R.id.recyclerView);
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                recyclerView.setLayoutManager(linearLayoutManager);
//                customAdapter = new CustomAdapter();
//                recyclerView.setAdapter(customAdapter);
//
//                recyclerView.addOnScrollListener(scrollListener = new RecyclerView.OnScrollListener() {
//                    @Override
//                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                        super.onScrollStateChanged(recyclerView, newState);
//                    }
//
//                    @Override
//                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                        super.onScrolled(recyclerView, dx, dy);
//                        loadData();
//                    }
//                });
//                loadData();
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//
//            }
//        });
//        return srView;
//    }
//
//    private void loadData() {
//        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//        if (loadedApiLength >= apiLength) {
//            return;
//        }
//        if (layoutManager == null) {
//            return;
//        }
//        if (layoutManager.findLastCompletelyVisibleItemPosition() < customAdapter.getItemCount() -1 ) {
//            return;
//        }
//
//        for (int i = 0; i < apiLength; i++) {
//            callApiFacility = apiService.getSearchFacility(i, query);
//            callApiFacility.enqueue(new Callback<AnimalEntity>() {
//                @Override
//                public void onResponse(Call<AnimalEntity> call, Response<AnimalEntity> response) {
//                    loadedApiLength++;
//
//                    if (isRemoving()) {
//                        return;
//                    }
//                    if (!response.isSuccessful()) {
//                        return;
//                    }
//
//                    AnimalEntity apiInfo = response.body();
//
//                    String placeName = apiInfo.getName(); //상호명
//                    String placeAddr1 = apiInfo.getCity(); //도시명
//                    String placeAddr2 = apiInfo.getDistrict(); //도로명 주소
//                    String placeOtherInfo = apiInfo.getParkingAvailable(); //주차가능
//                    ViewItemData viewItemData = new ViewItemData(placeName, placeAddr1, placeAddr2, placeOtherInfo);
//                    customAdapter.add(viewItemData);
//                    progressBar.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onFailure(Call<AnimalEntity> call, Throwable t) {
//                    loadedApiLength++;
//                }
//            });
//        }
//    }
//
//    @SuppressLint("NotifyDataSetChanged")
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        if (customAdapter != null) {
//            customAdapter.clear();
//        }
//        if (recyclerView != null) {
//            recyclerView.removeOnScrollListener(scrollListener);
//        }
//        if (callApiFacility != null) {
//            callApiFacility.cancel();
//        }
//    }


//    public final static String KEY_INPUT_QUERY = "INPUT_QUERY";
//
//    private RecyclerView recyclerView;
//    private CustomAdapter customAdapter;
//    private ProgressBar progressBar;
//    private Call<Integer> indexLengthCall;
//    private Call<AnimalEntity> animalDtoCall;
//    private AnimalApiService apiService;
//    private int apiLength;
//    private int loadedApiLength;
//    private String query;
//    private boolean isLoadingData;  // 데이터 로딩 중인지 여부를 나타내는 플래그 변수
//
//
//    private RecyclerView.OnScrollListener scrollListener;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View srView = inflater.inflate(R.layout.recycler_view, container, false);
//        progressBar = srView.findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);
//
//        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("http://192.168.100.108:23468") //학원
//                .baseUrl(BaseUrl.url) // 집 wifi 서버의 기본 URL
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        apiService = retrofit.create(AnimalApiService.class);
//
//        return srView;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        Bundle bundle = getArguments();
//        onSearchRequested(bundle != null ? bundle : new Bundle());
//    }
//
//    private void loadData() {
//        if (loadedApiLength >= apiLength || isLoadingData) {
//            if (customAdapter.getItemCount() == 0) {
//                customAdapter.clear(); // 어댑터 초기화
//                TextView noResultsTextView = getView().findViewById(R.id.emptyView);
//                progressBar.setVisibility(View.INVISIBLE);
//                noResultsTextView.setVisibility(View.VISIBLE);
//            } else {
//                TextView noResultsTextView = getView().findViewById(R.id.emptyView);
//                progressBar.setVisibility(View.INVISIBLE);
//                noResultsTextView.setVisibility(View.GONE);
//            }
//            return;
//        }
//
//        isLoadingData = true;  // 데이터 로딩 중 플래그를 설정합니다.
//
//        animalDtoCall = apiService.getSearchFacility(query, loadedApiLength);
//        animalDtoCall.enqueue(new Callback<AnimalEntity>() {
//            @Override
//            public void onResponse(Call<AnimalEntity> call, Response<AnimalEntity> response) {
//                loadedApiLength++;
//
//                if (isRemoving()) {
//                    isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
//                    return;
//                }
//                if (!response.isSuccessful()) {
//                    isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
//                    return;
//                }
//
//                // 데이터 처리 로직
//
//                isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
//                AnimalEntity apiInfo = response.body();
//
//                String placeName = apiInfo.getFacility();
//                String placeAddr1 = apiInfo.getCity();
//                String placeAddr2 = apiInfo.getMunicipality();
//                String placeInfo = apiInfo.getCategory3();
//                String placeOtherInfo = apiInfo.getParking();
//                ViewItemData viewItemData = new ViewItemData(placeName, placeAddr1, placeAddr2, placeOtherInfo, placeInfo);
//
//                // 중복 데이터 확인 후 추가합니다
//                if (!customAdapter.containsItem(viewItemData)) {
//                    customAdapter.add(viewItemData);
//                    progressBar.setVisibility(View.GONE);
//                }
//
//                // 추가된 데이터가 있으면 다시 loadData() 호출하여 다음 데이터를 가져옵니다
//                loadData();
//                if(customAdapter.getItemCount() ==0){
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<AnimalEntity> call, Throwable t) {
//                loadedApiLength++;
//                isLoadingData = false;  // 데이터 로딩이 완료되었으므로 플래그를 해제합니다.
//            }
//        });
//    }
//
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//        if (customAdapter != null) {
//            customAdapter.clear();
//        }
//        if (recyclerView != null) {
//            recyclerView.removeOnScrollListener(scrollListener);
//        }
//        if (animalDtoCall != null) {
//            animalDtoCall.cancel();
//        }
//    }
//
//    public void onSearchRequested(Bundle bundle) {
//        // 이전 데이터 초기화
//        if (customAdapter != null) {
//            customAdapter.clear();
//        }
//
//        // 이전 검색 결과를 더 이상 사용하지 않으므로 animalDtoCall 취소
//        if (animalDtoCall != null) {
//            animalDtoCall.cancel();
//        }
//
//        View srView = getView();
//        query = bundle.getString(KEY_INPUT_QUERY, "");
//        if (query.isEmpty()) {
//            // 검색어가 없는 경우 아무 동작을 수행하지 않고 종료
//            return;
//        }
//        recyclerView = srView.findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        customAdapter = new CustomAdapter();
//        recyclerView.setAdapter(customAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        progressBar = srView.findViewById(R.id.progressBar);
//        progressBar.setVisibility(View.VISIBLE);
//
//        indexLengthCall = apiService.getSearchIndexLength(query);
//        indexLengthCall.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if (!response.isSuccessful()) {
//                    return;
//                }
//
//                apiLength = response.body();
//                loadedApiLength = 0;
//                loadData(); // loadData()를 한 번만 호출하도록 수정
//
//                scrollListener = new RecyclerView.OnScrollListener() {
//                    @Override
//                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                        super.onScrolled(recyclerView, dx, dy);
//                        loadData(); // 스크롤 이벤트 발생 시 loadData() 호출
//                    }
//                };
//                recyclerView.addOnScrollListener(scrollListener);
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//
//            }
//        });
//    }
}
