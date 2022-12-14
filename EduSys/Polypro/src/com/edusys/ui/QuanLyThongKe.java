/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.ui;

import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.ThongKeDAO;
import com.edusys.model.KhoaHoc;
import com.edusys.utils.Auth;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class QuanLyThongKe extends javax.swing.JDialog {

    ThongKeDAO tkdao = new ThongKeDAO();
    KhoaHocDAO khdao = new KhoaHocDAO();

    /**
     * Creates new form QuanLyThongKe
     */
    public QuanLyThongKe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init() {
        setLocationRelativeTo(null);
        this.selectTabs(0);
        if (!Auth.isManager()) {
            jTabbedPane1.remove(3);
        }
    }

    void selectTabs(int index) {
        jTabbedPane1.setSelectedIndex(index);
    }

    @SuppressWarnings("unchecked")
    public void fillComboBoxKH() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel();
//        model.removeAllElements();
//        List<KhoaHoc> list = khdao.selectAll();
//        for (KhoaHoc khoaHoc : list) {
//            model.addElement(list);
//        }
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboKhoaHoc.getModel();
        model.removeAllElements();
        List<KhoaHoc> list = khdao.selectAll();
        for (KhoaHoc kh : list) {
            model.addElement(kh);
        }
    }

    @SuppressWarnings("unchecked")
    public void fillComboBoxNam() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();

        List<Integer> list = khdao.selectYears();
        for (Integer year : list) {
            model.addElement(year);
        }
