package home.hallgassaszivedre.infrastructure.acl;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.infrastructure.dto.PuffDTO;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.springframework.stereotype.Component;

@Component
public class DataConverter {
    
    @Resource
    private ObjectSerializer objectSerializer;

    public String toJSON(List<Puff> puffs) {
	    
        JSONArray jsonArray = new JSONArray();
        for (Puff puff : puffs) {
            jsonArray.put(objectSerializer.getJson(toDTO(puff)));
        }
		return jsonArray.toString();
	}

	public Puff fromJSON(String json) {
        return fromDTO(objectSerializer.getObject(json, PuffDTO.class));
    }
    
	private Puff fromDTO(PuffDTO dto) {
		Puff puff = new Puff();
		dto.setId(dto.getId());
		dto.setDate(dto.getDate());
		dto.setPhrase(dto.getPhrase());
		dto.setWeight(dto.getWeight());
		dto.setContent(dto.getContent());
		return puff;
	}

	private Serializable toDTO(Puff puff) {
		PuffDTO dto = new PuffDTO();
		dto.setId(puff.getId());
		dto.setDate(puff.getDate());
		dto.setPhrase(puff.getPhrase());
		dto.setWeight(puff.getWeight());
		dto.setContent(puff.getContent());
		return dto;
	}
    

}
