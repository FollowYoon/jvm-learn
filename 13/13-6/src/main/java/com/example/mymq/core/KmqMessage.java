package com.example.mymq.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

/**
 * @author qindong
 * @date 2021/12/17 16:14
 */
@AllArgsConstructor
@Data
public class KmqMessage<T> {

    private HashMap<String,Object> headers;

    private T body;

}
