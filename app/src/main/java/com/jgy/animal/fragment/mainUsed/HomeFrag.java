package com.jgy.animal.fragment.mainUsed;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.jgy.animal.R;
import com.jgy.animal.activities.ButtonActivity;
import com.jgy.animal.slideviewpagers.ImageSliderAdapter;
import com.jgy.animal.slideviewpagers.ViewPagerCustomDuration;

import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class HomeFrag extends Fragment implements View.OnClickListener {

    private ViewPagerCustomDuration viewPagerCustomDuration;

    //ViewPager - 홈화면에 적용/////////////////////////////////////////////////////
    private ViewPager viewPager; // 자동 이미지 스크롤을 위한 코드
    private int[] images
            = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image8,
            R.drawable.image9,
            R.drawable.image10
    }; //이미지는 임시로 넣음.
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 5000; // 앱 실행후 처음으로 이미지 넘어갈 때까지 대기하는 시간(밀리초(millisecond, ms))
    // 앱 실행시 초기 이미지는 다음 이미지로 넘어가기 까지 10초정도 소요됨.
    private final long PERIOD_MS = 5000; // 이후 이미지 전환 시간(밀리초(millisecond, ms))

    ////////////////////////////////////////
    private Handler handler;
    private Runnable updateRunnable;
    private int previousState = ViewPager.SCROLL_STATE_IDLE;
    private boolean isUserScrolling = false;

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.frag_home, container, false);

        AppCompatImageButton camp = homeView.findViewById(R.id.imageCamp);

        //
        AppCompatImageButton trip = homeView.findViewById(R.id.imageTrip);
        AppCompatImageButton food = homeView.findViewById(R.id.imageFood);
        AppCompatImageButton cult = homeView.findViewById(R.id.imageCult);
        AppCompatImageButton buty = homeView.findViewById(R.id.imageButy);
        AppCompatImageButton medi = homeView.findViewById(R.id.imageMedi);
        //

        camp.setOnClickListener(this::onClick);

        //
        trip.setOnClickListener(this::onClick);
        food.setOnClickListener(this::onClick);
        cult.setOnClickListener(this::onClick);
        buty.setOnClickListener(this::onClick);
        medi.setOnClickListener(this::onClick);
        //

        return homeView;
    }

    //Viewpager 사용 /////////////////////////////////////////////////////////////////////////////////

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPagerCustomDuration = view.findViewById(R.id.viewpager);

        viewPagerCustomDuration.setScrollDurationFactor(1);

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(requireContext(), images);
        viewPagerCustomDuration.setAdapter(imageSliderAdapter);

        // (CircleIndicator)
        ///////////////////////////////
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        indicator.setViewPager(viewPagerCustomDuration);
        // optional
        imageSliderAdapter.registerDataSetObserver(indicator.getDataSetObserver());
        ///////////////////////////////


//        final Handler handler = new Handler();
//        final Runnable update = new Runnable() {
//            @Override
//            public void run() {
//                if (currentPage == images.length) {
//                    currentPage = 0;
//                }
//                viewPagerCustomDuration.setCurrentItem(currentPage++, true);
//
//            }
//        };
//        timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
////                int nextPage = viewPagerCustomDuration.getCurrentItem() + 1;
////                if (nextPage >= images.length) {
////                    nextPage = 0;
////                }
////                viewPagerCustomDuration.setCurrentItem(nextPage, true);
//
//
//
//                handler.post(update);
//            }
//        }, DELAY_MS, PERIOD_MS); //DELAY_MS, PERIOD_MS

        ////////////////////////////////////////////////////////////////////////////////////////////
        // ViewPager 스크롤 이벤트 처리 ///////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////

        handler = new Handler();
        updateRunnable = new Runnable() {
            @Override
            public void run() {
//                if (currentPage == images.length) {
//                    currentPage = 0;
//                }
//                viewPagerCustomDuration.setCurrentItem(currentPage++, true);

                int nextPage = viewPagerCustomDuration.getCurrentItem() + 1;
                if (nextPage >= images.length) {
                    nextPage = 0;
                }
                viewPagerCustomDuration.setCurrentItem(nextPage, true);
                startAutoScroll();
            }
        };

//        viewPagerCustomDuration.setAdapter(imageSliderAdapter);

        startAutoScroll();

        viewPagerCustomDuration.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 페이지 스크롤 시 호출되는 메서드
            }

            @Override
            public void onPageSelected(int position) {
                // 페이지 선택 시 호출되는 메서드
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    stopAutoScroll();
                    isUserScrolling = true;
                } else if (state == ViewPager.SCROLL_STATE_IDLE && isUserScrolling) {
                    startAutoScroll();
                    isUserScrolling = false;
                }
            }
        });

    }

    private void startAutoScroll() {
        handler.postDelayed(updateRunnable, DELAY_MS);
    }

    private void stopAutoScroll() {
        handler.removeCallbacks(updateRunnable);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onClick(View v) {
        if (v == null) {
            return;
        }
        switch (v.getId()) {
            case R.id.imageTrip:
                startNextActivity(ButtonActivity.Screens.Trip);
                break;
            case R.id.imageFood:
                startNextActivity(ButtonActivity.Screens.Food);
                break;
            case R.id.imageCult:
                startNextActivity(ButtonActivity.Screens.Cult);
                break;
            case R.id.imageButy:
                startNextActivity(ButtonActivity.Screens.Buty);
                break;
            case R.id.imageMedi:
                startNextActivity(ButtonActivity.Screens.Medi);
                break;
            case R.id.imageCamp:
            default:
                startNextActivity(ButtonActivity.Screens.Camp);
                break;
        }
    }
    void startNextActivity(ButtonActivity.Screens screen) {
        Intent intent = new Intent(requireContext(), ButtonActivity.class);
        intent.putExtra("screen", screen.ordinal());
        startActivity(intent);
    }

    //-------------------------------------------

}
