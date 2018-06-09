/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr.Requests;

import truco_cbr.MatchData;
import truco_cbr.Player.Player;

/**
 *
 * @author Guto
 */
public abstract class GameRequest
{ 
    private Player _requestMaker;
        
    public void setRequestMaker(Player requestMaker)
    {
        _requestMaker = requestMaker;
    }
    
    public Player getRequestMaker()
    {
        return _requestMaker;
    }
    
    public GameRequest(Player requestMaker)
    {
        _requestMaker = requestMaker;
    }
        
    /*
    Indica se um request pode ser executado, dependendo do estado atual de jogo.
    */
    public abstract boolean canMakeRequest(MatchData state, Player other);
    
    /*
    Função chamada quando um request pode ser executado.
    */
    public RequestResponse processRequest(MatchData state, Player other)
    {
        RequestResponse response = other.onRequestReceived(this);
        if(response.acceptsRequest())
        {
            onRequestAccepted(state, other);
        }
        else
        {
            onRequestDeclined(state, other);
        }
        return response;
    }
    
    /*
    Função chamada no ultimo request, quando a cadeia de requests terminou.
    */
    public void onRequestChainEnded(MatchData state, Player other, RequestResponse lastResponse){}
    
    /*
    Callback chamado quando o request não é aceito pelo outro jogador.
    */
    protected abstract void onRequestDeclined(MatchData state, Player other);
    
    /*
    Callback chamado quando o request é aceito pelo outro jogador.
    */
    protected abstract void onRequestAccepted(MatchData state, Player other);
}
