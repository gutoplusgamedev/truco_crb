/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Guto
 */
public class Truco_cbr 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        runGameLoop();
    }
    
    public static void runGameLoop()
    {
        CardDealer dealer = new CardDealer();
        Player p1 = new HumanPlayer("Jogador 1", dealer.getCards(3));
        Player p2 = new HumanPlayer("Jogador 2", dealer.getCards(3));
        MatchData state = new MatchData(p1);
        Player[] players = new Player[] { p1, p2 };
        for(int i = 0; i < 3; i++)
        {
            HashMap<Player, Card> cardsPlayed = new HashMap<>();
            for(int turn = 0; turn < 2; turn++)
            {
                int currentPlayerIndex = turn;
                processRequests(state, players, currentPlayerIndex);
                cardsPlayed.put(players[currentPlayerIndex], players[currentPlayerIndex].playCard(state));
            }    
            //compara as cartas jogadas e decide qual é maior.
            if(Card.getCardValue(cardsPlayed.get(players[1])) > Card.getCardValue(cardsPlayed.get(players[0])))
            {
                Player tempP1 = players[0];
                players[0] = players[1];
                players[1] = tempP1;
                
                //invert array order.
            }
        }
    }    
    
    public static void processRequests(MatchData state, Player[] players, int currentPlayerIndex)
    {
        GameRequest currentRequest = players[currentPlayerIndex].getRequest();
        while(currentRequest != null)
        {
            Player other = players[(currentPlayerIndex + 1) % players.length];
            //System.out.println(currentRequest);
            if(currentRequest.canMakeRequest(state, other))
            {
                RequestResponse response = currentRequest.processRequest(state, other);
                GameRequest nextRequest = response.getResponseRequest();
                if(nextRequest == null)
                {
                    //Processa o envido, por exemplo. O truco nao possui post process.
                   currentRequest.onRequestChainEnded(state, other);
                }
                currentRequest = nextRequest;
                currentPlayerIndex++;
            }
            else
            {
                break;
            }
        }
    }
}
