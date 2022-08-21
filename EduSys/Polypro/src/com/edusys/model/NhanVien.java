/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.model;

/**
 *
 * @author Admin
 */
public class NhanVien {
    private String MaNV;
    private String MatKhau;
    private String HoTen;
    private boolean VaiTro = true;
//    private String GioiTinh;
    @Override
    public String toString(){
        return this.HoTen;
    }

    public NhanVien() {
    }

    public NhanVien(String MaNV, String MatKhau, String HoTen, boolean VaiTro, String GioiTinh) {
        this.MaNV = MaNV;
        this.MatKhau = MatKhau;
        this.HoTen = HoTen;
        this.VaiTro = true;
//        this.GioiTinh = GioiTinh;
    }


    public String getMaNV() {
        return MaNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public String getHoTen() {
        return HoTen;
    }

    public boolean isVaiTro() {
        return VaiTro;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setVaiTro(boolean VaiTro) {
        this.VaiTro = VaiTro;
    }

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public String getGioiTinh() {
//        return GioiTinh;
//    }
//
//    public void setGioiTinh(String GioiTinh) {
//        this.GioiTinh = GioiTinh;
//    }
    
}
