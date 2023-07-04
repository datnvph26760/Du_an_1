package Service;

import DomainModels.NhanVien;
import Repositories.NhanVienRepository;

import java.util.List;
import java.util.UUID;

public class NhanVienServiceImpl implements NhanVienSeriviceInterface {

    private NhanVienRepository nvRepo;

    public NhanVienServiceImpl() {
        nvRepo = new NhanVienRepository();
    }

    @Override
    public List<NhanVien> getAll() {
        return new NhanVienRepository().getAll();
    }

    @Override
    public String create(NhanVien nv) throws Exception {
        if (nv.getHoTen().equals("") || nv.getPass().equals("")
                || nv.getCmnd().equals("") || nv.getDiaChi().equals("")
                || nv.getSdt().equals("") || nv.getNgaySinh().equals("")) {
            return "Khong duoc de trong";
        }
        if (nvRepo.create(nv) == true) {
            return "Add thanh cong";
        } else {
            return "Add that bai";
        }
    }

    @Override
    public String update(NhanVien nv) throws Exception {
        if (nv.getHoTen().equals("") || nv.getPass().equals("")
                || nv.getCmnd().equals("") || nv.getDiaChi().equals("")
                || nv.getSdt().equals("") || nv.getNgaySinh().equals("")) {
            return "Khong duoc de trong";
        }
        if (nvRepo.update(nv) == true) {
            return "Add thanh cong";
        } else {
            return "Add that bai";
        }
    }

    @Override
    public void delete(String id) throws Exception {
        nvRepo.delete(id);
    }

    @Override
    public NhanVien getone(String id) {
        return nvRepo.getOne(id);
    }

    @Override
    public boolean hasAdminAccess(String idnv) {
        nvRepo.hasAdminAccess(idnv);
        return true;
    }
}
