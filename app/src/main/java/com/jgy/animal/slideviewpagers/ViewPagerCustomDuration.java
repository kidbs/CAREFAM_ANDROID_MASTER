package com.jgy.animal.slideviewpagers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.lang.reflect.Field;

// 베너 이미지 자동 스크롤을 위한 코드
// ViewPager : 이미지나 컨텐츠를 슬라이드하는 뷰를 구현하는데 사용
public class ViewPagerCustomDuration extends ViewPager {

    public ViewPagerCustomDuration(@NonNull Context context) {
        // Context 를 받아 ViewPager 를 초기화하는 역할
        super(context);
        postInitViewPager();
    }

    public ViewPagerCustomDuration(@NonNull Context context, @Nullable AttributeSet attrs) {
        // postInitViewPager() 를 호출 -> 초기화
        super(context, attrs);
        postInitViewPager();
    }

    // 스크롤 애니메이션의 지속 시간을 조절하는데 사용
    private ScrollerCustomDuration mScroller = null;

    private void postInitViewPager() {
        try {
            // ViewPager - mScroller 필드 접근
            Field scroller = ViewPager.class.getDeclaredField("mScroller");
            scroller.setAccessible(true); // 필드의 접근제한 해제
            // ViewPager - sInterpolator 필드 접근
            Field interpolator = ViewPager.class.getDeclaredField("sInterpolator");
            interpolator.setAccessible(true); // 필드의 접근제한 해제

            if (mScroller == null) {
                mScroller = new ScrollerCustomDuration(getContext(), (Interpolator) interpolator.get(null));
            }
            scroller.set(this, mScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 스크롤 애니메이션의 지속 시간을 조절
    public void setScrollDurationFactor(double scrollFactor) {
        mScroller.setScrollDurationFactor(scrollFactor);
    }

}
