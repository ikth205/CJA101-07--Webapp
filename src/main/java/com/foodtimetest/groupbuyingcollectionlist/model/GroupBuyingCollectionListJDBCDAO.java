package com.foodtimetest.groupbuyingcollectionlist.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupBuyingCollectionListJDBCDAO implements GroupBuyingCollectionListDAO_interface{
    // JDBC 驅動與連線參數
    private static final String DRIVER   = "com.mysql.cj.jdbc.Driver";
    private static final String URL      = "jdbc:mysql://localhost:3306/g05?serverTimezone=Asia/Taipei";
    private static final String USER     = "root";
    private static final String PASSWORD = "123456";

    // SQL 語句
    private static final String INSERT_STMT   = "INSERT INTO GROUP_BUYING_COLLECTION_LIST (GB_ID, MEM_ID, CREATE_AT) VALUES (?, ?, ?)";
    private static final String DELETE_STMT   = "DELETE FROM GROUP_BUYING_COLLECTION_LIST WHERE GB_ID = ? AND MEM_ID = ?";
    private static final String GET_ONE_STMT  = "SELECT GB_ID, MEM_ID, CREATE_AT FROM GROUP_BUYING_COLLECTION_LIST WHERE GB_ID = ? AND MEM_ID = ?";
    private static final String GET_ALL_STMT  = "SELECT GB_ID, MEM_ID, CREATE_AT FROM GROUP_BUYING_COLLECTION_LIST";
    private static final String GET_BY_MEMID  = "SELECT GB_ID, MEM_ID, CREATE_AT FROM GROUP_BUYING_COLLECTION_LIST WHERE MEM_ID = ?";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load JDBC driver: " + DRIVER, e);
        }
    }

    public void insert(GroupBuyingCollectionListVO collectionVO) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(INSERT_STMT)) {

            pstmt.setInt(1, collectionVO.getGbId());
            pstmt.setInt(2, collectionVO.getMemId());
            pstmt.setTimestamp(3, new Timestamp(collectionVO.getCreateAt().getTime()));
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred during insert: " + e.getMessage(), e);
        }
    }

    public void delete(Integer gbId, Integer memId) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(DELETE_STMT)) {

            pstmt.setInt(1, gbId);
            pstmt.setInt(2, memId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred during delete: " + e.getMessage(), e);
        }
    }

    public GroupBuyingCollectionListVO findByPrimaryKey(Integer gbId, Integer memId) {
        GroupBuyingCollectionListVO collectionVO = null;
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT)) {

            pstmt.setInt(1, gbId);
            pstmt.setInt(2, memId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	collectionVO = new GroupBuyingCollectionListVO();
                	collectionVO.setGbId     (rs.getInt("GB_ID"));
                	collectionVO.setMemId    (rs.getInt("MEM_ID"));
                	collectionVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred during findByPrimaryKey: " + e.getMessage(), e);
        }
        return collectionVO;
    }

    public List<GroupBuyingCollectionListVO> getAll() {
        List<GroupBuyingCollectionListVO> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                GroupBuyingCollectionListVO collectionVO = new GroupBuyingCollectionListVO();
                collectionVO.setGbId     (rs.getInt("GB_ID"));
                collectionVO.setMemId    (rs.getInt("MEM_ID"));
                collectionVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
                list.add(collectionVO);
            }

        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred during getAll: " + e.getMessage(), e);
        }
        return list;
    }

    public List<GroupBuyingCollectionListVO> findByMemId(Integer memId) {
        List<GroupBuyingCollectionListVO> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement(GET_BY_MEMID)) {

            pstmt.setInt(1, memId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    GroupBuyingCollectionListVO collectionVO = new GroupBuyingCollectionListVO();
                    collectionVO.setGbId     (rs.getInt("GB_ID"));
                    collectionVO.setMemId    (rs.getInt("MEM_ID"));
                    collectionVO.setCreateAt(rs.getTimestamp("CREATE_AT"));
                    list.add(collectionVO);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("A database error occurred during findByMemId: " + e.getMessage(), e);
        }
        return list;
    }
}







