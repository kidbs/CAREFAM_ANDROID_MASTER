package com.jgy.animal.interfaces;

import com.jgy.animal.Entities.AnimalEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AnimalApiService {
    // Search 기능 /////////////////////////////////////////////////////////////////////////////////

//    @POST("/home/search")
//    Call<ApiFacility> getSearchFacility(@Query("apiIndex") int apiIndex, @Query("query") String query);
    @POST("/search")
    Call<AnimalEntity> getSearchFacility(@Query("keyword") String query, @Query("apiIndex") int apiIndex);

//    @GET("/home/searchIndexLength")
//    Call<Integer> getSearchIndexLength(@Query("query") String query);

    @GET("/searchIndexLength")
    Call<Integer> getSearchIndexLength(@Query("keyword") String query);

    ///////////////////////////////////////////////////////////////////////////////////////////////

    //카테고리별 리스트 ///////////////////////////////////////////////////////////////////////////////
    //==============================================================================================
//    @POST("/home/campData")
//    Call<AnimalEntity> getCampFacility(@Query("apiIndex") int apiIndex);
//
//    @GET("/home/campIndexLength")
//    Call<Integer> getCampIndexLength();

    @POST("/camp")
    Call<AnimalEntity> getFindCamp(@Query("apiIndex") int apiIndex);


    @GET("/campIndexLength")
    Call<Integer> getCampIndexLength();

    //==============================================================================================
//    @POST("/home/cultData")
//    Call<AnimalEntity> getCultFacility(@Query("apiIndex") int apiIndex);
//
//    @GET("/home/cultIndexLength")
//    Call<Integer> getCultIndexLength();

    @POST("/culture")
    Call<AnimalEntity> getFindCulture(@Query("apiIndex") int apiIndex);

    @GET("/cultureIndexLength")
    Call<Integer> getCultureIndexLength();

    //==============================================================================================
//    @POST("/home/butyData")
//    Call<AnimalEntity> getButyFacility(@Query("apiIndex") int apiIndex);
//
//    @GET("/home/butyIndexLength")
//    Call<Integer> getButyIndexLength();

    @POST("/beauty")
    Call<AnimalEntity> getFindBeauty(@Query("apiIndex") int apiIndex);

    @GET("/beautyIndexLength")
    Call<Integer> getBeautyIndexLength();

    //==============================================================================================
//    @POST("/home/foodData")
//    Call<AnimalEntity> getFoodFacility(@Query("apiIndex") int apiIndex);
//
//    @GET("/home/foodIndexLength")
//    Call<Integer> getFoodIndexLength();

    @POST("/food")
    Call<AnimalEntity> getFindFood(@Query("apiIndex") int apiIndex);

    @GET("/foodIndexLength")
    Call<Integer> getFoodIndexLength();

    //==============================================================================================
//    @POST("/home/mediData")
//    Call<AnimalEntity> getMediFacility(@Query("apiIndex") int apiIndex);
//
//    @GET("/home/mediIndexLength")
//    Call<Integer> getMediIndexLength();

    @POST("/medical")
    Call<AnimalEntity> getFindMedical(@Query("apiIndex") int apiIndex);

    @GET("/medicalIndexLength")
    Call<Integer> getMedicalIndexLength();

    //==============================================================================================
//    @POST("/home/tripData")
//    Call<AnimalEntity> getTripFacility(@Query("apiIndex") int apiIndex);
//
//    @GET("/home/tripIndexLength")
//    Call<Integer> getTripIndexLength();

    @POST("/trip")
    Call<AnimalEntity> getFindTrip(@Query("apiIndex") int apiIndex);

    @GET("/tripIndexLength")
    Call<Integer> getTripIndexLength();

    //지역별 데이터
    //==========================================================================================

    @POST("/seoul")
    Call<AnimalEntity> getFindSeoul(@Query("apiIndex") int apiIndex);

    @GET("/seoulIndexLength")
    Call<Integer> getSeoulIndexLength();

    @POST("/gyeonggi")
    Call<AnimalEntity> getFindGyeonggi(@Query("apiIndex") int apiIndex);

    @GET("/gyeonggiIndexLength")
    Call<Integer> getGyeonggiIndexLength();

    @POST("/jeonla")
    Call<AnimalEntity> getFindJeonla(@Query("apiIndex") int apiIndex);

    @GET("/jeonlaIndexLength")
    Call<Integer> getJeonlaIndexLength();

    @POST("/incheon")
    Call<AnimalEntity> getFindIncheon(@Query("apiIndex") int apiIndex);

    @GET("/incheonIndexLength")
    Call<Integer> getIncheonIndexLength();

    @POST("/gyeongsang")
    Call<AnimalEntity> getFindGyeongsang(@Query("apiIndex") int apiIndex);

    @GET("/gyeongsangIndexLength")
    Call<Integer> getGyeongsangIndexLength();

    @POST("/daegu")
    Call<AnimalEntity> getFindDaegu(@Query("apiIndex") int apiIndex);

    @GET("/daeguIndexLength")
    Call<Integer> getDaeguIndexLength();

    @POST("/sejong")
    Call<AnimalEntity> getFindSejong(@Query("apiIndex") int apiIndex);

    @GET("/sejongIndexLength")
    Call<Integer> getSejongIndexLength();




    @POST("/gangwon")
    Call<AnimalEntity> getFindGangwon(@Query("apiIndex") int apiIndex);

    @GET("/gangwonIndexLength")
    Call<Integer> getGangwonIndexLength();


    @POST("/busan")
    Call<AnimalEntity> getFindBusan(@Query("apiIndex") int apiIndex);

    @GET("/busanIndexLength")
    Call<Integer> getBusanIndexLength();

    @POST("/gwangju")
    Call<AnimalEntity> getFindGwangju(@Query("apiIndex") int apiIndex);

    @GET("/gwangjuIndexLength")
    Call<Integer> getGwangjuIndexLength();

    @POST("/daejeon")
    Call<AnimalEntity> getFindDaejeon(@Query("apiIndex") int apiIndex);

    @GET("/daejeonIndexLength")
    Call<Integer> getDaejeonIndexLength();

    @POST("/chungchung")
    Call<AnimalEntity> getFindChungchung(@Query("apiIndex") int apiIndex);

    @GET("/chungchungIndexLength")
    Call<Integer> getChungchungIndexLength();

    @POST("/jeju")
    Call<AnimalEntity> getFindJeju(@Query("apiIndex") int apiIndex);

    @GET("/jejuIndexLength")
    Call<Integer> getJejuIndexLength();




    @POST("/ulsan")
    Call<AnimalEntity> getFindUlsan(@Query("apiIndex") int apiIndex);

    @GET("/ulsanIndexLength")
    Call<Integer> getUlsanIndexLength();
}
