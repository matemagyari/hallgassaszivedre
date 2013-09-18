package home.hallgassaszivedre.infrastructure.persistence;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Maps;

@Component
public class InMemoryPuffRepository implements PuffRepository {
	
	private final Map<Integer, Puff> map = Maps.newHashMap();
	private final AtomicInteger idGenerator = new AtomicInteger(1);

	@Override
	public List<Puff> findAll() {
		return Lists.newArrayList( map.values());
	}

    @Override
    public void create(Puff puff) {
        puff.setId(idGenerator.getAndIncrement());
        map.put(puff.getId(), puff);
    }

    @Override
    public void update(Puff puff) {
        map.put(puff.getId(), puff);
    }

}
