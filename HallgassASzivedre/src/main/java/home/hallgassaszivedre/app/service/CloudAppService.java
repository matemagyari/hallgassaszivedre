package home.hallgassaszivedre.app.service;

import home.hallgassaszivedre.app.bootstrapper.Bootstrapper;
import home.hallgassaszivedre.app.x.XX;
import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;
import home.hallgassaszivedre.infrastructure.persistence.StoreBasedPuffRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CloudAppService {
  //TODO test
	StoreBasedPuffRepository basedPuffRepository;
	//TODO test
	XX x;
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
