package controller;

import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cards.Card;
import cards.CardImpl;
import cards.Deck;
import cards.Rank;
import cards.Suit;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import player.InvalidPlayerException;
import player.Player;
import player.PlayerValidatorImpl;

@RunWith(HierarchicalContextRunner.class)
public class PlayerControllerTest {

	private Deck deck;
	
	private PlayerController playerController;
	
	private Card card1;
	private Card card2;

	private Table table;
	
	@Before
	public void setup(){
		deck = mock(Deck.class);

        card1 = new CardImpl(Rank.ACE,Suit.CLUBS);
		card2 = new CardImpl(Rank.FIVE,Suit.HEARTS);
		when(deck.drawCard()).thenReturn(card1, card2);

        playerController = new PlayerController();
        
        
        table = new Table();
        table.setValidator(new PlayerValidatorImpl());
		table.setDeck(deck);
		playerController.setTable(table);
		
		
        playerController.setName("testName");
        playerController.createPlayer();

        
	}
	
	@Test
	public void getPlayerCardsCallsDeckTwice() {
		playerController.getCards();
		verify(deck,times(2)).drawCard();
	}
	
	@Test
	public void getPlayerCardsReturnsListOfTwoCards() throws Exception {
		List<Card> playerCards = playerController.getCards();
		
		Assertions.assertThat(playerCards).hasSize(2);
	}
	
	@Test
	public void getPlayerCardsReturnDifferentCards() throws Exception {
		List<Card> firstDraw = playerController.getCards();
		List<Card> secondDraw = playerController.getCards();
		
		Assertions.assertThat(firstDraw).isEqualTo(secondDraw);
	}

    @Test
    public void shouldNotCreatePlayerTheSecondTime() {
        playerController.setName("testName");
        playerController.createPlayer();
        Player player1 = playerController.getPlayer();

        playerController.createPlayer();
        Player player2 = playerController.getPlayer();

        assertTrue(player1 == player2);
    }
    
    @Test
    public void crashPlayerValidator() {    	
    	PlayerController playerController = new PlayerController();
    	playerController.setName("testName");
    	playerController.setTable(table);
    	
    	System.out.println("error:"+playerController.getError());
    	
    	assertNull(playerController.getError());
    	playerController.createPlayer();
    	assertNotNull(playerController.getError());
    	
    	System.out.println("error:"+playerController.getError());
    	
    }
    
  public class SinglePlayerAtTable{
    	
    	PlayerController pc1;
    	Table myTable;
    	
    	@Before
    	public void setup() throws InvalidPlayerException{
    		pc1 = new PlayerController();
    		pc1.setName("Player1");
			pc1.createPlayer();
			
			
			myTable = new Table();
			pc1.setTable(myTable);

			myTable.registerPlayer(pc1.getPlayer());
    	}
    
	    @Test
		public void whenPlayerFoldsTableIsEmpty() throws Exception {
			
			pc1.fold();
			
			int numberOfPlayers = myTable.getNumberOfPlayers();
			
			assertEquals(0,numberOfPlayers);
		}
	    
	    @Test
		public void playerIsOwner() throws Exception {
	    	Player player = pc1.getPlayer();
	    	
	    	assertTrue(myTable.isOwner(player));
		}
	    
	    public class TwoPlayersAtTable {
	    	PlayerController pc2;
	    	
	    	@Before
	    	public void newPlayerJoins() throws InvalidPlayerException{
	    		pc2 = new PlayerController();
	    		pc2.setName("Player2");
				pc2.createPlayer();
				
				pc2.setTable(myTable);

				myTable.registerPlayer(pc2.getPlayer());
	    	}
	    	
	    	@Test
			public void firstPlayerIsOwner() throws Exception {
	    		Player firstPlayer = pc1.getPlayer();
		    	
		    	assertTrue(myTable.isOwner(firstPlayer));
			}
	    	
	    	@Test
			public void whenFirstPlayerFoldsSecondPlayerBecomesOwner() throws Exception {
	    		pc1.fold();
	    		
	    		Player secondPlayer = pc2.getPlayer();
	    		
	    		assertTrue(myTable.isOwner(secondPlayer));
			}
	    }
    }

}
