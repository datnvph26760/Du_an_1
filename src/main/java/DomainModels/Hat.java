package DomainModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "Hat")
public class Hat {

    @Id
    @Column(name = "Ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

   

    public Hat() {
    }

    public Hat(String ma, String ten) {
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


    @Override
    public String toString() {
        return ten;
    }

}
