package application;

import chess.ChessMatch;

/**
 * This class is the main class. It's responsible all the user interaction and program execution.
 */
public class Program {

  /**
   * The main class.
   */
  public static void main(String[] args) {
    ChessMatch chessMatch = new ChessMatch();

    Ui.printBoard(chessMatch.getPieces());

    // System.out.println(new ChessPosition('h', 8));
  }
}
