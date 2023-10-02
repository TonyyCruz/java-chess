package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import chess.ChessPiece;
import chess.ChessPosition;

/**
 * This shows the chess game in the screen.
 */
public class Ui {

  /**
   * This receive a string with a chess position, separates the position into 'char' and 'int' and
   * returns the stance of a ChessPosition.
   */
  public static ChessPosition readChessPosition(Scanner sc) {
    try {
      String in = sc.next();

      char column = in.charAt(0);
      int row = Integer.parseInt(in.substring(1));

      return new ChessPosition(column, row);

    } catch (RuntimeException e) {
      throw new InputMismatchException("Position error: The position need be from 'a1' to 'h8'.");
    }
  }

  /**
   * This print a board with the current state of the game.
   */
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

  /**
   * This print the received piece.
   */
  public static void print(ChessPiece piece) {
    if (piece == null) {
      System.out.print("-");
    } else {
      System.out.print(piece);
    }
    System.out.print(" ");
  }

}
