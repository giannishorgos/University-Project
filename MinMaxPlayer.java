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
    		if( board.tiles[currentPos].isUp()) {
    				sum -=2;
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
    		if( board.tiles[currentPos].isRight()) {
    			sum -=2;
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
    		if( board.tiles[currentPos].isDown()) {
    			sum -=2;
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
    		if( board.tiles[currentPos].isLeft()) {
    			sum -= 2;
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
    
    public int chooseMinMaxMove(Node root) {
	return 5;
	}

	public void createMySubTree(int currentPos, int opponentCurrentPos, Node root, int depth) {
		Node up = new Node(root, board, x, y + 1, 0);
		up.setNodeEvaluation(evaluateT(currentPos, 0, opponentCurrentPos, 1));

		Node right = new Node(root, board, x + 1, y, 1);
		up.setNodeEvaluation(evaluateT(currentPos, 1, opponentCurrentPos, 1));

		Node down = new Node(root, board, x, y - 1, 2);
		up.setNodeEvaluation(evaluateT(currentPos, 2, opponentCurrentPos, 1));

		Node left = new Node(root, board, x - 1, y, 3);
		up.setNodeEvaluation(evaluateT(currentPos, 3, opponentCurrentPos, 1));

		root.children.add(up);
		root.children.add(right);
		root.children.add(down);
		root.children.add(left);
	}
}
