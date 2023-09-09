package com.jgy.animal.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jgy.animal.R;

public class RecyclerviewItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerviewclick_activity);

        // 인텐트에서 데이터 가져오기
        String placeName = getIntent().getStringExtra("facility");
        String placeAddress = getIntent().getStringExtra("address");
        String phoneNumber = getIntent().getStringExtra("phoneNumber");
        String parking = getIntent().getStringExtra("parking");
        String operatingTime = getIntent().getStringExtra("operatingTime");
        String homePage = getIntent().getStringExtra("homePage");
        String closedDays = getIntent().getStringExtra("closedDays");


        TextView nameTextView = findViewById(R.id.text_view_name);
        TextView addressTextView = findViewById(R.id.text_view_address);
        TextView phoneNumTextView = findViewById(R.id.text_view_phone);
        TextView parkingTextView = findViewById(R.id.text_view_parking);
        TextView operatingTimeTextView = findViewById(R.id.text_view_hours);
        TextView homePageTextView = findViewById(R.id.text_view_website);
        TextView closedDaysTextView = findViewById(R.id.text_view_holiday);

        // 뷰에 데이터 설정
        nameTextView.setText(placeName);
        addressTextView.setText("주소: " + placeAddress);
        phoneNumTextView.setText("Tel: " + phoneNumber);
        parkingTextView.setText("주차: " + parking);
        operatingTimeTextView.setText("운영 시간: " + operatingTime);
        homePageTextView.setText("homePage: " + homePage);
        closedDaysTextView.setText("휴일: " + closedDays);


//        // 데이터가 null이 아닌 경우에만 뷰에 설정
//        if (placeName != null) {
//            TextView nameTextView = findViewById(R.id.text_view_name);
//            nameTextView.setText(placeName);
//        }
//
//        if (placeAddress != null) {
//            TextView addressTextView = findViewById(R.id.text_view_address);
//            addressTextView.setText("주소: " + placeAddress);
//        }
//
//        if (phoneNumber != null) {
//            TextView phoneNumTextView = findViewById(R.id.text_view_phone);
//            phoneNumTextView.setText("Tel: " + phoneNumber);
//        }
//
//        if (parking != null) {
//            TextView parkingTextView = findViewById(R.id.text_view_parking);
//            parkingTextView.setText("주차: " + parking);
//        }
//
//        if (operatingTime != null) {
//            TextView operatingTimeTextView = findViewById(R.id.text_view_hours);
//            operatingTimeTextView.setText("운영 시간: " + operatingTime);
//        }
//
//        if (homePage != null) {
//            TextView homePageTextView = findViewById(R.id.text_view_website);
//            homePageTextView.setText("homePage: " + homePage);
//        }
//
//        if (closedDays != null) {
//            TextView closedDaysTextView = findViewById(R.id.text_view_holiday);
//            closedDaysTextView.setText("휴일: " + closedDays);
//        }



        // 게시물 작성 버튼 가져오기
        Button writeButton = findViewById(R.id.button_write_post);

        // 게시물 작성 버튼 클릭 이벤트 처리
        writeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 상태 확인 후 처리
                if (isLoggedIn()) {
                    startActivity(new Intent(RecyclerviewItemActivity.this, PostActivity.class));
                } else {
                    // 로그인하지 않은 경우 로그인 화면으로 이동
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragmentContainerView, new LoginActivity())
//                            .addToBackStack(null)
//                            .commit();
//                    Intent intent = new Intent(RecyclerviewItemActivity.this, LoginActivity.class);
//                    intent.putExtra("originActivity", RecyclerviewItemActivity.class.getName());
                    startActivity(new Intent(RecyclerviewItemActivity.this, LoginActivity.class));
                }
            }
        });
    }

    // 로그인 상태 확인 함수
    // 로그인 상태 확인 함수
    private boolean isLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("is_logged_in", false); // 로그인 상태가 아닌 경우 false를 반환
    }
}
