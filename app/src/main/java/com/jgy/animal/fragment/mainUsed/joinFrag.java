//package com.jgy.animal.fragment.mainUsed;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentManager;
//import androidx.fragment.app.FragmentTransaction;
//
//import com.jgy.animal.R;
//
//public class joinFrag extends LoginFrag implements View.OnClickListener {
//    @Nullable
//    @Override
//    public View onCreateView(
//            @NonNull LayoutInflater inflater,
//            @Nullable ViewGroup container,
//            @Nullable Bundle savedInstanceState) {
//        View joinView = inflater.inflate(R.layout.frag_joinmember, container, false);
//
//        return joinView;
//    }
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
//            case R.id.check_nickname:
//                Toast.makeText(getActivity(), "닉네임 중복확인 버튼입니다.", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.btn_joincomplete:
//                Toast.makeText(getActivity(), "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
////                Fragment LoginFrag = new LoginFrag();
////                fragmentTransaction.replace(R.id.fragmentContainerView, LoginFrag);
////                fragmentTransaction.addToBackStack(null); // 이전 Fragment로 돌아가기 위해 백스택에 추가
////                fragmentTransaction.commit();
//                break;
//        }
//    }
//}
