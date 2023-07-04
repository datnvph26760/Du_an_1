package Service;

import DomainModels.Hat;
import DomainModels.NhietDo;
import java.util.List;
import java.util.UUID;

public interface NhietDoServiceInterface {

    public List<NhietDo> getAll();
    
    public List<NhietDo> getTen();

    public String create(NhietDo nd) throws Exception;

    public String update(NhietDo nd) throws Exception;

    public void delete(String id) throws Exception;
}
