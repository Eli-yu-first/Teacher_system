package com.hnust.store;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 用于存放界面的公共数据
 */
@Component
@Data
public class DataStore {
    private String token;
}
