package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Queen chess piece.
 */
public class Queen extends ChessPiece {

  public Queen(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♕";
    } else {
      return "♛";
    }

  }
}
