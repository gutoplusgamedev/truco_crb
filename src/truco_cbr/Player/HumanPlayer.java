/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr.Player;

import truco_cbr.Requests.EnvidoRequest;
import truco_cbr.Requests.TrucoRequest;
import truco_cbr.Requests.GameRequest;
import truco_cbr.Requests.RequestResponse;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import truco_cbr.Card;
import truco_cbr.MatchData;
import truco_cbr.ThreadedFrame;
import truco_cbr.TrucoCardPanel;

/**
 *
 * @author Guto
 */
public class HumanPlayer extends Player
{
    public HumanPlayer(String playerName) 
    {
        super(playerName);
    }

    @Override
    public GameRequest getRequest(MatchData state) 
    {
        TrucoRequest trucoRequest = new TrucoRequest(this);
        EnvidoRequest envidoRequest = new EnvidoRequest(this);
        HashMap<String, GameRequest> possibleRequests = new HashMap<>();
        possibleRequests.put("Jogar carta.", null);
        if(trucoRequest.canMakeRequest(state, null))
        {
            possibleRequests.put("Pedir/aumentar truco.", trucoRequest);
        }
        if(envidoRequest.canMakeRequest(state, null))
        {
            possibleRequests.put("Pedir envido.", envidoRequest);
        }
        if(possibleRequests.size() > 1)
        {
            try
            {
                return (GameRequest)getMultipleChoiceFrameResult(this, getPlayerName() + " selecione uma ação", possibleRequests);
            }
            catch(InterruptedException e) {}
        }
        return null;
    }

    @Override
    public RequestResponse onRequestReceived(GameRequest request) 
    {
        try
        {
            if(request instanceof TrucoRequest)
            {
                return processTrucoRequest(request);
            }
            else if (request instanceof EnvidoRequest)
            {
                return processEnvidoRequest(request);
            }
            //processar flor request?
        }
        catch (InterruptedException e) {}
        return null;
    }
    
    public Object getMultipleChoiceFrameResult(Player player, String text, HashMap<String, ?> choices) throws InterruptedException
    {
        String[] keys = choices.keySet().toArray(new String[choices.size()]);
        ThreadedFrame frame = ThreadedFrame.getFrame();
        frame.setLayout(new FlowLayout());
        frame.setParams(keys, text);
        Thread thread = new Thread(frame);
        
        TrucoCardPanel panel = new TrucoCardPanel(super.playerCards);
        frame.add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        thread.start(); thread.join();
        frame.remove(panel);
        return choices.get(keys[frame.getComboBox().getSelectedIndex()]);
    }
    
    private RequestResponse processEnvidoRequest(GameRequest request) throws InterruptedException
    {
        HashMap<String, RequestResponse> results = new HashMap<>();
        results.put("Real envido", new RequestResponse(true, new EnvidoRequest(this)));
        results.put("Recusar", new RequestResponse(false));
        results.put("Aceitar", new RequestResponse(true));
        return (RequestResponse)getMultipleChoiceFrameResult(this, request.getRequestMaker().getPlayerName() + " pediu envido.", results);
    }
    
    private RequestResponse processTrucoRequest(GameRequest request) throws InterruptedException
    {
        HashMap<String, RequestResponse> results = new HashMap<>();
        results.put("Recusar", new RequestResponse(false));
        results.put("Aceitar", new RequestResponse(true));
        results.put("Aumentar", new RequestResponse(true, new TrucoRequest(this)));
        return (RequestResponse)getMultipleChoiceFrameResult(this, request.getRequestMaker().getPlayerName() + " pediu truco.", results);
    }
    
    @Override
    public Card playCard(MatchData gameState) 
    {
        HashMap<String, Card> cardsHash = new HashMap<>();
        for(Card c : playerCards)
        {
            cardsHash.put(c.toString(), c);
        }
        Card selected = null;
        try
        {
           selected = (Card)getMultipleChoiceFrameResult(this, getPlayerName() + " selecione uma carta", cardsHash);
        } 
        catch (InterruptedException ex) {}
        playerCards.remove(selected);
        return selected;
    }
}
