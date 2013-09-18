package home.hallgassaszivedre.infrastructure.acl;

import home.hallgassaszivedre.domain.model.Puff;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DataConverterTest {
	
	private DataConverter testObj = new DataConverter();

	@Test
	public void convert() {
		Puff puff1 = new Puff();
		Puff puff2 = new Puff();
		List<Puff> puffs = Arrays.asList(puff1, puff2);
		//act
		//String result = testObj.convertToDTO(puffs);
		//assert
		//System.err.println(result);
	}

}
