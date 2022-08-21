/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.model.KhoaHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Integer> {

    public static ResultSet rs = null;
    public static String INSERT_SQL = "INSERT INTO KhoaHoc (MaCD,HocPhi,ThoiLuong,NgayKG,GhiChu,MaNV,NgayTao) VALUES (?,?,?,?,?,?,?)";
    public static String UPDATE_SQL = "UPDATE KhoaHoc SET MaCD=?,HocPhi=?,ThoiLuong=?,NgayKG=?,GhiChu=?,MaNV=? WHERE MaKH=?";
    public static String DELETE_SQL = "DELETE FROM KhoaHoc WHERE MaKH=?";
    public static String SELECT_ALL_SQL = "SELECT * FROM KhoaHoc";
    public static String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";
    public static String SELECT_BY_CD_SQL = "SELECT * FROM KhoaHoc WHERE MaCD=?";
    public static String SELECT_YEARS_SQL = "SELECT DISTINCT year(NgayKG) Year FROM KhoaHoc ORDER BY Year DESC";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.udate(INSERT_SQL,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.udate(UPDATE_SQL,
                entity.getMaCD(),
                entity.getHocPhi(),
                entity.getThoiLuong(),
                entity.getNgayKG(),
                entity.getGhiChu(),
                entity.getMaNV(),
                entity.getMaKH());
    }

    public void delete(Integer key) {
        JdbcHelper.udate(DELETE_SQL, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    public KhoaHoc selectById(Integer key) {
        List<KhoaHoc> list = selectBySql(SELECT_BY_ID_SQL, key);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            try {
                rs = JdbcHelper.query(sql, args);
                while (rs.next()) {
                    KhoaHoc entity = new KhoaHoc();
                    entity.setMaKH(rs.getInt("MaKH"));
                    entity.setHocPhi(rs.getDouble("HocPhi"));
                    entity.setThoiLuong(rs.getInt("ThoiLuong"));
                    entity.setNgayKG(rs.getDate("NgayKG"));
                    entity.setGhiChu(rs.getString("GhiChu"));
                    entity.setMaNV(rs.getString("MaNV"));
                    entity.setNgayTao(rs.getDate("NgayTao"));
                    entity.setMaCD(rs.getString("MaCD"));
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

    public List<KhoaHoc> selectByChuyenDe(String makh) {
        return this.selectBySql(SELECT_BY_CD_SQL, makh);
    }

    public List<Integer> selectYears() {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(SELECT_YEARS_SQL);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
}
