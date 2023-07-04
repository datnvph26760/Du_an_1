package Service;

import DomainModels.Hat;
import Repositories.HatRepository;
import Service.HatServiceInterface;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class HatServiceImpl implements HatServiceInterface {

    private HatRepository hatRepo;

    public HatServiceImpl() {
        hatRepo = new HatRepository();
    }

    @Override
    public List<Hat> getAll() {
        return new HatRepository().all();
    }

    @Override
    public String create(Hat hat) throws Exception {
        if (hat.getTen().equals("")) {
            return "Không để trống tên";
        }
        if (hatRepo.create(hat) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(Hat hat) throws Exception {
        if (hat.getTen().equals("")) {
            return "Không để trống tên";
        }
        if (hatRepo.update(hat) == true) {
            return "Update thành công ^^";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public void delete(String id) throws Exception {
        hatRepo.delete(id);
    }

    @Override
    public List<Hat> getTen() {
        return new HatRepository().getTen();
    }

}
