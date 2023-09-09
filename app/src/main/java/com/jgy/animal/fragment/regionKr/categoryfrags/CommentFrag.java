//package com.jgy.animal.fragment.regionKr.categoryfrags;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.jgy.animal.Adapter.NoteAdapter;
//import com.jgy.animal.Adapter.NoteItemData;
//import com.jgy.animal.BaseUrl;
//import com.jgy.animal.Entities.CommentEntity;
//import com.jgy.animal.R;
//import com.jgy.animal.fragment.mainUsed.AddCommentFrag;
//import com.jgy.animal.interfaces.CommentApiService;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class CommentFrag extends Fragment implements View.OnClickListener {
//
//    public final static String KEY_INPUT_SCREEN = "INPUT_SCREEN";
//
//    // 리사이클러뷰 작업
//    RecyclerView recyclerView = null;
//    NoteAdapter noteAdapter = null;
//    //ArrayList<ViewItemData> mList = new ArrayList<>();
//    /////////////////////////////////////////////////////
//
//    // 서버연결작업 //////////////////////////////////////
//    private int mComment = 0;
//
//    // api 길이
//    int apiLength; // 총 데이터의 길이
//    int loadedApiLength; // 필터된 데이터의 길이
//
//    private ProgressBar progressBar; // 작업진행상태표시
//
//    ///////////////////////////////////////////////////////////
//
//    // Call : Retrofit 라이브러리에서 제공하는 REST API 요청을 나타내는 인터페이스
//    // Call<Integer> : Integer 타입의 응답을 받을 수 있는 REST API 호출을 의미
//    Call<Integer> indexLengthCall;
//
//    // Call<Apifacility> : Apifacility 타입의 응답을 받을 수 있는 REST API 호출을 의미
//    Call<CommentEntity> boardEntityCall;
//    /////////////////////////////////////////////////////////////////////////////////
//    //    boolean isRequestCompleted = false;
//    RecyclerView.OnScrollListener scrollListener;
//    private CommentApiService commentApiService;
//
//    ///////////////////////////////////////////////////////////////////////////
//
//    @Nullable
//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater,
//            @Nullable ViewGroup container,
//            @Nullable Bundle savedInstanceState) {
//        View commentView = inflater.inflate(R.layout.recycler_noteview, container, false);
//
//        // 버튼으로 게시판 추가로 작성하기 위한 버튼
//        Button addComment = commentView.findViewById(R.id.add_comment);
//        addComment.setOnClickListener(this::onClick);
//
//
//        progressBar =  commentView.findViewById(R.id.progressBarNote);
//        progressBar.setVisibility(View.VISIBLE);
//
//        // Retrofit 인스턴스 생성
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BaseUrl.url) // 서버의 기본 URL
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        commentApiService = retrofit.create(CommentApiService.class);
//
//        Bundle args = getArguments();
//
//        if (args == null) {
//            args = new Bundle();
//        }
//
//        mComment = args.getInt(KEY_INPUT_SCREEN, R.id.btn_seoul);
//
//        switch (mComment) {
//            // 지역버튼들
//            case R.id.btn_busan: //부산광역시
//                indexLengthCall = commentApiService.getBusanIndexLength();
//                break;
//            case R.id.btn_incheon: //인천광역시
//                indexLengthCall = commentApiService.getIncheonIndexLength();
//                break;
//            case R.id.btn_daegu: //대구광역시
//                indexLengthCall = commentApiService.getDaeguIndexLength();
//                break;
//            case R.id.btn_daejeon: //대전광역시
//                indexLengthCall = commentApiService.getDaejeonIndexLength();
//                break;
//            case R.id.btn_gwangju: //광주광역시
//                indexLengthCall = commentApiService.getGwangjuIndexLength();
//                break;
//            case R.id.btn_ulsan: //울산광역시
//                indexLengthCall = commentApiService.getUlsanIndexLength();
//                break;
//            case R.id.btn_gyeonggi: //경기도
//                indexLengthCall = commentApiService.getGyeonggiIndexLength();
//                break;
//            case R.id.btn_gyeongsang: //경상도
//                indexLengthCall = commentApiService.getGyeongsangIndexLength();
//                break;
//            case R.id.btn_jeonlla: //전라도
//                indexLengthCall = commentApiService.getJeonlaIndexLength();
//                break;
//            case R.id.btn_chungcheong: //충청도
//                indexLengthCall = commentApiService.getChungchungIndexLength();
//                break;
//            case R.id.btn_gangwon: //강원도
//                indexLengthCall = commentApiService.getGangwonIndexLength();
//                break;
//            case R.id.btn_sejong: //세종특별자치시
//                indexLengthCall = commentApiService.getSejongIndexLength();
//                break;
//            case R.id.btn_jeju: //제주특별자치도
//                indexLengthCall = commentApiService.getJejuIndexLength();
//                break;
//            case R.id.btn_seoul: //서울광역시
//            default:
//                indexLengthCall = commentApiService.getSeoulIndexLength();
//                break;
//        }
//
//        indexLengthCall.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if (isRemoving()) {
//                    return; // 프래그먼트가 종료되었으면 중지
//                }
//
//                if (!response.isSuccessful()) {
//                    // indexLength 를 가져오는 요청이 실패한 경우 처리
//                    return;
//                }
//
//                apiLength = response.body();
//
//                Log.e("INDEX LENGTH", String.valueOf(apiLength));
//
//                recyclerView = commentView.findViewById(R.id.recyclerViewNote);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//                recyclerView.setLayoutManager(layoutManager);
//                noteAdapter = new NoteAdapter();
//                recyclerView.setAdapter(noteAdapter);
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
//
//
//                        loadData();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//
//            }
//        });
//
//        return commentView;
//    }
//
//
//
//    private void loadData() {
//        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
//
//        if (loadedApiLength >= apiLength) {
//            return; //모두 로드 되었음
//        }
//
//        if (layoutManager == null) {
//            return;
//        }
//
//        if (layoutManager.findLastCompletelyVisibleItemPosition() < noteAdapter.getItemCount() - 1) {
//            return;
//        }
//
//        for (int i = 0; i < apiLength; i++) {
//            switch (mComment) {
//                // 지역버튼들
//                case R.id.btn_busan:
//                    boardEntityCall = commentApiService.getFindBusan(i);
//                    break;
//                case R.id.btn_incheon:
//                    boardEntityCall = commentApiService.getFindIncheon(i);
//                    break;
//                case R.id.btn_daegu:
//                    boardEntityCall = commentApiService.getFindDaegu(i);
//                    break;
//                case R.id.btn_daejeon:
//                    boardEntityCall = commentApiService.getFindDaejeon(i);
//                    break;
//                case R.id.btn_gwangju:
//                    boardEntityCall = commentApiService.getFindGwangju(i);
//                    break;
//                case R.id.btn_ulsan:
//                    boardEntityCall = commentApiService.getFindUlsan(i);
//                    break;
//                case R.id.btn_gyeonggi:
//                    boardEntityCall = commentApiService.getFindGyeonggi(i);
//                    break;
//                case R.id.btn_gyeongsang:
//                    boardEntityCall = commentApiService.getFindGyeongsang(i);
//                    break;
//                case R.id.btn_jeonlla:
//                    boardEntityCall = commentApiService.getFindJeonla(i);
//                    break;
//                case R.id.btn_chungcheong:
//                    boardEntityCall = commentApiService.getFindChungchung(i);
//                    break;
//                case R.id.btn_gangwon:
//                    boardEntityCall = commentApiService.getFindGangwon(i);
//                    break;
//                case R.id.btn_sejong:
//                    boardEntityCall = commentApiService.getFindSejong(i);
//                    break;
//                case R.id.btn_jeju:
//                    boardEntityCall = commentApiService.getFindJeju(i);
//                    break;
//                case R.id.btn_seoul:
//                default:
//                    boardEntityCall = commentApiService.getFindSeoul(i);
//                    break;
//            }
//
//            boardEntityCall.enqueue(new Callback<CommentEntity>() {
//                @Override
//                public void onResponse(Call<CommentEntity> call, Response<CommentEntity> response) {
//                    loadedApiLength++;
//
//                    if (isRemoving()) {
//                        return; // 프래그먼트가 종료되었으면 중지
//                    }
//
//                    if (!response.isSuccessful()) {
//                        Log.e("server code", String.valueOf(response.code()));
//                        Log.e("response", "fail");
//                        return;
//                    }
//
//                    CommentEntity apiInfo = response.body();
//
//                    // 리사 작업 /////////////////////////////
//                    String noteID = apiInfo.getId(); // 닉네임
//                    String notePlace1 = apiInfo.getPlace1();
//                    String notePalce2 = apiInfo.getPlace2();
//                    String notetitle = apiInfo.getTitle();
//                    String noteScope = apiInfo.getScope();
//                    String noteComment = apiInfo.getComment();
//                    NoteItemData noteItemData = new NoteItemData(noteID, notePlace1, notePalce2, notetitle,noteScope, noteComment);
//                    noteAdapter.add(noteItemData);
//                    progressBar.setVisibility(View.GONE);
//
//                    ////////////////////////////////////////
//                }
//
//                @Override
//                public void onFailure(Call<CommentEntity> call, Throwable t) {
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
//
//        if (noteAdapter != null) {
//            noteAdapter.clear();
//        }
//
//        if (recyclerView != null) {
//            recyclerView.removeOnScrollListener(scrollListener);
//        }
//        if (boardEntityCall != null) {
//            boardEntityCall.cancel();
//        }
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        if (v.getId() == R.id.add_comment) {
//            Toast.makeText(getActivity(), "게시물 추가페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
//            Fragment addContentFrag = new AddCommentFrag();
//            fragmentTransaction.replace(R.id.fragmentContainerView, addContentFrag);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        }
//    }
//}
