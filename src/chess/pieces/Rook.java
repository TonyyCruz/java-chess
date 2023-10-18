package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

/**
 * This creates the Rook chess piece.
 */
public class Rook extends ChessPiece {

  public Rook(Board board, Color color) {
    super(board, color);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position abovePosition = new Position(position.getRow() - 1, position.getColumn());
    while (isEmptyPosition(abovePosition)) {
      matrix[abovePosition.getRow()][abovePosition.getColumn()] = true;
      abovePosition.setRow(abovePosition.getRow() - 1);
    }
    if (getBoard().positionExists(abovePosition) && isThereOpponentPiece(abovePosition)) {
      matrix[abovePosition.getRow()][abovePosition.getColumn()] = true;
    }
    Position belowPosition = new Position(position.getRow() + 1, position.getColumn());
    while (isEmptyPosition(belowPosition)) {
      matrix[belowPosition.getRow()][belowPosition.getColumn()] = true;
      belowPosition.setRow(belowPosition.getRow() + 1);
    }
    if (getBoard().positionExists(belowPosition) && isThereOpponentPiece(belowPosition)) {
      matrix[belowPosition.getRow()][belowPosition.getColumn()] = true;
    }
    Position leftPosition = new Position(position.getRow(), position.getColumn() - 1);
    while (isEmptyPosition(leftPosition)) {
      matrix[leftPosition.getRow()][leftPosition.getColumn()] = true;
      leftPosition.setColumn(leftPosition.getColumn() - 1);
    }
    if (getBoard().positionExists(leftPosition) && isThereOpponentPiece(leftPosition)) {
      matrix[leftPosition.getRow()][leftPosition.getColumn()] = true;
    }
    Position rightPosition = new Position(position.getRow(), position.getColumn() + 1);
    while (isEmptyPosition(rightPosition)) {
      matrix[rightPosition.getRow()][rightPosition.getColumn()] = true;
      rightPosition.setColumn(rightPosition.getColumn() + 1);
    }
    if (getBoard().positionExists(rightPosition) && isThereOpponentPiece(rightPosition)) {
      matrix[rightPosition.getRow()][rightPosition.getColumn()] = true;
    }
    return matrix;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♖";
    } else {
      return "♜";
    }
  }
}
