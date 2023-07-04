/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import DomainModels.Hat;
import DomainModels.LoaiMon;
import DomainModels.Mon;
import DomainModels.NhietDo;
import DomainModels.Size;
import Service.HatServiceImpl;
import Service.LoaiMonHServiceImpl;
import Service.MonHServiceImpl;
import Service.NhietDoServiceImpl;
import Service.SizeServiceImpl;
import Ulties.ShareHelper;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class SanPhamView1 extends javax.swing.JFrame {

    private DefaultTableModel dtm = new DefaultTableModel();
    private MonHServiceImpl serviceMon = new MonHServiceImpl();
    private List<Mon> listMon = new ArrayList<>();
    private HatServiceImpl serviceHat = new HatServiceImpl();
    private NhietDoServiceImpl serviceNhietDo = new NhietDoServiceImpl();
    private SizeServiceImpl serviceSize = new SizeServiceImpl();
    private LoaiMonHServiceImpl serviceLoaiMon = new LoaiMonHServiceImpl();

    public SanPhamView1() {
        initComponents();
        this.setLocationRelativeTo(null);
        tblCTSP.setModel(dtm);
        Object[] header = {"Loại SP", "Tên SP", "Hạt", "Nhiệt độ", "Size", "Đơn giá"};
        dtm.setColumnIdentifiers(header);
        this.loadDataCbb();
        listMon = serviceMon.getAllH();
        loadDataTable(listMon);
        this.loadDataCbbFilter();

    }

    /*----------------------------------------------------------------------------*/
    private void loadDataCbb() {

        //Loai sp
        List<LoaiMon> listLoaiSP = serviceLoaiMon.getAllH();
        cbbLoaiSP.setModel(new DefaultComboBoxModel(listLoaiSP.toArray()));
        cbbLocLoaiSP.setModel(new DefaultComboBoxModel(listLoaiSP.toArray()));

        //Hat
        List<Hat> listHat = serviceHat.getAll();
        cbbHat.setModel(new DefaultComboBoxModel(listHat.toArray()));

        //Nhiet do
        List<NhietDo> listND = serviceNhietDo.getAll();
        cbbNhietDo.setModel(new DefaultComboBoxModel(listND.toArray()));
        cbbLocNhietDo.setModel(new DefaultComboBoxModel(listND.toArray()));

        //Size
        List<Size> listSz = serviceSize.getAll();
        cbbSize.setModel(new DefaultComboBoxModel(listSz.toArray()));
        cbbLocSize.setModel(new DefaultComboBoxModel(listSz.toArray()));

        //Auto complete
        AutoCompleteDecorator.decorate(cbbHat);
        AutoCompleteDecorator.decorate(cbbLoaiSP);
        AutoCompleteDecorator.decorate(cbbLocLoaiSP);
        AutoCompleteDecorator.decorate(cbbLocNhietDo);
        AutoCompleteDecorator.decorate(cbbLocSize);
        AutoCompleteDecorator.decorate(cbbNhietDo);
        AutoCompleteDecorator.decorate(cbbSize);

    }

    /*----------------------------------------------------------------------------*/
    private void loadDataTable(List<Mon> list) {
        dtm.setRowCount(0);
        for (Mon mon : list) {
            dtm.addRow(
                    new Object[]{
                        mon.getLoaiMon().getTenLoaiMon(),
                        mon.getTenMon(),
                        mon.getHat().getTen(),
                        mon.getNhietDo().getTen(),
                        mon.getSize().getTen(),
                        mon.getGia()
                    }
            );
        }
    }

    /*----------------------------------------------------------------------------*/
    private void loadDataCbbFilter() {

        //loai sp
        List<LoaiMon> listLsp = serviceLoaiMon.getAllH();
        listLsp.add(0, new LoaiMon(null, "Tất cả"));
        DefaultComboBoxModel dcbmLSP = new DefaultComboBoxModel((listLsp.toArray()));
        cbbLocLoaiSP.setModel(dcbmLSP);

        //Nhiet do
        List<NhietDo> listND = serviceNhietDo.getAll();
        listND.add(0, new NhietDo(null, "Tất cả"));
        DefaultComboBoxModel dcbmND = new DefaultComboBoxModel((listND.toArray()));
        cbbLocNhietDo.setModel(dcbmND);

        //Size
        List<Size> listSize = serviceSize.getAll();
        listSize.add(0, new Size(null, "Tất cả"));
        DefaultComboBoxModel dcbmSize = new DefaultComboBoxModel((listSize.toArray()));
        cbbLocSize.setModel(dcbmSize);
    }

    /*------------------------------------------------------------------------------*/
    private void add() {
        try {
            if (Float.parseFloat(txtGia.getText().toString()) < 0) {
                JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
            return;
        }

        listMon = serviceMon.getAllH();

        Mon mon = new Mon();
        int i = listMon.size() + 1;
        mon.setMaMon("SP" + i);
        mon.setTenMon(txtTen.getText());
        mon.setLoaiMon((LoaiMon) cbbLoaiSP.getSelectedItem());
        mon.setHat((Hat) cbbHat.getSelectedItem());
        mon.setNhietDo((NhietDo) cbbNhietDo.getSelectedItem());
        mon.setSize((Size) cbbSize.getSelectedItem());
        mon.setGia(Integer.parseInt(txtGia.getText()));
        mon.setMoTa(txtMoTa.getText());
        mon.setNguyenLieu(txtNguyenLieu.getText());
        mon.setAnh(lblAnh.getToolTipText());
        if (rdoOn.isSelected()) {
            mon.setTinhTrang(1);
        } else if (rdoOff.isSelected()) {
            mon.setTinhTrang(0);
        }
        try {
            JOptionPane.showMessageDialog(this, this.serviceMon.createH(mon));
        } catch (Exception ex) {
            Logger.getLogger(SanPhamView1.class.getName()).log(Level.SEVERE, null, ex);
        }
        listMon = serviceMon.getAllH();
        loadDataTable(listMon);
        this.clear();

    }

    /*----------------------------------------------------------------------------*/
    private void update() {
        int row = tblCTSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm bạn muốn sửa");
        } else {
            Mon mon = listMon.get(row);
            mon.setTenMon(txtTen.getText());
            mon.setLoaiMon((LoaiMon) cbbLoaiSP.getSelectedItem());
            mon.setHat((Hat) cbbHat.getSelectedItem());
            mon.setNhietDo((NhietDo) cbbNhietDo.getSelectedItem());
            mon.setSize((Size) cbbSize.getSelectedItem());
            mon.setGia(Integer.parseInt(txtGia.getText()));
            mon.setMoTa(txtMoTa.getText());
            mon.setNguyenLieu(txtNguyenLieu.getText());
            mon.setAnh(lblAnh.getToolTipText());
            if (rdoOn.isSelected()) {
                mon.setTinhTrang(1);
            } else if (rdoOff.isSelected()) {
                mon.setTinhTrang(0);
            }
            int option = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    JOptionPane.showMessageDialog(this, this.serviceMon.updateH(mon));
                } catch (Exception ex) {
                    Logger.getLogger(SanPhamView1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
            listMon = serviceMon.getAllH();
            loadDataTable(listMon);
            this.clear();
        }
    }

    /*----------------------------------------------------------------------------*/
    private void delete() {
        int row = tblCTSP.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm bạn muốn xóa");
        } else {
            Mon mon = listMon.get(row);

            String ma = mon.getMaMon();
            int option = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa không ?", "Có", JOptionPane.YES_OPTION, JOptionPane.NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    this.serviceMon.delete(ma);
                } catch (Exception ex) {
                    Logger.getLogger(SanPhamView1.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return;
            }
            listMon = serviceMon.getAllH();
            loadDataTable(listMon);

        }
    }

    /*-----------------------------------------------------------------------------*/
    private void fillData() {
        int row = tblCTSP.getSelectedRow();
        List<Size> listSz = serviceSize.getAll();
        List<NhietDo> listND = serviceNhietDo.getAll();
        List<Hat> listHat = serviceHat.getAll();
        List<LoaiMon> listLoaiSP = serviceLoaiMon.getAllH();
//        List<SanPham> listSP = serviceSP.getAll();
        Mon mon = listMon.get(row);

        for (int i = 0; i < listHat.size(); i++) {
            if (listHat.get(i).getTen().equalsIgnoreCase(tblCTSP.getValueAt(row, 2).toString())) {
                cbbHat.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < listLoaiSP.size(); i++) {
            if (listLoaiSP.get(i).getTenLoaiMon().equalsIgnoreCase(tblCTSP.getValueAt(row, 0).toString())) {
                cbbLoaiSP.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < listND.size(); i++) {
            if (listND.get(i).getTen().equalsIgnoreCase(tblCTSP.getValueAt(row, 3).toString())) {
                cbbNhietDo.setSelectedIndex(i);
            }
        }

        for (int i = 0; i < listSz.size(); i++) {
            if (listSz.get(i).getTen().equalsIgnoreCase(tblCTSP.getValueAt(row, 4).toString())) {
                cbbSize.setSelectedIndex(i);
            }
        }

        txtGia.setText(tblCTSP.getValueAt(row, 5).toString());
        txtNguyenLieu.setText(mon.getNguyenLieu());
        txtMoTa.setText(mon.getMoTa());
        txtMa.setText(mon.getMaMon());
        txtTen.setText(mon.getTenMon());
        if (mon.getTinhTrang() == 1) {
            this.rdoOn.setSelected(true);
        } else {
            this.rdoOff.setSelected(true);
        }

        upImage(mon.getAnh());
    }

    /*----------------------------------------------------------------*/
    private void clear() {
        txtMa.setText("");
        txtTen.setText("");
        this.txtGia.setText("");
        this.txtMoTa.setText("");
        this.txtNguyenLieu.setText("");
        this.rdoOff.setSelected(false);
        this.rdoOn.setSelected(false);
    }

    /*----------------------------------------------------------------*/
    private void locSP(String loaiSP, String nhietDo, String size) {
        loaiSP = this.cbbLocLoaiSP.getSelectedItem().toString();
        if (this.cbbLocLoaiSP.getSelectedIndex() == -1) {
            loaiSP = "";
        }

        nhietDo = this.cbbLocNhietDo.getSelectedItem().toString();
        if (this.cbbLocNhietDo.getSelectedIndex() == -1) {
            nhietDo = "";
        }

        size = this.cbbLocSize.getSelectedItem().toString();
        if (this.cbbLocSize.getSelectedIndex() == -1) {
            size = "";
        }
        List<Mon> listSPLoc = serviceMon.locSP(loaiSP, nhietDo, size);
        loadDataTable(listSPLoc);
    }

    /*----------------------------------------------------------------*/
    public void selectImage() {
        JFileChooser FileChooser = new JFileChooser();
        if (FileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = FileChooser.getSelectedFile();
            if (ShareHelper.saveLogo(file)) {
                // Hiển thị hình lên form
                BufferedImage img = null;
                try {
                    img = ImageIO.read(new File("images", file.getName()));
                    Image dimg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(),
                            Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(dimg);
                    lblAnh.setIcon(imageIcon);
                    lblAnh.setToolTipText(file.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*----------------------------------------------------------------*/
    public void fillImages(String imgName) {
        ImageIcon icon = new ImageIcon("images\\" + imgName);
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(),
                image.SCALE_SMOOTH));
        lblAnh.setIcon(icon1);
    }

    public void upImage(String imageName) {
        ImageIcon icon = new ImageIcon("images\\" + imageName);
        Image image = icon.getImage();
        ImageIcon icon1 = new ImageIcon(image.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), image.SCALE_SMOOTH));
        lblAnh.setIcon(icon1);
    }


    /*----------------------------------------------------------*/
    private void addCbbSP() {
//        ViewSanPham sp = new ViewSanPham();
//        sp.setVisible(true);

        //Loai sp
        List<LoaiMon> listLoaiSP = serviceLoaiMon.getAllH();
        cbbLoaiSP.setModel(new DefaultComboBoxModel(listLoaiSP.toArray()));

        //Hat
        List<Hat> listHat = serviceHat.getAll();
    }

    /*----------------------------------------------------------*/
    private void addCbbLoaiSP() {
        ViewLoaiSP sp = new ViewLoaiSP();
        sp.setVisible(true);

        //Loai sp
        List<LoaiMon> listLoaiSP = serviceLoaiMon.getAllH();
        cbbLoaiSP.setModel(new DefaultComboBoxModel(listLoaiSP.toArray()));

        //Hat
        List<Hat> listHat = serviceHat.getAll();
    }

    /*----------------------------------------------------------*/
    private void addCbbHat() {
        ViewHat sp = new ViewHat();
        sp.setVisible(true);
//        //San pham
//        List<SanPham> listSP = serviceSP.getAll();
//        cbbTenSP.setModel(new DefaultComboBoxModel(listSP.toArray()));

        //Loai sp
        List<LoaiMon> listLoaiSP = serviceLoaiMon.getAllH();
        cbbLoaiSP.setModel(new DefaultComboBoxModel(listLoaiSP.toArray()));

        //Hat
        List<Hat> listHat = serviceHat.getAll();
    }

    /*----------------------------------------------------------*/
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblback = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCTSP = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbbLocLoaiSP = new javax.swing.JComboBox<>();
        cbbLocNhietDo = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cbbLocSize = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        btnLoc = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cbbLoaiSP = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        cbbHat = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cbbNhietDo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbbSize = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdoOn = new javax.swing.JRadioButton();
        rdoOff = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        btnAnh = new javax.swing.JButton();
        lblAnh = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNguyenLieu = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        btnThemLoaiSpCbb = new javax.swing.JButton();
        btnThemHatCbb = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtMa = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(153, 51, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý sản phẩm");

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
                .addGap(34, 34, 34)
                .addComponent(lblback)
                .addGap(427, 427, 427)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblback)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCTSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tblCTSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCTSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCTSP);

        jPanel7.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 51, 789, 283));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel2.setText("Loại");

        cbbLocLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbLocNhietDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setText("Nhiệt độ");

        cbbLocSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel6.setText("Size");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbbLocSize, 0, 151, Short.MAX_VALUE)
                    .addComponent(cbbLocNhietDo, 0, 151, Short.MAX_VALUE)
                    .addComponent(cbbLocLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cbbLocLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbbLocNhietDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbbLocSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel7.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, -1, -1));

        btnLoc.setText("Lọc sản phẩm");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });
        jPanel7.add(btnLoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(905, 341, -1, -1));

        jLabel4.setText("Tìm kiếm");
        jPanel7.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 25, 57, -1));

        txtSearch.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtSearchCaretUpdate(evt);
            }
        });
        jPanel7.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(98, 22, 493, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel5.setText("Loại sản phẩm");

        cbbLoaiSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel8.setText("Hạt");

        cbbHat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Nhiệt độ");

        cbbNhietDo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("Size");

        cbbSize.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Mô tả");

        jLabel12.setText("Nguyên liệu");

        jLabel14.setText("Đơn giá");

        txtGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiaActionPerformed(evt);
            }
        });

        jLabel15.setText("Trạng thái");

        rdoOn.setText("Kinh doanh");

        rdoOff.setText("Ngừng kinh doanh");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnAnh.setText("Ảnh");
        btnAnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(btnAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        txtNguyenLieu.setColumns(20);
        txtNguyenLieu.setRows(5);
        jScrollPane4.setViewportView(txtNguyenLieu);

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane5.setViewportView(txtMoTa);

        jLabel17.setText("VND");

        btnThemLoaiSpCbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemLoaiSpCbbActionPerformed(evt);
            }
        });

        btnThemHatCbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemHatCbbActionPerformed(evt);
            }
        });

        btnClear.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        txtMa.setEditable(false);

        jLabel13.setText("Mã");

        jLabel16.setText("Tên");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(btnThem)
                                .addGap(18, 18, 18)
                                .addComponent(btnSua)
                                .addGap(18, 18, 18)
                                .addComponent(btnXoa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnClear))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel13))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel5)
                                .addGap(15, 15, 15)
                                .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel9))
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbSize, javax.swing.GroupLayout.Alignment.TRAILING, 0, 167, Short.MAX_VALUE)
                                    .addComponent(cbbNhietDo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbbHat, 0, 202, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemHatCbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThemLoaiSpCbb, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(62, 62, 62)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(rdoOn)
                        .addGap(18, 18, 18)
                        .addComponent(rdoOff))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(cbbHat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel16))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(cbbLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5))
                                            .addComponent(btnThemLoaiSpCbb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(80, 80, 80))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(btnThemHatCbb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(cbbNhietDo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnThem)
                        .addComponent(btnSua)
                        .addComponent(btnXoa)
                        .addComponent(btnClear))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rdoOn)
                        .addComponent(rdoOff)))
                .addGap(50, 50, 50))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1196, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(59, 59, 59)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGap(60, 60, 60)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 784, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void tblCTSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCTSPMouseClicked
        this.fillData();
    }//GEN-LAST:event_tblCTSPMouseClicked

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        String loaiSP = this.cbbLocLoaiSP.getSelectedItem().toString();
        if (this.cbbLocLoaiSP.getSelectedIndex() == 0) {
            loaiSP = "";
        }

        String nhietDo = this.cbbLocNhietDo.getSelectedItem().toString();
        if (this.cbbLocNhietDo.getSelectedIndex() == 0) {
            nhietDo = "";
        }

        String size = this.cbbLocSize.getSelectedItem().toString();
        if (this.cbbLocSize.getSelectedIndex() == 0) {
            size = "";
        }
        List<Mon> listSPLoc = serviceMon.locSP(loaiSP, nhietDo, size);
        loadDataTable(listSPLoc);
    }//GEN-LAST:event_btnLocActionPerformed

    private void txtSearchCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtSearchCaretUpdate
        List<Mon> ds = new ArrayList<>();
        for (Mon ctsp : serviceMon.getAllH()) {
            if (ctsp.getTenMon().contains(txtSearch.getText())) {
                ds.add(ctsp);
            }
        }
        loadDataTable(ds);
    }//GEN-LAST:event_txtSearchCaretUpdate

    private void txtGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiaActionPerformed

    private void btnAnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnhActionPerformed
        this.selectImage();
    }//GEN-LAST:event_btnAnhActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.add();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.update();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.delete();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemLoaiSpCbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemLoaiSpCbbActionPerformed
        this.addCbbLoaiSP();
    }//GEN-LAST:event_btnThemLoaiSpCbbActionPerformed

    private void btnThemHatCbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemHatCbbActionPerformed
        this.addCbbHat();
    }//GEN-LAST:event_btnThemHatCbbActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        this.clear();
    }//GEN-LAST:event_btnClearActionPerformed

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
            java.util.logging.Logger.getLogger(SanPhamView1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SanPhamView1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SanPhamView1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SanPhamView1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SanPhamView1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnh;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHatCbb;
    private javax.swing.JButton btnThemLoaiSpCbb;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbHat;
    private javax.swing.JComboBox<String> cbbLoaiSP;
    private javax.swing.JComboBox<String> cbbLocLoaiSP;
    private javax.swing.JComboBox<String> cbbLocNhietDo;
    private javax.swing.JComboBox<String> cbbLocSize;
    private javax.swing.JComboBox<String> cbbNhietDo;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JLabel lblback;
    private javax.swing.JRadioButton rdoOff;
    private javax.swing.JRadioButton rdoOn;
    private javax.swing.JTable tblCTSP;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextArea txtNguyenLieu;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
