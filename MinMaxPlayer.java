import java.util.ArrayList;

public class MinMaxPlayer extends Player {
	private ArrayList<Integer[]> path;
    private double movepoints; // the points he collects 
    private int[] supplydistance; // is an array of 4 integers because holds the distance for supplies in the four directions 
    private int[] enemydistance; // is an array of 4 integers because holds the distance for supplies in the four directions 

	// constructors
    public MinMaxPlayer(){
        super();
        path = new ArrayList<Integer[]>();
        movepoints = 0;
        supplydistance = new int[4];
        enemydistance = new int[4];
    }
    
    public MinMaxPlayer(int playerId, String name, Board board, int score, int x, int y) {
    	super( playerId, name, board, score, x, y);
    	path = new ArrayList<Integer[]>();
        movepoints = 0;
        supplydistance = new int[4];
        enemydistance = new int[4];
    }
    
    public double evaluateT(int currentPos, int dice, int minosTile, int n) {
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
				movepoints += evaluateT( currentPos +  board.getN(), dice, minosTile, n) / (float)n; 
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
        		movepoints += evaluateT( currentPos + 1, dice, minosTile, n) / (float)n;
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
        		movepoints += evaluateT( currentPos -  board.getN(), dice, minosTile, n) / (float)n;
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
        		movepoints += evaluateT( currentPos - 1, dice, minosTile, n) / (float)n;
        	}
    		break;
    		
    	} 
    	return sum + movepoints;
    }
    
    public double evaluateM(int currentPos, int dice, int ThesseusTile, int n) {
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
    				supplydistance[dice] = n;
    			}
    		}
    		if( ThesseusTile == currentPos + board.getN()) {
    			sum += 10;
    			enemydistance[dice] = n;
    		}
        	n++;
			if( n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
				movepoints += evaluateM( currentPos +  board.getN(), dice, ThesseusTile, n) / (float)n; 
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
    				supplydistance[dice] = n;
    			}
    		}
    		if( ThesseusTile == currentPos + 1) {
    			sum += 10;
    			enemydistance[dice] = n;
    		}
        	n++;
			if( n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepoints += evaluateM( currentPos + 1, dice, ThesseusTile, n) / (float)n;
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
    				supplydistance[dice] = n;
    			}
    		}
    		if( ThesseusTile == currentPos - board.getN()) {
    			sum += 10;
    			enemydistance[dice] = n;
    		}
        	n++;
			if(n<4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepoints += evaluateM( currentPos -  board.getN(), dice, ThesseusTile, n) / (float)n;
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
    				supplydistance[dice] = n;
    			}
    		}
    		if( ThesseusTile == currentPos - 1) {
    			sum += 10;
    			enemydistance[dice] = n;
    		}
        	n++;
			if(n < 4) { // n can gets values from 1 to 3 cause the player can see only 3 tiles ahead from him
				
				/* divides the points that collects with n (tiles away from the player) so player gets 
				 * more points if the supply is near him and fewer if the supply is far away. After this
				 * we add the points in the movepoints.
				 */
        		movepoints += evaluateM( currentPos - 1, dice, ThesseusTile, n) / (float)n;
        	}
    		break;
    		
    	} 
    	return sum + movepoints;
    }
    
    public int chooseMinMaxMove(Node root, int currentPos, int opponentCurrentPos) {
    	double bestMinosValue=-100.0;
    	int bestMinosMove= -1;
    	
    	double bestValue=-100;
    	int bestMove= -1;
    	
    	for( int i=0; i< root.children.size(); i++) {
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
    	return bestMove;
	}
    
    public int[] getNextMove (int currentPos, int opponentCurrentPos) {
    	Node root = new Node();
    	createMySubTree( currentPos, opponentCurrentPos, root, 1);
    	chooseMinMaxMove( root, currentPos, opponentCurrentPos);
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
}
