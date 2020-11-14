package com.hnust.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.hnust.domain.CourseData;
import com.hnust.domain.QuestionType;
import com.hnust.domain.SubjectInfo;
import com.hnust.domain.Teacher;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
@RetrofitClient(baseUrl = "${retrofit.baseUrl}",poolName = "test2")
@Component
public interface TestPaperApi {
    @GET("/subject/getCourseData")
    Call<List<CourseData>> getCourseData(@Query("id") String id,@Query("token") String token);

    @GET("/subject/getCourseQuesList")
    Call<List<QuestionType>> getCourseQuesList(@Query("token")String token,@Query("id") String id,@Query("course_id") String course_id);

    @GET("/testPaper/getCourseQuesList")
    Call<SubjectInfo> getQuestion(@Query("token")String token,@Query("id")String id,@Query("course_id")String course_id,@Query("tye_id")String tye_id,@Query("now_page")String now_page);

    @GET("/testPaper/getQuesByCon")
    Call<SubjectInfo> getQuesByCon(@Query("token")String token,@Query("id")String id,@Query("course_id")String course_id,@Query("tye_id")String tye_id,@Query("keyword")String keyword,@Query("now_page")String now_page);

}
