package home.hallgassaszivedre.domain.model.exception;

import home.hallgassaszivedre.domain.model.Puff;



@SuppressWarnings("serial")
public class PuffException extends RuntimeException {

    Puff puff;
    public PuffException(String msg) {
        super(msg);
    }

}
