package com.jgy.animal.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.jgy.animal.BaseUrl;
import com.jgy.animal.Entities.IdEntity;
import com.jgy.animal.Entities.MemberEntity;
import com.jgy.animal.PasswordHashing;
import com.jgy.animal.R;
import com.jgy.animal.interfaces.RegisterApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText getRegisterName;

    private EditText getRegisterEmail;

    private EditText getRegisterID;

    private EditText getRegisterPassword;

    private EditText getRegisterConfirmPassword;
    private Button getRegisterButton;

    private Button getIdDuplication;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_joinmember);

        getRegisterID = findViewById(R.id.join_id);
        getRegisterName = findViewById(R.id.join_name);
        getRegisterEmail = findViewById(R.id.join_email);
        getRegisterPassword = findViewById(R.id.join_password);
        getRegisterConfirmPassword = findViewById(R.id.join_repassword);

        getRegisterButton = findViewById(R.id.btn_joincomplete);
        getRegisterButton.setOnClickListener(this);

        // 중복 확인 버튼 초기화
        getIdDuplication = findViewById(R.id.check_nickname);
        getIdDuplication.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.check_nickname) {
            String id = getRegisterID.getText().toString();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Retrofit을 사용하여 API 인터페이스 구현체 생성
            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);

            IdEntity requestId = new IdEntity(id);

            // 회원 ID 중복 확인 API 호출
            Call<Integer> checkDuplicateIdCall = registerApiService.checkDuplicateId(requestId);
            checkDuplicateIdCall.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    // 중복 ID 확인 결과 처리
                    if (response.isSuccessful()) {
                        int result = response.body();
                        if (result == 1) {
                            // 중복된 ID인 경우
                            Toast.makeText(RegisterActivity.this, "중복된 ID입니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            // 사용 가능한 ID인 경우
                            Toast.makeText(RegisterActivity.this, "사용 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // 중복 ID 확인 실패 처리
                        Toast.makeText(RegisterActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {
                    // 중복 ID 확인 실패 처리
                    Toast.makeText(RegisterActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
                }
            });
        }

        if (v.getId() == R.id.btn_joincomplete) {
            String id = getRegisterID.getText().toString();
            String name = getRegisterName.getText().toString();
            String email = getRegisterEmail.getText().toString();
            String pw = getRegisterPassword.getText().toString();
            String confirmPw= getRegisterConfirmPassword.getText().toString();

            // 비밀번호와 비밀번호 확인이 일치하는지 확인
            if (!pw.equals(confirmPw)) {
                Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            // 암호화된 비밀번호 생성
            String encryptedPw = PasswordHashing.sha256Hash(pw);


            // Retrofit 인스턴스 생성
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Retrofit을 사용하여 API 인터페이스 구현체 생성
            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);

            // 회원 가입 정보를 담은 객체 생성
            MemberEntity registerRequest = new MemberEntity(id,name,email,encryptedPw);



            // 회원 가입 API 호출
            Call<Void> call = registerApiService.registerUser(registerRequest);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    // 회원 가입 성공 시

                    if (response.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // 회원 가입 실패 시 처리
                    Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();

                }
            });
        }







    }
}

