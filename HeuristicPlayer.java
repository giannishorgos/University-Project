//import Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeuristicPlayer extends Player {
    private ArrayList<Integer[]> path;
    private int n;
    private float movepoints;
    private int[] supplydistance;
    private int[] minosdistance;

    public HeuristicPlayer(){
        super();
        path = new ArrayList<Integer[]>();
        movepoints = 0;
        supplydistance = new int[4];
        minosdistance = new int[4];
    }
    
    public HeuristicPlayer(int playerId, String name, Board board, int score, int x, int y) {
    	super( playerId, name, board, score, x, y);
    	path = new ArrayList<Integer[]>();
        movepoints = 0;
        supplydistance = new int[4];
        minosdistance = new int[4];
    }
    
    public double evaluate(int currentPos, int dice, int minosTile, int n) {
    	int sum = 0;
    	switch( dice){
    	case 0:
    		if( board.tiles[currentPos].isUp()) {
    			sum -=10;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos + board.getN()) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos + board.getN()) {
    			sum -= 5;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos + (n-1) * board.getN(), dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 1:
    		if( board.tiles[currentPos].isRight()) {
    			sum -= 10;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos + 1) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos + 1) {
    			sum -= 5;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos + (n-1), dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 2:
    		if( board.tiles[currentPos].isDown()) {
    			sum -=10;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos - board.getN()) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos - board.getN()) {
    			sum -= 5;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos - (n-1) * board.getN(), dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 3:
    		if( board.tiles[currentPos].isLeft()) {
    			sum -= 10;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos - 1) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos - 1) {
    			sum -= 5;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos - (n-1), dice, minosTile, n) / (float)n;
        	}
    		break;
    		
    	}
    	return sum;
    }
    
    public int getNextMove(int currentPos, int minosTile) {
    	Map <String, Double> availableMoves = new HashMap<String, Double>();
    	
    	availableMoves.put("Up", evaluate(currentPos, 0, minosTile, 1));
    	availableMoves.put("Right", evaluate(currentPos, 1, minosTile, 1));
    	availableMoves.put("Down", evaluate(currentPos, 2, minosTile, 1));
    	availableMoves.put("Left", evaluate(currentPos, 3, minosTile, 1));
    	
    	double max=-99999;
    	String key = "";
    	for( String srt: availableMoves.keySet()) {
    		if( availableMoves.get(srt) > max) {
    			max = availableMoves.get(srt);
    			key = srt;
    		}
    	}
    	
    	switch( key){
    		case "Up":
    			path.add(new Integer[] { 0, supplydistance[0]==1 ? 1 : 0, supplydistance[0], minosdistance[0]});
    			return 0;
    		case "Right":
    			path.add(new Integer[] { 1, supplydistance[1]==1 ? 1 : 0, supplydistance[1], minosdistance[1]});
    			return 1;
    		case "Down":
    			path.add(new Integer[] { 2, supplydistance[2]==1 ? 1 : 0, supplydistance[2], minosdistance[2]});
    			return 2;
    		case "Left":
    			path.add(new Integer[] { 3, supplydistance[3]==1 ? 1 : 0, supplydistance[3], minosdistance[3]});
				return 3;
    	}
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    

    /*public double evaluate(int currentPos, int dice, int minosTile){
        int supplypoints = 0;
        int minospoints = 0;
        int wallpoints = 0;
        switch( dice){
            case 0:
                if( board.tiles[currentPos].isUp()){
                    wallpoints += 10;
                    break;
                }
                for( int i = 0; i < board.getS(); i++){
                    if( board.supplies[i].getSupplyTileId() == currentPos + board.getN()){
                        supplypoints += 3;
                    }
                    if( !board.tiles[currentPos + board.getN()].isUp()){
                        if( board.supplies[i].getSupplyTileId() == currentPos + 2 * board.getN()){
                            supplypoints += 2;
                        }
                        if(!board.tiles[currentPos + 2 * board.getN()].isUp()) {
                        	if( board.supplies[i].getSupplyTileId() == currentPos + 2 * board.getN()){
                                supplypoints += 1;
                            }
                        }
                    }
                }
                if( currentPos + board.getN() == minosTile){
                    minospoints += 5;
                }
                if(!board.tiles[currentPos + board.getN()].isUp()) {
                	if( currentPos + 2 * board.getN() == minosTile) {
                		minospoints += 3;
                	}
                	if( !board.tiles[currentPos + 2 * board.getN()].isUp()) {
                		if( currentPos + 2 * board.getN() == minosTile) {
                    		minospoints += 1;
                    	}
                	}
                }
            break;

            case 1:
                if( board.tiles[currentPos].isRight()){
                    wallpoints +=10;
                    break;
                }
                for( int i = 0; i < board.getS(); i++){
                    if( board.supplies[i].getSupplyTileId() == currentPos + 1){
                        supplypoints += 2;
                    }
                    if( !board.tiles[currentPos + 1].isRight()){
                        if( board.supplies[i].getSupplyTileId() == currentPos + 2){
                            supplypoints += 1;
                        }
                    }
                }
                if( currentPos + 1 == minosTile){
                    minospoints += 5;
                }
                if( currentPos + 2 == minosTile) {
                    minospoints += 3;
                }
                break;

            case 2:
            	if( board.tiles[currentPos].isDown()){
                    wallpoints += 10;
                    break;
                }
                for( int i = 0; i < board.getS(); i++){
                    if( board.supplies[i].getSupplyTileId() == currentPos - board.getN()){
                        supplypoints += 3;
                    }
                    if( !board.tiles[currentPos - board.getN()].isUp()){
                        if( board.supplies[i].getSupplyTileId() == currentPos - 2 * board.getN()){
                            supplypoints += 2;
                        }
                        if(!board.tiles[currentPos - 2 * board.getN()].isUp()) {
                        	if( board.supplies[i].getSupplyTileId() == currentPos - 2 * board.getN()){
                                supplypoints += 1;
                            }
                        }
                    }
                }
                if( currentPos - board.getN() == minosTile){
                    minospoints += 5;
                }
                if(!board.tiles[currentPos - board.getN()].isUp()) {
                	if( currentPos - 2 * board.getN() == minosTile) {
                		minospoints += 3;
                	}
                	if( !board.tiles[currentPos - 2 * board.getN()].isUp()) {
                		if( currentPos - 2 * board.getN() == minosTile) {
                    		minospoints += 1;
                    	}
                	}
                }
            break;

            case 3:
                if( board.tiles[currentPos].isLeft()){
                    wallpoints += 10;
                    break;
                }
                for( int i = 0; i < board.getS(); i++){
                    if( board.supplies[i].getSupplyTileId() == currentPos - 1){
                        supplypoints += 2;
                    }
                    if( !board.tiles[currentPos - 1].isLeft()){
                        if( board.supplies[i].getSupplyTileId() == currentPos - 2){
                            supplypoints += 1;
                        }
                    }
                }
                if( currentPos - 1 == minosTile){
                    minospoints += 5;
                }
                if( currentPos - 2 == minosTile) {
                    minospoints += 3;
                }
                break;
        }
        return 3.5*supplypoints - 6.0*minospoints - 1000.0*wallpoints;
    }*/
    
    
}
