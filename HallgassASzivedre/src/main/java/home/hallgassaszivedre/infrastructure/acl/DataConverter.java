package home.hallgassaszivedre.infrastructure.acl;

import home.hallgassaszivedre.domain.model.Puff;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class DataConverter {

    public String convert(List<Puff> puffs) {
	    
        JSONArray jsonArray = new JSONArray();
        for (Puff puff : puffs) {
            JSONObject jsonObject = new JSONObject(puff);
            jsonArray.put(jsonObject);
        }
		return jsonArray.toString();
	}

}
