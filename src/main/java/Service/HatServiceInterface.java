package Service;

import DomainModels.Hat;
import java.util.List;
import java.util.UUID;

public interface HatServiceInterface {

    public List<Hat> getAll();

    public String create(Hat hat) throws Exception;

    public String update(Hat hat) throws Exception;

    public void delete(String id) throws Exception;
    
    public List<Hat> getTen();
}
