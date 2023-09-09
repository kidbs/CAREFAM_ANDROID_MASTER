package com.jgy.animal.fragment.mainUsed;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jgy.animal.FragmentScreenController;
import com.jgy.animal.MainActivity;
import com.jgy.animal.R;
import com.jgy.animal.fragment.regionKr.categoryfrags.CategoryFrag;
//import com.jgy.animal.fragment.regionKr.categoryfrags.CommentFrag;
//import com.jgy.animal.fragment.regionKr.UlsanFrag;

public class regionFrag extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View searchView = inflater.inflate(R.layout.frag_search, container, false);

        // 지역 id 찾기 //////////////////////////////////////////
        initButtons(
            searchView.findViewById(R.id.btn_seoul),//서울특별시
            searchView.findViewById(R.id.btn_busan),//부산광역시
            searchView.findViewById(R.id.btn_incheon),//인천광역시
            searchView.findViewById(R.id.btn_daegu),//대구광역시
            searchView.findViewById(R.id.btn_daejeon),//대전광역시
            searchView.findViewById(R.id.btn_gwangju),//광주광역시
            searchView.findViewById(R.id.btn_ulsan),//울산광역시
            searchView.findViewById(R.id.btn_gyeonggi),//경기도
            searchView.findViewById(R.id.btn_gyeongsang),//경상도
            searchView.findViewById(R.id.btn_jeonlla),//전라도
            searchView.findViewById(R.id.btn_chungcheong),//충청도
            searchView.findViewById(R.id.btn_gangwon),//강원도
            searchView.findViewById(R.id.btn_sejong),//세종특별자치시
            searchView.findViewById(R.id.btn_jeju)//제주특별자치도
        );

        return searchView;
    }

    void initButtons(View... views) {
        for(final View view : views) {
            view.setOnClickListener(this::onClick);
        }
    }

    @Override
    public void onClick(View view) {
        Activity activity = requireActivity();
        FragmentScreenController fragmentScreenController = null;
        if(activity instanceof MainActivity) {
            fragmentScreenController = ((MainActivity) activity).getFragmentScreenController();
        }

        if(fragmentScreenController == null) {
            return;
        }

        Screens[] screens = Screens.values();
        Screens screen = null;

        for (int i = 0; i < screens.length; i++) {
            if (screens[i].id == view.getId()) {
                screen = screens[i];
                break;
            }
        }

        if (screen == null) {
            return;
        }

        Bundle args = new Bundle();
        args.putSerializable(CategoryFrag.KEY_INPUT_SCREEN, screen.getId());

        fragmentScreenController.replaceFragment(screen, true, args);
    }

    public enum Screens implements FragmentScreenController.Screen {
        SEOUL(R.id.btn_seoul, new CategoryFrag()),//서울특별시
        BUSAN(R.id.btn_busan, new CategoryFrag()),//부산광역시
        INCHEON(R.id.btn_incheon, new CategoryFrag()),//인천광역시
        DAEGU(R.id.btn_daegu, new CategoryFrag()),//대구광역시
        DAEJEON(R.id.btn_daejeon, new CategoryFrag()),//대전광역시
        GWANGJU(R.id.btn_gwangju, new CategoryFrag()),//광주광역시
        ULSAN(R.id.btn_ulsan, new CategoryFrag()),//울산광역시
        GYEONGGI(R.id.btn_gyeonggi, new CategoryFrag()),//경기도
        GYEONGSANG(R.id.btn_gyeongsang, new CategoryFrag()),//경상도
        JEONLLA(R.id.btn_jeonlla, new CategoryFrag()),//전라도
        CHUNGCHEONG(R.id.btn_chungcheong, new CategoryFrag()),//충청도
        GANGWON(R.id.btn_gangwon, new CategoryFrag()),//강원도
        SEJONG(R.id.btn_sejong, new CategoryFrag()),//세종특별자치시
        JEJU(R.id.btn_jeju, new CategoryFrag());//제주특별자치도


        private int id;
        private Fragment fragment;

        Screens(int id, Fragment fragment) {
            this.id = id;
            this.fragment = fragment;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public Fragment getFragment() {
            return fragment;
        }
    }

}


