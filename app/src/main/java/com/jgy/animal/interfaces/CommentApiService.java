//package com.jgy.animal.interfaces;
//
//import com.jgy.animal.Entities.CommentEntity;
//
//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.POST;
//import retrofit2.http.Query;
//
//public interface CommentApiService {
//    //지역별 리스트 //////////////////////////////////////////////////////////////////////////////////
//    //==============================================================================================
//    // 서울특별시
////    @POST("/home/seoulData")
////    Call<AnimalEntity> getSeoulFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/seoulIndexLength")
////    Call<Integer> getSeoulIndexLength();
//
//    @POST("/seoul")
//    Call<CommentEntity> getFindSeoul(@Query("apiIndex") int apiIndex);
//
//    @GET("/seoulIndexLength")
//    Call<Integer> getSeoulIndexLength();
//    //==============================================================================================
//    // 부산광역시
////    @POST("/home/busanData")
////    Call<AnimalEntity> getBusanFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/busanIndexLength")
////    Call<Integer> getBusanIndexLength();
//    @POST("/busan")
//    Call<CommentEntity> getFindBusan(@Query("apiIndex") int apiIndex);
//
//    @GET("/busanIndexLength")
//    Call<Integer> getBusanIndexLength();
//
//    //==============================================================================================
//    // 인천광역시
////    @POST("/home/incheonData")
////    Call<AnimalEntity> getIncheonFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/incheonIndexLength")
////    Call<Integer> getIncheonIndexLength();
//
//    @POST("/incheon")
//    Call<CommentEntity> getFindIncheon(@Query("apiIndex") int apiIndex);
//
//    @GET("/incheonIndexLength")
//    Call<Integer> getIncheonIndexLength();
//    //==============================================================================================
//    // 대구광역시
////    @POST("/home/daeguData")
////    Call<AnimalEntity> getDaeguFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/daeguIndexLength")
////    Call<Integer> getDaeguIndexLength();
//
//    @POST("/daegu")
//    Call<CommentEntity> getFindDaegu(@Query("apiIndex") int apiIndex);
//
//    @GET("/daeguIndexLength")
//    Call<Integer> getDaeguIndexLength();
//
//
//    //==============================================================================================
//    // 대전광역시
////    @POST("/home/daejeonData")
////    Call<AnimalEntity> getDaejeonFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/daejeonIndexLength")
////    Call<Integer> getDaejeonIndexLength();
//
//    @POST("/daejeon")
//    Call<CommentEntity> getFindDaejeon(@Query("apiIndex") int apiIndex);
//
//    @GET("/daejeonIndexLength")
//    Call<Integer> getDaejeonIndexLength();
//    //==============================================================================================
//    // 광주광역시
////    @POST("/home/gwangjuData")
////    Call<AnimalEntity> getGwangjuFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/gwangjuIndexLength")
////    Call<Integer> getGwangjuIndexLength();
//
//    @POST("/gwangju")
//    Call<CommentEntity> getFindGwangju(@Query("apiIndex") int apiIndex);
//
//    @GET("/gwangjuIndexLength")
//    Call<Integer> getGwangjuIndexLength();
//    //==============================================================================================
//    // 울산광역시
////    @POST("/home/ulsanData")
////    Call<AnimalEntity> getUlsanFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/ulsanIndexLength")
////    Call<Integer> getUlsanIndexLength();
//
//    @POST("/ulsan")
//    Call<CommentEntity> getFindUlsan(@Query("apiIndex") int apiIndex);
//
//    @GET("/ulsanIndexLength")
//    Call<Integer> getUlsanIndexLength();
//    //==============================================================================================
//    // 경기도
////    @POST("/home/gyeonggiData")
////    Call<AnimalEntity> getGyeonggiFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/gyeonggiIndexLength")
////    Call<Integer> getGyeonggiIndexLength();
//
//    @POST("/gyeonggi")
//    Call<CommentEntity> getFindGyeonggi(@Query("apiIndex") int apiIndex);
//
//    @GET("/gyeonggiIndexLength")
//    Call<Integer> getGyeonggiIndexLength();
//    //==============================================================================================
//    // 경상도
////    @POST("/home/gyeongsangData")
////    Call<AnimalEntity> getGyeongsangFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/gyeongsangIndexLength")
////    Call<Integer> getGyeongsangIndexLength();
//
//    @POST("/gyeongsang")
//    Call<CommentEntity> getFindGyeongsang(@Query("apiIndex") int apiIndex);
//
//    @GET("/gyeongsangIndexLength")
//    Call<Integer> getGyeongsangIndexLength();
//    //==============================================================================================
//    // 전라도
////    @POST("/home/jeonllaData")
////    Call<AnimalEntity> getJeonllaFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/jeonllaIndexLength")
////    Call<Integer> getJeonllaIndexLength();
//
//    @POST("/jeonla")
//    Call<CommentEntity> getFindJeonla(@Query("apiIndex") int apiIndex);
//
//    @GET("/jeonlaIndexLength")
//    Call<Integer> getJeonlaIndexLength();
//    //==============================================================================================
//    // 충청도
////    @POST("/home/chungcheongData")
////    Call<AnimalEntity> getChungcheongFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/chungcheongIndexLength")
////    Call<Integer> getChungcheongIndexLength();
//
//    @POST("/chungchung")
//    Call<CommentEntity> getFindChungchung(@Query("apiIndex") int apiIndex);
//
//    @GET("/chungchungIndexLength")
//    Call<Integer> getChungchungIndexLength();
//    //==============================================================================================
//    // 강원도
////    @POST("/home/gangwonData")
////    Call<AnimalEntity> getGangwonFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/gangwonIndexLength")
////    Call<Integer> getGangwonIndexLength();
//
//    @POST("/gangwon")
//    Call<CommentEntity> getFindGangwon(@Query("apiIndex") int apiIndex);
//
//    @GET("/gangwonIndexLength")
//    Call<Integer> getGangwonIndexLength();
//    //==============================================================================================
//    // 세종특별자치시
////    @POST("/home/sejongData")
////    Call<AnimalEntity> getSejongFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/sejongIndexLength")
////    Call<Integer> getSejongIndexLength();
//
//    @POST("/sejong")
//    Call<CommentEntity> getFindSejong(@Query("apiIndex") int apiIndex);
//
//    @GET("/sejongIndexLength")
//    Call<Integer> getSejongIndexLength();
//    //==============================================================================================
//    // 제주특별자치도
////    @POST("/home/jejuData")
////    Call<AnimalEntity> getJejuFacility(@Query("apiIndex") int apiIndex);
////
////    @GET("/home/jejuIndexLength")
////    Call<Integer> getJejuIndexLength();
//
//    @POST("/jeju")
//    Call<CommentEntity> getFindJeju(@Query("apiIndex") int apiIndex);
//
//    @GET("/jejuIndexLength")
//    Call<Integer> getJejuIndexLength();
//    //==============================================================================================
//
//}
