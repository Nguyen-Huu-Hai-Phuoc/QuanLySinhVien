/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.model.NhanVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhanVienDAO {//extends EduSysDAO<NhanVien, String>
//    final String INSEERT_SQL = "INSERT INTO NhanVien(MaNV, MatKhau, HoTen, VaiTro) values (?,?,?,?)";
//    final String UPDATE_SQL = "UPDATE NhanVien set MatKhau = ?, HoTen = ?, VaiTro = ? where MaNV = ?";
//    final String DELETE_SQL = "DELETE FROM NhanVien where MaNV = ?";
//    final String SELEC_ALL_SQL ="SELECT * FROM NhanVien";
//    final String SELECT_BY_ID_SQL ="SELECT * FROM NhanVien where MaNV = ?";
//
//    @Override
//    public void insert(NhanVien entity) {
//        JdbcHelper.udate(INSEERT_SQL, 
//                entity.getMaNV(),entity.getMatKhau(),entity.getHoTen(),entity.isVaiTro());
//    }
//
//    @Override
//    public void update(NhanVien entity) {
//        JdbcHelper.udate(UPDATE_SQL,
//                entity.getHoTen(), entity.getMaNV(), entity.isVaiTro(), entity.getMaNV());
//    }
//
//    @Override
//    public void delete(String id) {
//        JdbcHelper.udate(DELETE_SQL, id);
//    }
//
//    @Override
//    public List<NhanVien> selecAll() {
//        return selecBySQL(SELEC_ALL_SQL);
//    }
//
//    @Override
//    public NhanVien selecByID(String id) {
//        List<NhanVien> list = selecBySQL(SELECT_BY_ID_SQL, id);
//        return list.size() > 0 ? list.get(0) : null;
//    }
//
//    @Override
//    public List<NhanVien> selecBySQL(String sql, Object... args) {
//        List<NhanVien> list = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.query(sql, args);
//            while(rs.next()){
//                NhanVien nv = new NhanVien();
//                nv.setMaNV(rs.getString("MaNV"));
//                nv.setMatKhau(rs.getString("MatKhau"));
//                nv.setHoTen(rs.getString("HoTen"));
//                nv.setVaiTro(rs.getBoolean("VaiTro"));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }

    public void insert(NhanVien model) {
        String sql = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro) VALUES (?, ?, ?,?)";
        JdbcHelper.udate(sql,
                model.getMaNV(),
                model.getMatKhau(),
                model.getHoTen(),
//                model.getGioiTinh(),
                model.isVaiTro());
    }

    public void update(NhanVien model) {
        String sql = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? WHERE MaNV=?";
        JdbcHelper.udate(sql,
                model.getMatKhau(),
                model.getHoTen(),
                model.isVaiTro(),
//                model.getGioiTinh(),
                model.getMaNV());
    }

    public void delete(String MaNV) {
        String sql = "DELETE FROM NhanVien WHERE MaNV=?";
        JdbcHelper.udate(sql, MaNV);
    }

    public List<NhanVien> select() {
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }

    public NhanVien findById(String manv) {
        String sql = "SELECT * FROM NhanVien WHERE MaNV=?";
        List<NhanVien> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
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

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setMatKhau(rs.getString("MatKhau"));
        model.setHoTen(rs.getString("HoTen"));
        model.setVaiTro(rs.getBoolean("VaiTro"));
//        model.setGioiTinh(rs.getString("GioiTinh"));
        return model;
    }

}
