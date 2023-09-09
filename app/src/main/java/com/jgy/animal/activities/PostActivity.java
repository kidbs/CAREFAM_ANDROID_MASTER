package com.jgy.animal.activities;

import android.content.SharedPreferences;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jgy.animal.BaseUrl;
import com.jgy.animal.Entities.BoardEntity;
import com.jgy.animal.R;
import com.jgy.animal.interfaces.BoardApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etCommnet;
    private Button etButton;

    private RatingBar etRatingbar;

    private String loggedInUserId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_addcomment);

        etTitle = findViewById(R.id.et_title);
        etCommnet = findViewById(R.id.et_comment);
        etButton = findViewById(R.id.et_button);

        // 로그인 상태 확인
        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false);
        if (isLoggedIn) {
            loggedInUserId = sharedPreferences.getString("loggedInUserId", "");
        }

        // 게시물 작성 완료 버튼 클릭 이벤트 처리
        etButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String content = etCommnet.getText().toString();
                String writer = loggedInUserId;

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BaseUrl.url)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                // Retrofit을 사용하여 API 인터페이스 구현체 생성
                BoardApiService boardApiService = retrofit.create(BoardApiService.class);

                // 게시물 데이터를 담은 객체 생성
                // 여기서 작성자 정보도 추가해야 합니다. (예: 세션에서 로그인한 사용자 정보를 가져와서 사용)
                BoardEntity postEntity = new BoardEntity(writer,title, content);

                // 게시물 작성 API 호출
                Call<Void> call = boardApiService.insertBoard(postEntity);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(PostActivity.this, "게시물이 작성되었습니다.", Toast.LENGTH_SHORT).show();
                            finish(); // 작성 완료 후 화면 종료
                        } else {
                            Toast.makeText(PostActivity.this, "게시물 작성에 실패했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(PostActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
