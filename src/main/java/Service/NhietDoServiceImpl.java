package Service;

import DomainModels.Hat;
import DomainModels.NhietDo;
import Repositories.HatRepository;
import Repositories.NhietDoRepository;
import Service.HatServiceInterface;
import Service.NhietDoServiceInterface;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class NhietDoServiceImpl implements NhietDoServiceInterface {

    private NhietDoRepository ndRepo;

    public NhietDoServiceImpl() {
        ndRepo = new NhietDoRepository();
    }

    @Override
    public List<NhietDo> getAll() {
        return new NhietDoRepository().all();
    }

    @Override
    public String create(NhietDo nd) throws Exception {
        if (nd.getTen().equals("")) {
            return "Không để trống tên";
        }
        if (ndRepo.create(nd) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(NhietDo nd) throws Exception {
        if (nd.getTen().equals("")) {
            return "Không để trống tên";
        }
        if (ndRepo.update(nd) == true) {
            return "Update thành công ^^";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public void delete(String id) throws Exception {
        ndRepo.delete(id);
    }

    @Override
    public List<NhietDo> getTen() {
        return new NhietDoRepository().getTen();
    }

}
