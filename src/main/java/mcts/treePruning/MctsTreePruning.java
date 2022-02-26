package mcts.treePruning;

import org.openjdk.jol.info.GraphLayout;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MctsTreePruning {
    public static final double PRUNED_NODES_LIMIT = 0.3;
    public static final double HEURISTIC_SCORE_THRESHOLD_THETA = 1.3;
    private static final int NUMBER_OF_CHILDREN = 30;
    private static final int LIMIT = NUMBER_OF_CHILDREN / 3;
    private static int TARGET_DEPTH = 0;
    private final Random randomInstance = new Random(123L);
    private TreeNode gameTree;
    private int numberOfNodes = 0;

    public MctsTreePruning() {
        gameTree = new TreeNode(0);
        numberOfNodes += 1;
    }

    private void expand(TreeNode parent) {
        addChildren(parent, 0);
    }

    private void addChildren(TreeNode parent, int currentDepth) {
        if (currentDepth < TARGET_DEPTH) {
            List<TreeNode> children = getChildren(parent);
            parent.children = children;
            numberOfNodes += children.size();
            children.forEach(child -> addChildren(child, currentDepth + 1));
        }
    }

    private List<TreeNode> getChildren(TreeNode parent) {
        List<TreeNode> nextLegalGames = getNextLegalGames(parent);
        long numberOfNextLegalGames = nextLegalGames.size();
        List<TreeNode> nextLegalGamesOverThreshold = getNextLegalGamesOverThreshold(parent, nextLegalGames);
        long numberOfNextLegalGamesOverThreshold = nextLegalGamesOverThreshold.size();
        if (numberOfNextLegalGamesOverThreshold >= PRUNED_NODES_LIMIT * numberOfNextLegalGames) {
            return nextLegalGamesOverThreshold;
        } else {
            return nextLegalGames.stream().limit(LIMIT).collect(Collectors.toList());
        }
    }

    private List<TreeNode> getNextLegalGames(TreeNode parent) {
        return IntStream
                .range(0, NUMBER_OF_CHILDREN)
                .mapToObj(i -> new TreeNode(randomInt(), parent))
                .sorted(Comparator.comparing(o -> o.score, Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

    private List<TreeNode> getNextLegalGamesOverThreshold(TreeNode parent, List<TreeNode> nextLegalGames) {
        return nextLegalGames.stream().filter(child -> child.score > parent.heuristicScoreThreshold).collect(Collectors.toList());
    }

    public int randomInt() {
        return randomInstance.nextInt(100_000_000);
    }

    private void printSize() {
        long sizeBytes = GraphLayout.parseInstance(gameTree).totalSize();
        long sizeKBs = sizeBytes / 1024;
        long sizeMBs = sizeKBs / 1024;
        long sizeGBs = sizeMBs / 1024;
        System.out.printf("Depth %d: GameTreeSize[nodes=%d] %d Bytes / %d KB / %d MB / %d GB.%n", TARGET_DEPTH, numberOfNodes, sizeBytes, sizeKBs, sizeMBs, sizeGBs);
    }

    public static void main(String[] args) {
        MctsTreePruning mcts = new MctsTreePruning();
        mcts.printSize();
        for (int i = 1; i <= 10; i++) {
            mcts = new MctsTreePruning();
            TARGET_DEPTH = i;

            mcts.expand(mcts.gameTree);
            System.out.println(mcts.numberOfNodes);
            mcts.printSize();
        }
        System.out.println();
    }

}
