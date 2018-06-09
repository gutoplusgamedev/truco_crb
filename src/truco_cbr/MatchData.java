/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import truco_cbr.Player.Player;
import java.util.HashMap;

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
    
    public void setCurrentRound(int round)
    {
        _currentRound = round;
    }
    
    public HashMap<Player, Integer> getMatchPoints()
    {
        HashMap<Player, Integer> pointsPerPlayer = new HashMap<Player, Integer>();
        if(_envido.getWinner() != null)
        {
            pointsPerPlayer.put(_envido.getWinner(), _envido.getWonPoints());
        }
        if(_truco.getWinner() != null)
        {
            Integer curPoints = 0;
            if(pointsPerPlayer.containsKey(_truco.getWinner()))
            {
                curPoints = pointsPerPlayer.get(_truco.getWinner());                
            }
            pointsPerPlayer.put(_truco.getWinner(), curPoints);
        }
        return pointsPerPlayer;
    }
    
    public MatchData(Player handPlayer)
    {
        _handPlayer = handPlayer;
        _envido = new EnvidoData();
        _truco = new TrucoData();
        _currentRound = 0;
    }
}
