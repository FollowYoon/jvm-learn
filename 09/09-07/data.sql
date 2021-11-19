CREATE TABLE `account`(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `name`        varchar(10) DEFAULT NULL,
    `cny_balance` int(11) DEFAULT NULL,
    `us_balance`  int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `account` (`id`, `name`, `cny_balance`, `us_balance`) VALUES ('1', 'A', '7', '1');
INSERT INTO `account` (`id`, `name`, `cny_balance`, `us_balance`) VALUES ('2', 'B', '7', '1');
