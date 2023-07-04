package Repositories;

import DomainModels.Hat;
import Ulties.DBConnection;
import Ulties.HibernateUtil;
import jakarta.persistence.TypedQuery;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

public class HatRepository {

    private ArrayList<Hat> list;
    private Session hSession;
    private Connection con = DBConnection.getConnection();

    public HatRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public List<Hat> all() {
        String hql = "SELECT hatObj FROM Hat hatObj";
        TypedQuery<Hat> query = this.hSession.createQuery(hql, Hat.class);
        return query.getResultList();
    }

    public Boolean create(Hat hat) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(hat);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public Boolean update(Hat hat) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(hat);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public void delete(String id) throws Exception {
        try {
            Hat hat = null;
            this.hSession.getTransaction().begin();
            hat = hSession.get(Hat.class, id);
            this.hSession.delete(hat);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public List<Hat> getTen() {
        String hql = "SELECT hatObj.Ten FROM Hat hatObj";
        TypedQuery<Hat> query = this.hSession.createQuery(hql, Hat.class);
        return query.getResultList();
    }

}
