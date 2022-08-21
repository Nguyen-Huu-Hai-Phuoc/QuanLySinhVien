/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.model.ChuyenDe;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChuyenDeDAO { //extends EduSysDAO<ChuyenDe, String> 

//    final String INSERT_SQL = "INSERT INTO ChuyenDe(MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) VALUES(?,?,?,?,?,?)";
//    final String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
//    final String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
//    final String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
//    final String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD=?";
//
//    @Override
//    public void insert(ChuyenDe entity) {
//        JdbcHelper.udate(INSERT_SQL,
//                entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
//    }
//
//    @Override
//    public void update(ChuyenDe entity) {
//        JdbcHelper.udate(UPDATE_SQL,
//                entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
//    }
//
//    @Override
//    public void delete(String id) {
//        JdbcHelper.udate(SELECT_BY_ID_SQL, id);
//    }
//
//    @Override
//    public List<ChuyenDe> selecAll() {
//        return selecBySQL(SELECT_ALL_SQL);
//    }
//
//    @Override
//    public ChuyenDe selecByID(String id) {
//        List<ChuyenDe> list = selecBySQL(SELECT_BY_ID_SQL, id);
//        if(list.isEmpty()){
//            return null;
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public List<ChuyenDe> selecBySQL(String sql, Object... args) {
//        List<ChuyenDe> list = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.query(sql, args);
//            while (rs.next()) {
//                ChuyenDe cd = new ChuyenDe();
//                cd.setMaCD(rs.getString("MaCD"));
//                cd.setTenCD(rs.getString("TenCD"));
//                cd.setHocPhi(rs.getDouble("HocPhi"));
//                cd.setThoiLuong(rs.getInt("ThoiLuong"));
//                cd.setHinh(rs.getString("Hinh"));
//                cd.setMoTa(rs.getString("MoTa"));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
//    public void insert(ChuyenDe model) {
//        String sql = "INSERT INTO ChuyenDe (MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?, ?, ?, ?, ?, ?)";
//        JdbcHelper.udate(sql,
//                model.getMaCD(),
//                model.getTenCD(),
//                model.getHocPhi(),
//                model.getThoiLuong(),
//                model.getHinh(),
//                model.getMoTa());
//    }
//
//    public void update(ChuyenDe model) {
//        String sql = "UPDATE ChuyenDe SET TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? WHERE MaCD=?";
//        JdbcHelper.udate(sql,
//                model.getTenCD(),
//                model.getHocPhi(),
//                model.getThoiLuong(),
//                model.getHinh(),
//                model.getMoTa(),
//                model.getMaCD());
//    }
//
//    public void delete(String MaCD) {
//        String sql = "DELETE FROM ChuyenDe WHERE MaCD=?";
//        JdbcHelper.udate(sql, MaCD);
//    }
//
//    public List<ChuyenDe> select() {
//        String sql = "SELECT * FROM ChuyenDe";
//        return select(sql);
//    }
//
//    public ChuyenDe findById(String macd) {
//        String sql = "SELECT * FROM ChuyenDe WHERE MaCD=?";
//        List<ChuyenDe> list = select(sql, macd);
//        return list.size() > 0 ? list.get(0) : null;
//    }
//
//    private List<ChuyenDe> select(String sql, Object... args) {
//        List<ChuyenDe> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = JdbcHelper.query(sql, args);
//                while (rs.next()) {
//                    ChuyenDe model = readFromResultSet(rs);
//                    list.add(model);
//                }
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return list;
//    }
//
//    private ChuyenDe readFromResultSet(ResultSet rs) throws SQLException {
//        ChuyenDe model = new ChuyenDe();
//        model.setMaCD(rs.getString("MaCD"));
//        model.setHinh(rs.getString("Hinh"));
//        model.setHocPhi(rs.getDouble("HocPhi"));
//        model.setMoTa(rs.getString("MoTa"));
//        model.setTenCD(rs.getString("TenCD"));
//        model.setThoiLuong(rs.getInt("ThoiLuong"));
//        return model;
//    }
public static ResultSet rs = null;
    public static String INSERT_SQL = "INSERT INTO ChuyenDe (MaCD,TenCD,HocPhi,ThoiLuong,Hinh,MoTa) VALUES (?,?,?,?,?,?)";
    public static String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD=?,HocPhi=?,ThoiLuong=?,Hinh=?,MoTa=? WHERE MaCD=?";
    public static String DELETE_SQL = "DELETE FROM ChuyenDe WHERE MaCD=?";
    public static String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe WHERE MaCD=?";

    public void insert(ChuyenDe entity) {
        JdbcHelper.udate(INSERT_SQL,
                entity.getMaCD(),
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa());
    }

    public void update(ChuyenDe entity) {
        JdbcHelper.udate(UPDATE_SQL,
                entity.getTenCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getHinh(),
                entity.getMoTa(),
                entity.getMaCD());
    }

    public void delete(String key) {
        JdbcHelper.udate(DELETE_SQL, key);
    }

    public List<ChuyenDe> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public ChuyenDe selectById(String key) {
        List<ChuyenDe> list = selectBySql(SELECT_BY_ID_SQL, key);
        return list.size() > 0 ? list.get(0) : null;
    }

    protected List<ChuyenDe> selectBySql(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<>();
        try {
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    ChuyenDe entity = new ChuyenDe();
                    entity.setMaCD(rs.getString("MaCD"));
                    entity.setHinh(rs.getString("Hinh"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setMoTa(rs.getString("MoTa"));
                    entity.setTenCD(rs.getString("TenCD"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
}
