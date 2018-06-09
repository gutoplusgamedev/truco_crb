/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr.Requests;

/**
 *
 * @author Guto
 */
public class RequestResponse 
{
    private final boolean _accept;
    private final GameRequest _response;
    
    public RequestResponse(boolean acceptsRequest, GameRequest response)
    {
        _accept = acceptsRequest;
        _response = response; 
    }
    
    public RequestResponse(boolean acceptsRequest)
    {
        _accept = acceptsRequest;
        _response = null;
    }
    
    public boolean acceptsRequest()
    {
        return _accept;
    }
    
    public GameRequest getResponseRequest()
    {
        return _response;
    }
    
}
