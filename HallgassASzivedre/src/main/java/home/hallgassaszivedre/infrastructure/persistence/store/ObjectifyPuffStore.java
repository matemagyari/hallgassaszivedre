package home.hallgassaszivedre.infrastructure.persistence.store;

import static com.googlecode.objectify.ObjectifyService.ofy;
import home.hallgassaszivedre.infrastructure.dto.PuffDTO;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ObjectifyPuffStore implements PuffStore {
	
	public ObjectifyPuffStore() {
	    ObjectifyService.register(PuffDTO.class);
    }

	@Override
	public boolean isEmpty() {
		return this.findAll().isEmpty();
	}
	
	@Override
	public List<PuffDTO> findAll() {
	    return ofy().load().type(PuffDTO.class).list();
	}

    @Override
    public void create(PuffDTO puff) {
    	Key<PuffDTO> key = ofy().save().entity(puff).now();
    	System.err.println(key);
    }

    @Override
    public void update(PuffDTO puff) {
        Key<PuffDTO> key = ofy().save().entity(puff).now();
        System.err.println(key);
    }
    
	@Override
	public void remove(Long puffId) {
		Key<PuffDTO> key = Key.create(PuffDTO.class, puffId);
		ofy().delete().key(key);
	}    

}
