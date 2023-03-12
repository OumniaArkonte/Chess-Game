package com.chess.engine.player.ai;

import com.chess.engine.Board.Board;
import com.chess.engine.Board.Move;

public interface MoveStrategy {
    Move execute(Board board);
}
