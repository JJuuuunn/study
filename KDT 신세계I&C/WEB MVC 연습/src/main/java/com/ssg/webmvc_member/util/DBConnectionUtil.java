package com.ssg.webmvc_member.util;

import com.zaxxer.hikari.*;

import java.sql.Connection;

public enum DBConnectionUtil {
    INSTANCE;
    private HikariDataSource ds;

    DBConnectionUtil() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setJdbcUrl("jdbc:mariadb://localhost:3307/webmvc");
        config.setUsername("webmvc");
        config.setPassword("webmvc");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws Exception {
        return ds.getConnection();
    }
}
