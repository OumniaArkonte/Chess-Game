package com.chess.engine.player;

import com.chess.engine.Alliance;
import com.chess.engine.Board.Board;
import com.chess.engine.Board.Move;
import com.chess.engine.Board.Tile;
import com.chess.engine.Piece.Piece;
import com.chess.engine.Piece.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.Board.Move.*;

public class BlackPlayer extends Player{
    public BlackPlayer(final Board board,
                       final Collection<Move> whiteStandardLegalMoves,
                       final Collection<Move> blackStandardLegalMoves) {

        super(board, blackStandardLegalMoves, whiteStandardLegalMoves);

    }

    @Override
    public Collection<Piece> getActivePieces() {
        return this.board.getBlackPieces();
    }

    @Override
    public Alliance getAlliance() {
        return Alliance.BLACK;
    }
    @Override
    public Player getOpponent() {
        return this.board.whitePlayer();
    }
    @Override
    protected Collection<Move> calculateKingCastles(final Collection<Move> playerLegals,
                                                    final Collection<Move> opponentsLegals) {
        final List<Move> KingCastles = new ArrayList<>();
        if (this.playerKing.isFirstMove() && !this.isInCheck()) {
            //blacks king side castle
            if (!this.board.getTile(5).isTileOccupied() && !this.board.getTile(6).isTileOccupied()) {
                final Tile rookTile = this.board.getTile(7);
                if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove()) {
                    if (Player.calculateAttacksOnTile(5, opponentsLegals).isEmpty() &&
                            Player.calculateAttacksOnTile(6, opponentsLegals).isEmpty() &&
                            rookTile.getPiece().getPieceType().isRook()){
                        KingCastles.add(new KingSideCastleMove(this.board,
                                                               this.playerKing, 6,
                                                               (Rook) rookTile.getPiece(),
                                                                rookTile.getTileCoordinate(), 5 ));
                    }
                }
            }
            if (!this.board.getTile(1).isTileOccupied() &&
                !this.board.getTile(2).isTileOccupied() &&
                !this.board.getTile(3).isTileOccupied()){
                    final Tile rookTile = this.board.getTile(0);
                         if (rookTile.isTileOccupied() && rookTile.getPiece().isFirstMove() &&
                             Player.calculateAttacksOnTile(2, opponentsLegals).isEmpty() &&
                             Player.calculateAttacksOnTile(3, opponentsLegals).isEmpty() &&
                             rookTile.getPiece().getPieceType().isRook()){
                              KingCastles.add(new QueenSideCastleMove(this.board,
                                                                      this.playerKing, 2,
                                                                      (Rook) rookTile.getPiece(),
                                                                      rookTile.getTileCoordinate(),
                                                     3));
                }
            }
        }
        return ImmutableList.copyOf(KingCastles);
    }
}
