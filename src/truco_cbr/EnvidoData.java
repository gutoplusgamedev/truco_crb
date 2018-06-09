/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import truco_cbr.Player.Player;
import truco_cbr.Requests.EnvidoRequest;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Guto
 */
public class EnvidoData 
{   
    private EnvidoLevel _envidoLevel = EnvidoLevel.None;
    private EnvidoRequest _envidoRequest;
    private Player _winner;
    private int _wonPoints;

    public EnvidoLevel getEnvidoLevel()
    {
        return _envidoLevel;
    }
    
    public void setEnvidoLevel(EnvidoLevel level)
    {
        _envidoLevel = level;
    }
    
    public void setWinner(Player winner, int wonPoints)
    {
        _winner = winner;
        _wonPoints = wonPoints;
    }
    
    public Player getWinner()
    {
        return _winner;
    }
    
    public int getWonPoints()
    {
        return _wonPoints;
    }
    
    public EnvidoData()
    {
        _winner = null;
        _envidoRequest = null;
    }
    
    public void setEnvidoRequest(EnvidoRequest request)
    {
        _envidoRequest = request;
    }
    
    public static int getEnvidoPoints(Card card)
    {
        if(card.getValue() >= 10)
        {
            return 0;
        }
        else
        {
            return card.getValue();
        }
    }
    
    public static int getEnvidoPoints(ArrayList<Card> cards)
    {
        int points = 0;
        if(cards.size() > 1)
        {
            points += 20;
        }
        for(Card c : cards)
        {
            points += getEnvidoPoints(c);
        }
        return points;
    }
}
