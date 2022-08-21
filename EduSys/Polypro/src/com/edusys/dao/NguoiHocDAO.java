/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.model.NguoiHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NguoiHocDAO { //extends EduSysDAO<NguoiHoc, String>{

//    final String INSERT_SQL ="INSERT INTO NguoiHoc(MaNH,HoTen,NgaySinh,GioiTinh,DienThoai,Email,GhiChu,MaNV) VALUES (?,?,?,?,?,?,?,?)";
//    final String UPDATE_SQL ="UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? WHERE MaNH=?";
//    final String DELETE_SQL ="DELETE FROM NguoiHoc WHERE MaNH=?";
//    final String SELECT_ALL_SQL ="SELECT * FROM NguoiHoc";
//    final String SELECT_BY_ID_SQL ="SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
//   
//    @Override
//    public void insert(NguoiHoc entity) {
//        JdbcHelper.udate(INSERT_SQL, 
//                entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), 
//                entity.isGioiTinh(), entity.getDienThoai(), entity.getEmail(), 
//                entity.getGhiChu(), entity.getMaNV());
//    }
//
//    @Override
//    public void update(NguoiHoc entity) {
//        JdbcHelper.udate(UPDATE_SQL, 
//                entity.getHoTen(), entity.getNgayDK(), entity.isGioiTinh(), entity.getDienThoai(),
//                entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getMaNH());
//    }
//
//    @Override
//    public void delete(String id) {
//        JdbcHelper.udate(DELETE_SQL, id);
//    }
//
//    @Override
//    public List<NguoiHoc> selecAll() {
//        return selecBySQL(SELECT_ALL_SQL);
//    }
//
//    @Override
//    public NguoiHoc selecByID(String id) {
//        List<NguoiHoc> list = selecBySQL(SELECT_BY_ID_SQL, "%"+id+"%");
//        if(list.isEmpty()){
//            return null;
//        }
//        return list.get(0);
//    }
//
//    @Override
//    public List<NguoiHoc> selecBySQL(String sql, Object... args) {
//        List<NguoiHoc> list = new ArrayList<>();
//        try {
//            ResultSet rs = JdbcHelper.query(sql, args);
//            while(rs.next()){
//                NguoiHoc nh = new NguoiHoc();
//                nh.setMaNH(rs.getString("MaNH"));
//                nh.setHoTen(rs.getString("HoTen"));
//                nh.setNgaySinh(rs.getDate("NgaySinh"));
//                nh.setGioiTinh(rs.getBoolean("GioiTinh"));
//                nh.setDienThoai(rs.getString("DienThoai"));
//                nh.setEmail(rs.getString("Email"));
//                nh.setGhiChu(rs.getString("GhiChu"));
//                nh.setNgaySinh(rs.getDate("NgayDK"));
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return list;
//    }
    public void insert(NguoiHoc model) {
        String sql
                = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, TrinhDo) VALUES(?, ?,  ?,  ?,  ?,  ?,  ?,  ?,  ?)";
        JdbcHelper.udate(sql,
                model.getMaNH(),
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getTrinhDo(),
                model.getMaNV());
        
    }

    public void update(NguoiHoc model) {
        String sql
                = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV =  ? WHERE  MaNH =  ?";
        JdbcHelper.udate(sql,
                model.getHoTen(),
                model.getNgaySinh(),
                model.isGioiTinh(),
                model.getDienThoai(),
                model.getEmail(),
                model.getGhiChu(),
                model.getMaNV(),
                model.getTrinhDo(),
                model.getMaNH());
    }

    public void delete(String id) {
        String sql = "DELETE FROM NguoiHoc WHERE MaNH=?";
        JdbcHelper.udate(sql, id);
    }

    public List<NguoiHoc> select() {
        String sql = "SELECT * FROM NguoiHoc";
        return select(sql);
    }

    public NguoiHoc findById(String manh) {
        String sql = "SELECT * FROM NguoiHoc WHERE MaNH=?";
        List<NguoiHoc> list = select(sql, manh);
        return list.size() > 0 ? list.get(0) : null;
    }

    private List<NguoiHoc> select(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    NguoiHoc model = readFromResultSet(rs);
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

    private NguoiHoc readFromResultSet(ResultSet rs) throws SQLException {
        NguoiHoc model = new NguoiHoc();
        model.setMaNH(rs.getString("MaNH"));
        model.setHoTen(rs.getString("HoTen"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setDienThoai(rs.getString("DienThoai"));
        model.setEmail(rs.getString("Email"));
        model.setGhiChu(rs.getString("GhiChu"));
        model.setMaNV(rs.getString("MaNV"));
        model.setNgayDK(rs.getDate("NgayDK"));
        model.setTrinhDo(rs.getString("TrinhDo"));
        return model;
    }

    public List<NguoiHoc> selectByCourse(int makh, String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ? AND "
                + "MaNH NOT IN (SELECT MaNH FROM HocVien WHERE MaKH=?)";
        return select(sql, "%" + keyword + "%", makh);
    }

    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM NguoiHoc WHERE HoTen LIKE ?";
        return select(sql, "%" + keyword + "%");
    }
    
}
