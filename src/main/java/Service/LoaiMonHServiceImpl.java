/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.LoaiMon;
import Repositories.LoaiMonHRepository;
import Repositories.LoaiMonRepository;
import Service.LoaiMonService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fuoc
 */
public class LoaiMonHServiceImpl implements LoaiMonHService {

    private LoaiMonHRepository loaiMonRepository = new LoaiMonHRepository();

//    @Override
//    public List<LoaiMon> getAll() {
//        return loaiMonRepository.select();
//    }
//
//    @Override
//    public LoaiMon getone(String id) {
//        return loaiMonRepository.findById(id);
//    }
//
//    @Override
//    public void insert(LoaiMon loaiMon) {
//        loaiMonRepository.insert(loaiMon);
//    }

    @Override
    public void delete(String manv) {
        try {
            loaiMonRepository.delete(manv);
        } catch (Exception ex) {
            Logger.getLogger(LoaiMonHServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    @Override
//    public void update(LoaiMon loaiMon) {
//        loaiMonRepository.update(loaiMon);
//    }
//
//    @Override
//    public LoaiMon getten(String manv) {
//        return loaiMonRepository.findByten(manv);
//    }

    @Override
    public List<LoaiMon> getAllH() {
        return loaiMonRepository.all();
    }

    @Override
    public String createH(LoaiMon loaiSP) throws Exception {
        if (loaiSP.getMaLoaiMon().equals("")) {
            return "Không để trống tên";
        }
        if (loaiSP.getTenLoaiMon().equals("")) {
            return "Không để trống tên";
        }
        if (loaiMonRepository.create(loaiSP) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String updateH(LoaiMon loaiSP) throws Exception {
        List<LoaiMon> list = loaiMonRepository.all();
        if (loaiSP.getMaLoaiMon().equals("")) {
            return "Không để trống mã";
        }
        if (loaiSP.getMaLoaiMon().trim().isEmpty()) {
            return "Mã không nhập kí tự trắng";
        }
        for (LoaiMon test : list) {
            if (loaiSP.getMaLoaiMon().equals(test.getMaLoaiMon())) {
                return "Không nhập trùng mã";
            }
        }
        if (loaiSP.getTenLoaiMon().equals("")) {
            return "Không để trống tên";
        }
        if (loaiMonRepository.updateH(loaiSP) == true) {
            return "Sua thành công";
        } else {
            return "Sua thất bại";
        }
    }

    @Override
    public void deleteH(String id) throws Exception {
        loaiMonRepository.delete(id);
    }

//    @Override
//    public List<LoaiMon> getTen() {
//        return loaiMonRepository.getTen();
//    }

    @Override
    public List<LoaiMon> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LoaiMon getten(String manv) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public LoaiMon getone(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(LoaiMon loaiMon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(LoaiMon loaiMon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<LoaiMon> getTen() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
