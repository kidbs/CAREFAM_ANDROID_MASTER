package com.jgy.animal.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jgy.animal.BaseUrl;
import com.jgy.animal.Entities.LoginEntity;
import com.jgy.animal.MainActivity;
import com.jgy.animal.PasswordHashing;
import com.jgy.animal.R;
import com.jgy.animal.fragment.mainUsed.myFrag;
import com.jgy.animal.interfaces.RegisterApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userIdText;
    private EditText userPasswordText;
    private Button loginButton;
    private Button loginToregisterButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_login);

        userIdText = findViewById(R.id.txt_login_id);
        userPasswordText = findViewById(R.id.txt_login_pw);

        loginButton = findViewById(R.id.btn_login);
//        loginButton.setOnClickListener(this::onClick);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new myFrag();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment); // R.id.fragmentContainer는 Fragment를 표시할 레이아웃의 ID입니다.
                fragmentTransaction.addToBackStack(null); // Back 버튼으로 이전 Fragment로 돌아갈 수 있도록 스택에 추가합니다.
                fragmentTransaction.commit();
            }
        });


        loginToregisterButton = findViewById(R.id.btn_joinMember);
        loginToregisterButton.setOnClickListener(this::onClick);

//        boolean loginSuccess = false;
//
//        // 로그인이 완료되면 이전 작업으로 돌아가기
//        if (loginSuccess == true) {
//            String originActivityName = getIntent().getStringExtra("originActivity");
//
//            if (originActivityName != null) {
//                try {
//                    Class<?> originActivity = Class.forName(originActivityName);
//                    Intent backIntent = new Intent(LoginActivity.this, originActivity);
//                    startActivity(backIntent);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            finish();
//        }

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_joinMember) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_login) {
            String id = userIdText.getText().toString();
            String pw = userPasswordText.getText().toString();

            String encryptedPw = PasswordHashing.sha256Hash(pw);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Retrofit을 사용하여 API 인터페이스 구현체 생성
            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
            //

            // 로그인 정보를 담은 객체 생성
            LoginEntity registerRequest = new LoginEntity(id, encryptedPw);

            Call<Integer> checkLoginCall = registerApiService.loginUser(registerRequest);
            checkLoginCall.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    // 중복 ID 확인 결과 처리
                    if (response.isSuccessful()) {
                        int result = response.body();
                        if (result == 1) {
                            // 중복된 ID인 경우
                            Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();

                            // 로그인 상태 저장
                            saveLoginState(true,id);

                            navigateToMainActivity();


                            // Fragment 전환
                            Fragment fragment = new myFrag();
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragmentContainerView, fragment); // R.id.fragmentContainer는 Fragment를 표시할 레이아웃의 ID입니다.
                            fragmentTransaction.addToBackStack(null); // Back 버튼으로 이전 Fragment로 돌아갈 수 있도록 스택에 추가합니다.
                            fragmentTransaction.commit();
                        } else {
                            // 사용 가능한 ID인 경우
                            Toast.makeText(LoginActivity.this, "잘못된 ID 혹은 비밀번호입니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // 중복 ID 확인 실패 처리
                        Toast.makeText(LoginActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    // 중복 ID 확인 실패 처리
                    Toast.makeText(LoginActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    private void saveLoginState(boolean isLoggedIn,String loggedInUserId) {
        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("is_logged_in", isLoggedIn);
        editor.putString("loggedInUserId", loggedInUserId); // 로그인된 사용자 아이디 저장
        editor.apply();
    }

    private void navigateToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class); // 로그인 후 메인 화면으로 이동
        startActivity(intent);
        finish(); // 로그인 화면 종료
    }

}



//public class LoginActivity extends Fragment implements View.OnClickListener {
//
//    private EditText userIdText;
//    private EditText userPasswordText;
//    private Button loginButton;
//    private Button loginToRegisterButton;
//
//    @SuppressLint("MissingInflatedId")
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.frag_login, container, false);
//
//        userIdText = rootView.findViewById(R.id.txt_login_id);
//        userPasswordText = rootView.findViewById(R.id.txt_login_pw);
//        loginButton = rootView.findViewById(R.id.btn_login);
//        loginButton.setOnClickListener(this);
//
//        loginToRegisterButton = rootView.findViewById(R.id.btn_joinMember);
//        loginToRegisterButton.setOnClickListener(this);
//
//        return rootView;
//    }
//
//    @Override
//    public void onClick(View v) {
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//
//        if (v.getId() == R.id.btn_joinMember) {
//                Toast.makeText(getActivity(), "회원가입 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
//                Fragment myFrag = new RegisterFrag();
//                fragmentTransaction.replace(R.id.fragmentContainerView, myFrag);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//        }
//
//        if (v.getId() == R.id.btn_login) {
//            String id = userIdText.getText().toString();
//            String pw = userPasswordText.getText().toString();
//
//            // 암호화된 비밀번호 생성
//            String encryptedPw = PasswordHashing.sha256Hash(pw);
//
//            // Retrofit 인스턴스 생성
//            Retrofit retrofit = new Retrofit.Builder()
////                    .baseUrl("http://192.168.100.108:23468") //학원
//
//                    .baseUrl(BaseUrl.url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            // Retrofit을 사용하여 API 인터페이스 구현체 생성
//            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
//            //
//
//            // 로그인 정보를 담은 객체 생성
//            LoginEntity registerRequest = new LoginEntity(id, encryptedPw);
//
//            Call<Integer> checkLoginCall = registerApiService.loginUser(registerRequest);
//            checkLoginCall.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    // 중복 ID 확인 결과 처리
//                    if (response.isSuccessful()) {
//                        int result = response.body();
//                        if (result == 1) {
//                            // 중복된 ID인 경우
//                            Toast.makeText(getActivity(), "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
//                            Fragment pageFrag = new myFrag();
//                            fragmentTransaction.replace(R.id.fragmentContainerView, pageFrag);
//                            fragmentTransaction.addToBackStack(null);
//                            fragmentTransaction.commit();
//                        } else {
//                            // 사용 가능한 ID인 경우
//                            Toast.makeText(getActivity(), "잘못된 ID 혹은 비밀번호입니다.", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        // 중복 ID 확인 실패 처리
//                        Toast.makeText(getActivity(), "서버 통신 오류", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//                    // 중복 ID 확인 실패 처리
//                    Toast.makeText(getActivity(), "서버 통신 오류", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//}



//----------------------------
//package com.jgy.animal;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.jgy.animal.Entities.LoginEntity;




//import com.jgy.animal.interfaces.RegisterApiService;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
//
//    private EditText userIdText;
//    private EditText userPasswordText;
//    private Button loginButton;
//    private Button loginToregisterButton;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.frag_login);
//
//        userIdText = findViewById(R.id.txt_login_id);
//        userPasswordText = findViewById(R.id.txt_login_pw);
//        loginButton = findViewById(R.id.btn_login);
//        loginButton.setOnClickListener(this::onClick);
//
//        loginToregisterButton = findViewById(R.id.btn_joinMember);
//        loginToregisterButton.setOnClickListener(this::onClick);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.btn_joinMember) {
//            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
//            startActivity(intent);
//        }
//
//
//        if (v.getId() == R.id.btn_login) {
//            String id = userIdText.getText().toString();
//            String pw = userPasswordText.getText().toString();
//
//            // 암호화된 비밀번호 생성
//            String encryptedPw = PasswordHashing.sha256Hash(pw);
//
//            // Retrofit 인스턴스 생성
//            Retrofit retrofit = new Retrofit.Builder()
////                    .baseUrl("http://192.168.100.108:23468") //학원
//
//                    .baseUrl(BaseUrl.url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            // Retrofit을 사용하여 API 인터페이스 구현체 생성
//            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
//            //
//
//            // 로그인 정보를 담은 객체 생성
//            LoginEntity registerRequest = new LoginEntity(id, encryptedPw);
//
//            Call<Integer> checkLoginCall = registerApiService.loginUser(registerRequest);
//            checkLoginCall.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    // 중복 ID 확인 결과 처리
//                    if (response.isSuccessful()) {
//                        int result = response.body();
//                        if (result == 1) {
//                            // 중복된 ID인 경우
//                            Toast.makeText(LoginActivity.this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // 사용 가능한 ID인 경우
//                            Toast.makeText(LoginActivity.this, "잘못된 ID 혹은 비밀번호입니다.", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        // 중복 ID 확인 실패 처리
//                        Toast.makeText(LoginActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//                    // 중복 ID 확인 실패 처리
//                    Toast.makeText(LoginActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//}
