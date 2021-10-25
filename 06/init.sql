CREATE TABLE `goods_category`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品分类id',
    `category_name` varchar(30) DEFAULT NULL COMMENT '商品分类名称',
    `logic_delete`  int(11) DEFAULT '0' COMMENT '逻辑删除 0否 1是',
    `date_created`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品分类表';

CREATE TABLE `goods_info_base`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `goods_name`   varchar(100)   NOT NULL COMMENT '商品名称',
    `price`        decimal(10, 2) NOT NULL COMMENT '价格',
    `available`    int(11) NOT NULL DEFAULT '1' COMMENT '是否上架 0否 1是',
    `category_id`  bigint(20) NOT NULL COMMENT '分类id',
    `discount`     int(11) DEFAULT NULL COMMENT '折扣率%',
    `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

CREATE TABLE `order_info_base`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `user_id`      bigint(20) NOT NULL COMMENT '用户id',
    `address_id`   bigint(20) DEFAULT NULL COMMENT '收货地址',
    `goods_count`  int(11) NOT NULL COMMENT '商品数量',
    `total_price`  decimal(10, 2) DEFAULT NULL COMMENT '商品总价',
    `order_status` int(11) DEFAULT '0' COMMENT '订单状态 0新提交待支付 1已支付 2已取消 3已删除 4待发货 5已发货 6已完成',
    `earn_score`   int(11) DEFAULT NULL COMMENT '获得积分',
    `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息表';

CREATE TABLE `order_goods`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单商品id',
    `order_id`     bigint(20) NOT NULL COMMENT '订单id',
    `goods_id`     bigint(20) NOT NULL COMMENT '商品id',
    `count`        int(11) NOT NULL COMMENT '数量',
    `unit`         varchar(10)    NOT NULL COMMENT '计量单位',
    `unit_price`   decimal(10, 2) NOT NULL COMMENT '单价',
    `price`        decimal(10, 2) NOT NULL COMMENT '价格',
    `discount`     int(11) DEFAULT NULL COMMENT '折扣率%',
    `date_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';

CREATE TABLE `user_info_base`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `username`      varchar(30) NOT NULL COMMENT '用户名',
    `phone`         char(11)    NOT NULL COMMENT '手机号',
    `password`      varchar(32) NOT NULL COMMENT '密码md5加密',
    `nick_name`     varchar(20)  DEFAULT NULL COMMENT '昵称',
    `gender`        int(11) DEFAULT NULL COMMENT '性别 1男 2女',
    `status`        int(11) NOT NULL DEFAULT '1' COMMENT '状态 0删除 1可用 2禁用',
    `email_address` varchar(50)  DEFAULT NULL COMMENT '邮箱地址',
    `avatar_url`    varchar(255) DEFAULT NULL COMMENT '头像图片地址',
    `score`         int(11) DEFAULT '0' COMMENT '积分',
    `date_created`  timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户基本信息表';

CREATE TABLE `user_address`
(
    `id`                 bigint(20) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
    `user_id`            bigint(20) NOT NULL COMMENT '用户id',
    `area_id`            varchar(20)  NOT NULL COMMENT '地区id',
    `detail_address`     varchar(200) NOT NULL COMMENT '详细地址',
    `receive_user_name`  varchar(30)  NOT NULL COMMENT '收货人姓名',
    `receive_user_phone` varchar(11)  NOT NULL COMMENT '收货人手机号',
    `default_address`    int(11) DEFAULT '0' COMMENT '是否默认收货地址 0否 1是',
    `date_created`       timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`        timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';





