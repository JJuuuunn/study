DROP TABLE IF EXISTS `pharmacy`;

CREATE TABLE `pharmacy`
(
    `id`                   varchar(10) NOT NULL,
    `name`                 varchar(100) NOT NULL,
    `info`                 varchar(300),
    `tel`                  varchar(14),
    `address`              varchar(200) NOT NULL,
    `zip_code`             varchar(10),
    `latitude`             double NOT NULL,
    `longitude`            double NOT NULL,

    `mon_open_time`        time,
    `mon_close_time`       time,
    `tue_open_time`        time,
    `tue_close_time`       time,
    `wed_open_time`        time,
    `wed_close_time`       time,
    `thu_open_time`        time,
    `thu_close_time`       time,
    `fri_open_time`        time,
    `fri_close_time`       time,
    `sat_open_time`        time,
    `sat_close_time`       time,
    `sun_open_time`        time,
    `sun_close_time`       time,
    `hol_open_time`        time,
    `hol_close_time`       time,

    `created_date`         datetime(6) DEFAULT NULL,
    `modified_date`        datetime(6) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

LOCK
TABLES `pharmacy` WRITE;

UNLOCK
TABLES;