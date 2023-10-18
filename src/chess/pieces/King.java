package chess.pieces;

import java.util.List;
import boardgame.Board;
import boardgame.Piece;
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
    Position southPosition = new Position(position.getRow() - 1, position.getColumn());
    if (canMoveThere(southPosition)) {
      matrix[southPosition.getRow()][southPosition.getColumn()] = true;
    }
    Position northPosition = new Position(position.getRow() + 1, position.getColumn());
    if (canMoveThere(northPosition)) {
      matrix[northPosition.getRow()][northPosition.getColumn()] = true;
    }
    Position westPosition = new Position(position.getRow(), position.getColumn() - 1);
    if (canMoveThere(westPosition)) {
      matrix[westPosition.getRow()][westPosition.getColumn()] = true;
    }
    Position eastPosition = new Position(position.getRow(), position.getColumn() + 1);
    if (canMoveThere(eastPosition)) {
      matrix[eastPosition.getRow()][eastPosition.getColumn()] = true;
    }
    Position nwPosition = new Position(position.getRow() - 1, position.getColumn() - 1);
    if (canMoveThere(nwPosition)) {
      matrix[nwPosition.getRow()][nwPosition.getColumn()] = true;
    }
    Position nePosition = new Position(position.getRow() - 1, position.getColumn() + 1);
    if (canMoveThere(nePosition)) {
      matrix[nePosition.getRow()][nePosition.getColumn()] = true;
    }
    Position swPosition = new Position(position.getRow() + 1, position.getColumn() - 1);
    if (canMoveThere(swPosition)) {
      matrix[swPosition.getRow()][swPosition.getColumn()] = true;
    }
    Position sePosition = new Position(position.getRow() + 1, position.getColumn() - 1);
    if (canMoveThere(sePosition)) {
      matrix[sePosition.getRow()][sePosition.getColumn()] = true;
    }
    if (canCastlingRight()) {
      matrix[position.getRow()][position.getColumn() + 2] = true;
    }
    if (canCastlingLeft()) {
      matrix[position.getRow()][position.getColumn() - 2] = true;
    }
    return matrix;
  }

  private boolean canThisRookCastling(Position position) {
    if (!getBoard().positionExists(position)) {
      return false;
    }
    ChessPiece piece = (ChessPiece) getBoard().piece(position);
    return piece instanceof Rook && piece.getMoveCount() == 0 && piece.getColor() == getColor();
  }

  private boolean isSafePosition(Position position) {
    Color opponentColor = chessMatch.opponentColor(this.getColor());
    List<Piece> opponentPieces = chessMatch.getPiecesWithTheColor(opponentColor);
    for (Piece p : opponentPieces) {
      if (p.isMovementPossible(position)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Castling is permitted provided all of the following conditions are met.
   * <ol>
   * <li>Neither the king nor the rook has previously moved.</li>
   * <li>There are no pieces between the king and the rook.</li>
   * <li>The king is not currently in check.</li>
   * <li>The king does not pass through or finish on a square that is attacked by an enemy
   * piece.</li>
   * </ol>
   */
  private boolean canCastlingRight() {
    if (getColor() != chessMatch.getCurrentPlayer()) {
      return false;
    }
    Position rightRookPosition = new Position(position.getRow(), position.getColumn() + 3);
    if (!canThisRookCastling(rightRookPosition) || getMoveCount() != 0 || chessMatch.getCheck()) {
      return false;
    }
    Position oneToRight = new Position(position.getRow(), position.getColumn() + 1);
    Position twoToRight = new Position(position.getRow(), position.getColumn() + 2);
    boolean isSafeWay = isSafePosition(oneToRight) && isSafePosition(twoToRight);
    boolean isTheWayEmpty =
        getBoard().piece(oneToRight) == null && getBoard().piece(twoToRight) == null;
    return isTheWayEmpty && isSafeWay;
  }

  /**
   * Castling is permitted provided all of the following conditions are met.
   * <ol>
   * <li>Neither the king nor the rook has previously moved.</li>
   * <li>There are no pieces between the king and the rook.</li>
   * <li>The king is not currently in check.</li>
   * <li>The king does not pass through or finish on a square that is attacked by an enemy
   * piece.</li>
   * </ol>
   */
  private boolean canCastlingLeft() {
    if (getColor() != chessMatch.getCurrentPlayer()) {
      return false;
    }
    Position leftRookPosition = new Position(position.getRow(), position.getColumn() - 4);
    if (!canThisRookCastling(leftRookPosition) || getMoveCount() != 0 || chessMatch.getCheck()) {
      return false;
    }
    Position oneToLeft = new Position(position.getRow(), position.getColumn() - 1);
    Position twoToLeft = new Position(position.getRow(), position.getColumn() - 2);
    Position threeToLeft = new Position(position.getRow(), position.getColumn() - 3);
    boolean isSafeWay = isSafePosition(oneToLeft) && isSafePosition(twoToLeft);
    boolean isTheWayEmpty = getBoard().piece(oneToLeft) == null
        && getBoard().piece(twoToLeft) == null && getBoard().piece(threeToLeft) == null;
    return isTheWayEmpty && isSafeWay;
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
