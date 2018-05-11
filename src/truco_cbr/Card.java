/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Guto
 */
public class Card 
{   
    public int _value;
    public CardType _type;
    private static HashMap<Card, Integer> _specialCards;
    
    private static void initializeSpecialCards()
    {
        _specialCards = new HashMap<>();
        _specialCards.put(new Card(1, CardType.Spades), 20);
        _specialCards.put(new Card(1, CardType.Clubs), 19);
        _specialCards.put(new Card(7, CardType.Spades), 18);
        _specialCards.put(new Card(7, CardType.Gold), 17);
    }
    
    public static int getCardValue(Card card)
    {
        int cardValue = card.getValue();
        Integer specialCardValue = getSpecialValue(card);
        if(specialCardValue != null)
        {
            cardValue = specialCardValue;
        }
        else if (cardValue <= 3)
        {
            cardValue += 12;
        }
        return cardValue;
    }
    
    private static int getSpecialValue(Card card)
    {
        if(_specialCards == null)
        {
            initializeSpecialCards();
        }
        return _specialCards.get(card); 
    }
    
    public int getValue()
    {
        return _value;
    }
    
    public CardType getType()
    {
        return _type;
    }
    
    public Card (int value, CardType type)
    {
        _value = value;
        _type = type;
    }
    
    public static ArrayList<Card> getMostFrequentSuit(ArrayList<Card> cards)
    {
        Map<CardType, ArrayList<Card>> cardsBySuit = new HashMap<>();
        for(Card card : cards)
        {
            if(!cardsBySuit.containsKey(card.getType()))
            {
                cardsBySuit.put(card.getType(), new ArrayList<>());
            }
            ArrayList<Card> current = cardsBySuit.get(card.getType());
            current.add(card);
            cardsBySuit.put(card.getType(), current);
        }
        return cardsBySuit.get(0);
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Card)
        {
            Card card = (Card)other;
            return card.getType() == getType() && card.getValue() == getValue();
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        return _value + " " +  _type.getIcon();
    }
}
