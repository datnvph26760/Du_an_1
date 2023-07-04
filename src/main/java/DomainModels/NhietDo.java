package DomainModels;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "NhietDo")
public class NhietDo {

    @Id
    @Column(name = "Ma")
    private String ma;
    @Column(name = "ten")
    private String ten;

    @OneToMany(
            mappedBy = "nhietDo",
            fetch = FetchType.EAGER
    )
    private List<Mon> listMon;

    public NhietDo() {
    }

    public NhietDo(String ma, String ten, List<Mon> listMon) {
        this.ma = ma;
        this.ten = ten;
        this.listMon = listMon;
    }
    
    public NhietDo(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<Mon> getListMon() {
        return listMon;
    }

    public void setListMon(List<Mon> listMon) {
        this.listMon = listMon;
    }

    @Override
    public String toString() {
        return ten;
    }

}
