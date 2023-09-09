package com.jgy.animal.interfaces;

import com.jgy.animal.Entities.IdEntity;
import com.jgy.animal.Entities.LoginEntity;
import com.jgy.animal.Entities.MemberEntity;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterApiService {
    @POST("/member/submit")
    Call<Void> registerUser(@Body MemberEntity requestBody);

    @POST("/member/checkDuplicateId")
    Call<Integer> checkDuplicateId(@Body IdEntity id);

    @POST("/member/login")
    Call<Integer> loginUser(@Body LoginEntity requestBody);
}
