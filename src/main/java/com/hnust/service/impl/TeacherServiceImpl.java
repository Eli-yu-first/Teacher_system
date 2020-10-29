package com.hnust.service.impl;

import com.hnust.api.TeacherApi;
import com.hnust.domain.Teacher;
import com.hnust.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Callback;


/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherApi teacherApi;

    @Override
    public void teacherLogin(Callback<Teacher> callback, String account, String password) {
        teacherApi.teacherLogin(account,password).enqueue(callback);
    }
}
