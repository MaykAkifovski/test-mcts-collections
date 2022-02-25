package mcts.treePruning;

import java.util.ArrayList;
import java.util.List;

import static mcts.treePruning.MctsTreePruning.HEURISTIC_SCORE_THRESHOLD_THETA;

public class TreeNode {
    TreeNode parent;
    List<TreeNode> children;
    Integer score;
    Double heuristicScoreThreshold;

    public TreeNode(Integer score) {
        this.parent = null;
        this.score = score;
        this.heuristicScoreThreshold = HEURISTIC_SCORE_THRESHOLD_THETA * score;
    }

    public TreeNode(Integer score, TreeNode parent) {
        this.parent = parent;
        this.score = score;
        this.heuristicScoreThreshold = HEURISTIC_SCORE_THRESHOLD_THETA * score;
    }


}
