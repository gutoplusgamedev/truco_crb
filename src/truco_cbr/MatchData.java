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
public class MatchData 
{
    private Player _handPlayer;
    private EnvidoData _envido;
    private TrucoData _truco;
    private int _currentRound;
    
    public Player getHandPlayer()
    {
        return _handPlayer;
    }
    
    public int getCurrentRound()
    {
        return _currentRound;
    }

    public EnvidoData getEnvidoData()
    {
        return _envido;
    }
    
    public TrucoData getTrucoData()
    {
        return _truco;
    }
    
    public MatchData(Player handPlayer)
    {
        _handPlayer = handPlayer;
        _envido = new EnvidoData();
        _truco = new TrucoData();
        _currentRound = 0;
    }
}
