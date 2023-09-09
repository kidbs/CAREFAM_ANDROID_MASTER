package com.jgy.animal.interfaces;

import com.jgy.animal.Entities.BoardEntity;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface BoardApiService {

    @POST("/board/insert")
    Call<Void> insertBoard (@Body BoardEntity requestBody);

}
