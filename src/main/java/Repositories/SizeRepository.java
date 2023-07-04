/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DomainModels.Size;
import Ulties.HibernateUtil;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.Session;

public class SizeRepository {

    private ArrayList<Size> list;
    private Session hSession;

    public SizeRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public List<Size> all() {
        String hql = "SELECT szObj FROM Size szObj";
        TypedQuery<Size> query = this.hSession.createQuery(hql, Size.class);
        return query.getResultList();
    }

    public Boolean create(Size size) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(size);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public Boolean update(Size size) throws Exception {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(size);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return true;
    }

    public void delete(String id) throws Exception {
        try {
            Size size = null;
            this.hSession.getTransaction().begin();
            size = this.hSession.get(Size.class, id);
            this.hSession.delete(size);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }
    
    public List<Size> getTen() {
        String hql = "SELECT szObj.Ten FROM Size szObj";
        TypedQuery<Size> query = this.hSession.createQuery(hql, Size.class);
        return query.getResultList();
    }
}
