import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class ChessGame {
    
    public static Piece selectedPiece;

    public static void main(String[] Args) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        int screenHeight = (int)screenSize.getHeight()-(int)screenSize.getHeight()%10;
        int screenWidth = (int)screenSize.getWidth()-(int)screenSize.getWidth()%10;
        int sideX = screenHeight-screenHeight/5;
        int sideY = sideX;
        int pieceSize = sideX/8;

        JFrame frame = new JFrame();
        frame.setBounds((screenWidth-sideX)/2, (screenHeight-sideY)/2, sideX, sideY);
        frame.setUndecorated(true);

        Board chessBoard = new Board();
        //White pieces
        for(int x = 0; x < 8; x++) {
            chessBoard.addPiece(new Pawn("pawn", "Images/PawnWnew.png", true, pieceSize), x, 6);
        }
        chessBoard.addPiece(new Rook("rook", "Images/RookWnew.png", true, pieceSize), 0, 7);
        chessBoard.addPiece(new Rook("rook", "Images/RookWnew.png", true, pieceSize), 7, 7);
        chessBoard.addPiece(new Knight("knight", "Images/KnightWnew.png", true, pieceSize), 1, 7);
        chessBoard.addPiece(new Knight("knight", "Images/KnightWnew.png", true, pieceSize), 6, 7);
        chessBoard.addPiece(new Bishop("bishop", "Images/BishopWnew.png", true, pieceSize), 2, 7);
        chessBoard.addPiece(new Bishop("bishop", "Images/BishopWnew.png", true, pieceSize), 5, 7);
        chessBoard.addPiece(new King("king", "Images/KingWnew.png", true, pieceSize), 3, 7);
        chessBoard.addPiece(new Queen("queen", "Images/QueenWnew.png", true, pieceSize), 4, 7);

        //Black pieces
        for(int x = 0; x < 8; x++) {
            chessBoard.addPiece(new Pawn("pawn", "Images/PawnBnew.png", false, pieceSize), x, 1);
        }
        chessBoard.addPiece(new Rook("rook", "Images/RookBnew.png", false, pieceSize), 0, 0);
        chessBoard.addPiece(new Rook("rook", "Images/RookBnew.png", false, pieceSize), 7, 0);
        chessBoard.addPiece(new Knight("knight", "Images/KnightBnew.png", false, pieceSize), 1, 0);
        chessBoard.addPiece(new Knight("knight", "Images/KnightBnew.png", false, pieceSize), 6, 0);
        chessBoard.addPiece(new Bishop("bishop", "Images/BishopBnew.png", false, pieceSize), 2, 0);
        chessBoard.addPiece(new Bishop("bishop", "Images/BishopBnew.png", false, pieceSize), 5, 0);
        chessBoard.addPiece(new King("king", "Images/KingBnew.png", false, pieceSize), 3, 0);
        chessBoard.addPiece(new Queen("queen", "Images/QueenBnew.png", false, pieceSize), 4, 0);

        
        JPanel panel = new JPanel()
        {
            @Override
            public void paint(Graphics g) {
                boolean white = true;
                for ( int y = 0 ; y < 8 ; y++ ){
                    for ( int x = 0 ; x < 8 ; x++ ){
                        if (white) {
                            //HSB COLOR PEN https://codepen.io/HunorMarton/full/eWvewo
                            g.setColor(Color.white);
                            //g.setColor(new Color(x, y, x));
                        } else {
                            g.setColor(Color.black);
                        }
                        g.fillRect(x*pieceSize, y*pieceSize, pieceSize, pieceSize);
                        white =! white;
                        if(selectedPiece != null) {
                            if(selectedPiece.validMoves[x][y] == -1) {
                                g.setColor(Color.blue);
                                g.fillRect(x*pieceSize, y*pieceSize, pieceSize, pieceSize);
                            }
                            if(selectedPiece.validMoves[x][y] == 2) {
                                g.setColor(Color.red);
                                g.fillRect(x*pieceSize, y*pieceSize, pieceSize, pieceSize);
                            }
                        }
                        Piece current = chessBoard.getPiece(x, y);
                        if(current != null) {
                            g.drawImage(current.getImage()[0], current.getPosX()*pieceSize, current.getPosY()*pieceSize, this);
                        }
                    }
                    white =! white; 
                }
            }
        };
        frame.add(panel);

        frame.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int xPos = e.getX()/pieceSize;
                System.out.print("x pos"); System.out.println(xPos);
                int yPos = e.getY()/pieceSize;
                System.out.print("y pos"); System.out.println(yPos);
                if(selectedPiece != null) {
                    if(selectedPiece.validMoves[xPos][yPos] == -1) {
                        System.out.println("Doing the thing");
                        chessBoard.movePiece(selectedPiece, xPos, yPos);
                    }
                }
                if(chessBoard.getPiece(xPos, yPos) != null) {
                    if(selectedPiece != chessBoard.getPiece(xPos, yPos)) {
                        System.out.println("getting piece");
                        selectedPiece = chessBoard.getPiece(xPos, yPos);
                        System.out.println(selectedPiece.getPieceType());
                        System.out.println("move checking");
                        selectedPiece.moveCheck(xPos, yPos, chessBoard);
                    } else {
                        selectedPiece = null;
                    }
                } else {
                    selectedPiece = null;
                    chessBoard.addPiece(new Queen("queen", "Images/QueenWnew.png", true, pieceSize), xPos, yPos);
                }
                chessBoard.checkGameStatus();
                frame.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
            
        });
        frame.setDefaultCloseOperation(0);
        frame.setVisible(true);
    }
}