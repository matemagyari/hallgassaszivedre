package home.hallgassaszivedre.infrastructure.persistence;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Maps;

@Component
public class InMemoryPuffRepository implements PuffRepository {
	
	private final Map<Integer, Puff> map = Maps.newHashMap();

	@Override
	public List<Puff> findAll() {
		return Lists.newArrayList( map.values());
	}

}
