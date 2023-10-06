package chess;

import java.util.ArrayList;
import java.util.List;
import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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
  private int turn;
  private Color currentPlayer;

  private List<Piece> pieceOnTheTable = new ArrayList<>();
  private List<Piece> capturedPieces = new ArrayList<>();

  /**
   * The class will be initialized with the default settings.
   */
  public ChessMatch() {
    this.board = new Board(8, 8);
    this.turn = 1;
    this.currentPlayer = Color.WHITE;
    initialSetup();
  }

  public int getTurn() {
    return turn;
  }

  public Color getCurrentPlayer() {
    return currentPlayer;
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

  /**
   * This method receive a piece, a column and a row. The received piece is added to the board in
   * the received position and is added on the piecesOnTheTable.
   * 
   * @param column
   *
   * @param row
   * 
   * @param piece
   * 
   */
  private void placeNewPiece(char column, int row, ChessPiece piece) {
    board.placePieece(piece, new ChessPosition(column, row).toPosition());

    pieceOnTheTable.add(piece);
  }

  /**
   * This shows possible movements for the piece in the source position.
   */
  public boolean[][] possibleMoves(ChessPosition sourcePosition) {
    Position position = sourcePosition.toPosition();
    validateSourcePosition(position);

    return board.piece(position).possibleMoves();
  }

  /**
   * This performs the act of moving the pieces according to the chess positions. Return the
   * captured piece.
   */
  public ChessPiece performChessMovie(ChessPosition sourcePosition, ChessPosition targetPosition) {
    Position source = sourcePosition.toPosition();
    Position target = targetPosition.toPosition();

    validateSourcePosition(source);
    validateTargetPosition(source, target);

    Piece capturedPiece = makeMove(source, target);

    nextTurn();

    return (ChessPiece) capturedPiece;
  }

  /**
   * This moving the pieces according to the matrix position. The source piece is placed in the
   * target position and If any piece is captured it is removed from the board.
   */
  private Piece makeMove(Position source, Position target) {
    Piece movedPiece = board.removePieece(source);
    Piece capturedPiece = board.removePieece(target);
    board.placePieece(movedPiece, target);

    if (capturedPiece != null) {
      this.pieceOnTheTable.remove(capturedPiece);
      this.capturedPieces.add(capturedPiece);
    }

    return capturedPiece;
  }

  private void validateSourcePosition(Position position) {
    if (!board.thereIsApiece(position)) {
      throw new ChessException("There is no piece on source position.");
    }

    ChessPiece currentPiece = (ChessPiece) board.piece(position);
    if (currentPlayer != currentPiece.getColor()) {
      throw new ChessException("That piece is not your.");
    }

    if (!board.piece(position).isThereAnyPossibleMove(position)) {
      throw new ChessException("This piece cannot make any movement.");
    }
  }

  private void validateTargetPosition(Position source, Position target) {
    if (!board.piece(source).possibleMove(target)) {
      throw new ChessException("The chosen piece cannot move to target position.");
    }
  }

  private void nextTurn() {
    turn += 1;
    currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
  }

  /**
   * This places the pieces in their starting position.
   */
  public void initialSetup() {
    placeNewPiece('a', 8, new Rook(board, Color.BLACK));
    placeNewPiece('b', 8, new Horse(board, Color.BLACK));
    placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('d', 8, new Queen(board, Color.BLACK));
    placeNewPiece('e', 8, new King(board, Color.BLACK));
    placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
    placeNewPiece('g', 8, new Horse(board, Color.BLACK));
    placeNewPiece('h', 8, new Rook(board, Color.BLACK));

    // placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
    // placeNewPiece('h', 7, new Pawn(board, Color.BLACK));


    placeNewPiece('a', 1, new Rook(board, Color.WHITE));
    placeNewPiece('b', 1, new Horse(board, Color.WHITE));
    placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('d', 1, new Queen(board, Color.WHITE));
    placeNewPiece('e', 1, new King(board, Color.WHITE));
    placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
    placeNewPiece('g', 1, new Horse(board, Color.WHITE));
    placeNewPiece('h', 1, new Rook(board, Color.WHITE));

    // placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
    // placeNewPiece('h', 2, new Pawn(board, Color.WHITE));
  }
}
