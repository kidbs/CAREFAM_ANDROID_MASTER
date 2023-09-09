package com.jgy.animal.slideviewpagers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ScrollerCustomDuration extends Scroller {
    // Context : 안드로이드 애플리케이션의 현재 상태와 정보에 대한 액세스를 제공하는 클래스이며,
    //           안드로이드 앱의 컴포넌트(액티비티, 서비스, 브로드캐스트 리시버, 컨텐트 프로바이더 등)들이
    //           시스템과 상호작용하고 리소스에 접근하는 데 사용합니다.
    // Interpolator : 애니메이션의 속도를 조절하는데 사용됩니다.
    // flywheel : 스크롤 애니메이션이 완료된 후에도 관성을
    //            유지하여 부드럽고 자연스러운 스크롤 효과를 제공(기본값 false)합니다.


    // 애니메이션의 지속시간을 조절하기 위해 사용되는 변수
    private double mScrollFactor = 1; // 지속시간을 변경하지 않고 기본값으로 설정


    public ScrollerCustomDuration(Context context) {
        // context 를 받는 기본생성자
        super(context);
    }

    public ScrollerCustomDuration(Context context, Interpolator interpolator) {
        // context 와 interpalator 를 받는 생성자
        super(context, interpolator);
    }


    @SuppressLint("NewApi")
    public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {
        // context 와 interpalator 를 받고 flywheel 여부를 받는 생성자 생성자
        super(context, interpolator, flywheel);
    }

    /**
     * Set the factor by which the duration will change
     */
    public void setScrollDurationFactor(double scrollDurationFactor) {
        // 스크롤 지속 시간을 조절하기 위해 스크롤 지속 시간(factor)을 설정
        // double 형태로 매개변수가 전달되며, 기본값은 1, 값이 클수혹 스크롤이
        // 더 빠르게 완료됨.
        mScrollFactor = scrollDurationFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // 스크롤 애니메이션 시작부분
        // 스크롤 시작 위치와 스크롤 방향, 스크롤 거리(dx, dy) 및 스크롤 지속 시간(duration) 을 받음
        super.startScroll(startX, startY, dx, dy, (int) (duration * mScrollFactor));
        // 메서드를 호출하여 부모 클래스인 Scroller 의 startScroll() 메서드를 실행
        // super.startScroll( duration * mScrollFactor 를 통해 스크롤 지속시간을 조절 );
        }
}
