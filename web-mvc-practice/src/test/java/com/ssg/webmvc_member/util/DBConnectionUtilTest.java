package com.ssg.webmvc_member.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DBConnectionUtil 테스트")
class DBConnectionUtilTest {

    @Test
    @DisplayName("DBConnectionUtil getConnection() 테스트")
    void given_whenGetConnection_thenNotNull() {
        // given
        Connection connection = null;

        // when
        try {
            connection = DBConnectionUtil.INSTANCE.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // then
        assertNotNull(connection);
    }
}