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
        return truco.getGameLevel() < 3 && isRequesterDifferent;
    }

    @Override
    protected void onRequestDeclined(MatchData state, Player other) 
    {
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
        int nextLevel = state.getTrucoData().getGameLevel() + 1;
        state.getTrucoData().setGameLevel(nextLevel);
    }
    
    @Override
    public String toString()
    {
        return "Truco";
    }
}
