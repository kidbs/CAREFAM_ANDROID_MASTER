package com.jgy.animal.fragment.regionKr.categoryfrags;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

public class CategoryFrag extends Fragment {
    public final static String KEY_INPUT_SCREEN = "INPUT_SCREEN";

    // 리사이클러뷰 작업
    RecyclerView recyclerView = null;
    CustomAdapter customAdapter = null;
    //ArrayList<ViewItemData> mList = new ArrayList<>();
    /////////////////////////////////////////////////////

    // 서버연결작업 //////////////////////////////////////
    private int mCategory = 0;

    // api 길이
    int apiLength; // 총 데이터의 길이
    int loadedApiLength; // 필터된 데이터의 길이

    private ProgressBar progressBar; // 작업진행상태표시
    /////////////////////////////////////////////////////////////////////////////////

    // Call : Retrofit 라이브러리에서 제공하는 REST API 요청을 나타내는 인터페이스
    // Call<Integer> : Integer 타입의 응답을 받을 수 있는 REST API 호출을 의미
    Call<Integer> indexLengthCall;

    // Call<Apifacility> : Apifacility 타입의 응답을 받을 수 있는 REST API 호출을 의미
    Call<AnimalEntity> callApiFacility;

    Call<AnimalEntity> animalDtoCall;
    /////////////////////////////////////////////////////////////////////////////////
    //    boolean isRequestCompleted = false;
    RecyclerView.OnScrollListener scrollListener;

    private AnimalApiService apiService;
    /////////////////////////////////////////////////////

    @Nullable
    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        @Nullable ViewGroup container,
        @Nullable Bundle savedInstanceState
    ) {
        View categoryView = inflater.inflate(R.layout.recycler_view, container, false);

        // 서버 연결 작업 //////////////////////////////////////////////////////////////////////

        progressBar = categoryView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // Retrofit 인스턴스 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.url) // 서버의 기본 URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Retrofit 을 사용하여 API 인터페이스와 연결
        apiService = retrofit.create(AnimalApiService.class);

        Bundle args = getArguments();
        if(args == null){
            args = new Bundle();
        }
        mCategory = args.getInt(KEY_INPUT_SCREEN, R.id.btn_camp);

        // 데이터 넣기
        switch (mCategory) {
            // 지역버튼들
            case R.id.btn_seoul:
                indexLengthCall = apiService.getSeoulIndexLength();
                break;
            case R.id.btn_busan:
                indexLengthCall = apiService.getBusanIndexLength();
                break;
            case R.id.btn_incheon:
                indexLengthCall = apiService.getIncheonIndexLength();
                break;
            case R.id.btn_daegu:
                indexLengthCall = apiService.getDaeguIndexLength();
                break;
            case R.id.btn_daejeon:
                indexLengthCall = apiService.getDaejeonIndexLength();
                break;
            case R.id.btn_gwangju:
                indexLengthCall = apiService.getGwangjuIndexLength();
                break;
            case R.id.btn_ulsan:
                indexLengthCall = apiService.getUlsanIndexLength();
                break;
            case R.id.btn_gyeonggi:
                indexLengthCall = apiService.getGyeonggiIndexLength();
                break;
            case R.id.btn_gyeongsang:
                indexLengthCall = apiService.getGyeongsangIndexLength();
                break;
            case R.id.btn_jeonlla:
                indexLengthCall = apiService.getJeonlaIndexLength();
                break;
            case R.id.btn_chungcheong:
                indexLengthCall = apiService.getChungchungIndexLength();
                break;
            case R.id.btn_gangwon:
                indexLengthCall = apiService.getGangwonIndexLength();
                break;
            case R.id.btn_sejong:
                indexLengthCall = apiService.getSejongIndexLength();
                break;
            case R.id.btn_jeju:
                indexLengthCall = apiService.getJejuIndexLength();
                break;
            // 메인 카테고리별 버튼들
            case R.id.btn_trip:
                indexLengthCall = apiService.getTripIndexLength();
                break;
            case R.id.btn_food:
                indexLengthCall = apiService.getFoodIndexLength();
                break;
            case R.id.btn_cult:
                indexLengthCall = apiService.getCultureIndexLength();
                break;
            case R.id.btn_buty:
                indexLengthCall = apiService.getBeautyIndexLength();
                break;
            case R.id.btn_medi:
                indexLengthCall = apiService.getMedicalIndexLength();
                break;
            //지역별꺼
//            case R.id.btn_medi:
//                indexLengthCall = apiService.getMediIndexLength();
//                break;
            case R.id.btn_camp:
            default:
                indexLengthCall = apiService.getCampIndexLength();
                break;
        }

        indexLengthCall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (isRemoving()) {
                    return; // 프래그먼트가 종료되었으면 중지
                }

//                if (isRequestCompleted) {
//                    // 이미 요청이 완료된 경우, 처리를 중단
//                    return;
//                }

                if (!response.isSuccessful()) {
                    // indexLength 를 가져오는 요청이 실패한 경우 처리
                    return;
                }

                apiLength = response.body();

                Log.e("INDEX LENGTH", String.valueOf(apiLength));

                recyclerView = categoryView.findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                customAdapter = new CustomAdapter();
                recyclerView.setAdapter(customAdapter); // 어탭터 설정

                //----------------------------------------------------
                recyclerView.addOnScrollListener(scrollListener = new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);

                        loadData();
                    }
                });

                loadData();
