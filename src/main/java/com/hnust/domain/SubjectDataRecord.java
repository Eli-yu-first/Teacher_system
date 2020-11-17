package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-13 22:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDataRecord {
    private SubjectData subjectData;
    private Integer score;
    private Boolean repeated;
    private Boolean checked;
}
