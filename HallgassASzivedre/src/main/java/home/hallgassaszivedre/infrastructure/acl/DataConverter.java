package home.hallgassaszivedre.infrastructure.acl;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.infrastructure.dto.PuffDTO;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.json.JSONArray;
import org.springframework.util.CollectionUtils;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;

@Named
public class DataConverter {
    
    @Inject
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
    
	public Puff fromDTO(PuffDTO dto) {
		Puff puff = new Puff();
		puff.setId(dto.getId());
		puff.setDate(dto.getDate());
		puff.setPhrase(dto.getPhrase());
		puff.setWeight(dto.getWeight());
		puff.setContent(dto.getContent());
		return puff;
	}

	public PuffDTO toDTO(Puff puff) {
		PuffDTO dto = new PuffDTO();
		dto.setId(puff.getId());
		dto.setDate(puff.getDate());
		dto.setPhrase(puff.getPhrase());
		dto.setWeight(puff.getWeight());
		dto.setContent(puff.getContent());
		return dto;
	}

    public List<Puff> fromDTO(List<PuffDTO> dtos) {
    	if (CollectionUtils.isEmpty(dtos)) {
    		return Lists.newArrayList();
    	}
        Function<PuffDTO, Puff> transformer = new Function<PuffDTO, Puff>() {
            @Override
            public Puff apply(PuffDTO dto) {
                return fromDTO(dto);
            }
        };
        return Lists.newArrayList(Iterables.transform(dtos, transformer));
    }
    

}
