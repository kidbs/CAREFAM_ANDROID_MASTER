package com.jgy.animal.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.jgy.animal.FragmentScreenController;
import com.jgy.animal.R;
import com.jgy.animal.fragment.regionKr.categoryfrags.CategoryFrag;

public class ButtonActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentScreenController mFragmentScreenController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_btn);

        // 초기 시작 프레그먼트
        initFragment();

        // 버튼 눌렀을시 관련된 프레그먼트 실행
        initButtons(
            findViewById(R.id.btn_camp),
            findViewById(R.id.btn_trip),
            findViewById(R.id.btn_food),
            findViewById(R.id.btn_cult),
            findViewById(R.id.btn_medi),
            findViewById(R.id.btn_buty)
        );
    }

    // Button 들에 대해서 반복문 실행 - button 에 대해서 클릭이벤트처리
    void initButtons(Button... buttons) {
        for(final Button button : buttons) {
            button.setOnClickListener(this::onClick);
        }
    }

    void initFragment() {
        mFragmentScreenController = FragmentScreenController.get(this);

        // 현재 엑티비티에 전달된 인텐트를 가져옴.
        Intent intent = getIntent();

        //인텐트에서 "screen"이라는 이름의 추가 데이터를 추출합니다. 이 데이터는 정수형으로 전달되며,
        // 만약 "screen"이라는 이름의 추가 데이터가 존재하지 않거나 가져올 수 없는 경우에는 -1을 기본값으로 사용
        int startScreen = intent.getIntExtra("screen",-1);
        Screens screen = Screens.values()[startScreen]; // 열거형 상수를 가져옴.

        Bundle args = new Bundle();
        args.putSerializable(CategoryFrag.KEY_INPUT_SCREEN, screen.getId()); //(키, 값)

        mFragmentScreenController.startFragment(screen, args); // 초기 프래그먼트 시작
    }

    public void onClick(View view) {
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

        mFragmentScreenController.replaceFragment(screen, args);
    }


    public enum Screens implements FragmentScreenController.Screen {
        Camp(R.id.btn_camp, new CategoryFrag()),
        Trip(R.id.btn_trip, new CategoryFrag()),
        Food(R.id.btn_food, new CategoryFrag()),
        Cult(R.id.btn_cult, new CategoryFrag()),
        Buty(R.id.btn_buty, new CategoryFrag()),
        Medi(R.id.btn_medi, new CategoryFrag());

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