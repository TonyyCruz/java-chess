package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

/**
 * This creates the king chess piece.
 */
public class King extends ChessPiece {
  private ChessMatch chessMatch;

  public King(Board board, Color color, ChessMatch chessMatch) {
    super(board, color);
    this.chessMatch = chessMatch;
  }

  /**
   * Check if the received position can be used.
   */
  private boolean canMove(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p == null || isThereOpponentPiece(position);
  }

  private boolean testRookCastling(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p != null && p instanceof Rook && p.getMoveCount() == 0 && p.getColor() == getColor();
  }

  @Override
  public boolean[][] possibleMoves() {
    boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

    Position p = new Position();

    // Above
    p.setValue(position.getRow() - 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // Below
    p.setValue(position.getRow() + 1, position.getColumn());
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // Left
    p.setValue(position.getRow(), position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // Right
    p.setValue(position.getRow(), position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // NW
    p.setValue(position.getRow() - 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // NE
    p.setValue(position.getRow() - 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SW
    p.setValue(position.getRow() + 1, position.getColumn() - 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SE
    p.setValue(position.getRow() + 1, position.getColumn() + 1);
    if (getBoard().positionExists(p) && canMove(p)) {
      mat[p.getRow()][p.getColumn()] = true;
    }

    // SPECIAL move Castling
    if (getMoveCount() == 0 && !chessMatch.getCheck()) {
      // Castling to right.
      Position rightRook = new Position(position.getRow(), position.getColumn() + 3);
      if (testRookCastling(rightRook)) {
        Position oneToRight = new Position(position.getRow(), position.getColumn() + 1);
        Position twoToRight = new Position(position.getRow(), position.getColumn() + 2);

        if (getBoard().piece(oneToRight) == null && getBoard().piece(twoToRight) == null) {
          mat[position.getRow()][position.getColumn() + 2] = true;
        }
      }

      // Castling to left.
      Position leftRook = new Position(position.getRow(), position.getColumn() - 4);
      if (testRookCastling(leftRook)) {
        Position oneToLeft = new Position(position.getRow(), position.getColumn() - 1);
        Position twoToLeft = new Position(position.getRow(), position.getColumn() - 2);
        Position threeToLeft = new Position(position.getRow(), position.getColumn() - 3);

        if (getBoard().piece(oneToLeft) == null && getBoard().piece(twoToLeft) == null
            && getBoard().piece(threeToLeft) == null) {
          mat[position.getRow()][position.getColumn() - 2] = true;
        }
      }

    }

    return mat;
  }

  @Override
  public String toString() {
    if (getColor() == Color.WHITE) {
      return "♔";
    } else {
      return "♚";
    }
  }
}
