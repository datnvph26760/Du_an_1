package Service;

import DomainModels.Size;
import Repositories.SizeRepository;
import Service.SizeServiceInterface;
import java.util.List;
import java.util.UUID;

public class SizeServiceImpl implements SizeServiceInterface {

    private SizeRepository szRepo;

    public SizeServiceImpl() {
        szRepo = new SizeRepository();
    }

    @Override
    public List<Size> getAll() {
        return new SizeRepository().all();
    }

    @Override
    public String create(Size size) throws Exception {
        if (szRepo.create(size) == true) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(Size size) throws Exception {
        if (size.getTen().equals("")) {
            return "Không để trống mã";
        }
        if (size.getTen().trim().isEmpty()) {
            return "Mã không nhập kí tự trắng";
        }
        if (szRepo.update(size) == true) {
            return "Update thành công ^^";
        } else {
            return "Update thất bại";
        }
    }

    @Override
    public void delete(String id) throws Exception {
        szRepo.delete(id);
    }

    @Override
    public List<Size> getTen() {
        return new SizeRepository().getTen();
    }


}
