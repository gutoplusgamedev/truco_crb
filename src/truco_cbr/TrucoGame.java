/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import truco_cbr.Player.Player;
import truco_cbr.Requests.GameRequest;
import truco_cbr.Requests.RequestResponse;
import java.util.HashMap;

/**
 *
 * @author Guto
 */
public class TrucoGame 
{
    private HashMap<Player, Integer> _pointsPerPlayer;
    private int _pointsToWin;
    private Player _winner;
    
    public Player getWinner()
    {
        return _winner;
    }
    
    public TrucoGame(Player p1, Player p2, int pointsToWin)
    {
        _pointsPerPlayer = new HashMap<>();
        _pointsPerPlayer.put(p1, 0);
        _pointsPerPlayer.put(p2, 0);
        _pointsToWin = pointsToWin;
    }
    
    private void distributeCards(Player[] players)
    {
        CardDealer dealer = new CardDealer();
        for(Player p : players)
        {
            p.setPlayerCards(dealer.getCards(3));
        }
    }
    
    public void runGameLoop()
    {
        Player[] players = (Player[])_pointsPerPlayer.keySet().toArray(new Player[_pointsPerPlayer.size()]);
        int currentPlayerIndex = 0;
        while(true)
        {
            distributeCards(players);
            MatchData state = new MatchData(players[currentPlayerIndex]);
            for(int round = 0; round < 3; round++)
            {
                playRound(state, round, currentPlayerIndex, players);
            }
            
            //Move para o proximo jogador a jogar.
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        }
    }
    
    private void playRound(MatchData state, int round, int currentPlayerIndex, Player[] players)
    {
        state.setCurrentRound(round);
        //Jogada de cada jogador.
        for(int turn = 0; turn < 2; turn++)
        {
            int actualPlayer = (currentPlayerIndex + turn) % players.length;
            runRequestChain(state, players, actualPlayer);
            players[actualPlayer].playCard(state);
        }
    }
    
    private void runRequestChain(MatchData state, Player[] players, int currentPlayerIndex)
    {
        GameRequest currentRequest = players[currentPlayerIndex].getRequest(state);
        while(currentRequest != null)
        {
            Player other = players[(currentPlayerIndex + 1) % players.length];
            //System.out.println(currentRequest);
            if(currentRequest.canMakeRequest(state, other))
            {
                RequestResponse response = currentRequest.processRequest(state, other);
                GameRequest nextRequest = response.getResponseRequest();
                if(nextRequest == null)
                {
                    //Processa o envido, por exemplo. O truco nao possui post process.
                   currentRequest.onRequestChainEnded(state, other, response);
                }
                currentRequest = nextRequest;
                currentPlayerIndex++;
            }
            else
            {
                break;
            }
        }
    }
}
