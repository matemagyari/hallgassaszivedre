package home.hallgassaszivedre.infrastructure.persistence;

import static com.googlecode.objectify.ObjectifyService.ofy;
import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;
import home.hallgassaszivedre.infrastructure.acl.DataConverter;
import home.hallgassaszivedre.infrastructure.dto.PuffDTO;

import java.util.List;

import javax.annotation.Resource;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

//@Component
public class ObjectifyPuffRepository implements PuffRepository {
	
	@Resource
	private DataConverter dataConverter;

	public ObjectifyPuffRepository() {
	    ObjectifyService.register(PuffDTO.class);
    }

	@Override
	public List<Puff> findAll() {
	    List<PuffDTO> puffs = ofy().load().type(PuffDTO.class).list();
		return dataConverter.fromDTO(puffs);
	}

    @Override
    public void create(Puff puff) {
        PuffDTO dto = dataConverter.toDTO(puff);
    	Key<PuffDTO> key = ofy().save().entity(dto).now();
    }

    @Override
    public void update(Puff puff) {
        PuffDTO dto = dataConverter.toDTO(puff);
        Key<PuffDTO> key = ofy().save().entity(dto).now();
    }

}
