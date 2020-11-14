package com.hnust.store;

import com.hnust.domain.QuestionType;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-11 10:50
 */
@Component
@Data
public class GeneratePaperDataStore {
    private String course;
    private LocalDate examTimeData;
    private String courseId;
    private String courseName;
    private LocalDate outExamTime;
    private String passRate;
    private String paperKind;
    private List<QuestionType> questionTypes;
    private Integer quesyionTypeId;
    private String questionType;
}