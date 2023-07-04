package Service;


import DomainModels.ChucVu;
import Repositories.ChucVuRepository;


import java.util.List;
import java.util.UUID;

public class ChucVuServiceImpl implements ChucVuServiceInterface {
    private ChucVuRepository cvRepo;

    public ChucVuServiceImpl(){
        cvRepo = new ChucVuRepository();
    }

    @Override
    public List<ChucVu> getAll() {
        return new ChucVuRepository().getAll();
    }

    @Override
    public String create(ChucVu cv) throws Exception {
        if (cv.getTen().equals("")){
            return "Khong duoc de trong";
        }
        if (cvRepo.create(cv)==true){
            return "Add thanh cong";
        } else {
            return "Add that bai";
        }
    }

    @Override
    public String update(ChucVu cv) throws Exception {
        if (cv.getTen().equals("")){
            return "Khong duoc de trong";
        }
        if (cvRepo.update(cv)==true){
            return "Add thanh cong";
        } else {
            return "Add that bai";
        }
    }
  
    @Override
    public void delete(String id) throws Exception {
        cvRepo.delete(id);
    }
}
