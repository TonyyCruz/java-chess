package application;

import java.util.Scanner;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

/**
 * This class is the main class. It's responsible all the user interaction and program execution.
 */
public class Program {

  /**
   * The main class.
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ChessMatch chessMatch = new ChessMatch();

    while (true) {
      Ui.printBoard(chessMatch.getPieces());
      System.out.println();
      System.out.print("Source: ");
      ChessPosition source = Ui.readChessPosition(sc);

      System.out.println();
      System.out.print("Target: ");
      ChessPosition target = Ui.readChessPosition(sc);

      ChessPiece capturedPiece = chessMatch.performChessMovie(source, target);

    }


    // System.out.println(new ChessPosition('h', 8));
  }
}
