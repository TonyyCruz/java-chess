package chess.pieces;

import boardgame.Board;
import boardgame.Position;
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
    boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
    addLineMove(matrix);
    addDiagonalMove(matrix);
    return matrix;
  }

  private boolean[][] addLineMove(boolean[][] matrix) {
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

  private boolean[][] addDiagonalMove(boolean[][] matrix) {
    Position nwPosition = new Position(position.getRow() - 1, position.getColumn() - 1);
    while (isEmptyPosition(nwPosition)) {
      matrix[nwPosition.getRow()][nwPosition.getColumn()] = true;
      nwPosition.setValue(nwPosition.getRow() - 1, nwPosition.getColumn() - 1);
    }
    if (getBoard().positionExists(nwPosition) && isThereOpponentPiece(nwPosition)) {
      matrix[nwPosition.getRow()][nwPosition.getColumn()] = true;
    }
    Position nePosition = new Position(position.getRow() - 1, position.getColumn() + 1);
    while (isEmptyPosition(nePosition)) {
      matrix[nePosition.getRow()][nePosition.getColumn()] = true;
      nePosition.setValue(nePosition.getRow() - 1, nePosition.getColumn() + 1);
    }
    if (getBoard().positionExists(nePosition) && isThereOpponentPiece(nePosition)) {
      matrix[nePosition.getRow()][nePosition.getColumn()] = true;
    }
    Position swPosition = new Position(position.getRow() + 1, position.getColumn() - 1);
    while (isEmptyPosition(swPosition)) {
      matrix[swPosition.getRow()][swPosition.getColumn()] = true;
      swPosition.setValue(swPosition.getRow() + 1, swPosition.getColumn() - 1);
    }
    if (getBoard().positionExists(swPosition) && isThereOpponentPiece(swPosition)) {
      matrix[swPosition.getRow()][swPosition.getColumn()] = true;
    }
    Position sePosition = new Position(position.getRow() + 1, position.getColumn() + 1);
    while (isEmptyPosition(sePosition)) {
      matrix[sePosition.getRow()][sePosition.getColumn()] = true;
      sePosition.setValue(sePosition.getRow() + 1, sePosition.getColumn() + 1);
    }
    if (getBoard().positionExists(sePosition) && isThereOpponentPiece(sePosition)) {
      matrix[sePosition.getRow()][sePosition.getColumn()] = true;
    }
    return matrix;
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
