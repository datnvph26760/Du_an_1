/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DomainModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "LOAIMON")
public class LoaiMon {
    
    @Id
    @Column(name = "MaLoai")
    private String MaLoaiMon;

    @Column(name = "TenLoai")
    private String TenLoaiMon;
    

    public LoaiMon() {
    }

    public LoaiMon(String MaLoaiMon, String TenLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
        this.TenLoaiMon = TenLoaiMon;
    }

    public String getMaLoaiMon() {
        return MaLoaiMon;
    }

    public void setMaLoaiMon(String MaLoaiMon) {
        this.MaLoaiMon = MaLoaiMon;
    }

    public String getTenLoaiMon() {
        return TenLoaiMon;
    }

    public void setTenLoaiMon(String TenLoaiMon) {
        this.TenLoaiMon = TenLoaiMon;
    }
    
    @Override
    public String toString() {
        return TenLoaiMon;
    }
}
