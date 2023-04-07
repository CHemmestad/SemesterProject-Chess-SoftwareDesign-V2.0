public class Board {

    private Piece piece[][] = new Piece[8][8];
    private boolean gameOver = false;
    private boolean playerInCheck = false;

    public Board() {
        initialize();
    }

    public void initialize() {
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                piece[x][y] = null;
            }
        }
    }

    public Piece getPiece(int xPosition, int yPosition) {
        return piece[xPosition][yPosition];
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isPlayerChecked() {
        return playerInCheck;
    }

    public void movePiece(Piece piece, int x, int y) {
        System.out.println("Doing the thing 2");
        if(piece.getPieceType() == 1) {
            ((Pawn)piece).setFirstFalse();
        } else {
            
        }
        System.out.println("Doing the thing 3");
        removePiece(piece.getPosX(), piece.getPosY());
        piece.setPosX(x);
        piece.setPosY(y);
        this.piece[x][y] = piece;
    }

    public void addPiece(Piece piece, int x, int y) {
        piece.setPosX(x);
        piece.setPosY(y);
        this.piece[x][y] = piece;
    }

    public void removePiece(int x, int y) {
        this.piece[x][y] = null;
    }

    public void killPiece(Piece piece) {
        this.piece[piece.getPosX()][piece.getPosY()] = null;
    }

    public void checkGameStatus() {
        boolean WKingFound = false;
        boolean BKingFound = false;
        for(int y = 0; y < 8; y++) {
            for(int x = 0; x < 8; x++) {
                Piece current = this.piece[x][y];
                if(current != null) {
                    if(current.getPieceType() == 5) {
                        if(current.isWhite) {
                            WKingFound = true;
                            System.out.println("White king found");
                        } else {
                            BKingFound = true;
                            System.out.println("Black king found");
                        }
                        if(((King)current).inCheck()) {
                            playerInCheck = true;
                            System.out.println("King is in check");
                        }
                    }
                }
            }
        }
        if(!WKingFound || !BKingFound) {
            gameOver();
        }
    }

    private void gameOver() {
        this.gameOver = true;
    }
}
