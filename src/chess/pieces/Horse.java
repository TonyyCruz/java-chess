package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Horse chess piece.
 */
public class Horse extends ChessPiece {

  public Horse(Board board, Color color) {
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
      return "♘";
    } else {
      return "♞";
    }

  }
}
