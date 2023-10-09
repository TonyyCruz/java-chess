package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Pawn chess piece.
 */
public class Pawn extends ChessPiece {

  public Pawn(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position();

    if (getColor() == Color.WHITE) {
      if (getMoveCount() == 0) {
        mat[getChessPosition().getRow()][getChessPosition().getColumn() - 1] = true;
        mat[getChessPosition().getRow()][getChessPosition().getColumn() - 2] = true;
      }
    }

    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♙";
    } else {
      return "♟︎";
    }

  }
}
