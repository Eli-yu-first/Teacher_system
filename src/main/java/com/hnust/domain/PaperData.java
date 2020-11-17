package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-16 11:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaperData {
    private String token;
    private String id;
    /*** 通过率*/
    private String passRate;
    /*** 试卷名 */
    private String name;
    /*** 命题老师 */
    private String proTeacher;
    /*** 命题日期 */
    private String time;
    /*** 命题日期 */
    private String examTime;
    /*** 课程编号 */
    private String couNumber;
    /*** 年级专业 */
    private String grade;
    /*** 是否为样卷 */
    private int isSample;
    /*** 是A卷还是B卷 */
    private int isAb;
    /*** 开课单位 */
    private String collegeId;
    /*** 题目列表 */
    private List<String> questions;
}
