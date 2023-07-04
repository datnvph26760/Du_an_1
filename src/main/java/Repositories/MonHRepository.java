/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories;

import DomainModels.Mon;
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


public class MonHRepository {
    
    private ArrayList<Mon> list;
    private Session hSession;
    private Connection con = DBConnection.getConnection();
    
    public MonHRepository(){
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }
    
    public List<Mon> all() {
        String hql = "SELECT mon FROM Mon mon";
        TypedQuery<Mon> query = this.hSession.createQuery(hql, Mon.class);
        return query.getResultList();
    }

    public Boolean create(Mon mon) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(mon);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public Boolean update(Mon mon) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(mon);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public void delete(String ma) throws Exception {
        try {
            Mon mon = null;
            this.hSession.getTransaction().begin();
            mon = hSession.get(Mon.class, ma);
            this.hSession.delete(mon);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public List<Mon> locSP(String loaiMon, String nhietDo, String size) {
        String hql = "SELECT mon FROM Mon mon "
                + "JOIN FETCH mon.loaiMon loaiMon "
                + "JOIN FETCH mon.nhietDo nd "
                + "JOIN FETCH mon.size s "
                + "Where  "
                + "(:loaiMon = '' or loaiMon.TenLoaiMon Like: loaiMon)And "
                + "(:nhietDo = '' or nd.ten Like: nhietDo)And "
                + "(:size = '' or s.ten Like: size)";
        TypedQuery<Mon> query = this.hSession.createQuery(hql, Mon.class);
        query.setParameter("loaiMon", loaiMon);
        query.setParameter("nhietDo", nhietDo);
        query.setParameter("size", size);
        return query.getResultList();
    }

    public void delete1(String MaMon) {
        String sql = "DELETE FROM MON WHERE MaMon=?";
        DBContext.executeUpdate(sql, MaMon);
    }

    public List<Mon> select() {
        String sql = "SELECT * FROM MON";
        return select(sql);
    }

    public List<Mon> findtenmon(String ten) {
        String sql = "SELECT * FROM THUCDON where TenMon='" + ten + "'";
        return select(sql);
    }

    public List<Mon> selectByKeyword(String tenmon) {
        String sql = "SELECT * FROM MON WHERE TenMon LIKE ?";
        return select(sql, "%" + tenmon + "%");
    }

    public List<Mon> findByIdThucDon(String maloaimon) {
        String sql = "SELECT * FROM MON WHERE MaLoai_FK='" + maloaimon + "'";
        return select(sql);
    }

    public List<Mon> findByIdMon(String maimon) {
        String sql = "SELECT * FROM MON where MaMon='" + maimon + "'";
        return select(sql);
    }
    
//    public List<Mon> findByIdMonH(String ma) {
//        String sql = "SELECT * FROM MON where MaMon='" + maimon + "'";
//        return select(sql);
//    }
    public List<Mon> findByMa(String ma)
    {
        String hql = "SELECT mon FROM Mon mon WHERE mon.MaMon = ?1";
        TypedQuery<Mon> query =
                this.hSession.createQuery(hql, Mon.class);
        query.setParameter(1, ma);
        return query.getResultList();
    }

    public Mon findById(String manv) {
        String sql = "SELECT * FROM MON WHERE MaMon=?";
        List<Mon> list = select(sql, manv);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Mon> findByIdLoaiMon(String maloaimon) {
        String sql = "SELECT * FROM MON where MaLoai_FK='" + maloaimon + "'";
        return select(sql);
    }

    private List<Mon> select(String sql, Object... args) {
        List<Mon> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = DBContext.executeQuery(sql, args);
                while (rs.next()) {
                    Mon model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private Mon readFromResultSet(ResultSet rs) throws SQLException {
        Mon model = new Mon();
        model.setMaMon(rs.getString("MaMon"));
        model.setTenMon(rs.getString("TenMon"));
        model.setGia(rs.getInt("Gia"));
        model.setAnh(rs.getString("HinhAnh"));
//        model.setLoaiMon(rs.getString("MaLoai_FK"));
        return model;
    }

    public void insert(Mon mon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
