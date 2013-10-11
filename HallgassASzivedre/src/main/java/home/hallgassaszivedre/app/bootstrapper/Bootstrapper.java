package home.hallgassaszivedre.app.bootstrapper;

import home.hallgassaszivedre.app.service.CloudAppService;
import home.hallgassaszivedre.domain.model.Puff;
import home.hallgassaszivedre.domain.model.PuffRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;

import org.springframework.context.annotation.Lazy;

@Lazy(false)
@Named
public class Bootstrapper {
    //TODO test
    home.hallgassaszivedre.infrastructure.http.CloudServlet cloudServlet = new home.hallgassaszivedre.infrastructure.http.CloudServlet();
  //TODO test
    CloudAppService appService;
	@Resource
	public PuffRepository puffRepository;

	@SuppressWarnings("unused")
    @PostConstruct
	private void bootstrap() {
		if (puffRepository.isEmpty()) {
			Puff puff1 = createPuff(1);
			Puff puff2 = createPuff(2);
			puffRepository.create(puff1);
			puffRepository.create(puff2);
		}
	}

	private Puff createPuff(int id) {
		Puff puff1 = new Puff();
		puff1.setPhrase("phrase" + id);
		puff1.setContent("content" + id);
		puff1.setWeight(id * 10);
		return puff1;
	}
}
