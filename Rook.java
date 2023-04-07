public class Rook extends Piece {

    public Rook(String name, String imageName, boolean isWhite, int pieceSize) {
        super(name, imageName, isWhite, pieceSize);
    }

    @Override
    public int[][] moveCheck(int xPosition, int yPosition, Board board) {
        reset();
        up(xPosition, yPosition, 10, board);
        down(xPosition, yPosition, 10, board);
        right(xPosition, yPosition, 10, board);
        left(xPosition, yPosition, 10, board);
        return this.validMoves;
    }
}
