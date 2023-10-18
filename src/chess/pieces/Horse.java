package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Horse chess piece.
 */
public class Horse extends ChessPiece {

  public Horse(Board board, Color color) {
    super(board, color);
  }

  protected boolean canMoveThere(Position position) {
    if (!getBoard().positionExists(position)) {
      return false;
    }
    ChessPiece pieceInThePosition = (ChessPiece) getBoard().piece(position);
    return pieceInThePosition == null || isThereOpponentPiece(position);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position upLeft = new Position(position.getRow() - 2, position.getColumn() - 1);
    if (canMoveThere(upLeft)) {
      matrix[upLeft.getRow()][upLeft.getColumn()] = true;
    }
    Position upRight = new Position(position.getRow() - 2, position.getColumn() + 1);
    if (canMoveThere(upRight)) {
      matrix[upRight.getRow()][upRight.getColumn()] = true;
    }
    Position downLeft = new Position(position.getRow() + 2, position.getColumn() - 1);
    if (canMoveThere(downLeft)) {
      matrix[downLeft.getRow()][downLeft.getColumn()] = true;
    }
    Position downRight = new Position(position.getRow() + 2, position.getColumn() + 1);
    if (canMoveThere(downRight)) {
      matrix[downRight.getRow()][downRight.getColumn()] = true;
    }
    Position leftUp = new Position(position.getRow() - 1, position.getColumn() - 2);
    if (canMoveThere(leftUp)) {
      matrix[leftUp.getRow()][leftUp.getColumn()] = true;
    }
    Position rightUp = new Position(position.getRow() - 1, position.getColumn() + 2);
    if (canMoveThere(rightUp)) {
      matrix[rightUp.getRow()][rightUp.getColumn()] = true;
    }
    Position leftDown = new Position(position.getRow() + 1, position.getColumn() - 2);
    if (canMoveThere(leftDown)) {
      matrix[leftDown.getRow()][leftDown.getColumn()] = true;
    }
    Position rightDown = new Position(position.getRow() + 1, position.getColumn() + 2);
    if (canMoveThere(rightDown)) {
      matrix[rightDown.getRow()][rightDown.getColumn()] = true;
    }
    return matrix;
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
