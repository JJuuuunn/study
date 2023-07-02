DROP TABLE IF EXISTS `parking`;

CREATE TABLE `parking`
(
    `id`                   bigint(20) NOT NULL AUTO_INCREMENT,
    `parking_no`           varchar(20),
    `parking_name`         varchar(20),
    `st_name_address`      varchar(255),
    `rd_num_address`       varchar(255),
    `tel`                  varchar(12),
    `num_parking`          integer,
    `info`                 varchar(255),

    `latitude`             double NOT NULL,
    `longitude`            double NOT NULL,

    `basic_time`           time,
    `basic_charge`         integer,
    `add_unit_time`        time,
    `add_unit_charge`      integer,
    `day_time`             time,
    `day_charge`           integer,
    `month_charge`         integer,
    `pay_type`             varchar(20),

    `operate_day`          varchar(20),
    `weekday_open_time`    time,
    `weekday_close_time`   time,
    `sat_open_time`        time,
    `sat_close_time`       time,
    `hol_open_time`        time,
    `hol_close_time`       time,

    `created_date`         datetime(6) DEFAULT NULL,
    `modified_date`        datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK
TABLES `parking` WRITE;

UNLOCK
TABLES;