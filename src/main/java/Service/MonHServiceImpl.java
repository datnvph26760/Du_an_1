/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.Mon;
import Repositories.MonHRepository;
import Repositories.MonRepository;
import Service.MonService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fuoc
 */
public class MonHServiceImpl implements MonHService {

    private MonHRepository monRepository = new MonHRepository();

    @Override
    public List<Mon> getAll() {
        return monRepository.select();
    }

    @Override
    public Mon getone(String id) {
        return monRepository.findById(id);
    }

    @Override
    public void insert(Mon mon) {
        monRepository.insert(mon);
    }

    @Override
    public void delete(String manv) {
        try {
            monRepository.delete(manv);
        } catch (Exception ex) {
            Logger.getLogger(MonHServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Mon mon) {
        try {
            monRepository.update(mon);
        } catch (Exception ex) {
            Logger.getLogger(MonHServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Mon> getIdmenu(String maloaimon) {
        return monRepository.findByIdThucDon(maloaimon);
    }

    @Override
    public List<Mon> getIdMon(String mamon) {
        return monRepository.findByIdMon(mamon);
    }

    @Override
    public List<Mon> getten(String tenmon) {
        return monRepository.selectByKeyword(tenmon);
    }

    @Override
    public List<Mon> getIdLoaiMon(String maloaimon) {
        return monRepository.findByIdLoaiMon(maloaimon);
    }

    @Override
    public List<Mon> getAllH() {
        return monRepository.all();
    }

    @Override
    public String createH(Mon mon) throws Exception {
        if (mon.getTenMon().equals("")) {
            return "Không để trống tên";
        }
        if (mon.getNguyenLieu().equals("")) {
            return "Không để trống nguyên liệu";
        }
        if (mon.getMoTa().equals("")) {
            return "Không để mô tả";
        }
        if (String.valueOf(mon.getGia()).equals("")) {
            return "Không để trống đơn giá";
        }
        if (monRepository.create(mon) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String updateH(Mon mon) throws Exception {
        if (mon.getTenMon().equals("")) {
            return "Không để trống tên";
        }
        if (mon.getNguyenLieu().equals("")) {
            return "Không để trống nguyên liệu";
        }
        if (mon.getMoTa().equals("")) {
            return "Không để mô tả";
        }
        if (String.valueOf(mon.getGia()).equals("")) {
            return "Không để trống đơn giá";
        }
        if (monRepository.update(mon) == true) {
            return "Update thành công ^^";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public void deleteH(String id) throws Exception {
        monRepository.delete(id);
    }

    public List<Mon> locSP(String loaiMon, String nhietDo, String size) {
        return monRepository.locSP(loaiMon, nhietDo, size);
    }

    @Override
    public List<Mon> getIdMonH(String mamon) {
        return monRepository.findByMa(mamon);
    }

}
