package home.hallgassaszivedre.app.service;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CloudAppService {
	
    @Inject
    public PuffRepository puffRepository;
    
	public List<Puff> getPuffs() {
	    return puffRepository.findAll();
	}
	
	public void createPuff(Puff puff) {
	    puffRepository.create(puff);
	}
	
	public void updatePuff(Puff puff) {
	    puffRepository.update(puff);
	}

	public void deletePuff(Long puffId) {
		puffRepository.remove(puffId);
	}

}
