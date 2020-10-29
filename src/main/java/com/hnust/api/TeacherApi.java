package com.hnust.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.hnust.domain.Teacher;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
@RetrofitClient(baseUrl = "${retrofit.baseUrl}",poolName = "test1")
@Component
public interface TeacherApi {

    //@Headers({"Content-Type: application/json;charset=UTF-8"})
    @POST("/login")
    @FormUrlEncoded
    Call<Teacher> teacherLogin(@Field("faculty_number")String account,@Field("password")String password);
}
