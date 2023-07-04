package DomainModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Mon")
public class Mon {

    @Id
    @Column(name = "MaMon")
    private String MaMon;

    @Column(name = "TenMon")
    private String TenMon;

    @ManyToOne()
    @JoinColumn(
            name = "MaLoai_FK",
            referencedColumnName = "MaLoai"
    )
    private LoaiMon loaiMon;

    @ManyToOne()
    @JoinColumn(
            name = "MaSize",
            referencedColumnName = "Ma"
    )
    private Size size;

    @ManyToOne()
    @JoinColumn(
            name = "MaHat",
            referencedColumnName = "Ma"
    )
    private Hat hat;

    @ManyToOne()
    @JoinColumn(
            name = "MaNhietDo",
            referencedColumnName = "Ma"
    )
    private NhietDo nhietDo;

    @Column(name = "NguyenLieu")
    private String nguyenLieu;

    @Column(name = "Gia")
    private int gia;

    @Column(name = "Anh")
    private String anh;

    @Column(name = "Mota")
    private String moTa;

    @Column(name = "TinhTrang")
    private int tinhTrang;

//    private String MaMon;
//    private String TenMon;
//    private float Gia;
//    private String MaLoaiMon;
//    private String Hinh;
    public Mon() {
    }

    public Mon(String MaMon, String TenMon, LoaiMon loaiMon, Size size, Hat hat, NhietDo nhietDo, String nguyenLieu, int gia, String anh, String moTa, int tinhTrang) {
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.loaiMon = loaiMon;
        this.size = size;
        this.hat = hat;
        this.nhietDo = nhietDo;
        this.nguyenLieu = nguyenLieu;
        this.gia = gia;
        this.anh = anh;
        this.moTa = moTa;
        this.tinhTrang = tinhTrang;
    }

    public String getMaMon() {
        return MaMon;
    }

    public void setMaMon(String MaMon) {
        this.MaMon = MaMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String TenMon) {
        this.TenMon = TenMon;
    }

    public LoaiMon getLoaiMon() {
        return loaiMon;
    }

    public void setLoaiMon(LoaiMon loaiMon) {
        this.loaiMon = loaiMon;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Hat getHat() {
        return hat;
    }

    public void setHat(Hat hat) {
        this.hat = hat;
    }

    public NhietDo getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(NhietDo nhietDo) {
        this.nhietDo = nhietDo;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

}
