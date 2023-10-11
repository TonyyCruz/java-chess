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

  /**
   * Check if the received position can be used.
   */
  private boolean canMove(Position p) {
    return getBoard().positionExists(p) && !getBoard().thereIsApiece(p);
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
    Position p = new Position();
    Position p2 = new Position();

    if (getColor() == Color.WHITE) {
      p.setValue(position.getRow() - 1, position.getColumn());
      p2.setValue(position.getRow() - 2, position.getColumn());


      if (canMove(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      if (canMove(p) && canMove(p2) && getMoveCount() == 0) {
        mat[p2.getRow()][p2.getColumn()] = true;
      }

      p.setValue(position.getRow() - 1, position.getColumn() - 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValue(position.getRow() - 1, position.getColumn() + 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      // *SPECIAL MOVE* En Passant WHITE
      if (position.getRow() == 3) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(left) && isThereOpponentPiece(left)
            && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
          mat[left.getRow() - 1][left.getColumn()] = true;
        }

        Position right = new Position(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(right) && isThereOpponentPiece(right)
            && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
          mat[right.getRow() - 1][right.getColumn()] = true;
        }

      }

    } else {
      p.setValue(position.getRow() + 1, position.getColumn());
      p2.setValue(position.getRow() + 2, position.getColumn());


      if (canMove(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      if (canMove(p) && canMove(p2) && getMoveCount() == 0) {
        mat[p2.getRow()][p2.getColumn()] = true;
      }

      p.setValue(position.getRow() + 1, position.getColumn() + 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      p.setValue(position.getRow() + 1, position.getColumn() - 1);
      if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
        mat[p.getRow()][p.getColumn()] = true;
      }

      // *SPECIAL MOVE* En Passant BLACK
      if (position.getRow() == 4) {
        Position left = new Position(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(left) && isThereOpponentPiece(left)
            && getBoard().piece(left) == chessMatch.getEnPassantVunerable()) {
          mat[left.getRow() + 1][left.getColumn()] = true;
        }

        Position right = new Position(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(right) && isThereOpponentPiece(right)
            && getBoard().piece(right) == chessMatch.getEnPassantVunerable()) {
          mat[right.getRow() + 1][right.getColumn()] = true;
        }

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
