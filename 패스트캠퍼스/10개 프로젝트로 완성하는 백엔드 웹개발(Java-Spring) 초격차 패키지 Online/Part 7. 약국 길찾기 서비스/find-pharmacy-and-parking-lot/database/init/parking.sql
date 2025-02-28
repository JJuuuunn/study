DROP TABLE IF EXISTS `parking`;

CREATE TABLE `parking`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT,
    `parking_no`           varchar(20),
    `parking_name`         varchar(20),
    `st_name_address`      varchar(255),
    `rd_num_address`       varchar(255),
    `tel`                  varchar(12),
    `num_parking`          varchar(10),
    `info`                 varchar(255),

    `latitude`             varchar(20),
    `longitude`            varchar(20),

    `basic_time`           varchar(10),
    `basic_charge`         varchar(10),
    `add_unit_time`        varchar(10),
    `add_unit_charge`      varchar(10),
    `day_time`             varchar(10),
    `day_charge`           varchar(10),
    `month_charge`         varchar(10),
    `pay_type`             varchar(20),

    `operate_day`          varchar(20),
    `weekday_open_time`    varchar(10),
    `weekday_close_time`   varchar(10),
    `sat_open_time`        varchar(10),
    `sat_close_time`       varchar(10),
    `hol_open_time`        varchar(10),
    `hol_close_time`       varchar(10),

    `created_date`         datetime(6) DEFAULT NULL,
    `modified_date`        datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK
TABLES `parking` WRITE;

UNLOCK
TABLES;