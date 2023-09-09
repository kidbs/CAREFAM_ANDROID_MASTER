package com.jgy.animal.slideviewpagers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

// 베너 이미지 자동 스크롤을 위한 코드
// - 이미지 슬라이더를 위한 어뎁터를 구현, Context 와 이미지 배열을 받아 이미지뷰를 생성하고 관리,
//   페이지의 개수를 반환하고 페이지 생성/삭제를 처리.
public class ImageSliderAdapter extends PagerAdapter {

    private Context context; // 어댑터의 생성자에서 전달되는 context
    private int[] images; // 슬라이더에 표시할 이미지들을 담은 배열

    public ImageSliderAdapter(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        // 전체 이미지의 개수를 반환합니다.
        return images.length; // 슬라이더의 총 페이지 수를 나타냅니다.
    }

    @Override
    public boolean isViewFromObject(@NonNull View view,
                                    @NonNull Object object) {
        // View 와 객체(object) 가 연관되어 있는지를 확인
        // PagerAdapter 에서 오버라이딩이 되어야하는 메서드 이며,
        // 뷰와 객체의 관계를 정의하는 로직, 뷰와 객체가 동일한지 비교 후 반환
        return view == object;
    }

    // instantiateItem() 메서드는 새로운 페이지를 생성 후 뷰를 반환
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context); // ImageView 를 생성

        // 이미지뷰의 이미지를 화면에 맞게 조정하는 스케일 타입
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP); // 스케일 타입을 CENTER_CROP 설정
        // CENTER_CROP 스케일 타입 : 이미지를 중앙에 맞추고 가로 및 세로 비율을 유지, 이미지를 화면에 꽉 채우도록 조정
        //       이미지의 가로 또는 세로 중 큰 쪽을 기준으로 이미지를 잘라내어 화면에 맞게 표시
        //       -> 이미지의 일부가 잘릴 수 있으며, 중앙에 맞추기 떄문에 이미지의 일부는 화면에 표시되지 않을수도 있으니 주의할것.

        imageView.setImageResource(images[position]);
        container.addView(imageView); // 이미지뷰를 컨테이너에 추가 후, 이미지뷰를 반환

        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position,
                            @NonNull Object object) {
        // 페이지가 삭제될때 호출, 컨테이너에서 해당 뷰를 제거
        container.removeView((ImageView) object);
    }
}
