package Service;

import DomainModels.Size;
import java.util.List;
import java.util.UUID;

public interface SizeServiceInterface {

    public List<Size> getAll();

    public List<Size> getTen();

    public String create(Size size) throws Exception;

    public String update(Size size) throws Exception;

    public void delete(String id) throws Exception;
}
