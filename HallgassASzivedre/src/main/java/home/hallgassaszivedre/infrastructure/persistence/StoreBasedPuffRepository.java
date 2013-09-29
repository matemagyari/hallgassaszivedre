package home.hallgassaszivedre.infrastructure.persistence;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;
import home.hallgassaszivedre.infrastructure.acl.DataConverter;
import home.hallgassaszivedre.infrastructure.dto.PuffDTO;
import home.hallgassaszivedre.infrastructure.persistence.store.PuffStore;

import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Named;

@Named
public class StoreBasedPuffRepository implements PuffRepository {

	@Inject
	private DataConverter dataConverter;	
	@Resource(name="puffStore")
	private PuffStore store;	

	@Override
	public List<Puff> findAll() {
		return dataConverter.fromDTO(store.findAll());
	}

	@Override
	public void update(Puff puff) {
    	if (puff.getId() == null) {
    		throw new IllegalArgumentException("id should not be null! " + puff);
    	}
		store.update(dataConverter.toDTO(puff));
	}

	@Override
	public void create(Puff puff) {
    	if (puff.getId() != null) {
    		throw new IllegalArgumentException("id should be null! " + puff);
    	}
    	PuffDTO dto = dataConverter.toDTO(puff);
		store.create(dto);
		puff.setId(dto.getId());
	}

	@Override
	public boolean isEmpty() {
		return store.isEmpty();
	}

	@Override
	public void remove(Long puffId) {
		store.remove(puffId);
	}

}
