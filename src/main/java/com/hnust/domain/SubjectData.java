package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: 出卷系统项目
 * @author: 彭鑫淼
 * @description: 题目详细信息
 * @create: 2020-11-12 22:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectData {
    private String id;
    private String content;
    private String course_id;
    private String chapter_id;
    private String type;
    private int difficult;
    private String resolve;
    private String knowledgePointer;
    private List<AnswerObj> answer;
    private String courseTargetId;
    private int queAccess;
}
