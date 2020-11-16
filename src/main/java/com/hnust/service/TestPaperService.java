package com.hnust.service;

import com.hnust.domain.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
public interface TestPaperService {
    void getCourseData(Callback<Result<List<CourseData>>> callback, String id, String token);
    void getCourseQuesList(Callback<Result<List<QuestionType>>> callback,String token,String id);
    void getQuestion(Callback<Result<SubjectInfo>> callback,String token,String course_id,String type_id,String now_page);
    void getQuesByCon(Callback<Result<SubjectInfo>> callback,String token,String id,String course_id,String type_id,String keyword,String now_page);
    void checkPaperRepeat(Callback<Result<List<RepeatQues>>> callback,String token,String id,String course_id,List<String> subject_ids);
}
