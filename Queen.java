public class Queen extends Piece {

    public Queen(String name, String imageName, boolean isWhite, int pieceSize) {
        super(name, imageName, isWhite, pieceSize);
    }

    @Override
    public int[][] moveCheck(int xPosition, int yPosition, Board board) {
        reset();
        up(xPosition, yPosition, 10, board);
        down(xPosition, yPosition, 10, board);
        right(xPosition, yPosition, 10, board);
        left(xPosition, yPosition, 10, board);
        diagonalUpRight(xPosition, yPosition, 10, board);
        diagonalUpLeft(xPosition, yPosition, 10, board);
        diagonalDownRight(xPosition, yPosition, 10, board);
        diagonalDownLeft(xPosition, yPosition, 10, board);
        return this.validMoves;
    }
}