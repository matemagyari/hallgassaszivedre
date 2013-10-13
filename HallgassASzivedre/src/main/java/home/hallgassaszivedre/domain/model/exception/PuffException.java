package home.hallgassaszivedre.domain.model.exception;

import home.hallgassaszivedre.domain.model.Puff;


@SuppressWarnings("serial")
public class PuffException extends RuntimeException {

	//TODO test
	Puff zz;
    public PuffException(String msg) {
        super(msg);
    }

}
