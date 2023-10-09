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
    Position position1 = new Position();
    Position position2 = new Position();

    if (getColor() == Color.WHITE) {
      position1.setValue(position.getRow() - 1, position.getColumn());
      position2.setValue(position.getRow() - 2, position.getColumn());


      if (canMoveThere(position1)) {
        mat[position1.getRow()][position1.getColumn()] = true;
      }

      if (canMoveThere(position1) && canMoveThere(position2) && getMoveCount() == 0) {
        mat[position2.getRow()][position2.getColumn()] = true;
      }

      position1.setValue(position.getRow() - 1, position.getColumn() - 1);
      if (getBoard().positionExists(position1) && isThereOpponentPiece(position1)) {
        mat[position1.getRow()][position1.getColumn()] = true;
      }

      position1.setValue(position.getRow() - 1, position.getColumn() + 1);
      if (getBoard().positionExists(position1) && isThereOpponentPiece(position1)) {
        mat[position1.getRow()][position1.getColumn()] = true;
      }

    } else {
      position1.setValue(position.getRow() + 1, position.getColumn());
      position2.setValue(position.getRow() + 2, position.getColumn());


      if (canMoveThere(position1)) {
        mat[position1.getRow()][position1.getColumn()] = true;
      }

      if (canMoveThere(position1) && canMoveThere(position2) && getMoveCount() == 0) {
        mat[position2.getRow()][position2.getColumn()] = true;
      }

      position1.setValue(position.getRow() + 1, position.getColumn() + 1);
      if (getBoard().positionExists(position1) && isThereOpponentPiece(position1)) {
        mat[position1.getRow()][position1.getColumn()] = true;
      }

      position1.setValue(position.getRow() + 1, position.getColumn() - 1);
      if (getBoard().positionExists(position1) && isThereOpponentPiece(position1)) {
        mat[position1.getRow()][position1.getColumn()] = true;
      }
    }

    return mat;
  }

  private boolean canMoveThere(Position p) {
    return getBoard().positionExists(p) && !getBoard().thereIsApiece(p);
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
