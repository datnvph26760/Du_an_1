/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Ban;
import Models.KhuVuc;
import Service.BanService;
import Service.BanServiceImpl;
import Service.KhuVucService;
import Service.KhuVucServiceImpl;
import Ulties.DialogHelper;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fuoc
 */
public class QuanLyBanView extends javax.swing.JFrame {

    KhuVucService khuVucService = new KhuVucServiceImpl();
    BanService banService = new BanServiceImpl();
    String itemliskhuvuc = "";
    String itemtinhtrang = "";
    List<Ban> bn = new ArrayList();

    public QuanLyBanView() {
        initComponents();
        load();
        loadtablban();
        txtMaBan.setEnabled(false);
        this.setLocationRelativeTo(null);
    }

    void loadtablban() {
        DefaultTableModel model = (DefaultTableModel) tbBan.getModel();
        model.setRowCount(0);
        try {
            bn.removeAll(bn);
            List<Ban> list = banService.getAll();
            for (Ban nv : list) {

                KhuVuc listkv = khuVucService.getone(nv.getMaKV());
                Object[] row = {
                    nv.getMaBan(),
                    nv.getTenBan(),
                    listkv.getTenKhuVuc(),
                   nv.getSoNguoi(),
                   nv.getTinhTrang(),};
                Ban banla = new Ban();
                banla.setMaBan(nv.getMaBan());
                banla.setTenBan(nv.getTenBan());
                banla.setMaKV(nv.getMaKV());
                banla.setSoNguoi(nv.getSoNguoi());
                banla.setTinhTrang(nv.getTinhTrang());
                bn.add(banla);
                model.addRow(row);
            }
        } catch (Exception e) {
            alert("Lỗi truy vấn dữ liệu!");
        }
    }

    void load() {
        loadtablban();
        clear();
        DefaultListModel model = new DefaultListModel();
        try {
            itemliskhuvuc = "";
            List<KhuVuc> list = khuVucService.getAll();
            cbKV.removeAllItems();
            for (KhuVuc nv : list) {
                model.addElement(nv.getTenKhuVuc() + "  -" + nv.getMaKhuVuc());
                cbKV.addItem(nv.getTenKhuVuc() + "  -" + nv.getMaKhuVuc());
                itemliskhuvuc += nv.getTenKhuVuc() + "  -" + nv.getMaKhuVuc() + "chiakey";
            }
            listKV1.setModel(model);
        } catch (Exception e) {
            alert("Lỗi ");
        }
    }

    void editBan() {
        try {
            String manv = (String) tbBan.getValueAt(this.index, 0);
            Ban model = banService.getone(manv);
            if (model != null) {
                this.setModelBan(model);
            }
        } catch (Exception e) {
            alert("Lỗi ");
        }
    }

    Ban getModelBan() {
        Ban model = new Ban();
        String key = (String) cbKV.getSelectedItem();
//        String tinhtrang = (String) cbtinhtrang.getSelectedItem();
        String[] keys = key.split("-");
        model.setMaBan(txtMaBan.getText());
        model.setTenBan(txtTenBan.getText());
        model.setMaKV(keys[1]);
        model.setSoNguoi(txtsonguoi.getText());
        model.setTinhTrang((String) cbtinhtrang.getSelectedItem());
        System.out.println(keys[1]);
        return model;

    }

    void setModelBan(Ban model) {

        txtTenBan.setText(model.getTenBan());
        txtMaBan.setText(model.getMaBan());
        txtsonguoi.setText(model.getSoNguoi());
        cbtinhtrang.setSelectedItem(model.getTinhTrang());
        String[] key = itemliskhuvuc.split("chiakey");
        for (int i = 0; i < key.length; i++) {
            if (key[i].indexOf(model.getMaKV()) != -1) {
                cbKV.setSelectedItem(key[i]);
            }
        }

    }

    void insertBan() {
        Ban model = getModelBan();
        try {
            banService.insert(model);
            this.clear();
            alert("Thêm mới thành công!");
        } catch (Exception e) {
            alert("Hãy tạo mới lại!");
        }
    }

    int index;

