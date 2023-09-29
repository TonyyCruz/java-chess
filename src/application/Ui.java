package application;

import chess.ChessPiece;

/**
 * This shows the chess game in the screen.
 */
public class Ui {

  public static void printBoard(ChessPiece[][] pieces) {
    for (int r = 0; r < pieces.length; r += 1) {
      System.out.printf("%d ", pieces.length - r);

      for (int c = 0; c < pieces.length; c += 1) {
        print(pieces[r][c]);
      }
      System.out.println();
    }
    System.out.println("  a b c d e f g h");
  }

  public static void print(ChessPiece piece) {
    if (piece == null) {
      System.out.print("-");
    } else {
      System.out.print(piece);
    }
    System.out.print(" ");
  }

}
