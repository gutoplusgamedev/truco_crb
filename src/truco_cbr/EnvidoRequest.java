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
public class EnvidoRequest extends GameRequest
{
    public EnvidoRequest(Player requestMaker) 
    {
        super(requestMaker);
    }
    
    @Override
    public boolean canMakeRequest(MatchData state, Player other) 
    {
        return state.getCurrentRound() == 0 && state.getEnvidoData().isAvailable();
    }

    @Override
    protected void onRequestDeclined(MatchData state, Player other) 
    {
        //Aumenta pontuação do jogador request maker.
    }

    @Override
    protected void onRequestAccepted(MatchData state, Player other) 
    {
        //aumenta o numero de pontos em jogo
    }
    
    @Override
    public void onRequestChainEnded(MatchData state, Player other)
    {
        //Calcula pontuação de envido.
        int requesterPoints = super.getRequestMaker().getEnvidoPoints();
        int otherPoints = other.getEnvidoPoints();
        int pointDiff = requesterPoints - otherPoints;
        //Se der empate vence o jogador mão.
        if(pointDiff > 0 || (pointDiff == 0 && state.getHandPlayer() == getRequestMaker()))
        {
            //aumenta pontos do requester
        }
        else
        {
            
            //aumenta pontos do outro
        }
    }
    
    @Override
    public String toString()
    {
        return "Envido";
    }
}
