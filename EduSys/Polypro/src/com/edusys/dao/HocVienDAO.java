/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.model.HocVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HocVienDAO { //extends EduSysDAO<HocVien, String>{
//    final String INSERT_SQL ="INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
//    final String UPDATE_SQL ="UPDATE HocVienSET MaKH=?, MaNH=?, Diem? WHERE MaHV=?";
//    final String DELETE_SQL ="DELETE FROM HocVien WHERE MaHV=?";
//    final String SELECT_ALL_SQL ="SELECT * FROM HocVien";
//    final String SELECT_BY_ID_SQL ="SELECT * FROM HocVien WHERE MaHV=?";
//
//    @Override
//    public void insert(HocVien entity) {
//        JdbcHelper.udate(INSERT_SQL, 
//                entity.getMaKH(), entity.getMaNH(), entity.getDiem());
//    }
//
//    @Override
//    public void update(HocVien entity) {
//        JdbcHelper.udate(UPDATE_SQL, 
//                entity.getMaKH(), entity.getMaNH(), entity.getDiem(), entity.getMaHV());
//    }
//
//    @Override
//    public void delete(String id) {
//        JdbcHelper.udate(DELETE_SQL, id);
//    }
//
//    @Override
//    public List<HocVien> selecAll() {
//        return selecBySQL(SELECT_ALL_SQL);
//    }
//
//    @Override
//    public HocVien selecByID(String id) {
//        List<HocVien> list = selecBySQL(SELECT_BY_ID_SQL, id);
//        if(list.isEmpty()){
//            return null;
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public List<HocVien> selecBySQL(String sql, Object... args) {
//        List<HocVien> list = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.query(sql, args);
//            while(rs.next()){
//                HocVien hv = new HocVien();
//                hv.setMaHV(rs.getInt("MaHV"));
//                hv.setMaKH(rs.getInt("MaKH"));
//                hv.setMaNH(rs.getNString("MANH"));
//                hv.setDiem(rs.getDouble("Diem"));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }

    public void insert(HocVien model) {
        String sql = "INSERT INTO HocVien(MaKH, MaNH, Diem) VALUES(?, ?, ?)";
        JdbcHelper.udate(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem());
    }

    public void update(HocVien model) {
        String sql = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
        JdbcHelper.udate(sql,
                model.getMaKH(),
                model.getMaNH(),
                model.getDiem(),
                model.getMaHV());
    }

    public void delete(Integer MaHV) {
        String sql = "DELETE FROM HocVien WHERE MaHV=?";
        JdbcHelper.udate(sql, MaHV);
    }

    public List<HocVien> select() {
        String sql = "SELECT * FROM HocVien";
        return select(sql);
    }

    public HocVien findById(Integer mahv) {
        String sql = "SELECT * FROM HocVien WHERE MaHV=?";
        List<HocVien> list = select(sql, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<HocVien> select(String sql, Object... args) {
        List<HocVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    HocVien model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private HocVien readFromResultSet(ResultSet rs) throws SQLException {
        HocVien model = new HocVien();
        model.setMaHV(rs.getInt("MaHV"));
        model.setMaKH(rs.getInt("MaKH"));
        model.setMaNH(rs.getString("MaNH"));
        model.setDiem(rs.getDouble("Diem"));
        return model;
    }
    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "SELECT * FROM HocVien WHERE MaKH=?";
        return select(sql, maKH);
    }
}
