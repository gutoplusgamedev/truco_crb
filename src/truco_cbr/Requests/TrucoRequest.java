/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr.Requests;

import truco_cbr.MatchData;
import truco_cbr.Player.Player;
import truco_cbr.TrucoData;
import truco_cbr.TrucoLevel;

/**
 *
 * @author Guto
 */
public class TrucoRequest extends GameRequest 
{
    public TrucoRequest(Player requestMaker) 
    {
        super(requestMaker);
    }
    
    @Override
    public boolean canMakeRequest(MatchData state, Player other) 
    {
        TrucoData truco = state.getTrucoData();
        boolean isRequesterDifferent = truco.getLastMadeRequest() == null || (truco.getLastMadeRequest().getRequestMaker() != getRequestMaker());
        return truco.getGameLevel() != TrucoLevel.Vale4 && isRequesterDifferent;
    }

    @Override
    protected void onRequestDeclined(MatchData state, Player other) 
    {
        state.getTrucoData().setWinner(getRequestMaker(), state.getTrucoData().getGameLevel().getPointsWhenOpponentGivesUp());
        //Acabou o jogo. 
    }
    
    @Override
    public RequestResponse processRequest(MatchData state, Player other)
    {
        RequestResponse response = super.processRequest(state, other);
        state.getTrucoData().setLastMadeRequest(this);
        return response;
    }
    
    @Override
    protected void onRequestAccepted(MatchData state, Player other) 
    {
        //Aumenta o nivel atual do truco.
        TrucoLevel nextLevel = TrucoLevel.values()[state.getTrucoData().getGameLevel().ordinal() + 1];
        state.getTrucoData().setGameLevel(nextLevel);
    }
    
    @Override
    public String toString()
    {
        return "Truco";
    }
}
