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
public class TrucoData 
{
    private int _gameLevel = 0;
    //O ultimo jogador a aumentar o nivel do jogo.
    private GameRequest _lastMadeRequest;
    private Player _winner;
    
    public TrucoData()
    {
        _gameLevel = 0;
        _lastMadeRequest = null;
        _winner = null;
    }
    
    public void setGameLevel(int gameLevel)
    {
        _gameLevel = gameLevel;
    }
    
    public int getGameLevel()
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
    
    public Player getWinner()
    {
        return _winner;
    }
}
