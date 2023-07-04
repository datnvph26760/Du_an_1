package Service;

import DomainModels.NhanVien;

import java.util.List;


public interface NhanVienSeriviceInterface {
    List<NhanVien> getAll();
    NhanVien getone(String id);
    String create(NhanVien nv) throws Exception;
    String update(NhanVien nv) throws Exception;
    void delete(String id) throws Exception;
    boolean hasAdminAccess(String idnv);
}
