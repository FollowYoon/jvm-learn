package com.jvm.db.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qindong
 * @date 2021/11/12 16:13
 */
@Data
public class OrderInfoBase {

    private Long id;

    private Integer userId;

    private Integer addressId;

    private Integer goodsCount = 0;

    private BigDecimal totalPrice;

    private Integer orderStatus = 0;

    private Integer earnScore;

    private Date dateCreated;

    private Date updateTime;

}
