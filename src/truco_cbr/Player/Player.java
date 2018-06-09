/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr.Player;

import truco_cbr.Requests.GameRequest;
import truco_cbr.Requests.RequestResponse;
import java.util.ArrayList;
import truco_cbr.Card;
import truco_cbr.MatchData;

/**
 *
 * @author Guto
 */
public abstract class Player
{
    public ArrayList<Card> playerCards;
    private String _playerName;
    
    public String getPlayerName()
    {
        return _playerName;
    }
    
    public Player (String playerName)
    {
        _playerName = playerName;
    }
    
    public void setPlayerCards(ArrayList<Card> cards)
    {
        playerCards = cards;
    }
    
    /*
    */
    public abstract GameRequest getRequest(MatchData state);

    public abstract RequestResponse onRequestReceived(GameRequest request);
    
    public abstract Card playCard(MatchData gameState);
    
    public int getEnvidoPoints()
    {
        return 0;
    }
}
