/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package truco_cbr;

import truco_cbr.Player.Player;
import truco_cbr.Player.HumanPlayer;
import java.awt.Dialog;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Guto
 */
public class Truco_cbr 
{
    public static int pointsToWin = 24;
    public static String playerName1 = "Jogador 1";
    public static String playerName2 = "Jogador 2";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        Player p1 = new HumanPlayer(playerName1);
        Player p2 = new HumanPlayer(playerName2);
        TrucoGame game = new TrucoGame(p1, p2, pointsToWin);
        game.runGameLoop();
    }
}
