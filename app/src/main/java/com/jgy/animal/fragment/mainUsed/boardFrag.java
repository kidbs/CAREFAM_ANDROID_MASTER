package com.jgy.animal.fragment.mainUsed;

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

import com.jgy.animal.Adapter.NoteAdapter;
import com.jgy.animal.Adapter.NoteItemData;
import com.jgy.animal.Adapter.ViewItemData;
import com.jgy.animal.BaseUrl;
import com.jgy.animal.Entities.BoardEntity;
import com.jgy.animal.R;
import com.jgy.animal.fragment.regionKr.categoryfrags.CategoryFrag;
import com.jgy.animal.interfaces.BoardApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class boardFrag extends Fragment {

    public final static String KEY_INPUT_SCREEN = "INPUT_SCREEN";

    // 리사이클러뷰 작업
    RecyclerView recyclerView = null;
    NoteAdapter noteAdapter = null;

    // 서버연결작업 //////////////////////////////////////
    private int mCategory = 0;

    // api 길이
    int apiLength; // 총 데이터의 길이
    int loadedApiLength; // 필터된 데이터의 길이

    private ProgressBar progressBar; // 작업진행상태표시
    /////////////////////////////////////////////////////////////////////////////////

    Call<Integer> indexLengthCall;

    Call<BoardEntity> callApiFacility;
    Call<BoardEntity> boardDtoCall;

    /////////////////////////////////////////////////////////////////////////////////
    //    boolean isRequestCompleted = false;
    RecyclerView.OnScrollListener scrollListener;

    private BoardApiService boardApiService;
    /////////////////////////////////////////////////////


    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View boardView = inflater.inflate(R.layout.recycler_view, container, false);

        // 서버 연결 작업 //////////////////////////////////////////////////////////////////////

        progressBar = boardView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        // Retrofit 인스턴스 생성
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl.url) // 서버의 기본 URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Retrofit 을 사용하여 API 인터페이스와 연결
        boardApiService = retrofit.create(BoardApiService.class);
        Bundle args = getArguments();
        if(args == null){
            args = new Bundle();
        }

        // 데이터 넣기
        switch (mCategory) {

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
                recyclerView = boardView.findViewById(R.id.recyclerView);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                noteAdapter = new NoteAdapter();
                recyclerView.setAdapter(noteAdapter); // 어탭터 설정

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
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                // indexLength 를 가져오는 요청이 실패한 경우 처리
                Log.e(CategoryFrag.class.getSimpleName(), "Fail request : "+t.getMessage());
            }
        });

        return boardView;
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

        if (layoutManager.findLastCompletelyVisibleItemPosition() < noteAdapter.getItemCount() - 1) {
            return;
        }

        for (int i = 0; i < apiLength; i++) {
            // 데이터 넣기
            switch (mCategory) {

            }
            boardDtoCall.enqueue(new Callback<BoardEntity>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onResponse(Call<BoardEntity> call, Response<BoardEntity> response) {
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

                    BoardEntity apiInfo = response.body();
                    // 리사 작업
                    String noteID;
                    String notePlace1;
                    String notePlace2;
                    String notetitle;
                    String noteScope;
                    String noteComment;

                    //

//                    NoteItemData noteItemData = new NoteItemData();

                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<BoardEntity> call, Throwable t) {
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

        if (noteAdapter != null) {
            noteAdapter.clear();
        }

        if (recyclerView != null) {
            recyclerView.removeOnScrollListener(scrollListener);
        }
        if (callApiFacility != null) {
            callApiFacility.cancel();
        }

    }
}
