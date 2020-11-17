package com.hnust.api;

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
    @GET("download/question")
    Call<Result<File>> getTem();
}
