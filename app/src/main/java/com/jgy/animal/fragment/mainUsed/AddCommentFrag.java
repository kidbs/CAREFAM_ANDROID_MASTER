package com.jgy.animal.fragment.mainUsed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jgy.animal.R;

public class AddCommentFrag extends Fragment implements View.OnClickListener {

    private RatingBar ratingBarIndicator;
    private TextView ratingText;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View addCommentView = inflater.inflate(R.layout.frag_addcomment, container, false);

        ratingBarIndicator = addCommentView.findViewById(R.id.ratingBarIndicator);
        ratingBarIndicator.setStepSize(0.5f);

        ratingText = addCommentView.findViewById(R.id.ratingText);

        ratingBarIndicator.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText.setText("평점 : " + rating);
            }
        });

        return addCommentView;
    }

    @Override
    public void onClick(View v) {

    }
}
