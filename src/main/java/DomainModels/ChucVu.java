package DomainModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


import java.util.List;
@Entity
@Table(name = "ChucVu")

public class ChucVu {

    @Id
    @Column(name = "MaCV")
    private String maCV;
    @Column(name = "TenCV")
    private String ten;
    @OneToMany(
            mappedBy = "cv",
            fetch = FetchType.EAGER
    )
    private List<NhanVien> listNV;

    public ChucVu() {
    }

    public ChucVu(String maCV, String ten, List<NhanVien> listNV) {
        this.maCV = maCV;
        this.ten = ten;
        this.listNV = listNV;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<NhanVien> getListNV() {
        return listNV;
    }

    public void setListNV(List<NhanVien> listNV) {
        this.listNV = listNV;
    }

//    @Override
//    public String toString() {
//        return "ChucVu{" +
//                "id=" + id +
//                ", ten='" + ten + '\'' +
//                ", listNV=" + listNV +
//                '}';
//    }

    @Override
    public String toString() {
        return ten;
    }
}
