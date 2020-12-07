//import Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HeuristicPlayer extends Player {
    private ArrayList<Integer[]> path;
    private int n;
    private double movepoints;
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
    	double sum = 0;
    	switch( dice){
    	case 0:
    		if( board.tiles[currentPos].isUp()) {
    				sum -=2;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos + board.getN()) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos + board.getN()) {
    			sum -= 6;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos +  board.getN(), dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 1:
    		if( board.tiles[currentPos].isRight()) {
    			sum -=2;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos + 1) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos + 1) {
    			sum -= 6;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos + 1, dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 2:
    		if( board.tiles[currentPos].isDown()) {
    			sum -=2;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos - board.getN()) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos - board.getN()) {
    			sum -= 6;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos -  board.getN(), dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 3:
    		if( board.tiles[currentPos].isLeft()) {
    			sum -= 2;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos - 1) {
    				sum += 3;
    				supplydistance[dice] = n;
    			}
    		}
    		if( minosTile == currentPos - 1) {
    			sum -= 6;
    			minosdistance[dice] = n;
    		}
        	n++;
        	if( n<4) {
        		movepoints += evaluate( currentPos - 1, dice, minosTile, n) / (float)n;
        	}
    		break;
    		
    	} 
    	return sum + movepoints;
    }
    
    public int getNextMove(int currentPos, int minosTile) {
    	Map <String, Double> availableMoves = new HashMap<String, Double>();
    	
    	movepoints = 0;
    	availableMoves.put("Up", evaluate(currentPos, 0, minosTile, 1));
    	movepoints = 0;
    	availableMoves.put("Right", evaluate(currentPos, 1, minosTile, 1));
    	movepoints = 0;
    	availableMoves.put("Down", evaluate(currentPos, 2, minosTile, 1));
    	movepoints = 0;
    	availableMoves.put("Left", evaluate(currentPos, 3, minosTile, 1));
    	
    	//checks if the smae as last move
    	if( path.size()!=0) {
    		switch( path.get(path.size() -1)[0]) {
    		case 0:
    			availableMoves.replace("Down", availableMoves.get("Down"), availableMoves.get("Down") -1);
    			break;
    		case 1: 
    			availableMoves.replace("Left", availableMoves.get("Left"), availableMoves.get("Left") -1);
    			break;
    		case 2:
    			availableMoves.replace("Up", availableMoves.get("Up"), availableMoves.get("Up") -1);
    			break;
    		case 3:
    			availableMoves.replace("Right", availableMoves.get("Right"), availableMoves.get("Right") -1);
    			break;
    		}
    	}
    	
    	System.out.println( "~~~~~~Up value: " + availableMoves.get("Up"));
    	System.out.println( "~~~~~~Right value: " + availableMoves.get("Right"));
    	System.out.println( "~~~~~~Donw value: " + availableMoves.get("Down"));
    	System.out.println( "~~~~~~Left value: " + availableMoves.get("Left"));
    	
    	
    	
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
    		default:
    			path.add(new Integer[] { 3, supplydistance[3]==1 ? 1 : 0, supplydistance[3], minosdistance[3]});
				return 3;
    	}
    	
    }
    
    int[] move(int id, int minosTile) {
        
    	int bestMove = getNextMove( id, minosTile);
        switch (bestMove) {
            case 0:
                if (board.tiles[id].isUp()) {
                    System.out.println("Tries to move up, hits a wall.");
                } else {
                    x++;
                    tileid = x * board.getN() + y;
                    System.out.println("Moves up.");
                }
                break;
            case 1:
                if (board.tiles[id].isRight()) {
                    System.out.println("Tries to move right, hits a wall.");
                } else {
                    y++;
                    tileid = x * board.getN() + y;
                    System.out.println("Moves right.");
                }
                break;
            case 2:
                if (board.tiles[id].isDown()) {
                    System.out.println("Tries to move down, hits a wall.");
                } else {
                    x--;
                    tileid = x * board.getN() + y;
                    System.out.println("Moves down.");
                }
                break;
            case 3:
                if (board.tiles[id].isLeft()) {
                    System.out.println("Tries to move left, hits a wall.");
                } else {
                    y--;
                    tileid = x * board.getN() + y;
                    System.out.println("Moves left.");
                }
                break;
        }
        /*Checks if there is any supply on the tile where Thesseus is (with playerId = 1), and if there is any, 
         * increases the score by one and changes the supply coordinates and Supply Tile Id to -1 and in that way 
         * the supply is disappearing from the board*/
        int Sid=-1; //the id of the supply
        if (playerId == 1) {
            for (int i = 0; i < board.getS(); i++) {
                if (board.supplies[i].getSupplyTileId() == x * board.getN() + y) {
                    Sid = board.supplies[i].getSupplyId();
                    System.out.println("Collected supply No:" + Sid);
                    board.supplies[i].setX(-1);
                    board.supplies[i].setY(-1);
                    board.supplies[i].setSupplyTileId(-1);
                    score++;
                }
            }
        }
        int[] temp =  {x * board.getN() + y, x, y, Sid};
        return temp;
    }
    
    public void statistics() {
    	int timesUp=0, timesRight=0, timesDown=0, timesLeft=0, suppliesCollected=0;
    	for( int i=0; i< path.size(); i++ ) {
    		System.out.println( "\nRound: " +i);
    		switch( path.get(i)[0]) {
    			case 0: 
    				System.out.print("Player moved Up.");
    				timesUp++;
    				break;
    			case 1:
    				System.out.print("Player moved Right.");
    				timesRight++;
    				break;
    			case 2:
    				System.out.print("Player moved Down.");
    				timesDown++;
    				break;
    			case 3:
    				System.out.print("Player moved Left.");
    				timesLeft++;
    				break;
    		}
    		if( path.get(i)[1]==1) {
    			suppliesCollected++;
    			System.out.print(" He collected a supply. Supplies Collected: " + suppliesCollected + ".");
    		}
    		if( path.get(i)[2] == 0) {
    			System.out.print(" But he can't spot a supply.");
    		}
    		else {
    			System.out.print(" Closest supply is " + path.get(i)[2] + " tiles away.");
    		}
    		if( path.get(i)[3] == 0) {
    			System.out.print(" He can't spot Minotauros.");
    		}
    		else {
    			System.out.print(" Minotauros is " + path.get(i)[3] + " tiles away.");
    		}
    	}
    	System.out.println("\nTimes the player moved up: " + timesUp);
    	System.out.println("Times the player moved right: " + timesRight);
    	System.out.println("Times the player moved down: " + timesDown);
    	System.out.println("Times the player moved left: " + timesLeft);
    }


}
