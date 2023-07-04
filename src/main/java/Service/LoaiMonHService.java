/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.LoaiMon;
import java.util.List;

/**
 *
 * @author fuoc
 */
public interface LoaiMonHService {

    List<LoaiMon> getAll();

    LoaiMon getten(String manv);

    LoaiMon getone(String id);

    void insert(LoaiMon loaiMon);

    void delete(String manv);

    void update(LoaiMon loaiMon);
    
    public List<LoaiMon> getAllH();

    public String createH(LoaiMon loaiSP) throws Exception;

    public String updateH(LoaiMon loaiSP) throws Exception;

    public void deleteH(String id) throws Exception;

    public List<LoaiMon> getTen();
}
