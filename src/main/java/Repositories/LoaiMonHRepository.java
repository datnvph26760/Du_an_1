/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.LoaiMon;
import Ulties.DBConnection;
import Ulties.DBContext;
import Ulties.HibernateUtil;
import jakarta.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author fuoc
 */
public class LoaiMonHRepository {
    
    private ArrayList<LoaiMon> list;
    private Session hSession;
    private Connection con = DBConnection.getConnection();

    public LoaiMonHRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }
    
     public List<LoaiMon> all() {
        String hql = "SELECT loaiObj FROM LoaiMon loaiObj";
        TypedQuery<LoaiMon> query = this.hSession.createQuery(hql, LoaiMon.class);
        return query.getResultList();
    }

    public Boolean create(LoaiMon loaiSP) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(loaiSP);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public Boolean updateH(LoaiMon loaiSP) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(loaiSP);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public void delete(String ma) throws Exception {
        try {
            LoaiMon loaiSP = null;
            this.hSession.getTransaction().begin();
            loaiSP = hSession.get(LoaiMon.class, ma);
            this.hSession.delete(loaiSP);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

//    public List<LoaiMon> getTen() {
//        String hql = "SELECT loaiObj.Ten FROM LOAIMON loaiObj";
//        TypedQuery<LoaiMon> query = this.hSession.createQuery(hql, LoaiMon.class);
//        return query.getResultList();
//    }
//
//    public void insert(LoaiMon model) {
//        String sql = "INSERT INTO LOAIMON (MaLoai, TenLoai) VALUES (?, ?)";
//        DBContext.executeUpdate(sql, model.getMaLoaiMon(), model.getTenLoaiMon());
//    }
//
//    public void update(LoaiMon model) {
//        String sql = "UPDATE LOAIMON SET TenLoai=? WHERE MaLoai=?";
//        DBContext.executeUpdate(sql, model.getTenLoaiMon(), model.getMaLoaiMon());
//    }
//
//    public void delete1(String MaloaiMon) {
//        String sql = "DELETE FROM LOAIMON WHERE MaLoai=?";
//        DBContext.executeUpdate(sql, MaloaiMon);
//    }
//
//    public LoaiMon findByten(String manv) {
//        String sql = "SELECT * FROM LOAIMON WHERE TenLoai=?";
//        List<LoaiMon> list = select(sql, manv);
//        return list.size() > 0 ? list.get(0) : null;
//    }
//
//    public List<LoaiMon> select() {
//        String sql = "SELECT * FROM LOAIMON";
//        return select(sql);
//    }
//
//    public LoaiMon findById(String manv) {
//        String sql = "SELECT * FROM LOAIMON WHERE MaLoai=?";
//        List<LoaiMon> list = select(sql, manv);
//        return list.size() > 0 ? list.get(0) : null;
//    }
//
//    private List<LoaiMon> select(String sql, Object... args) {
//        List<LoaiMon> list = new ArrayList<>();
//        try {
//            ResultSet rs = null;
//            try {
//                rs = DBContext.executeQuery(sql, args);
//                while (rs.next()) {
//                    LoaiMon model = readFromResultSet(rs);
//                    list.add(model);
//                }
//            } finally {
//                rs.getStatement().getConnection().close();
//            }
//        } catch (SQLException ex) {
//            throw new RuntimeException(ex);
//        }
//        return list;
//    }
//
//    private LoaiMon readFromResultSet(ResultSet rs) throws SQLException {
//        LoaiMon model = new LoaiMon();
//        model.setMaLoaiMon(rs.getString("MaLoai"));
//        model.setTenLoaiMon(rs.getString("TenLoai"));
//        return model;
//    }
}
