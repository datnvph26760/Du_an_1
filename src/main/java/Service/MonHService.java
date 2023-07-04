/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DomainModels.LoaiMon;
import DomainModels.Mon;
import java.util.List;

/**
 *
 * @author fuoc
 */
public interface MonHService {

    List<Mon> getAll();

    List<Mon> getIdmenu(String maloaimon);

    Mon getone(String id);

    List<Mon> getIdMon(String mamon);
    
    List<Mon> getIdMonH(String mamon);

    List<Mon> getten(String tenmon);

    List<Mon> getIdLoaiMon(String maloaimon);

    void insert(Mon mon);

    void delete(String manv);

    void update(Mon mon);

    public List<Mon> getAllH();

    public String createH(Mon mon) throws Exception;

    public String updateH(Mon mon) throws Exception;

    public void deleteH(String id) throws Exception;

//    public List<Mon> getTen();
}
