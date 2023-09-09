//package com.jgy.animal.fragment.mainUsed;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.google.android.material.button.MaterialButton;
//import com.jgy.animal.R;
//
//public class LoginFrag extends Fragment implements View.OnClickListener{
//
//    private MaterialButton btnJoinmember;
//
//    @Nullable
//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater,
//            @Nullable ViewGroup container,
//            @Nullable Bundle savedInstanceState) {
//        View loginView = inflater.inflate(R.layout.frag_login, container, false);
//
//        btnJoinmember = loginView.findViewById(R.id.btn_joinMember);
//
//        btnJoinmember.setOnClickListener(this);
//
//        return loginView;
//    }
//
//
//
//
//
//    @Override
//    public void onClick(View v) {
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        if (v == null) {
//            return;
//        }
//
//        switch (v.getId()) {
//            case R.id.btn_login:
//                Toast.makeText(getActivity(), "로그인이 완료되었습니다.", Toast.LENGTH_SHORT).show();
//                Fragment myFrag = new myFrag();
//                fragmentTransaction.replace(R.id.fragmentContainerView, myFrag);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//                break;
//            case R.id.btn_joinMember:
//                Toast.makeText(getActivity(), "회원가입 페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
//                Fragment joinFrag = new joinFrag();
//                fragmentTransaction.replace(R.id.fragmentContainerView, joinFrag);
//                fragmentTransaction.addToBackStack(null); // 이전 Fragment로 돌아가기 위해 백스택에 추가
//                fragmentTransaction.commit();
//                break;
//        }
//    }
//}
