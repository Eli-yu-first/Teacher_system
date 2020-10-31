package com.hnust.api;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import com.hnust.domain.Result;
import com.hnust.domain.Visual1;
import com.hnust.domain.Visual2;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
@RetrofitClient(baseUrl = "${retrofit.baseUrl}",poolName = "test2")
@Component
public interface StudentApi {

    @GET("student/getVisual1")
    Call<Result<List<Visual1>>> getVisual1(@Query("token")String token, @Query("tea_id")String tea_id);

    @GET("student/getVisual2")
    Call<Result<List<Visual2>>> getVisual2(@Query("token")String token, @Query("tea_id")String tea_id);

}
