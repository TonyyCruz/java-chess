package chess;

import boardgame.Board;
import chess.pieces.Bishop;
import chess.pieces.Horse;
import chess.pieces.King;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * This class is responsible for the chess game.
 */
public class ChessMatch {
  private Board board;

  public ChessMatch() {
    this.board = new Board(8, 8);
    initialSetup();
  }

  /**
   * This returns an array with the current game progress.
   */
  public ChessPiece[][] getPieces() {
    ChessPiece[][] mat = new ChessPiece[board.getColumns()][board.getRows()];

    for (int r = 0; r < board.getRows(); r += 1) {
      for (int c = 0; c < board.getColumns(); c += 1) {
        mat[r][c] = (ChessPiece) board.piece(r, c);
      }
    }

    return mat;
  }

  private void placeNewPiece(char column, int row, ChessPiece piece) {
    board.placePieece(piece, new ChessPosition(column, row).toPosition());
  }

  public void initialSetup() {
    placeNewPiece('a', 8, new Rook(board, Color.BLACK));
    placeNewPiece('b', 8, new Horse(board, Color.BLACK));
    placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('d', 8, new Queen(board, Color.BLACK));
    placeNewPiece('e', 8, new King(board, Color.BLACK));
    placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('g', 8, new Horse(board, Color.BLACK));
    placeNewPiece('h', 8, new Rook(board, Color.BLACK));


    placeNewPiece('a', 1, new Rook(board, Color.WHITE));
    placeNewPiece('b', 1, new Horse(board, Color.WHITE));
    placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('d', 1, new Queen(board, Color.WHITE));
    placeNewPiece('e', 1, new King(board, Color.WHITE));
    placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('g', 1, new Horse(board, Color.WHITE));
    placeNewPiece('h', 1, new Rook(board, Color.WHITE));
  }
}
