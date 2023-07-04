package DomainModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    @Id
    @Column(name = "MaNV")
    private String maNV;

    @Column(name = "pass")
    private String pass;

    @Column(name = "TenNV")
    private String hoTen;

    @Column(name = "Hinh")
    private String hinh;

    @Column(name = "CMND")
    private String cmnd;

    @Column(name = "Diachi")
    private String diaChi;

    @Column(name = "DienThoai")
    private String sdt;

    @Column(name = "GioiTinh")
    private int gioiTinh;

    @Column(name = "NgaySinh")
    private Date ngaySinh;

    @ManyToOne()
    @JoinColumn(
            name = "MaCV_FK",
            referencedColumnName = "MaCV"
    )
    private ChucVu cv;

    @Column(name = "TinhTrang")
    private int tinhTrang;

    public NhanVien() {
    }

    public NhanVien(String maNV, String pass, String hoTen, String hinh, String cmnd, String diaChi, String sdt, int gioiTinh, Date ngaySinh, ChucVu cv, int tinhTrang) {
        this.maNV = maNV;
        this.pass = pass;
        this.hoTen = hoTen;
        this.hinh = hinh;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cv = cv;
        this.tinhTrang = tinhTrang;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public ChucVu getCv() {
        return cv;
    }

    public void setCv(ChucVu cv) {
        this.cv = cv;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    
}
