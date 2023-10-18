package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;


/**
 * This creates the Pawn chess piece.
 */
public class Pawn extends ChessPiece {
  private ChessMatch chessMatch;

  public Pawn(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
  }

  @Override
  public boolean[][] possibleMoves() {
    if (getColor() == Color.WHITE) {
      return whiteMoves();
    } else {
      return blackMoves();
    }
  }

  private boolean[][] whiteMoves() {
    boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position oneStepUp = new Position(position.getRow() - 1, position.getColumn());
    if (isEmptyPosition(oneStepUp)) {
      matrix[oneStepUp.getRow()][oneStepUp.getColumn()] = true;
    }
    Position twoStepsUp = new Position(position.getRow() - 2, position.getColumn());
    if (canMoveTwoSteps(oneStepUp, twoStepsUp)) {
      matrix[twoStepsUp.getRow()][twoStepsUp.getColumn()] = true;
    }
    Position nwEnemy = new Position(position.getRow() - 1, position.getColumn() - 1);
    if (getBoard().positionExists(nwEnemy) && isThereOpponentPiece(nwEnemy)) {
      matrix[nwEnemy.getRow()][nwEnemy.getColumn()] = true;
    }
    Position neEnemy = new Position(position.getRow() - 1, position.getColumn() + 1);
    if (getBoard().positionExists(neEnemy) && isThereOpponentPiece(neEnemy)) {
      matrix[neEnemy.getRow()][neEnemy.getColumn()] = true;
    }
    Position leftEnemy = new Position(position.getRow(), position.getColumn() - 1);
    boolean canEnPassantLeft = canEnPassant(leftEnemy);
    if (canEnPassantLeft) {
      matrix[leftEnemy.getRow() - 1][leftEnemy.getColumn()] = true;
    }
    Position rightEnemy = new Position(position.getRow(), position.getColumn() + 1);
    boolean canEnPassantRight = canEnPassant(rightEnemy);
    if (canEnPassantRight) {
      matrix[rightEnemy.getRow() - 1][rightEnemy.getColumn()] = true;
    }
    return matrix;
  }

  private boolean[][] blackMoves() {
    boolean[][] matrix = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position oneStepDown = new Position(position.getRow() + 1, position.getColumn());
    if (isEmptyPosition(oneStepDown)) {
      matrix[oneStepDown.getRow()][oneStepDown.getColumn()] = true;
    }
    Position twoStepsDown = new Position(position.getRow() + 2, position.getColumn());
    if (canMoveTwoSteps(oneStepDown, twoStepsDown)) {
      matrix[twoStepsDown.getRow()][twoStepsDown.getColumn()] = true;
    }
    Position swEnemy = new Position(position.getRow() + 1, position.getColumn() - 1);
    if (getBoard().positionExists(swEnemy) && isThereOpponentPiece(swEnemy)) {
      matrix[swEnemy.getRow()][swEnemy.getColumn()] = true;
    }
    Position seEnemy = new Position(position.getRow() + 1, position.getColumn() + 1);
    if (getBoard().positionExists(seEnemy) && isThereOpponentPiece(seEnemy)) {
      matrix[seEnemy.getRow()][seEnemy.getColumn()] = true;
    }
    Position leftEnemy = new Position(position.getRow(), position.getColumn() - 1);
    boolean canEnPassantLeft = canEnPassant(leftEnemy);
    if (canEnPassantLeft) {
      matrix[leftEnemy.getRow() + 1][leftEnemy.getColumn()] = true;
    }
    Position rightEnemy = new Position(position.getRow(), position.getColumn() + 1);
    boolean canEnPassantRight = canEnPassant(rightEnemy);
    if (canEnPassantRight) {
      matrix[rightEnemy.getRow() + 1][rightEnemy.getColumn()] = true;
    }
    return matrix;
  }

  private boolean canMoveTwoSteps(Position firstStep, Position secondStep) {
    return isEmptyPosition(firstStep) && isEmptyPosition(secondStep) && getMoveCount() == 0;
  }

  private boolean canEnPassant(Position enemyPosition) {
    boolean isEnPassantPosition =
        getColor() == Color.WHITE ? position.getRow() == 3 : position.getRow() == 4;
    return isEnPassantPosition && getBoard().positionExists(enemyPosition)
        && isThereOpponentPiece(enemyPosition)
        && getBoard().piece(enemyPosition) == chessMatch.getEnPassantVunerable();
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
