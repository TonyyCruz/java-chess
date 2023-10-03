package application;

import java.util.InputMismatchException;
import java.util.Scanner;
import chess.ChessPiece;
import chess.ChessPosition;

/**
 * This shows the chess game in the screen.
 */
public class Ui {
  // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

  // https://stackoverflow.com/questions/2979383/how-to-clear-the-console-using-java
  public static void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  /**
   * This receive a string position from a scanner and return a chess position.
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
        print(pieces[r][c], false);
      }
      System.out.println();
    }
    System.out.println("  a b c d e f g h");
  }

  public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
    for (int i = 0; i < pieces.length; i += 1) {
      System.out.printf("%d ", pieces.length - i);

      for (int j = 0; j < pieces.length; j += 1) {
        print(pieces[i][j], possibleMoves[i][j]);
      }
      System.out.println();
    }
    System.out.println("  a b c d e f g h");
  }

  /**
   * This print the received piece.
   */
  public static void print(ChessPiece piece, boolean paintBackground) {
    if (paintBackground) {
      System.out.print(ANSI_GREEN_BACKGROUND);
    }
    if (piece == null) {
      System.out.print("-" + ANSI_RESET);
    } else {
      System.out.print(piece + ANSI_RESET);
    }
    System.out.print(" ");
  }

}