//public class RegisterActivity extends Fragment implements View.OnClickListener {
//
//    private EditText getRegisterName;
//    private EditText getRegisterEmail;
//    private EditText getRegisterID;
//    private EditText getRegisterPassword;
//    private EditText getRegisterConfirmPassword;
//    private Button getEmailAuthentication;
//    private Button getRegisterButton;
//    private Button getIdDuplication;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater,
//            @Nullable ViewGroup container,
//            @Nullable Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.frag_joinmember, container, false);
//
//        getRegisterID = rootView.findViewById(R.id.join_id);
//        getRegisterName = rootView.findViewById(R.id.join_name);
//        getRegisterEmail = rootView.findViewById(R.id.join_email);
//        getRegisterPassword = rootView.findViewById(R.id.join_password);
//        getRegisterConfirmPassword = rootView.findViewById(R.id.join_repassword);
//
//        getRegisterButton = rootView.findViewById(R.id.btn_joincomplete);
//        getRegisterButton.setOnClickListener(this);
//
//        getIdDuplication = rootView.findViewById(R.id.check_nickname);
//        getIdDuplication.setOnClickListener(this);
//
//        return rootView;
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.check_nickname) {
//            // 중복 확인 버튼 클릭시, 서버에 중복 ID 확인 요청
//            String id = getRegisterID.getText().toString();
//
//            // Retrofit 인스턴스 생성
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BaseUrl.url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            // Retrofit 을 사용하여 API 인터페이스 구현체 생성
//            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
//
//            IdEntity requestId = new IdEntity(id);
//
//            // 회원 ID 중복 확인 API 호출
//            Call<Integer> checkDuplicateIdCall = registerApiService.checkDuplicateId(requestId);
//            checkDuplicateIdCall.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    // 중복 ID 확인 결과 처리
//                    if (response.isSuccessful()) {
//                        int result = response.body();
//                        if (result == 1) {
//                            // 중복된 ID인 경우
//                            Toast.makeText(getActivity(), "중복된 ID입니다.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // 사용 가능한 ID인 경우
//                            Toast.makeText(getActivity(), "사용 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
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
//
//
//        if (v.getId() == R.id.btn_joincomplete) {
//            String id = getRegisterID.getText().toString();
//            String name = getRegisterName.getText().toString();
//            String email = getRegisterEmail.getText().toString();
//            String pw = getRegisterPassword.getText().toString();
//            String confirmPw = getRegisterConfirmPassword.getText().toString();
//
//            // 비밀번호와 비밀번호 확인이 일치하는지 확인
//            if (!pw.equals(confirmPw)) {
//                Toast.makeText(getActivity(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // 암호화된 비밀번호 생성
//            String encryptedPw = PasswordHashing.sha256Hash(pw);
//
//            // Retrofit 인스턴스 생성
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BaseUrl.url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            // Retrofit을 사용하여 API 인터페이스 구현체 생성
//            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
//
//            // 회원 가입 정보를 담은 객체 생성
//            MemberEntity registerRequest = new MemberEntity(id, name, email, encryptedPw);
//
//            // 회원 가입 API 호출
//            Call<Void> call = registerApiService.registerUser(registerRequest);
//            call.enqueue(new Callback<Void>() {
//                @Override
//                public void onResponse(Call<Void> call, Response<Void> response) {
//                    // 회원 가입 성공 시
//                    if (response.isSuccessful()) {
//                        Toast.makeText(getActivity(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                        // 회원가입완료시 로그인 화면으로 전환
//                        if (v.getId() == R.id.btn_joincomplete) {
//                            moveToLoginFragment();
//                        }
//                    } else {
//                        Toast.makeText(getActivity(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Void> call, Throwable t) {
//                    // 회원 가입 실패 시 처리
//                    Toast.makeText(getActivity(), "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//    }
//
//    private void moveToLoginFragment() {
//        // FragmentManager와 FragmentTransaction을 사용하여 화면 전환
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        // LoginFrag로 전환
//        LoginActivity loginFragment = new LoginActivity();
//        fragmentTransaction.replace(R.id.fragmentContainerView, loginFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();
//    }
//
//}



//-----------------------------------------------------------
//package com.jgy.animal;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.jgy.animal.Entities.IdEntity;
//import com.jgy.animal.Entities.MemberEntity;
//import com.jgy.animal.interfaces.RegisterApiService;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
//    private EditText getRegisterName;
//
//    private ImageButton getBackButton;
//
//    private EditText getRegisterEmail;
//
//    private EditText getRegisterID;
//
//    private EditText getRegisterPassword;
//
//    private EditText getRegisterConfirmPassword;
//    private Button getEmailAuthentication;
//    private Button getRegisterButton;
//
//    private Button getIdDuplication;
//
//    //---------------------------------------
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.frag_joinmember);
//
//        getRegisterID = findViewById(R.id.join_id); // 아이디입력
//        getRegisterName = findViewById(R.id.join_name); // 이름입력
//        getRegisterEmail = findViewById(R.id.join_email); // 이메일 입력
//        getRegisterPassword = findViewById(R.id.join_password); // 비밀번호 입력
//        getRegisterConfirmPassword = findViewById(R.id.join_repassword); //비밀번호 확인
//
//        //getBackButton - fragment로 하게되면 필요가 없음
//        getRegisterButton = findViewById(R.id.btn_joincomplete); // 가입하기
//        getRegisterButton.setOnClickListener(this::onClick);
//
//        // 중복확인 버튼
//        getIdDuplication = findViewById(R.id.check_nickname);
//        getIdDuplication.setOnClickListener(this::onClick);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.check_nickname) {
//            // 중복 확인 버튼 클릭시, 서버에 중복 ID 확인 요청
//            String id = getRegisterID.getText().toString();
//
//            // Retrofit 인스턴스 생성
//            Retrofit retrofit = new Retrofit.Builder()
//                    .baseUrl(BaseUrl.url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            // Retrofit 을 사용하여 API 인터페이스 구현체 생성
//            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
//
//            IdEntity requestId = new IdEntity(id);
//
//            // 회원 ID 중복 확인 API 호출
//            Call<Integer> checkDuplicateIdCall = registerApiService.checkDuplicateId(requestId);
//            checkDuplicateIdCall.enqueue(new Callback<Integer>() {
//                @Override
//                public void onResponse(Call<Integer> call, Response<Integer> response) {
//                    // 중복 ID 확인 결과 처리
//                    if (response.isSuccessful()) {
//                        int result = response.body();
//                        if (result == 1) {
//                            // 중복된 ID인 경우
//                            Toast.makeText(RegisterActivity.this, "중복된 ID입니다.", Toast.LENGTH_SHORT).show();
//                        } else {
//                            // 사용 가능한 ID인 경우
//                            Toast.makeText(RegisterActivity.this, "사용 가능한 ID입니다.", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        // 중복 ID 확인 실패 처리
//                        Toast.makeText(RegisterActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Integer> call, Throwable t) {
//                    // 중복 ID 확인 실패 처리
//                    Toast.makeText(RegisterActivity.this, "서버 통신 오류", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//
//
//        if (v.getId() == R.id.btn_joincomplete) {
//            String id = getRegisterID.getText().toString();
//            String name = getRegisterName.getText().toString();
//            String email = getRegisterEmail.getText().toString();
//            String pw = getRegisterPassword.getText().toString();
//            String confirmPw = getRegisterConfirmPassword.getText().toString();
//
//            // 비밀번호와 비밀번호 확인이 일치하는지 확인
//            if (!pw.equals(confirmPw)) {
//                Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            // 암호화된 비밀번호 생성
//            String encryptedPw = PasswordHashing.sha256Hash(pw);
//
//            // Retrofit 인스턴스 생성
//            Retrofit retrofit = new Retrofit.Builder()
////                    .baseUrl("http://192.168.100.108:23468") //학원
//                    .baseUrl(BaseUrl.url) // 집 wifi 서버의 기본 URL
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//
//            // Retrofit을 사용하여 API 인터페이스 구현체 생성
//            RegisterApiService registerApiService = retrofit.create(RegisterApiService.class);
//
//            // 회원 가입 정보를 담은 객체 생성
//            MemberEntity registerRequest = new MemberEntity(id,name,email,encryptedPw);
//
//            // 회원 가입 API 호출
//            Call<Void> call = registerApiService.registerUser(registerRequest);
//            call.enqueue(new Callback<Void>() {
//                @Override
//                public void onResponse(Call<Void> call, Response<Void> response) {
//                    // 회원 가입 성공 시
//
//                    if (response.isSuccessful()) {
//                        Toast.makeText(RegisterActivity.this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Void> call, Throwable t) {
//                    // 회원 가입 실패 시 처리
//                    Toast.makeText(RegisterActivity.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
//    }
//
//
//}
