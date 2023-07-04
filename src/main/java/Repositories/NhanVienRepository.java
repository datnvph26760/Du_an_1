package Repositories;

import DomainModels.NhanVien;
import Ulties.DBConnection;
import Ulties.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NhanVienRepository {

    private ArrayList<NhanVien> list;
    private Session session;
    private Connection con = DBConnection.getConnection();

    public NhanVienRepository() {
        this.session = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public List<NhanVien> getAll() {
        String nvql = "select nvql from NhanVien nvql ";
        TypedQuery<NhanVien> query = this.session.createQuery(nvql, NhanVien.class);
        return query.getResultList();
    }
     public NhanVien getOne(String id) {
        String hql = "FROM NhanVien nv WHERE nv.id = :id";
        TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }


    public Boolean create(NhanVien nv) throws Exception {
        try {
            this.session.getTransaction().begin();
            this.session.persist(nv);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
        return true;
    }

    public Boolean update(NhanVien nv) throws Exception {
        try {
            this.session.getTransaction().begin();
            this.session.merge(nv);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
        return true;
    }

    public void delete(String id) throws Exception {
        try {
            NhanVien nv = null;
            this.session.getTransaction().begin();
            nv = session.get(NhanVien.class, id);
            this.session.delete(nv);
            this.session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
    }

  
    public boolean hasAdminAccess(String idnv) {
        String hql = "SELECT COUNT(*) FROM NhanVien nv JOIN nv.ChucVU cv WHERE nv.idnv = :id AND cv.ma = 'admin'";
        TypedQuery<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("id", idnv);
        Long count = query.getSingleResult();
        return count > 0;
    }


    public static void main(String[] args) {
        List<NhanVien> lst = new NhanVienRepository().getAll();
        for (NhanVien sp : lst) {
            System.out.println(sp.toString());
        }
    }
}
