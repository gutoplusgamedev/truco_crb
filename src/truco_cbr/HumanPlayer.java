/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guto
 */
public class HumanPlayer extends Player
{
    public HumanPlayer(String playerName, ArrayList<Card> cards) 
    {
        super(playerName, cards);
    }

    @Override
    public GameRequest getRequest() 
    {
        HashMap<String, GameRequest> possibleRequests = new HashMap<>();
        possibleRequests.put("Continuar.", null);
        possibleRequests.put("Pedir/aumentar truco.", new TrucoRequest(this));
        possibleRequests.put("Pedir envido.", new EnvidoRequest(this));
        try
        {
            return getRequestThroughFrame(possibleRequests);
        }
        catch(InterruptedException e) {}
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
    
    private GameRequest getRequestThroughFrame(HashMap<String, GameRequest> results) throws InterruptedException
    {
        String[] keys = results.keySet().toArray(new String[results.size()]);
        String text = getPlayerName() + " selecione uma ação.";
        ThreadedFrame frame = new ThreadedFrame(keys, text);
        Thread t = frame.getThread();
        t.start();
        t.join();
        return results.get(keys[frame.getComboBox().getSelectedIndex()]);
    }
    
    public Card getCardThroughFrame(HashMap<String, Card> results) throws InterruptedException 
    {
        String[] keys = results.keySet().toArray(new String[results.size()]);
        String text = getPlayerName() + " selecione uma carta para jogar.";
        ThreadedFrame frame = new ThreadedFrame(keys, text);
        Thread t = frame.getThread();
        t.start();
        t.join();
        return results.get(keys[frame.getComboBox().getSelectedIndex()]);
    }
    
    public Object getMultipleChoiceFrameResult(String text, HashMap<String, ?> choices) throws InterruptedException
    {
        String[] keys = choices.keySet().toArray(new String[choices.size()]);
        ThreadedFrame frame = new ThreadedFrame(keys, text);
        Thread t = frame.getThread();
        t.start(); t.join();
        return choices.get(keys[frame.getComboBox().getSelectedIndex()]);
        
    }
    
    private RequestResponse processRequestThroughFrame(GameRequest request, HashMap<String, RequestResponse> results) throws InterruptedException
    {
        String[] keys = results.keySet().toArray(new String[results.size()]);
        String frameText = request.getRequestMaker().getPlayerName() + " está pedindo "  + request.toString() + ". Selecione uma ação.";
        ThreadedFrame frame = new ThreadedFrame(keys, frameText);
        Thread t = frame.getThread();
        t.start();
        t.join();
        return results.get(keys[frame.getComboBox().getSelectedIndex()]);
    }
    
    private RequestResponse processEnvidoRequest(GameRequest request) throws InterruptedException
    {
        HashMap<String, RequestResponse> results = new HashMap<>();
        results.put("Real envido", new RequestResponse(true, new EnvidoRequest(this)));
        results.put("Recusar", new RequestResponse(false));
        results.put("Aceitar", new RequestResponse(true));
        return processRequestThroughFrame(request, results);
    }
    
    private RequestResponse processTrucoRequest(GameRequest request) throws InterruptedException
    {
        HashMap<String, RequestResponse> results = new HashMap<>();
        results.put("Recusar", new RequestResponse(false));
        results.put("Aceitar", new RequestResponse(true));
        results.put("Aumentar", new RequestResponse(true, new TrucoRequest(this)));
        return processRequestThroughFrame(request, results);
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
           selected = getCardThroughFrame(cardsHash);
        } 
        catch (InterruptedException ex) {}
        playerCards.remove(selected);
        return selected;
    }
}
