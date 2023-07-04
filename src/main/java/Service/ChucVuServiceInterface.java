package Service;

import DomainModels.ChucVu;

import java.util.List;
import java.util.UUID;

public interface ChucVuServiceInterface {
    List<ChucVu> getAll();
    String create(ChucVu cv) throws Exception;
    String update(ChucVu cv) throws Exception;
    void delete(String id) throws Exception;

}
