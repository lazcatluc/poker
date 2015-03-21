package controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cards.Card;
import cards.CardImpl;
import cards.Deck;
import cards.Rank;
import cards.Suit;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import player.Player;

@RunWith(HierarchicalContextRunner.class)
public class PlayerControllerTests {

	private Deck deck;
	
	private PlayerController playerController;
	private Table table;
	
	private Card card1;
	private Card card2;
	
	@Before
	public void setup(){
		deck = mock(Deck.class);

        card1 = new CardImpl(Rank.ACE,Suit.CLUBS);
		card2 = new CardImpl(Rank.FIVE,Suit.HEARTS);
		when(deck.drawCard()).thenReturn(card1, card2);

        playerController = new PlayerController();
        playerController.setName("testName");
        playerController.createPlayer();

        table = new Table();
		table.setDeck(deck);
		playerController.setTable(table);
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

        Assert.assertTrue(player1 == player2);
    }
    
    public class SinglePlayerAtTable{
    	
    	PlayerController pc1;
    	Table table;
    	
    	@Before
    	public void setup(){
    		pc1 = new PlayerController();
    		pc1.setName("Player1");
			pc1.createPlayer();
			
			
			table = new Table();
			pc1.setTable(table);

			table.registerPlayer(pc1.getPlayer());
    	}
    
	    @Test
		public void whenPlayerFoldsTableIsEmpty() throws Exception {
			
			pc1.fold();
			
			int numberOfPlayers = table.getNumberOfPlayers();
			
			assertEquals(0,numberOfPlayers);
		}
	    
	    @Test
		public void playerIsOwner() throws Exception {
	    	Player player = pc1.getPlayer();
	    	
	    	assertTrue(table.isOwner(player));
		}
	    
	    public class TwoPlayersAtTable {
	    	PlayerController pc2;
	    	
	    	@Before
	    	public void newPlayerJoins(){
	    		pc2 = new PlayerController();
	    		pc2.setName("Player2");
				pc2.createPlayer();
				
				pc2.setTable(table);

				table.registerPlayer(pc2.getPlayer());
	    	}
	    	
	    	@Test
			public void firstPlayerIsOwner() throws Exception {
	    		Player firstPlayer = pc1.getPlayer();
		    	
		    	assertTrue(table.isOwner(firstPlayer));
			}
	    	
	    	@Test
			public void whenFirstPlayerFoldsSecondPlayerBecomesOwner() throws Exception {
	    		pc1.fold();
	    		
	    		Player secondPlayer = pc2.getPlayer();
	    		
	    		assertTrue(table.isOwner(secondPlayer));
			}
	    }
    }

}
