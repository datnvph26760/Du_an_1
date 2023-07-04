package Models;

public class Ban {

    private String MaBan;
    private String TenBan;
    private String MaKV;
    private String SoNguoi;
    private String tinhTrang;

    public Ban() {

    }

    public Ban(String MaBan, String TenBan, String MaKV, String SoNguoi, String tinhTrang, String trangThai) {
        this.MaBan = MaBan;
        this.TenBan = TenBan;
        this.MaKV = MaKV;
        this.SoNguoi = SoNguoi;
        this.tinhTrang = tinhTrang;

    }

    public String getMaBan() {
        return MaBan;
    }

    public void setMaBan(String MaBan) {
        this.MaBan = MaBan;
    }

    public String getTenBan() {
        return TenBan;
    }

    public void setTenBan(String TenBan) {
        this.TenBan = TenBan;
    }

    public String getMaKV() {
        return MaKV;
    }

    public void setMaKV(String MaKV) {
        this.MaKV = MaKV;
    }

    public String getSoNguoi() {
        return SoNguoi;
    }

    public void setSoNguoi(String SoNguoi) {
        this.SoNguoi = SoNguoi;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Override
    public String toString() {
        return this.MaBan;
    }

}
