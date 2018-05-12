/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import java.util.ArrayList;

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
    
    public Player (String playerName, ArrayList<Card> cards)
    {
        _playerName = playerName;
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
