package player;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerValidatorImplTest {
	
	@Test
	public void testException() {
		PlayerValidator validator = new PlayerValidatorImpl();
		Player p;
		boolean failed = false;
		
		p=new PlayerImpl("Ion");
		try {
			validator.validatePlayer(p);
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
		}
		
		p=new PlayerImpl("Gheorghe");
		try {
			validator.validatePlayer(p);
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
		}
		
		p=new PlayerImpl("Gica");
		try {
			validator.validatePlayer(p);
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
		}
		
		p=new PlayerImpl("Dorel");
		try {
			validator.validatePlayer(p);
		} catch (InvalidPlayerException e) {
			e.printStackTrace();
		}
		
		assertEquals(4, validator.getNameList().size());
		
		
		p=new PlayerImpl("Ion");
		try {
			validator.validatePlayer(p);
		} catch (InvalidPlayerException e) {
			failed = true;
		}
		
		assertEquals(true, failed);	
		
	}

}
