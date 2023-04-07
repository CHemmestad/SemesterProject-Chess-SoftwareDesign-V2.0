public class Pawn extends Piece {

    private int limit;
    private boolean firstMove = true;

    public Pawn(String name, String imageName, boolean isWhite, int pieceSize) {
        super(name, imageName, isWhite, pieceSize);
    }

    @Override
    public int[][] moveCheck(int xPosition, int yPosition, Board board) {
        reset();
        if(this.firstMove) {
            this.limit = 2;
        } else {
            this.limit = 1;
        }
        if(this.isWhite) {
            up(xPosition, yPosition, limit, board);
        } else {
            down(xPosition, yPosition, limit, board);
        }
        return this.validMoves;
    }

    public void setFirstFalse() {
        this.firstMove = false;
    }
}
