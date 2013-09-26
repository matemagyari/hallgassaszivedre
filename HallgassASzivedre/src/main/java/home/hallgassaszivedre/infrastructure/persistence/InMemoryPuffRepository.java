package home.hallgassaszivedre.infrastructure.persistence;

import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;
import home.hallgassaszivedre.domain.model.exception.PuffException;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

@Component
public class InMemoryPuffRepository implements PuffRepository {
	
	private final Map<Long, Puff> map = Maps.newHashMap();
	private final AtomicLong idGenerator = new AtomicLong(1);
	private final Set<String> phrases = Sets.newHashSet();

	@Override
	public List<Puff> findAll() {
		return Lists.newArrayList( map.values());
	}

    @Override
    public void create(Puff puff) {
        puff.setId(idGenerator.getAndIncrement());
        checkPhraseUniqueness(puff.getPhrase());
        map.put(puff.getId(), puff);
    }

    @Override
    public void update(Puff puff) {
        checkPhraseUniqueness(puff.getPhrase());
        map.put(puff.getId(), puff);
    }
    

    private void checkPhraseUniqueness(String phrase) {
        if (phrases.contains(phrase)) {
            throw new PuffException("Phrase must be unique " + phrase);
        }
    }

}
