/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.ui;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.model.ChuyenDe;
import com.edusys.model.KhoaHoc;
import com.edusys.utils.Auth;
import com.edusys.utils.DialogHelPer;
import com.edusys.utils.XDate;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
//import javax.swing.i

/**
 *
 * @author Admin
 */
public class QuanLyKhoahoc extends javax.swing.JDialog {

    ChuyenDeDAO cddao = new ChuyenDeDAO();
    KhoaHocDAO khdao = new KhoaHocDAO();
    int index = 0;

    /**
     * Creates new form QuanLyKhoahoc
     */
    public QuanLyKhoahoc(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init() {
        setLocationRelativeTo(null);
        this.fillComcoBoxChuyenDe();
    }

    public void fillTableKhoaHoc() {
        DefaultTableModel model = (DefaultTableModel) tblTable.getModel();
        model.setRowCount(0);
        try {
            ChuyenDe cd = (ChuyenDe) cboChuyende.getSelectedItem();
            List<KhoaHoc> list = khdao.selectByChuyenDe(cd.getMaCD());
            for (KhoaHoc kh : list) {
                Object[] index = {
                    kh.getMaKH(), kh.getMaCD(), kh.getThoiLuong(),
                    kh.getHocPhi(), XDate.toString(kh.getNgayKG()),
                    kh.getMaNV(), XDate.toString(kh.getNgayTao())
                };
                model.addRow(index);
            }
        } catch (Exception e) {
            DialogHelPer.alert(this, "lỗi truy cập dữ liệu");
        }
    }

    @SuppressWarnings("unchecked")
    public void fillComcoBoxChuyenDe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboChuyende.getModel();
        model.removeAllElements();
        List<ChuyenDe> list = cddao.selectAll();
        for (ChuyenDe cd : list) {
            model.addElement(cd);
        }
    }

    public void chonChuyende() {
        ChuyenDe cd = (ChuyenDe) cboChuyende.getSelectedItem();
        KhoaHoc model = new KhoaHoc();
//        txtNgayKG.setText(XDate.toString(model.getNgayKG()));
//        txtNgayTao.setText(XDate.toString(model.getNgayTao()));
//        txtNguoiTao.setText(Auth.user.getMaNV());
        txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));
        txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
        lblTenCD.setText(cd.getTenCD());
