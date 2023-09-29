package chess;

import boardgame.Board;

/**
 * This class is responsible for the chess game.
 */
public class ChessMatch {
  private Board board;

  public ChessMatch() {
    this.board = new Board(8, 8);
  }

  public ChessPiece[][] getPieces() {
    ChessPiece[][] mat = new ChessPiece[board.getColumns()][board.getRows()];

    for (int r = 0; r < board.getRows(); r += 1) {
      for (int c = 0; c < board.getColumns(); c += 1) {
        mat[r][c] = (ChessPiece) board.piece(r, c);
      }
    }

    return mat;
  }
}