//                isRequestCompleted = true;
            }


            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                // indexLength 를 가져오는 요청이 실패한 경우 처리
                Log.e(CategoryFrag.class.getSimpleName(), "Fail request : "+t.getMessage());
            }
        });

        return categoryView;
    }

    private void loadData() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

        if (loadedApiLength >= apiLength) {
            //모두 로드 되었음
            return;
        }

        if (layoutManager == null) {
            return;
        }

        if (layoutManager.findLastCompletelyVisibleItemPosition() < customAdapter.getItemCount() - 1) {
            return;
        }

        for (int i = 0; i < apiLength; i++) {
            // 데이터 넣기
            switch (mCategory) {
            // 데이터 넣기
                case R.id.btn_seoul:
                    animalDtoCall = apiService.getFindSeoul(i);
                    break;
                case R.id.btn_busan:
                    animalDtoCall = apiService.getFindBusan(i);
                    break;
                case R.id.btn_incheon:
                    animalDtoCall = apiService.getFindIncheon(i);
                    break;
                case R.id.btn_daegu:
                    animalDtoCall = apiService.getFindDaegu(i);
                    break;
                case R.id.btn_daejeon:
                    animalDtoCall = apiService.getFindDaejeon(i);
                    break;
                case R.id.btn_gwangju:
                    animalDtoCall = apiService.getFindGwangju(i);
                    break;
                case R.id.btn_ulsan:
                    animalDtoCall = apiService.getFindUlsan(i);
                    break;
                case R.id.btn_gyeonggi:
                    animalDtoCall = apiService.getFindGyeonggi(i);
                    break;
                case R.id.btn_gyeongsang:
                    animalDtoCall = apiService.getFindGyeongsang(i);
                    break;
                case R.id.btn_jeonlla:
                    animalDtoCall = apiService.getFindJeonla(i);
                    break;
                case R.id.btn_chungcheong:
                    animalDtoCall = apiService.getFindChungchung(i);
                    break;
                case R.id.btn_gangwon:
                    animalDtoCall = apiService.getFindGangwon(i);
                    break;
                case R.id.btn_sejong:
                    animalDtoCall = apiService.getFindSejong(i);
                    break;
                case R.id.btn_jeju:
                    animalDtoCall = apiService.getFindJeju(i);
                    break;
                // 메인 카테고리별 버튼들
                case R.id.btn_trip:
                    animalDtoCall = apiService.getFindTrip(i);
                    break;
                case R.id.btn_food:
                    animalDtoCall = apiService.getFindFood(i);
                    break;
                case R.id.btn_cult:
                    animalDtoCall = apiService.getFindCulture(i);
                    break;
                case R.id.btn_buty:
                    animalDtoCall = apiService.getFindBeauty(i);
                    break;
                case R.id.btn_medi:
                    animalDtoCall = apiService.getFindMedical(i);
                    break;
                //지역별꺼
//                case R.id.btn_medi:
//                    callApiFacility = apiService.getMediFacility(i);
//                    break;
                case R.id.btn_camp:
                default:
                    animalDtoCall = apiService.getFindCamp(i);
                    break;
            }
            //
            animalDtoCall.enqueue(new Callback<AnimalEntity>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<AnimalEntity> call, Response<AnimalEntity> response) {
                    loadedApiLength++;

                    if (isRemoving()) {
                        return; //프래그먼트가 종료되었으면 중지
                    }
//                                Log.e("dzdzdzdzzddz","dwdwwddwwddwwd");
                    if (!response.isSuccessful()) {
                        Log.e("server code", String.valueOf(response.code()));
                        Log.e("response", "fail");
                        return;
                    }

                    AnimalEntity apiInfo = response.body();

                    // 리사작업 ///////////////////////////////////////////
                    String placeName = apiInfo.getFacility();
                    String placeAddr1 = apiInfo.getCity();
                    String placeAddr2 = apiInfo.getMunicipality();
                    String placeInfo = apiInfo.getCategory3();
                    String placeOtherInfo = apiInfo.getParking();
                    String address = apiInfo.getAddress();
                    String phoneNumber = apiInfo.getPhoneNumber();
                    String operatingTime = apiInfo.getOperatingTime();
                    String homePage = apiInfo.getHomePage();
                    String closedDays = apiInfo.getClosedDays();
                    String parking =apiInfo.getParking();

                    ViewItemData viewItemData = new ViewItemData(placeName, placeAddr1, placeAddr2, placeOtherInfo, placeInfo);
                    viewItemData.setAddress(address);
                    viewItemData.setPhoneNumber(phoneNumber);
                    viewItemData.setOperatingTime(operatingTime);
                    viewItemData.setWebsite(homePage);
                    viewItemData.setClosedDays(closedDays);
                    viewItemData.setParking(parking);

                    customAdapter.add(viewItemData);
                    progressBar.setVisibility(View.GONE);


                    /////////////////////////////////////////////////////////////////////////////////////////////

                }

                @Override
                public void onFailure(Call<AnimalEntity> call, Throwable t) {
                    loadedApiLength++;
                }
            });
        }

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onDestroy() {
        super.onDestroy();

//        isRequestCompleted = false;

        if (customAdapter != null) {
            customAdapter.clear();
        }

        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(scrollListener);
        }
        if (callApiFacility != null) {
            callApiFacility.cancel();
        }

    }

}
