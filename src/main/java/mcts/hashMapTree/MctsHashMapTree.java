package mcts.hashMapTree;

import mcts.MctsTest;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.stream.IntStream;

public class MctsHashMapTree extends MctsTest {

    private HashMap<Integer, HashMapTreeNode> gameTree;

    public MctsHashMapTree() {
        gameTree = new HashMap<>();
        gameTree.put(FIRST_NODE_INDEX, new HashMapTreeNode(getRanomValue()));
    }

    @Override
    protected void expand() {
        addChildren(gameTree.get(FIRST_NODE_INDEX), 0);
    }

    @Override
    protected int getNumberOfNodes() {
        return IntStream
                .range(0, TARGET_DEPTH + 1)
                .mapToObj(i -> (int) Math.pow(NUMBER_OF_CHILDREN, i))
                .reduce(0, Integer::sum);
    }

    @Override
    protected Object getGameTree() {
        return gameTree;
    }

    private void addChildren(HashMapTreeNode parentTreeNode, int actualDepth) {
        if (actualDepth < TARGET_DEPTH) {
            for (int i = 0; i < NUMBER_OF_CHILDREN; i++) {
                HashMapTreeNode childTreeNode = parentTreeNode.addChild(i, getRanomValue());
                addChildren(childTreeNode, actualDepth + 1);
            }
        }
    }

    @Override
    protected void selectRoot() {
        HashMapTreeNode integer = gameTree.get(FIRST_NODE_INDEX);
    }

    @Override
    protected void selectRootChildren() {
        HashMap<Integer, HashMapTreeNode> children = gameTree.get(FIRST_NODE_INDEX).children;
    }

    @Override
    protected void selectLeaf() {
        throw new NotImplementedException();
    }

    @Override
    protected void selectLeafChildren() {
        throw new NotImplementedException();
    }
}
