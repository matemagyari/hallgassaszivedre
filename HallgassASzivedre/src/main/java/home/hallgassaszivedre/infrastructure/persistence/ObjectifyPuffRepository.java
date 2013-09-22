package home.hallgassaszivedre.infrastructure.persistence;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Component;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;

@Component
public class ObjectifyPuffRepository implements PuffRepository {
	
	private final Map<Integer, Puff> map = Maps.newHashMap();
	private final AtomicInteger idGenerator = new AtomicInteger(1);
	private Objectify ofy;

	private void init() {
		ofy = ObjectifyService.ofy();
	}
	
	@Override
	public List<Puff> findAll() {
		return null;
	}

    @Override
    public void create(Puff puff) {
    	Key<Puff> key = ofy.save().entity(puff).now();
    }

    @Override
    public void update(Puff puff) {
        map.put(puff.getId(), puff);
    }

}
