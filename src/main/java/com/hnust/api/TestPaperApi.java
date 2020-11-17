package com.hnust.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.hnust.domain.*;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.hnust.domain.Result;
import com.hnust.domain.Visual2;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
@RetrofitClient(baseUrl = "${retrofit.baseUrl}",poolName = "test2")
@Component
public interface TestPaperApi {
    @GET("/subject/getCourseData")
    Call<Result<List<CourseData>>> getCourseData(@Query("id") String id,@Query("token") String token);

    //获取该课程的题型
    @GET("/api/teacher/testPaper/getQuesTypeList")
    Call<Result<List<QuestionType>>> getCourseQuesList(@Query("token")String token,@Query("id") String id);  //ID为courseId

    //获取该课程对应的题型题库
    @GET("/api/teacher/testPaper/getCourseQuesList")
    Call<Result<SubjectInfo>> getQuestion(@Query("token")String token,@Query("course_id")String course_id,@Query("type_id")String type_id,@Query("now_page")String now_page);

    @GET("/api/teacher/testPaper/getQuesByCon")
    Call<Result<SubjectInfo>> getQuesByCon(@Query("token")String token,@Query("id")String id,@Query("course_id")String course_id,@Query("type_id")String type_id,@Query("keyword")String keyword,@Query("now_page")String now_page);

    @GET("/api/teacher/testPaper/checkPaperRepeat")
    Call<Result<List<RepeatQues>>> checkPaperRepeat(@Query("token") String token,@Query("id") String id,@Query("course_id")String course_id,@Query("subject_ids")List<String> subject_ids);
    @GET("download/question")
    Call<Result<File>> getTem();
}
