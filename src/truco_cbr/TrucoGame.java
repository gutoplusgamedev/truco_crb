/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

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
    
    public void runGameLoop()
    {
        Player[] players = (Player[])_pointsPerPlayer.keySet().toArray(new Player[_pointsPerPlayer.size()]);
        //Jogador mão.
        for(Player handPlayer : players)
        {
            System.out.println(handPlayer.getPlayerName());
            MatchData state = new MatchData(handPlayer);
            //Rodada do truco.
            for(int round = 0; round < 3; round++)
            {
                state.setCurrentRound(round);
                HashMap<Player, Card> cardsPlayed = new HashMap<>();
                //Jogada de cada jogador.
                for(int turn = 0; turn < 2; turn++)
                {
                    int currentPlayerIndex = turn;
                    runRequestChain(state, players, currentPlayerIndex);
                    cardsPlayed.put(players[currentPlayerIndex], players[currentPlayerIndex].playCard(state));
                }
                //compara as cartas jogadas e decide qual é maior.
                if(Card.getCardValue(cardsPlayed.get(players[1])) > Card.getCardValue(cardsPlayed.get(players[0])))
                {
                    Player tempP1 = players[0];
                    players[0] = players[1];
                    players[1] = tempP1;
                }
            }
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
                   currentRequest.onRequestChainEnded(state, other);
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
