/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import truco_cbr.Player.Player;
import truco_cbr.Requests.GameRequest;

/**
 *
 * @author Guto
 */
public class TrucoData 
{
    private TrucoLevel _gameLevel = TrucoLevel.None;
    //O ultimo jogador a aumentar o nivel do jogo.
    private GameRequest _lastMadeRequest;
    private Player _winner;
    private int _wonPoints;
    
    public TrucoData()
    {
        _gameLevel = TrucoLevel.None;
        _lastMadeRequest = null;
    }
    
    public Player getWinner()
    {
        return _winner;
    }
    
    public int getWonPoints()
    {
        return _wonPoints;
    }
    
    public void setWinner(Player winner, int wonPoints)
    {
        _winner = winner;
        _wonPoints = wonPoints;
    }
    
    public void setGameLevel(TrucoLevel gameLevel)
    {
        _gameLevel = gameLevel;
    }
    
    public TrucoLevel getGameLevel()
    {
        return _gameLevel;
    }
    
    public GameRequest getLastMadeRequest()
    {
        return _lastMadeRequest;
    }
    
    public void setLastMadeRequest(GameRequest request)
    {
        _lastMadeRequest = request;
    }
}