//        txtGhiCu.setText(cd.getTenCD());

        this.fillTableKhoaHoc();
        this.index = -1;
        this.updateStatus();
        pnlCapNhat.setSelectedIndex(1);
    }

    public void edit() {
        try {
            Integer maKH = (Integer) tblTable.getValueAt(this.index, 0);
            KhoaHoc model = khdao.selectById(maKH);
            if (model != null) {
                this.setForm(model);
                this.updateStatus();
            }
        } catch (Exception e) {
            DialogHelPer.alert(this, "Lỗi truy cập dữ liệu!");
        }
    }

    public void clearForm() {
        KhoaHoc model = new KhoaHoc();
        ChuyenDe chuyenDe = (ChuyenDe) cboChuyende.getSelectedItem();
        model.setMaCD(chuyenDe.getMaCD());

        model.setMaNV(Auth.user.getMaNV());
        
        model.setNgayKG(XDate.addDays(30));
        model.setHocPhi(chuyenDe.getHocPhi());
        model.setThoiLuong(chuyenDe.getThoiLuong());
        model.setGhiChu(chuyenDe.getTenCD());
        model.setNgayTao(XDate.now());

        this.setForm(model);
//        this.updateStatus();
//        txtHocPhi.setEnabled(false);
//        txtThoiLuong.setEnabled(false);
//        txtNguoiTao.setEnabled(false);
//        txtNgayTao.setEnabled(false);
//        txtNgayKG.requestFocus();

    }

    public void insert() {

        KhoaHoc model = getForm();

        model.setNgayTao(XDate.now());
        try {

            khdao.insert(model);
            System.out.println("123");
            this.fillTableKhoaHoc();
            this.clearForm();
            DialogHelPer.alert(this, "thêm thành công!");
        } catch (Exception e) {
            DialogHelPer.alert(this, "Thêm thất bại!");
            e.printStackTrace();
        }
    }

    public void update() {
        KhoaHoc model = getForm();
        try {
            khdao.update(model);
            this.fillTableKhoaHoc();
            DialogHelPer.alert(this, "Cập nhật thành công!");
        } catch (Exception e) {
            DialogHelPer.alert(this, "Cập nhật thất bại!");
            e.printStackTrace();
        }
    }

    public void delete() {
        if (DialogHelPer.confirm(this, "Bạn có muốn xóa!")) {
            Integer maKH = Integer.valueOf(cboChuyende.getToolTipText());
            try {
                khdao.delete(maKH);
                this.fillTableKhoaHoc();
                this.clearForm();
                DialogHelPer.alert(this, "Xóa thành công!");
            } catch (Exception e) {
                DialogHelPer.alert(this, "Xóa thất bại!");
            }
        }

    }

    public void updateStatus() {
//        boolean edit = this.index >= 0;
//        boolean first = this.index == 0;
//        boolean last = this.index == tblTable.getRowCount() - 1;
////        txtHocPhi.setEditable(!edit);
////        txtThoiLuong.setEditable(!edit);
////        txtNguoiTao.setEditable(!edit);
////        txtNgayTao.setEditable(!edit);
//        btnInsert.setEnabled(!edit);
//        btnUpdatte.setEnabled(edit);
//        btnDelete.setEnabled(edit);
//
//        btnFirst.setEnabled(edit && !first);
//        btnPrev.setEnabled(edit && !first);
//        btnNext.setEnabled(edit && !last);
//        btnLast.setEnabled(edit && !last);

//        btnInsert.setEnabled(insertable);
//        btnUpdatte.setEnabled(!insertable);
//        btnDelete.setEnabled(!insertable);
//        boolean first = this.index > 0;
//        boolean last = this.index < tblTable.getRowCount() - 1;
//        btnFirst.setEnabled(!insertable && first);
//        btnPrev.setEnabled(!insertable && first);
//        btnLast.setEnabled(!insertable && last);
//        btnNext.setEnabled(!insertable && last);
        boolean edit = (this.index >= 0);
        boolean first = (this.index == 0);
        boolean last = (this.index == tblTable.getRowCount() - 1);
        // Trạng thái form
        txtNguoiTao.setEditable(!edit);
        btnInsert.setEnabled(!edit);
        btnUpdatte.setEnabled(edit);
        btnDelete.setEnabled(edit);

        // Trạng thái điều hướng
        btnFirst.setEnabled(edit && !first);
        btnPrev.setEnabled(edit && !first);
        btnNext.setEnabled(edit && !last);
        btnLast.setEnabled(edit && !last);
    }

    public void setForm(KhoaHoc kh) {
        cboChuyende.setToolTipText(String.valueOf(kh.getMaKH()));
        cboChuyende.setSelectedItem(cddao.selectById(kh.getMaCD()));
        txtNgayKG.setText(XDate.toString(kh.getNgayKG()));
        txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
        txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
        txtNguoiTao.setText(kh.getMaNV());
        txtNgayTao.setText(XDate.toString(kh.getNgayTao()));
        txtGhiCu.setText(kh.getGhiChu());
    }

    public KhoaHoc getForm() {
        ChuyenDe cd = (ChuyenDe) cboChuyende.getSelectedItem();
        KhoaHoc kh = new KhoaHoc();
        kh.setMaCD(cd.getMaCD());
        kh.setNgayKG(XDate.toDate(txtNgayKG.getText()));
        kh.setHocPhi(Double.valueOf(txtHocPhi.getText()));
        kh.setThoiLuong(Integer.valueOf(txtThoiLuong.getText()));
        kh.setGhiChu(txtGhiCu.getText());
        kh.setMaNV(Auth.user.getMaNV());
        kh.setNgayTao(XDate.toDate(txtNgayTao.getText()));
        kh.setMaKH(Integer.valueOf(cboChuyende.getToolTipText()));
        return kh;
    }

    public void first() {
        index = 0;
        edit();
    }

    public void prev() {
        if (index > 0) {
            index--;
            edit();
        }
    }

    public void next() {
        if (index < tblTable.getRowCount() - 1) {
            index++;
            edit();
        }
    }

    public void last() {
        index = tblTable.getRowCount() - 1;
        edit();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCapNhat = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNgayKG = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtGhiCu = new javax.swing.JTextArea();
        btnInsert = new javax.swing.JButton();
        btnUpdatte = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnNew = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        lblTenCD = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cboChuyende = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ KHÓA HỌC");

        jLabel2.setText("Chuyên đề");

        jLabel3.setText("Ngày khai giảng");

        txtNgayKG.setForeground(new java.awt.Color(153, 153, 153));
        txtNgayKG.setText("dd/MM/yyyy");
        txtNgayKG.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNgayKGFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNgayKGFocusLost(evt);
            }
        });

        jLabel4.setText("Học phí");

        txtHocPhi.setEditable(false);

        jLabel5.setText("Thời lượng (giờ)");

        txtThoiLuong.setEditable(false);

        jLabel6.setText("Người tạo");

        txtNguoiTao.setEditable(false);

        jLabel7.setText("Ngày tạo");

        txtNgayTao.setEditable(false);
        txtNgayTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNgayTaoActionPerformed(evt);
            }
        });

        jLabel8.setText("Ghi chú");

        txtGhiCu.setColumns(20);
        txtGhiCu.setRows(5);
        jScrollPane1.setViewportView(txtGhiCu);

        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdatte.setText("Sửa");
        btnUpdatte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatteActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnNew.setText("Mới");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnLast.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPrev.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        btnFirst.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        lblTenCD.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblTenCD.setForeground(new java.awt.Color(0, 102, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(txtHocPhi, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(txtNguoiTao)
                            .addComponent(lblTenCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayTao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtThoiLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNgayKG, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdatte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 225, Short.MAX_VALUE)
                        .addComponent(btnFirst)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPrev)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLast)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNgayKG)
                    .addComponent(lblTenCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdatte)
                    .addComponent(btnDelete)
                    .addComponent(btnNew)
                    .addComponent(btnLast)
                    .addComponent(btnNext)
                    .addComponent(btnPrev)
                    .addComponent(btnFirst))
                .addContainerGap())
        );

        pnlCapNhat.addTab("CẬP NHẬT", jPanel1);

        tblTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ KH", "CHUYÊN ĐỀ", "THỜI LƯỢNG", "HỌC PHÍ", "KHAI GIẢNG", "TẠO BỞI", "NGÀY TẠO"
            }
        ));
        tblTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblTableMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCapNhat.addTab("DANH SÁCH", jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CHUYÊN ĐỀ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(0, 51, 255))); // NOI18N

        cboChuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChuyendeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboChuyende, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboChuyende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCapNhat, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNgayTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNgayTaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNgayTaoActionPerformed

    private void cboChuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChuyendeActionPerformed
        // TODO add your handling code here:
        chonChuyende();
    }//GEN-LAST:event_cboChuyendeActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insert();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void tblTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTableMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.index = tblTable.rowAtPoint(evt.getPoint());
            this.edit();
            pnlCapNhat.setSelectedIndex(0);
        }
    }//GEN-LAST:event_tblTableMousePressed

    private void btnUpdatteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatteActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdatteActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        clearForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        // TODO add your handling code here:
        first();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        prev();
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        next();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        last();
    }//GEN-LAST:event_btnLastActionPerformed

    private void txtNgayKGFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayKGFocusGained
        // TODO add your handling code here:
        if(txtNgayKG.getText().equals("dd/MM/yyyy")){
            txtNgayKG.setText("");
            txtNgayKG.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txtNgayKGFocusGained

    private void txtNgayKGFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNgayKGFocusLost
        // TODO add your handling code here:
        if(txtNgayKG.getText().equals("")){
            txtNgayKG.setText("dd/MM/yyyy");
            txtNgayKG.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txtNgayKGFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoahoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoahoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoahoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyKhoahoc.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyKhoahoc dialog = new QuanLyKhoahoc(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }   
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdatte;
    private javax.swing.JComboBox<String> cboChuyende;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTenCD;
    private javax.swing.JTabbedPane pnlCapNhat;
    private javax.swing.JTable tblTable;
    private javax.swing.JTextArea txtGhiCu;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtNgayKG;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
