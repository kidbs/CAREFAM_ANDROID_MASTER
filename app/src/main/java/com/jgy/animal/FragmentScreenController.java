package com.jgy.animal;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jetbrains.annotations.Nullable;

public interface FragmentScreenController {
    interface Screen {
        int getId();
        Fragment getFragment();
    }

    static FragmentScreenController get(AppCompatActivity activity) {
        return new Default(activity);
    }

    public interface OnFragmentReplaceListener {
        void onFragmentReplace();
    }

    void startFragment(Screen screen);
    void startFragment(Screen screen, @Nullable Bundle args);
    void startFragment(Screen screen, Boolean addToBackStack, @Nullable Bundle args);
    void replaceFragment(Screen screen);
    void replaceFragment(Screen screen, @Nullable Bundle args);
    void replaceFragment(Screen screen, Boolean addToBackStack, @Nullable Bundle args);

    public class Default implements FragmentScreenController {
        private FragmentManager fragmentManager;

        Default(AppCompatActivity activity) {
            this.fragmentManager = activity.getSupportFragmentManager();
        }

        @Override
        public void startFragment(Screen screen) {
            startFragment(screen, null);
        }

        @Override
        public void startFragment(Screen screen, @Nullable Bundle args) {
            startFragment(screen, false, args);
        }

        @Override
        public void startFragment(Screen screen, Boolean addToBackStack, @Nullable Bundle args) {
            final Fragment fragment = screen.getFragment();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragmentContainerView, screen.getFragment());
            if(addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            // 띄울때 argument 넘겨줄수있음.
            fragment.setArguments(args);
            //
            fragmentTransaction.commit();
        }

        @Override
        public void replaceFragment(Screen screen) {
            replaceFragment(screen, null);
        }

        @Override
        public void replaceFragment(Screen screen, @Nullable Bundle args) {
            replaceFragment(screen, false, args);
        }

        @Override
        public void replaceFragment(Screen screen, Boolean addToBackStack, @Nullable Bundle args) {
            //현재 프래그먼트 가져오기
            Fragment prevFragment = fragmentManager.findFragmentById(R.id.fragmentContainerView);
            if(prevFragment instanceof OnFragmentReplaceListener) {
                ((OnFragmentReplaceListener)prevFragment).onFragmentReplace();
            }

            final Fragment fragment = screen.getFragment();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
            if(addToBackStack) {
                fragmentTransaction.addToBackStack(null);
            }
            // 띄울때 argument 넘겨줄수있음.
            fragment.setArguments(args);
            //
            fragmentTransaction.commit();
        }
    }
}