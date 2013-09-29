package home.hallgassaszivedre.infrastructure.persistence.store;

import home.hallgassaszivedre.infrastructure.dto.PuffDTO;

import java.util.List;

public interface PuffStore {

	List<PuffDTO> findAll();

    void update(PuffDTO puff);

    void create(PuffDTO puff);
    
    boolean isEmpty();

	void remove(Long puffId);

}
