package mcts.treePruning;

import org.openjdk.jol.info.GraphLayout;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MctsTreePruning {
    private static final int NUMBER_OF_CHILDREN = 10;
    public static final double HEURISTIC_SCORE_THRESHOLD_THETA = 0.9;
    private final Random randomInstance = new Random(123L);
    private TreeNode gameTree;
    private int numberOfNodes = 0;

    public MctsTreePruning() {
        gameTree = new TreeNode(0);
        numberOfNodes += 1;
    }


    private void expand(TreeNode parent) {
        List<TreeNode> children = IntStream
                .range(0, NUMBER_OF_CHILDREN)
                .mapToObj(i -> new TreeNode(randomInt(), parent))
                .collect(Collectors.toList());
        List<TreeNode> childrenOverThreshold = children.stream().filter(child -> child.score > parent.heuristicScoreThreshold).collect(Collectors.toList());
        if (childrenOverThreshold.size() >= 0.3 * children.size()) {
            parent.children = childrenOverThreshold;
        } else {
            parent.children = children.stream().sorted(Comparator.comparing(o -> o.score)).limit(children.size() / 2).collect(Collectors.toList());
        }
        numberOfNodes += parent.children.size();
    }

    public int randomInt() {
        return randomInstance.nextInt(10_000);
    }

    private void printSize(int depth) {
        long sizeBytes = GraphLayout.parseInstance(gameTree).totalSize();
        long sizeKBs = sizeBytes / 1024;
        long sizeMBs = sizeKBs / 1024;
        long sizeGBs = sizeMBs / 1024;
        System.out.printf("Depth %d: GameTreeSize[nodes=%d] %d Bytes / %d KB / %d MB / %d GB.%n", depth, numberOfNodes, sizeBytes, sizeKBs, sizeMBs, sizeGBs);
    }

    public static void main(String[] args) {
        MctsTreePruning mcts = new MctsTreePruning();
        mcts.printSize(0);

        mcts.expand(mcts.gameTree);
        mcts.printSize(1);

        List<TreeNode> children = mcts.gameTree.children;
        children.forEach(mcts::expand);
        mcts.printSize(2);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(3);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(4);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(5);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(6);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(7);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(8);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(9);

        children = children.stream().map(child -> child.children).flatMap(Collection::stream).collect(Collectors.toList());
        children.forEach(mcts::expand);
        mcts.printSize(10);
    }

}
