package application;

import chess.ChessMatch;

/**
 * This class is the main class. It's responsible all the user interaction and program execution.
 */
public class Program {

  public static void main(String[] args) {
    ChessMatch chessMatch = new ChessMatch();

    UI.printBoard(chessMatch.getPieces());
  }
}
