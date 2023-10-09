package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;


/**
 * This class is responsible for instantiating chess pieces.
 */
public abstract class ChessPiece extends Piece {
  private Color color;
  private int moveCount;

  public ChessPiece(Board board, Color color) {
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

  protected boolean isThereOpponentPiece(Position position) {
    ChessPiece p = (ChessPiece) getBoard().piece(position);

    return p != null && p.getColor() != color;
  }

}
