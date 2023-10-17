package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;


/**
 * Create a generic chess pieces.
 */
public abstract class ChessPiece extends Piece {
  private Color color;
  private int moveCount;

  protected ChessPiece(Board board, Color color) {
    super(board);
    this.color = color;
  }

  public Color getColor() {
    return color;
  }

  public ChessPosition getChessPosition() {
    return ChessPosition.fromPosition(position);
  }

  public int getMoveCount() {
    return moveCount;
  }

  public void increseMoveCount() {
    moveCount += 1;
  }

  public void decreaseMoveCount() {
    moveCount -= 1;
  }

  /**
   * Check if the received position is valid to move.
   */
  protected boolean canMoveThere(Position position) {
    if (!getBoard().positionExists(position)) {
      return false;
    }
    ChessPiece pieceInThePosition = (ChessPiece) getBoard().piece(position);
    return pieceInThePosition == null || isThereOpponentPiece(position);
  }

  protected boolean isThereOpponentPiece(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p != null && p.getColor() != color;
  }
}
