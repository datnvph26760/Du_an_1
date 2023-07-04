package Repositories;

import DomainModels.Hat;
import DomainModels.NhietDo;
import Ulties.DBConnection;
import Ulties.HibernateUtil;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;

public class NhietDoRepository {

    private ArrayList<NhietDo> list;
    private Session hSession;
    private Connection con = DBConnection.getConnection();

    public NhietDoRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public List<NhietDo> all() {
        String hql = "SELECT ndObj FROM NhietDo ndObj";
        TypedQuery<NhietDo> query = this.hSession.createQuery(hql, NhietDo.class);
        return query.getResultList();
    }

    public Boolean create(NhietDo nd) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(nd);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public Boolean update(NhietDo nd) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(nd);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public void delete(String id) throws Exception {
        try {
            NhietDo nd = null;
            this.hSession.getTransaction().begin();
            nd = hSession.get(NhietDo.class, id);
            this.hSession.delete(nd);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }
    
    public List<NhietDo> getTen() {
        String hql = "SELECT ndObj.Ten FROM NhietDo ndObj";
        TypedQuery<NhietDo> query = this.hSession.createQuery(hql, NhietDo.class);
        return query.getResultList();
    }

}
