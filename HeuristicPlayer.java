package erga1;
import erga1.Player;
import java.util.ArrayList;

public class HeuristicPlayer extends Player {
    private ArrayList<Integer[]> path;

    public HeuristicPlayer(){
        super();
        path = new ArrayList<Integer[]>();
    }

    public double evaluate(int currentPos, int dice, int minosTile){
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
                        supplypoints += 2;
                    }
                    if( !board.tiles[currentPos + board.getN()].isUp()){
                        if( board.supplies[i].getSupplyTileId() == currentPos + board.getN() * 2){
                            supplypoints += 1;
                        }
                    }
                }
                if( currentPos + board.getN() == minosTile){
                    minospoints += 5;
                }
                if( currentPos + 2 * board.getN() == minosTile) {
                    minospoints += 3;
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
                        supplypoints += 2;
                    }
                    if( !board.tiles[currentPos - board.getN()].isDown()){
                        if( board.supplies[i].getSupplyTileId() == currentPos - board.getN() * 2){
                            supplypoints += 1;
                        }
                    }
                }
                if( currentPos - board.getN() == minosTile){
                    minospoints += 5;
                }
                if( currentPos - 2 * board.getN() == minosTile) {
                    minospoints += 2;
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
    }
}
