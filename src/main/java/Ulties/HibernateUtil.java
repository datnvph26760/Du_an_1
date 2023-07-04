package Ulties;


import DomainModels.ChucVu;
import DomainModels.Hat;
import DomainModels.LoaiMon;
import DomainModels.Mon;
import DomainModels.NhanVien;
import DomainModels.NhietDo;
import DomainModels.Size;

import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

    private static SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=duan1_3");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123");
//        properties.put(Environment.SHOW_SQL, "true");

        conf.setProperties(properties);
        conf.addAnnotatedClass(Hat.class);
        conf.addAnnotatedClass(NhietDo.class);
        conf.addAnnotatedClass(Size.class);
        conf.addAnnotatedClass(Mon.class);
        conf.addAnnotatedClass(LoaiMon.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(ChucVu.class);
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        System.out.println(getFACTORY());
    }
}
