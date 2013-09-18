package home.hallgassaszivedre.infrastructure.acl;

import home.hallgassaszivedre.domain.model.Puff;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class DataConverter {
    
    @Resource
    private ObjectSerializer objectSerializer;

    public String convertToDTO(List<Puff> puffs) {
	    
        JSONArray jsonArray = new JSONArray();
        for (Puff puff : puffs) {
            jsonArray.put(objectSerializer.getJson(puff));
        }
		return jsonArray.toString();
	}

    public Puff convertFromDTO(String dto) {
        return objectSerializer.getObject(dto, Puff.class);
    }

}
