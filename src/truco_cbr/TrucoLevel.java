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
public enum TrucoLevel 
{
    None(1, 1), Truco(2, 1), Retruco(3, 2), Vale4(4, 3);
   
    private final int _pointsWorth, _pointsWhenOpponentGivesUp;
    
    public int getPointsWorth()
    {
        return _pointsWorth;
    }
    
    public int getPointsWhenOpponentGivesUp()
    {
        return _pointsWhenOpponentGivesUp;
    }
    
    TrucoLevel(int pointsWorth, int pointsWhenOpponentGivesUp)
    {
        _pointsWorth = pointsWorth;
        _pointsWhenOpponentGivesUp = pointsWhenOpponentGivesUp;
    }
}
