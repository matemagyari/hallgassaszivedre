package home.hallgassaszivedre.app.service;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CloudAppService {
	
    @Autowired
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

}
