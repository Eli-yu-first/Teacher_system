package com.hnust.service.impl;

import com.hnust.api.TeacherApi;
import com.hnust.api.TestPaperApi;
import com.hnust.domain.*;
import com.hnust.service.TestPaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Callback;

import java.util.List;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
@Service("testPaperService")
public class TestPaperServiceImpl implements TestPaperService {
    @Autowired
    private TestPaperApi testPaperApi;

    @Override
    public void getCourseData(Callback<List<CourseData>> callback, String id, String token) {
        testPaperApi.getCourseData(id, token).enqueue(callback);
    }

    @Override
    public void getCourseQuesList(Callback<List<QuestionType>> callback, String id, String course_id, String token) {
        testPaperApi.getCourseQuesList(token, id, course_id).enqueue(callback);
    }

    @Override
    public void getQuestion(Callback<SubjectInfo> callback, String token, String id, String course_id, String tye_id, String now_page) {
        testPaperApi.getQuestion(token, id,course_id,tye_id,now_page).enqueue(callback);
    }

    @Override
    public void getQuesByCon(Callback<SubjectInfo> callback, String token, String id, String course_id, String tye_id, String keyword, String now_page) {
        testPaperApi.getQuesByCon(token, id,course_id,tye_id,keyword,now_page).enqueue(callback);
    }

    @Override
    public void checkPaperRepeat(Callback<List<RepeatQues>> callback, String token, String id, String course_id, List<String> subject_ids) {
        testPaperApi.checkPaperRepeat(token,id,course_id,subject_ids).enqueue(callback);
    }
}
