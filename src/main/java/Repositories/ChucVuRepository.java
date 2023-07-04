package Repositories;

import DomainModels.ChucVu;
import Ulties.DBConnection;
import Ulties.HibernateUtil;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ChucVuRepository {
    private ArrayList<ChucVu> list;
    private Session session;
    private Connection con = DBConnection.getConnection();

    public ChucVuRepository(){
        this.session = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }
    public List<ChucVu> getAll(){
        String cvsql = "SELECT cvsql FROM ChucVu cvsql";
        TypedQuery<ChucVu> query = this.session.createQuery(cvsql,ChucVu.class);
        return query.getResultList();
    }
    public Boolean create(ChucVu cv) throws Exception{
        try{
            this.session.getTransaction().begin();
            this.session.persist(cv);
            this.session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
        return true;
    }
    public Boolean update(ChucVu cv) throws Exception{
        try{
            this.session.getTransaction().begin();
            this.session.merge(cv);
            this.session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
        return true;
    }
    public void delete(String id) throws Exception{
        try{
            ChucVu cv = null;
            this.session.getTransaction().begin();
            cv = session.get(ChucVu.class,id);
            this.session.delete(cv);
            this.session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();
            this.session.getTransaction().rollback();
        }
    }

}
