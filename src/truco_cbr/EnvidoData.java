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
public class EnvidoData 
{   
    private Player _winner;
    private int _pointsAtStake = 0;
    private EnvidoRequest _envidoRequest;
    
    public EnvidoData()
    {
        _envidoRequest = null;
    }
    
    public boolean isAvailable()
    {
        return _envidoRequest == null;
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