//        cboKhoaHoc.setSelectedIndex(0);
    }

    public void fillTableBangDiem() {
        try {
            DefaultTableModel model = (DefaultTableModel) tblBangdiem.getModel();
            model.setRowCount(0);
            KhoaHoc kh = (KhoaHoc) cboKhoaHoc.getSelectedItem();
            List<Object[]> list = tkdao.getBangDiem(kh.getMaKH());
            for (Object[] obj : list) {
                double diem = (double) obj[2];
                model.addRow(new Object[]{obj[0], obj[1], diem, getXepLoai(diem)});
            }
        } catch (Exception e) {
            return;
        }
    }

    public void fillTableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tblNguoiHoc.getModel();
        model.setRowCount(0);

        List<Object[]> list = tkdao.getLuongNguoiHoc();
        for (Object[] obj : list) {
            model.addRow(obj);
        }
    }

    public void fillTableDiemchuyenDe() {
        DefaultTableModel model = (DefaultTableModel) tblTongHopDiem.getModel();
        model.setRowCount(0);

        List<Object[]> list = tkdao.getDiemChuyenDe();
        for (Object[] obj : list) {
            model.addRow(new Object[]{
                obj[0], obj[1], obj[2], obj[3], String.format("%.1f", obj[4])
            });
        }
    }

    public void fillTableDoanhThu() {
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);

        int nam = (Integer) cboNam.getSelectedItem();
        List<Object[]> list = tkdao.getDoanhThu(nam);
        for (Object[] obj : list) {
            model.addRow(obj);
        }
    }

    public static String getXepLoai(double diem) {
        String xepLoai = "Xu???t s???c";
        if (diem < 0) {
            xepLoai = "Ch??a nh???p";
        } else if (diem < 3) {
            xepLoai = "K??m";
        } else if (diem < 5) {
            xepLoai = "Y???u";
        } else if (diem < 6.5) {
            xepLoai = "Trung b??nh";
        } else if (diem < 7.5) {
            xepLoai = "Kh??";
        } else if (diem < 9) {
            xepLoai = "Gi???i";
        }
        return xepLoai;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pnlNguoiHoc = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNguoiHoc = new javax.swing.JTable();
        pnlbangdiem = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblBangdiem = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cboKhoaHoc = new javax.swing.JComboBox<>();
        pnlTongHopdiem = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTongHopDiem = new javax.swing.JTable();
        pnlDoanhThu = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblDoanhThu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        cboNam = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("T???NG H???P - TH??NG K??");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("T???NG H???P - TH???NG K??");

        tblNguoiHoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N??M", "S??? NG?????I H???C", "?????U TI??N", "SAU C??NG"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblNguoiHoc);

        javax.swing.GroupLayout pnlNguoiHocLayout = new javax.swing.GroupLayout(pnlNguoiHoc);
        pnlNguoiHoc.setLayout(pnlNguoiHocLayout);
        pnlNguoiHocLayout.setHorizontalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlNguoiHocLayout.setVerticalGroup(
            pnlNguoiHocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNguoiHocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("NG?????I H???C", pnlNguoiHoc);

        tblBangdiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "M?? NH", "H??? V?? T??N", "??I???M ", "X???P LO???I"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblBangdiem);

        jLabel3.setText("KH??A H???C:");

        cboKhoaHoc.setMaximumRowCount(15);
        cboKhoaHoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));
        cboKhoaHoc.setSelectedIndex(-1);
        cboKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhoaHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlbangdiemLayout = new javax.swing.GroupLayout(pnlbangdiem);
        pnlbangdiem.setLayout(pnlbangdiemLayout);
        pnlbangdiemLayout.setHorizontalGroup(
            pnlbangdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlbangdiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlbangdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(pnlbangdiemLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlbangdiemLayout.setVerticalGroup(
            pnlbangdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlbangdiemLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlbangdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboKhoaHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("B???NG ??I???M", pnlbangdiem);

        tblTongHopDiem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHUY??N ?????", "T???NG S??? HV", "CAO NH???T", "TH???P NH???T", "??I???M TB"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblTongHopDiem);

        javax.swing.GroupLayout pnlTongHopdiemLayout = new javax.swing.GroupLayout(pnlTongHopdiem);
        pnlTongHopdiem.setLayout(pnlTongHopdiemLayout);
        pnlTongHopdiemLayout.setHorizontalGroup(
            pnlTongHopdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHopdiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTongHopdiemLayout.setVerticalGroup(
            pnlTongHopdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTongHopdiemLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("T???NG H???P ??I???M", pnlTongHopdiem);

        tblDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CHUY??N ?????", "S??? KH??A", "S??? HV", "DOANH THU", "HP CAO NH???T", "HP TH???P NH???T", "HP TRUNG B??NH"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblDoanhThu);
        if (tblDoanhThu.getColumnModel().getColumnCount() > 0) {
            tblDoanhThu.getColumnModel().getColumn(0).setPreferredWidth(300);
            tblDoanhThu.getColumnModel().getColumn(1).setPreferredWidth(70);
            tblDoanhThu.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblDoanhThu.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblDoanhThu.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblDoanhThu.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblDoanhThu.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jLabel2.setText("N??m:");

        cboNam.setMaximumRowCount(15);
        cboNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDoanhThuLayout = new javax.swing.GroupLayout(pnlDoanhThu);
        pnlDoanhThu.setLayout(pnlDoanhThuLayout);
        pnlDoanhThuLayout.setHorizontalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 767, Short.MAX_VALUE)
                    .addGroup(pnlDoanhThuLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlDoanhThuLayout.setVerticalGroup(
            pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDoanhThuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlDoanhThuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cboNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("DOANH THU", pnlDoanhThu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhoaHocActionPerformed
        // TODO add your handling code here:
        fillTableBangDiem();
    }//GEN-LAST:event_cboKhoaHocActionPerformed

    private void cboNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNamActionPerformed
        // TODO add your handling code here:
        fillTableDoanhThu();
    }//GEN-LAST:event_cboNamActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        fillComboBoxKH();
        fillComboBoxNam();
        fillTableBangDiem();
        fillTableDoanhThu();
        fillTableDiemchuyenDe();
        fillTableNguoiHoc();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(QuanLyThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                QuanLyThongKe dialog = new QuanLyThongKe(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cboKhoaHoc;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pnlDoanhThu;
    private javax.swing.JPanel pnlNguoiHoc;
    private javax.swing.JPanel pnlTongHopdiem;
    private javax.swing.JPanel pnlbangdiem;
    private javax.swing.JTable tblBangdiem;
    private javax.swing.JTable tblDoanhThu;
    private javax.swing.JTable tblNguoiHoc;
    private javax.swing.JTable tblTongHopDiem;
    // End of variables declaration//GEN-END:variables
}
