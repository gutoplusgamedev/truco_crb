/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr.Requests;

import truco_cbr.EnvidoLevel;
import truco_cbr.MatchData;
import truco_cbr.Player.Player;

/**
 *
 * @author Guto
 */
public class EnvidoRequest extends GameRequest
{
    public EnvidoRequest(Player requestMaker) 
    {
        super(requestMaker);
    }
    
    @Override
    public boolean canMakeRequest(MatchData state, Player other) 
    {
        return state.getCurrentRound() == 0 && state.getEnvidoData().getEnvidoLevel() != EnvidoLevel.RealEnvido;
    }

    @Override
    protected void onRequestDeclined(MatchData state, Player other) 
    {
        state.getEnvidoData().setWinner(getRequestMaker(), state.getEnvidoData().getEnvidoLevel().getPointsWhenOpponentGivesUp());   
        //Aumenta pontuação do jogador request maker.
    }

    @Override
    protected void onRequestAccepted(MatchData state, Player other) 
    {
        EnvidoLevel currentLevel = state.getEnvidoData().getEnvidoLevel();
        EnvidoLevel nextLevel = EnvidoLevel.values()[currentLevel.ordinal() + 1];
        state.getEnvidoData().setEnvidoLevel(nextLevel);
        //aumenta o numero de pontos em jogo
    }
    
    @Override
    public void onRequestChainEnded(MatchData state, Player other, RequestResponse lastResponse)
    {
        if(lastResponse.acceptsRequest())
        {
            //Calcula pontuação de envido.
            int requesterPoints = getRequestMaker().getEnvidoPoints();
            int otherPoints = other.getEnvidoPoints();
            int pointDiff = requesterPoints - otherPoints;
            Player winner = getRequestMaker();
            //Se der empate vence o jogador mão (?)
            if(pointDiff < 0 || (pointDiff == 0 && state.getHandPlayer() != getRequestMaker()))
            {
                winner = other;
            }
            state.getEnvidoData().setWinner(winner, state.getEnvidoData().getEnvidoLevel().getWorthPoints());
        }
    }
    
    @Override
    public String toString()
    {
        return "Envido";
    }
}
