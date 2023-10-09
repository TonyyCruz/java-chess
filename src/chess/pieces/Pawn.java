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

  /**
   * Check if the received position can be used.
   */
  private boolean canMove(Position p) {
    return getBoard().positionExists(p) && !getBoard().thereIsApiece(p);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p1 = new Position();
    Position p2 = new Position();

    if (getColor() == Color.WHITE) {
      p1.setValue(position.getRow() - 1, position.getColumn());
      p2.setValue(position.getRow() - 2, position.getColumn());


      if (canMove(p1)) {
        mat[p1.getRow()][p1.getColumn()] = true;
      }

      if (canMove(p1) && canMove(p2) && getMoveCount() == 0) {
        mat[p2.getRow()][p2.getColumn()] = true;
      }

      p1.setValue(position.getRow() - 1, position.getColumn() - 1);
      if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
        mat[p1.getRow()][p1.getColumn()] = true;
      }

      p1.setValue(position.getRow() - 1, position.getColumn() + 1);
      if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
        mat[p1.getRow()][p1.getColumn()] = true;
      }

    } else {
      p1.setValue(position.getRow() + 1, position.getColumn());
      p2.setValue(position.getRow() + 2, position.getColumn());


      if (canMove(p1)) {
        mat[p1.getRow()][p1.getColumn()] = true;
      }

      if (canMove(p1) && canMove(p2) && getMoveCount() == 0) {
        mat[p2.getRow()][p2.getColumn()] = true;
      }

      p1.setValue(position.getRow() + 1, position.getColumn() + 1);
      if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
        mat[p1.getRow()][p1.getColumn()] = true;
      }

      p1.setValue(position.getRow() + 1, position.getColumn() - 1);
      if (getBoard().positionExists(p1) && isThereOpponentPiece(p1)) {
        mat[p1.getRow()][p1.getColumn()] = true;
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