    void editlistkhuvuc() {
        String manv = (String) listKV1.getSelectedValue();
        String[] key = manv.split("-");
        DefaultTableModel modelbang = (DefaultTableModel) tbBan.getModel();
        modelbang.setRowCount(0);
        try {
            KhuVuc model = khuVucService.getone(key[1]);
            if (model != null) {
                this.setModelkhuvuc(model);
            }
        } catch (Exception e) {
            alert("Lỗi truy vấn dữ liệu!");
        }
        try {
            List<Ban> list = banService.getIdKhuVuc(key[1]);
            for (Ban nv : list) {
                KhuVuc listkv = khuVucService.getone(nv.getMaKV());
                Object[] row = {
                    nv.getMaBan(),
                    nv.getTenBan(),
                    listkv.getTenKhuVuc(),
                     nv.getSoNguoi(),
                     nv.getTinhTrang()
                };
                modelbang.addRow(row);
            }
        } catch (Exception e) {
            alert("Lỗi ");
        }
    }

    void setModelkhuvuc(KhuVuc model) {
        txtMaKV.setText(model.getMaKhuVuc());
        txtTenKV.setText(model.getTenKhuVuc());

    }

    KhuVuc getModelKhuvuc() {
        KhuVuc model = new KhuVuc();
        model.setMaKhuVuc(txtMaKV.getText());
        model.setTenKhuVuc(txtTenKV.getText());
        return model;

    }

    void insertKhuvuc() {
        KhuVuc model = getModelKhuvuc();

        try {
            khuVucService.insert(model);
            this.load();

            alert("Thêm mới thành công!");

        } catch (Exception e) {
            alert("Hãy tạo mới lại!");

        }
    }

    void updateKhuvuc() {
        KhuVuc model = getModelKhuvuc();
        try {
            khuVucService.update(model);
            this.load();
            alert("Cập nhật thành công!");

        } catch (Exception e) {
            alert("Cập nhật thất bại");

        }
    }

