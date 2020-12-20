import java.util.ArrayList;

public class MinMaxPlayer extends Player {
	private ArrayList<Integer[]> path;
    private double movepointsT;// the points he collects
    private double movepointsM;
    private int[] supplydistance; // is an array of 4 integers because holds the distance for supplies in the four directions 
    private int[] enemydistance; // is an array of 4 integers because holds the distance for supplies in the four directions 

	// constructors
    public MinMaxPlayer(){
        super();
        path = new ArrayList<Integer[]>();
        movepointsT = 0;
        movepointsM = 0;
        supplydistance = new int[4];
        enemydistance = new int[4];
    }
    
    public MinMaxPlayer(int playerId, String name, Board board, int score, int x, int y) {
    	super( playerId, name, board, score, x, y);
    	path = new ArrayList<Integer[]>();
        movepointsT = 0;
        movepointsM = 0;
        supplydistance = new int[4];
        enemydistance = new int[4];
    }
    
    public double evaluateT(int currentPos, int dice, int minosTile, int n) {
    	if( n == 1) {
    		movepointsT = 0;
    	}
    	double sum = 0;
    	switch( dice){
    	case 0: // UP
    		if( board.tiles[currentPos].isUp() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isUp()) {
    				sum -=1;
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
    			enemydistance[dice] = n;
    		}
        	n++;
			if( n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
				movepointsT += evaluateT( currentPos +  board.getN(), dice, minosTile, n) / (float)n; 
			}
			break;
    	case 1: // RIGHT
    		if( board.tiles[currentPos].isRight() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isRight()) {
    			sum -=1;
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
    			enemydistance[dice] = n;
    		}
        	n++;
			if( n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepointsT += evaluateT( currentPos + 1, dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 2: //DOWN
    		if( board.tiles[currentPos].isDown() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isDown()) {
    			sum -=1;
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
    			enemydistance[dice] = n;
    		}
        	n++;
			if(n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepointsT += evaluateT( currentPos -  board.getN(), dice, minosTile, n) / (float)n;
        	}
    		break;
    	case 3: //LEFT
    		if( board.tiles[currentPos].isLeft() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isLeft()) {
    			sum -= 1;
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
    			enemydistance[dice] = n;
    		}
        	n++;
			if(n < 4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepointsT += evaluateT( currentPos - 1, dice, minosTile, n) / (float)n;
        	}
    		break;
    		
    	} 
    	return sum + movepointsT;
    }
    
    public double evaluateM(int currentPos, int dice, int ThesseusTile, int n) {
    	if( n ==1) {
    		movepointsM = 0;
    	}
    	double sum = 0;
    	switch( dice){
    	case 0: // UP
    		if( board.tiles[currentPos].isUp() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isUp()) {
    				sum -=1;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos + board.getN()) {
    				sum += 1;
    			}
    		}
    		if( ThesseusTile == currentPos + board.getN()) {
    			sum += 10;
    		}
        	n++;
			if( n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
				movepointsM += evaluateM( currentPos +  board.getN(), dice, ThesseusTile, n) / (float)n; 
			}
			break;
    	case 1: // RIGHT
    		if( board.tiles[currentPos].isRight() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isRight()) {
    			sum -=1;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos + 1) {
    				sum += 1;
    			}
    		}
    		if( ThesseusTile == currentPos + 1) {
    			sum += 10;
    		}
        	n++;
			if( n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepointsM += evaluateM( currentPos + 1, dice, ThesseusTile, n) / (float)n;
        	}
    		break;
    	case 2: //DOWN
    		if( board.tiles[currentPos].isDown() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isDown()) {
    			sum -=1;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos - board.getN()) {
    				sum += 1;
    			}
    		}
    		if( ThesseusTile == currentPos - board.getN()) {
    			sum += 10;
    		}
        	n++;
			if(n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepointsM += evaluateM( currentPos -  board.getN(), dice, ThesseusTile, n) / (float)n;
        	}
    		break;
    	case 3: //LEFT
    		if( board.tiles[currentPos].isLeft() && n==1) {
    			return 420;
    		}
    		if( board.tiles[currentPos].isLeft()) {
    			sum -= 1;
    			break;
    		}
    		for( int i=0; i<board.getS(); i++) {
    			if( board.supplies[i].getSupplyTileId() == currentPos - 1) {
    				sum += 1;
    			}
    		}
    		if( ThesseusTile == currentPos - 1) {
    			sum += 10;
    		}
        	n++;
			if(n < 4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepointsM += evaluateM( currentPos - 1, dice, ThesseusTile, n) / (float)n;
        	}
    		break;
    		
    	} 
    	return sum + movepointsM;
    }
    
    public int chooseMinMaxMove(Node root, int currentPos, int opponentCurrentPos) {
    	double bestMinosValue=Double.NEGATIVE_INFINITY;
    	int bestMinosMove= -1;
    	
    	double bestValue=Double.NEGATIVE_INFINITY;
    	int bestMove= -1;
    	
    	for( int i=0; i< root.children.size(); i++) {
    		bestMinosValue = Double.NEGATIVE_INFINITY;
    		bestMinosMove = -1;
    		for( int j=0; j< root.children.get(i).children.size(); j++) {
    			if( root.children.get(i).children.get(j).getNodeEvaluation() > bestMinosValue) {
    				bestMinosValue = root.children.get(i).children.get(j).getNodeEvaluation();
    				bestMinosMove = root.children.get(i).children.get(j).getNodeMove()[2];
    			}
    		}
    		switch( bestMinosMove) {
    		case 0:
    			root.children.get(i).setNodeEvaluation(evaluateT( currentPos, root.children.get(i).getNodeMove()[2], opponentCurrentPos + board.getN(), 1));
    			break;
    		case 1:
    			root.children.get(i).setNodeEvaluation(evaluateT( currentPos, root.children.get(i).getNodeMove()[2], opponentCurrentPos + 1, 1));
    			break;
    		case 2:
    			root.children.get(i).setNodeEvaluation(evaluateT( currentPos, root.children.get(i).getNodeMove()[2], opponentCurrentPos - board.getN(), 1));
    			break;
    		case 3:
    			root.children.get(i).setNodeEvaluation(evaluateT( currentPos, root.children.get(i).getNodeMove()[2], opponentCurrentPos - 1, 1));
    			break;
    		}
    		
    		if( root.children.get(i).getNodeEvaluation() > bestValue) {
    			bestValue = root.children.get(i).getNodeEvaluation();
    			bestMove= root.children.get(i).getNodeMove()[2];
    		}
    	}
    	System.out.println("Best T: " + bestMove + " Best M: " + bestMinosMove);
    	return bestMove;
	}
    
    public int[] getNextMove (int currentPos, int opponentCurrentPos) {
    	Node root = new Node();
    	createMySubTree( currentPos, opponentCurrentPos, root, 1);
    	int bestMove = chooseMinMaxMove( root, currentPos, opponentCurrentPos);
    	
    	
    	
        switch (bestMove) {
        case 0:
            if (board.tiles[currentPos].isUp()) {
                System.out.println("Tries to move up, hits a wall.");
            } else {
                x++;
                tileid = x * board.getN() + y;
                System.out.println("Moves up.");
            }
            path.add(new Integer[] { 0, supplydistance[0]==1 ? 1 : 0, supplydistance[0], enemydistance[0]});
            clearArray();
            break;
        case 1:
            if (board.tiles[currentPos].isRight()) {
                System.out.println("Tries to move right, hits a wall.");
            } else {
                y++;
                tileid = x * board.getN() + y;
                System.out.println("Moves right.");
            }
            path.add(new Integer[] { 1, supplydistance[1]==1 ? 1 : 0, supplydistance[1], enemydistance[1]});
            clearArray();
            break;
        case 2:
            if (board.tiles[currentPos].isDown()) {
                System.out.println("Tries to move down, hits a wall.");
            } else {
                x--;
                tileid = x * board.getN() + y;
                System.out.println("Moves down.");
            }
            path.add(new Integer[] { 2, supplydistance[2]==1 ? 1 : 0, supplydistance[2], enemydistance[2]});
            clearArray();
            break;
        case 3:
            if (board.tiles[currentPos].isLeft()) {
                System.out.println("Tries to move left, hits a wall.");
            } else {
                y--;
                tileid = x * board.getN() + y;
                System.out.println("Moves left.");
            }
            path.add(new Integer[] { 3, supplydistance[3]==1 ? 1 : 0, supplydistance[3], enemydistance[3]});
            clearArray();
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

    
	public void createMySubTree(int currentPos, int opponentCurrentPos, Node root, int depth) {
		
		double temp = evaluateT(currentPos, 0, opponentCurrentPos, 1);
		if( temp != 420.0) {
			Node up = new Node(root, board, x, y + 1, 0);
			//up.setNodeEvaluation(temp);
			root.children.add(up);
			createOpponentSubtree( currentPos, opponentCurrentPos, up, depth+1, temp);
		}

		temp = evaluateT(currentPos, 1, opponentCurrentPos, 1);
		if( temp != 420.0) {
			Node right = new Node(root, board, x + 1, y, 1);
			//right.setNodeEvaluation(temp);
			root.children.add(right);
			createOpponentSubtree( currentPos, opponentCurrentPos, right, depth+1, temp);
		}

		temp = evaluateT(currentPos, 2, opponentCurrentPos, 1);
		if( temp != 420.0) {
			Node down = new Node(root, board, x, y - 1, 2);
			//down.setNodeEvaluation(temp);
			root.children.add(down);
			createOpponentSubtree( currentPos, opponentCurrentPos, down, depth+1, temp);
		}
		
		temp = evaluateT(currentPos, 3, opponentCurrentPos, 1);
		if( temp != 420.0) {
			Node left = new Node(root, board, x - 1, y, 3);
			//left.setNodeEvaluation(temp);
			root.children.add(left);
			createOpponentSubtree( currentPos, opponentCurrentPos, left, depth+1, temp);
		}
		
	}
	
	public void createOpponentSubtree(int currentPos, int opponentCurrentPos, Node parent, int depth, double parentEval) {
		
		double temp;
		switch( parent.getNodeMove()[2]) {
		case 0:
			temp = evaluateM(opponentCurrentPos, 0, currentPos + board.getN(), 1);
			if( temp != 420.0) {
				Node up = new Node(parent, board, x, y + 1, 0);
				up.setNodeEvaluation(temp);
				parent.children.add(up);
			}
			
			temp = evaluateM(opponentCurrentPos, 1, currentPos + board.getN(), 1);
			if( temp != 420.0) {
				Node right = new Node(parent, board, x + 1, y, 1);
				right.setNodeEvaluation( temp);
				parent.children.add(right);
			}
			
			temp = evaluateM(opponentCurrentPos, 2, currentPos + board.getN(), 1);
			if( temp != 420.0) {
				Node down = new Node(parent, board, x, y - 1, 2);
				down.setNodeEvaluation(temp);
				parent.children.add(down);
			}
			
			temp = evaluateM(opponentCurrentPos, 3, currentPos + board.getN(), 1);
			if( temp != 420.0) {
				Node left = new Node(parent, board, x - 1, y, 3);
				left.setNodeEvaluation(temp);
				parent.children.add(left);
			}
			
			break;
		case 1:
			temp = evaluateM(opponentCurrentPos, 0, currentPos + 1, 1);
			if( temp != 420.0) {
				Node up = new Node(parent, board, x, y + 1, 0);
				up.setNodeEvaluation(temp);
				parent.children.add(up);
			}
			
			temp = evaluateM(opponentCurrentPos, 1, currentPos + 1, 1);
			if( temp != 420.0) {
				Node right = new Node(parent, board, x + 1, y, 1);
				right.setNodeEvaluation( temp);
				parent.children.add(right);
			}
			
			temp = evaluateM(opponentCurrentPos, 2, currentPos + 1, 1);
			if( temp != 420.0) {
				Node down = new Node(parent, board, x, y - 1, 2);
				down.setNodeEvaluation(temp);
				parent.children.add(down);
			}
			
			temp = evaluateM(opponentCurrentPos, 3, currentPos + 1, 1);
			if( temp != 420.0) {
				Node left = new Node(parent, board, x - 1, y, 3);
				left.setNodeEvaluation(temp);
				parent.children.add(left);
			}
			
			break;
		case 2:
			temp = evaluateM(opponentCurrentPos, 0, currentPos - board.getN(), 1);
			if( temp != 420.0) {
				Node up = new Node(parent, board, x, y + 1, 0);
				up.setNodeEvaluation(temp);
				parent.children.add(up);
			}
			
			temp = evaluateM(opponentCurrentPos, 1, currentPos - board.getN(), 1);
			if( temp != 420.0) {
				Node right = new Node(parent, board, x + 1, y, 1);
				right.setNodeEvaluation( temp);
				parent.children.add(right);
			}
			
			temp = evaluateM(opponentCurrentPos, 2, currentPos - board.getN(), 1);
			if( temp != 420.0) {
				Node down = new Node(parent, board, x, y - 1, 2);
				down.setNodeEvaluation(temp);
				parent.children.add(down);
			}
			
			temp = evaluateM(opponentCurrentPos, 3, currentPos - board.getN(), 1);
			if( temp != 420.0) {
				Node left = new Node(parent, board, x - 1, y, 3);
				left.setNodeEvaluation(temp);
				parent.children.add(left);
			}
			
			break;
		case 3:
			temp = evaluateM(opponentCurrentPos, 0, currentPos - 1, 1);
			if( temp != 420.0) {
				Node up = new Node(parent, board, x, y + 1, 0);
				up.setNodeEvaluation(temp);
				parent.children.add(up);
			}
			
			temp = evaluateM(opponentCurrentPos, 1, currentPos - 1, 1);
			if( temp != 420.0) {
				Node right = new Node(parent, board, x + 1, y, 1);
				right.setNodeEvaluation( temp);
				parent.children.add(right);
			}
			
			temp = evaluateM(opponentCurrentPos, 2, currentPos - 1, 1);
			if( temp != 420.0) {
				Node down = new Node(parent, board, x, y - 1, 2);
				down.setNodeEvaluation(temp);
				parent.children.add(down);
			}
			
			temp = evaluateM(opponentCurrentPos, 3, currentPos - 1, 1);
			if( temp != 420.0) {
				Node left = new Node(parent, board, x - 1, y, 3);
				left.setNodeEvaluation(temp);
				parent.children.add(left);
			}
			
			break;
		}
	}
	
	/*
	 * @description	when the game finishes, print a resume of the player's moves that contains
	 * 				the direction of the move, if he collects a supply, the distance for supplies or the Minotaurus
	 * 				and the totals supplies he collects, all that for every round
	 */
	
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
    		if( path.get(i)[1] == 1) {
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
    
	/*
	 * @description clears the arrays supplydistance and minosdistance from previous values
	 */
	public void clearArray() {
		for(int i = 0; i < 4; i++) {
			supplydistance[i] = 0;
			enemydistance[i] = 0;
		}
	}

}
