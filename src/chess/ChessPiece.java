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

  public boolean isEmptyPosition(Position position) {
    return getBoard().positionExists(position) && !getBoard().thereIsApiece(position);
  }

  protected boolean isThereOpponentPiece(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);
    return p != null && p.getColor() != color;
  }
}
