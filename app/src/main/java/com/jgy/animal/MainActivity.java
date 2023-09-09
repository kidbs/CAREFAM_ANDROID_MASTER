package com.jgy.animal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jgy.animal.fragment.mainUsed.HomeFrag;
//import com.jgy.animal.activities.LoginFrag;
import com.jgy.animal.activities.LoginActivity;
import com.jgy.animal.fragment.mainUsed.MapFrag;
import com.jgy.animal.fragment.mainUsed.boardFrag;
import com.jgy.animal.fragment.mainUsed.regionFrag;
//import com.jgy.animal.fragment.mainUsed.NoteFrag;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentScreenController mFragmentScreenController;

    private SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

        initButtons(
            findViewById(R.id.buttonHome),
            findViewById(R.id.buttonSearch),
            findViewById(R.id.buttonBoard),
            findViewById(R.id.buttonMap)
        );

        // 로그인 버튼 추가
        Button buttonLogin = findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this::onClick);


        // Search 기능 ////////////////////////////////////////////////////////////

//        searchView = findViewById(R.id.searchView);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // 검색어 제출 시 동작할 코드를 작성하세요
//                performSearch(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                // 검색어 입력 시 동작할 코드를 작성하세요
//                // 예를 들어, 입력된 텍스트로 검색어 자동 완성을 구현할 수 있습니다
//                performAutoComplete(newText);
//                return false;
//            }
//        });

        //////////////////////////////////////////////////////////////////////////
    }

    // search =========================================================

//    // 검색어 제출 시 동작할 메서드를 작성하세요
//    private void performSearch(String query) {
//        Bundle bundle = new Bundle();
//        bundle.putString(SRFrag.KEY_INPUT_QUERY, query);
//
//        // 검색 결과를 처리하고 화면에 표시하는 코드를 작성하세요
//        mFragmentScreenController.replaceFragment(Screens.SearchResult, true, bundle);
//
//    }
//
//    // 검색어 자동 완성 시 동작할 메서드를 작성하세요
//    private void performAutoComplete(String newText) {
//        // 검색어 자동 완성을 처리하고 화면에 표시하는 코드를 작성하세요
//    }

    // ================================================================


    void initButtons(Button... buttons) {
        for(final Button button : buttons) {
            button.setOnClickListener(this::onClick);
        }
    }

    void initFragment() {
        mFragmentScreenController = FragmentScreenController.get(this);
        mFragmentScreenController.startFragment(Screens.Home);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonLogin) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }


        Screens[] screens = Screens.values();
        Screens screen = null;
        //for~~
        for(int i = 0; i < screens.length; i++) {
            if(screens[i].id == v.getId()){
                screen = screens[i];
                break;
            }
        }

        if(screen == null) {
            return;
        }

        // Screen 이 Home 이 아니면 BackStack 을 하겠다.
        boolean addToBackStack = screen != Screens.Home;

        mFragmentScreenController.replaceFragment(screen, addToBackStack, null);
    }

    public FragmentScreenController getFragmentScreenController() {
        return mFragmentScreenController;
    }



    //SearchResult(R.id.searchView, new SRFrag())
    enum Screens implements FragmentScreenController.Screen {
        Home(R.id.buttonHome, new HomeFrag()),
        Search(R.id.buttonSearch, new regionFrag()),
        Board(R.id.buttonBoard, new boardFrag()),
        Map(R.id.buttonMap, new MapFrag());

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