package com.ssg.webmvc_member.dao;

import com.ssg.webmvc_member.dto.MemberDTO;
import com.ssg.webmvc_member.dto.ModifiedDTO;
import com.ssg.webmvc_member.dto.SignInDTO;
import com.ssg.webmvc_member.util.DBConnectionUtil;
import com.ssg.webmvc_member.vo.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDao {

    public int save(MemberVO vo) {
        String INSERT_SQL = "INSERT INTO MVC_MEMBER (ID, PASSWORD, NAME, EMAIL) VALUES (?, ?, ?, ?)";
        int result = 0;
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(INSERT_SQL);
        ) {
            pstmt.setString(1, vo.getId());
            pstmt.setString(2, vo.getPassword());
            pstmt.setString(3, vo.getName());
            pstmt.setString(4, vo.getEmail());

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean existsById(String id) {
        String SELECT_SQL = "SELECT * FROM MVC_MEMBER WHERE ID = ?";
        boolean exists = false;
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL);
        ) {
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            exists = rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }

    public Optional<MemberDTO> findByIdAndPassword(SignInDTO dto) {
        String SELECT_SQL = "SELECT ID, PASSWORD, NAME, EMAIL, CREATED_AT, MODIFIED_AT FROM MVC_MEMBER WHERE ID = ? AND PASSWORD = ?";
        MemberDTO memberDTO = null;
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL);
        ) {
            pstmt.setString(1, dto.id());
            pstmt.setString(2, dto.password());

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                memberDTO = MemberDTO.builder()
                        .id(rs.getString(1))
                        .password(rs.getString(2))
                        .name(rs.getString(3))
                        .email(rs.getString(4))
                        .createdAt(rs.getDate(5).toLocalDate())
                        .modifiedAt(rs.getDate(6).toLocalDate())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(memberDTO);
    }

    public int update(ModifiedDTO dto) {
        String UPDATE_SQL = "UPDATE MVC_MEMBER SET PASSWORD = ?, NAME = ?, EMAIL = ? WHERE ID = ?";
        int result = 0;
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(UPDATE_SQL);
        ) {
            pstmt.setString(1, dto.password());
            pstmt.setString(2, dto.name());
            pstmt.setString(3, dto.email());
            pstmt.setString(4, dto.id());

            result = pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public int deleteById(String id) {
        String DELETE_SQL = "DELETE FROM MVC_MEMBER WHERE ID = ?";
        int result = 0;
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL);
        ) {
            pstmt.setString(1, id);

            result = pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteTestData() {
        String DELETE_SQL = "DELETE FROM MVC_MEMBER WHERE ID LIKE 'test%'";
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(DELETE_SQL);
        ) {
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<List<MemberDTO>> findAll() {
        String SELECT_SQL = "SELECT ID, PASSWORD, NAME, EMAIL, CREATED_AT, MODIFIED_AT FROM MVC_MEMBER";
        List<MemberDTO> memberDTOList = new ArrayList<>();
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL);
        ) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                MemberDTO memberDTO = MemberDTO.builder()
                        .id(rs.getString(1))
                        .password(rs.getString(2))
                        .name(rs.getString(3))
                        .email(rs.getString(4))
                        .createdAt(rs.getDate(5).toLocalDate())
                        .modifiedAt(rs.getDate(6).toLocalDate())
                        .build();
                memberDTOList.add(memberDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(memberDTOList);
    }

    public Optional<MemberDTO> findById(String id) {
        String SELECT_SQL = "SELECT ID, PASSWORD, NAME, EMAIL, CREATED_AT, MODIFIED_AT FROM MVC_MEMBER WHERE ID = ?";
        MemberDTO memberDTO = null;
        try (
                Connection connection = DBConnectionUtil.INSTANCE.getConnection();
                PreparedStatement pstmt = connection.prepareStatement(SELECT_SQL);
        ) {
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                memberDTO = MemberDTO.builder()
                        .id(rs.getString(1))
                        .password(rs.getString(2))
                        .name(rs.getString(3))
                        .email(rs.getString(4))
                        .createdAt(rs.getDate(5).toLocalDate())
                        .modifiedAt(rs.getDate(6).toLocalDate())
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(memberDTO);
    }
}