    void deleteKhuVuc() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa khu vực này?")) {
            String manv = txtMaKV.getText();
            try {
                khuVucService.delete(manv);
                this.load();
                alert("Xóa thành công!");

            } catch (Exception e) {
                alert("Xóa Thất bại.");
            }
        }
    }

    void updateban() {
        Ban model = getModelBan();
        try {
            banService.update(model);
            this.clear();
            alert("Cập nhật thành công!");

        } catch (Exception e) {
            alert("Cập nhật thất bại");

        }
    }

    void deleteban() {
        if (DialogHelper.confirm(this, "Bạn thực sự muốn xóa bàn này?")) {
            String manv = txtMaBan.getText();
            try {
                banService.delete(manv);
                this.load();
                alert("Xóa thành công!");

            } catch (Exception e) {
                alert("Xóa Thất bại.");
            }
        }
    }

    void clear() {
        loadtablban();
        int Soluong = 1;
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        List<KhuVuc> listkv = khuVucService.getAll();
        List<Ban> listban = banService.getAll();
        txtMaKV.setText(null);
        txtTenKV.setText(null);
        txtsonguoi.setText(null);
        txtTenBan.setText(null);
        cbtinhtrang.setSelectedIndex(0);
        int Mahdct = (listkv.size()) + 1;
        int maban = (listban.size()) + 1;
        String Mahdstr = "KV" + Mahdct + hour + minute + second;
        String Mahdstr2 = second + "A" + hour + minute + maban;
        txtMaBan.setText(Mahdstr2);
        txtMaKV.setText(Mahdstr);
        DefaultListModel model = new DefaultListModel();

    }
    help al;

    private void alert(String loi) {
        if (al != null) {
            al.dispose();
            al = new help(loi);
        } else {
            al = new help(loi);
        }
        al.setVisible(true);

    }
    boolean ch = false;

    void checkdau() {
        String ten = txtTenBan.getText();
        String tenkv = txtTenKV.getText();
        if (ten.length() > 0) {
            for (int i = 0; i < ten.length(); i++) {
                if (ten.substring(i, i + 1).equals("-")) {
                    ch = true;
                }
            }
        }
        if (tenkv.length() > 0) {
            for (int i = 0; i < tenkv.length(); i++) {
                if (tenkv.substring(i, i + 1).equals("-")) {
                    ch = true;
                }

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblback = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listKV1 = new javax.swing.JList<>();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBan = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKV = new javax.swing.JTextField();
        txtTenKV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMaBan = new javax.swing.JTextField();
        cbKV = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtTenBan = new javax.swing.JTextField();
        btnThemKV = new javax.swing.JButton();
        btnSuaKV = new javax.swing.JButton();
        btnXoaKV = new javax.swing.JButton();
        btnLamMoiKV = new javax.swing.JButton();
        btnThemBan = new javax.swing.JButton();
        btnSuaBan = new javax.swing.JButton();
        btnXoaBan = new javax.swing.JButton();
        btnLamMoiBan = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnsqpxep = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btn_excel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cbtinhtrang = new javax.swing.JComboBox<>();
        txtsonguoi = new javax.swing.JTextField();
        cbtrangthai = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 51, 0));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quản lý bàn và Khu vực");

        lblback.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblback.setForeground(new java.awt.Color(255, 255, 255));
        lblback.setText("Back");
        lblback.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblbackMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lblback)
                .addGap(339, 339, 339)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblback))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        listKV1.setFont(new java.awt.Font("Times New Roman", 0, 27)); // NOI18N
        listKV1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listKV1.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        listKV1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listKVMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(listKV1);

        jLabel10.setText("Khu vực");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        tbBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã bàn", "Tên bàn", "Khu vực", "Số người", "Ổ điện"
            }
        ));
        tbBan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbBan);

        jLabel3.setText("Mã khu vực");

        jLabel4.setText("Tên khu vực");

        jLabel5.setText("Mã bàn");

        jLabel6.setText("Khu vực");

        cbKV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Tên bàn");

        btnThemKV.setText("Thêm");
        btnThemKV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemKVActionPerformed(evt);
            }
        });

        btnSuaKV.setText("Sửa");
        btnSuaKV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaKVActionPerformed(evt);
            }
        });

        btnXoaKV.setText("Xóa");
        btnXoaKV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaKVActionPerformed(evt);
            }
        });

        btnLamMoiKV.setText("Làm mới");
        btnLamMoiKV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiKVActionPerformed(evt);
            }
        });

        btnThemBan.setText("Thêm");
        btnThemBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemBanActionPerformed(evt);
            }
        });

        btnSuaBan.setText("Sửa");
        btnSuaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaBanActionPerformed(evt);
            }
        });

        btnXoaBan.setText("Xóa");
        btnXoaBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaBanActionPerformed(evt);
            }
        });

        btnLamMoiBan.setText("Làm mới");
        btnLamMoiBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiBanActionPerformed(evt);
            }
        });

        jLabel8.setText("Khu vực");

        jLabel9.setText("Bàn");

        btnsqpxep.setText("Sắp xếp bàn");
        btnsqpxep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsqpxepActionPerformed(evt);
            }
        });

        btnPDF.setText("PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btn_excel.setText("Excel");
        btn_excel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excelActionPerformed(evt);
            }
        });

        jLabel1.setText("Số người");

        jLabel11.setText("Tình trạng");

        cbtinhtrang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Không có ổ điện", "Có ổ điện", " " }));

        cbtrangthai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã đặt", "Chưa đặt", " " }));

        jLabel12.setText("Trạng thái");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 976, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel3))
                                        .addGap(29, 29, 29)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtMaKV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTenKV, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(140, 140, 140)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel7))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel11)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnXoaKV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnThemKV, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(btnLamMoiKV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnSuaKV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(cbKV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtTenBan, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                                                .addComponent(txtMaBan))
                                            .addComponent(txtsonguoi, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(54, 54, 54)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnXoaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnThemBan, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(btnLamMoiBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(btnSuaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnPDF)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btn_excel))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(btnsqpxep))))
                                    .addComponent(cbtinhtrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(266, 266, 266)
                        .addComponent(jLabel8)
                        .addGap(383, 383, 383)
                        .addComponent(jLabel9)))
                .addContainerGap(162, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaKV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemBan)
                    .addComponent(btnSuaBan))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTenKV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbKV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoaBan)
                    .addComponent(btnLamMoiBan))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThemKV)
                            .addComponent(btnSuaKV))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnXoaKV)
                            .addComponent(btnLamMoiKV)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPDF)
                            .addComponent(btn_excel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtsonguoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnsqpxep))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbtinhtrang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbtrangthai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblbackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblbackMouseClicked
        MainFrame sp = new MainFrame();
        sp.setVisible(true);
        sp.pack();
        sp.setLocationRelativeTo(null);
        sp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }//GEN-LAST:event_lblbackMouseClicked

    private void tbBanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBanMouseClicked
        if (evt.getClickCount() == 1) {
            this.index = tbBan.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                editBan();

            }
        }
    }//GEN-LAST:event_tbBanMouseClicked

    private void btnLamMoiBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiBanActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiBanActionPerformed

    private void btnXoaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaBanActionPerformed
        deleteban();
    }//GEN-LAST:event_btnXoaBanActionPerformed

    private void btnSuaBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaBanActionPerformed
        String cbo = (String) cbKV.getSelectedItem();
        String cbott = (String) cbtinhtrang.getSelectedItem();
        String[] makhuvuc = cbo.split("-");
        List<Ban> list = banService.getIdKhuVuc(makhuvuc[1]);
        if (list.size() >= 18) {
            alert("Khu vực này đã đạt số bàn tối đa");
        } else {
            checkdau();
            if (ch == true) {
                alert("Vui lòng không nhập ký tự \"-\" vào mục tên");
            } else {
                updateban();
            }
        }
    }//GEN-LAST:event_btnSuaBanActionPerformed

    private void btnThemBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemBanActionPerformed
        checkdau();
        if (ch == true) {
            alert("Vui lòng không nhập ký tự \"-\" vào mục tên");
        } else {
            String cbo = (String) cbKV.getSelectedItem();
            String cbott = (String) cbtinhtrang.getSelectedItem();
            String[] makhuvuc = cbo.split("-");
            List<Ban> list = banService.getIdKhuVuc(makhuvuc[1]);
            if (list.size() == 18) {
                alert("Khu vực này đã đạt số bàn tối đa");
            } else if (list.size() < 18) {
                insertBan();
            }        
        }
        ch = false;
    }//GEN-LAST:event_btnThemBanActionPerformed

    private void btnLamMoiKVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiKVActionPerformed
        clear();
    }//GEN-LAST:event_btnLamMoiKVActionPerformed

    private void btnXoaKVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaKVActionPerformed
        deleteKhuVuc();
    }//GEN-LAST:event_btnXoaKVActionPerformed

    private void btnSuaKVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaKVActionPerformed
        checkdau();
        if (ch == true) {
            alert("Vui lòng không nhập ký tự \"-\" vào mục tên");
        } else {
            updateKhuvuc();
        }
        ch = false;
    }//GEN-LAST:event_btnSuaKVActionPerformed

    private void btnThemKVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemKVActionPerformed
        checkdau();
        if (ch == true) {
            alert("Vui lòng không nhập ký tự \"-\" vào mục tên");
        } else {
            insertKhuvuc();
        }
        ch = false;               
    }//GEN-LAST:event_btnThemKVActionPerformed

    private void listKVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listKVMouseClicked
        editlistkhuvuc();
    }//GEN-LAST:event_listKVMouseClicked

    private void btnsqpxepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsqpxepActionPerformed
        loadtablban();
        DefaultTableModel model = (DefaultTableModel) tbBan.getModel();
        int o = model.getRowCount();
        for (int i = 0; i < o; i++) {

            Ban banla = new Ban();
            banla.setMaBan(bn.get(i).getMaBan());
            if (i < 9) {
                banla.setTenBan("Bàn 0" + (i + 1));
            } else {
                banla.setTenBan("Bàn " + (i + 1));
            }
            banla.setTenBan(bn.get(i).getTenBan());
            banla.setMaKV(bn.get(i).getMaKV());
            banla.setSoNguoi(bn.get(i).getSoNguoi());
            banla.setTinhTrang(bn.get(i).getTinhTrang());
            banService.update(banla);
        }
        alert("Đã Cập Nhập");
        this.load();
    }//GEN-LAST:event_btnsqpxepActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:
                MessageFormat header = new MessageFormat("Bàn");
        MessageFormat footer = new MessageFormat("");
        try {
            PrintRequestAttributeSet set = new HashPrintRequestAttributeSet();
            set.add(OrientationRequested.LANDSCAPE);
            tbBan.print(JTable.PrintMode.FIT_WIDTH, header, footer, true, set, true);
        } catch (java.awt.print.PrinterException ex) {
        }
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btn_excelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_excelActionPerformed

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
            java.util.logging.Logger.getLogger(QuanLyBanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyBanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyBanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyBanView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyBanView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoiBan;
    private javax.swing.JButton btnLamMoiKV;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnSuaBan;
    private javax.swing.JButton btnSuaKV;
    private javax.swing.JButton btnThemBan;
    private javax.swing.JButton btnThemKV;
    private javax.swing.JButton btnXoaBan;
    private javax.swing.JButton btnXoaKV;
    private javax.swing.JButton btn_excel;
    private javax.swing.JButton btnsqpxep;
    private javax.swing.JComboBox<String> cbKV;
    private javax.swing.JComboBox<String> cbtinhtrang;
    private javax.swing.JComboBox<String> cbtrangthai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblback;
    private javax.swing.JList<String> listKV1;
    private javax.swing.JTable tbBan;
    private javax.swing.JTextField txtMaBan;
    private javax.swing.JTextField txtMaKV;
    private javax.swing.JTextField txtTenBan;
    private javax.swing.JTextField txtTenKV;
    private javax.swing.JTextField txtsonguoi;
    // End of variables declaration//GEN-END:variables

}
