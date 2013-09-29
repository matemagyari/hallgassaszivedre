package home.hallgassaszivedre.infrastructure.persistence.store;

import home.hallgassaszivedre.domain.model.exception.PuffException;
import home.hallgassaszivedre.infrastructure.dto.PuffDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.appengine.repackaged.com.google.common.collect.Maps;
import com.google.appengine.repackaged.com.google.common.collect.Sets;

public class InMemoryPuffStore implements PuffStore {
	
	private final Map<Long, PuffDTO> map = Maps.newHashMap();
	private final AtomicLong idGenerator = new AtomicLong(1);
	private final Set<String> phrases = Sets.newHashSet();
	

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}	

	@Override
	public List<PuffDTO> findAll() {
		return Lists.newArrayList( map.values());
	}

    @Override
    public void create(PuffDTO puff) {
        puff.setId(idGenerator.getAndIncrement());
        checkPhraseUniqueness(puff.getPhrase());
        map.put(puff.getId(), puff);
    }

    @Override
    public void update(PuffDTO puff) {
        checkPhraseUniqueness(puff.getPhrase());
        map.put(puff.getId(), puff);
    }
    

    private void checkPhraseUniqueness(String phrase) {
        if (phrases.contains(phrase)) {
            throw new PuffException("Phrase must be unique " + phrase);
        }
    }

	@Override
	public void remove(Long puffId) {
		map.remove(puffId);
	}


}
