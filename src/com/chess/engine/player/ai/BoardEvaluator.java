package com.chess.engine.player.ai;

import com.chess.engine.Board.Board;

public interface BoardEvaluator {
    int evaluate(Board board, int depth);
}
