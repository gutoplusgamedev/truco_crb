/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Guto
 */
public class CardDealer 
{   
    private final int[] _cardValues = new int[] { 1, 2, 3, 4, 5, 6, 7, 10, 11, 12 };
    private final CardType[] _cardSuits = new CardType[] { CardType.Clubs, CardType.Cups, CardType.Gold, CardType.Spades };
    private ArrayList<Card> _cards;
    
    public CardDealer()
    {
        GenerateCardDeck();
        Shuffle(300);
    }
    
    public ArrayList<Card> getCards(int count)
    {
        Random random = new Random();
        ArrayList<Card> cards = new ArrayList<>();
        for(int i = 0; i < count; i++)
        {
           int randomIndex = random.nextInt(_cards.size());
           cards.add(_cards.remove(randomIndex));
        }
        return cards;
    }
    
    private void Shuffle(int count)
    {
        Random random = new Random();
        for(int i = 0; i < count; i++)
        {
            int indexA = random.nextInt(_cards.size());
            int indexB = random.nextInt(_cards.size());
            Card temp = _cards.get(indexA);
            _cards.set(indexA, _cards.get(indexB));
            _cards.set(indexB, temp);
        }
    }
    
    private void GenerateCardDeck()
    {
        _cards = new ArrayList<>();
        for(int value : _cardValues)
        {
            for (CardType suit : _cardSuits) 
            {
                _cards.add(new Card(value, suit));
            }
        }
    }
}
