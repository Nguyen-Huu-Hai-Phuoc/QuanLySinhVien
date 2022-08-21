/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import com.edusys.model.NhanVien;

/**
 *
 * @author Admin
 */
public class Auth {

    /**
     *
     * Đối tượng chứa thông tin người sử dụng khi đăng nhập
     */
    public static NhanVien user = null;

    /**
     *
     * Xóa thông tin người sử dụng khi đăng xuất
     */
    public static void clear() {
        Auth.user = null;
    }

    /**
     *
     * Kiểm tra xem đăng nhập hay chưa
     */
    public static boolean isLogin() {
        return Auth.user != null;
    }

    /**
     *
     * Kiểm tra vai trò đăng nhập
     */
    public static boolean isManager(){
        return Auth.isLogin() && user.isVaiTro();
    }
}
