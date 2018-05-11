/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

/**
 *
 * @author Guto
 */
public enum CardType 
{    
    Clubs('♣'),
    Spades('♠'),
    Gold('●'),
    Cups('♥');
    
    private final char _icon;
    
    public char getIcon()
    {
        return _icon;
    }
    
    CardType(char icon)
    {
        _icon = icon;
    }
}
