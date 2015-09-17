package javasmartphone.cointoss;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CoinTest {
	Coin coin;
	
	@Before
	public void setUp() throws Exception {
		coin = new Coin();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInitialCountShouldBeZero() {
		assertEquals(0, coin.getHeadCount());
		assertEquals(0, coin.getTailCount());
	}

	@Test
	public void testInitialSideUpCanNotNull() {
		assertNotNull(coin.getSideUp());
	}
	
	@Test
	public void testInitialSideUpIsEitherTailsOrHeads() {
		String sideUp = coin.getSideUp();
		boolean isHead = sideUp.equals("heads");
		boolean isTail = sideUp.equals("tails");
		assertEquals(true, isHead | isTail);
	}
	
	@Test 
	public void testHasValidSideUpAfterToss() {
		coin.toss();
		String sideUp = coin.getSideUp();
		boolean isHead = sideUp.equals("heads");
		boolean isTail = sideUp.equals("tails");
		assertEquals(true, isHead | isTail);
	}
	
	@Test
	public void testTotalCountShouldBe20After20Toss() {
		for (int i = 1; i <= 20; ++i)  {
			coin.toss();
			assertEquals(i, coin.getHeadCount()+coin.getTailCount());
		}
	}
}
