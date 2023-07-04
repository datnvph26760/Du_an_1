package Models;


public class Mon {

    private String MaMon;
    private String TenMon;
    private String MaLoaiMon;
    private String MaNhietDo;
    private String MaSize;
    private String MaHat;
    private String NguyenLieu;
    private String Mota;
    private Integer Gia;
    private Integer TrangThai;
    private String Hinh;

    public Mon() {
    }

    public Mon(String MaMon, String TenMon, String MaLoaiMon, String MaNhietDo, String MaSize, String MaHat, String NguyenLieu, String Mota, Integer Gia, Integer TrangThai, String Hinh) {
        this.MaMon = MaMon;
        this.TenMon = TenMon;
        this.MaLoaiMon = MaLoaiMon;
        this.MaNhietDo = MaNhietDo;
        this.MaSize = MaSize;
        this.MaHat = MaHat;
        this.NguyenLieu = NguyenLieu;
        this.Mota = Mota;
        this.Gia = Gia;
        this.TrangThai = TrangThai;
        this.Hinh = Hinh;
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

    public String getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(String MaLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
    }

    public String getMaNhietDo() {
        return MaNhietDo;
    }

    public void setMaNhietDo(String MaNhietDo) {
        this.MaNhietDo = MaNhietDo;
    }

    public String getMaSize() {
        return MaSize;
    }

    public void setMaSize(String MaSize) {
        this.MaSize = MaSize;
    }

    public String getMaHat() {
        return MaHat;
    }

    public void setMaHat(String MaHat) {
        this.MaHat = MaHat;
    }

    public String getNguyenLieu() {
        return NguyenLieu;
    }

    public void setNguyenLieu(String NguyenLieu) {
        this.NguyenLieu = NguyenLieu;
    }

    public String getMota() {
        return Mota;
    }

    public void setMota(String Mota) {
        this.Mota = Mota;
    }

    public Integer getGia() {
        return Gia;
    }

    public void setGia(Integer Gia) {
        this.Gia = Gia;
    }

    public Integer getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(Integer TrangThai) {
        this.TrangThai = TrangThai;
    }

    public String getHinh() {
        return Hinh;
    }

    public void setHinh(String Hinh) {
        this.Hinh = Hinh;
    }

}
