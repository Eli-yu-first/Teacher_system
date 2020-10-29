package com.hnust.service;


import com.hnust.domain.Teacher;
import retrofit2.Callback;

import java.util.Calendar;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/28
 * 描述：
 */
public interface TeacherService {
    void teacherLogin(Callback<Teacher> callback, String account, String password);
}
