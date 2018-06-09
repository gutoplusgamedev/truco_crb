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
public enum EnvidoLevel 
{
    None(0, 0), Envido(2, 1), RealEnvido(5, 2);
    
    private final int _pointsWorth;
    private final int _pointsWhenOtherGivesUp;
    
    public int getWorthPoints()
    {
        return _pointsWorth;
    }
    
    public int getPointsWhenOpponentGivesUp()
    {
        return _pointsWhenOtherGivesUp;
    }
    
    EnvidoLevel(int pointsWorth, int pointsWhenOtherGivesUp)
    {
        _pointsWorth = pointsWorth;
        _pointsWhenOtherGivesUp = pointsWhenOtherGivesUp;
    }
}
