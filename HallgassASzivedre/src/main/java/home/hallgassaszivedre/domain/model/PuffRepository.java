package home.hallgassaszivedre.domain.model;

import java.util.List;

public interface PuffRepository {

	List<Puff> findAll();

    void update(Puff puff);

    void create(Puff puff);

}
