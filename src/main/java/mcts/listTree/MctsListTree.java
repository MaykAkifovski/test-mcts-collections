package mcts.listTree;

import mcts.MctsTest;
import mcts.hashMapTree.HashMapTreeNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MctsListTree extends MctsTest {

    private List<ListTreeNode> gameTree;

    public MctsListTree() {
        gameTree = new ArrayList<>();
        gameTree.add(new ListTreeNode(getRanomValue()));
    }

    @Override
    protected void expand() {
        addChildren(gameTree.get(FIRST_NODE_INDEX), 0);
    }

    @Override
    protected int getNumberOfNodes() {
        return gameTree.size();
    }

    @Override
    protected Object getGameTree() {
        return gameTree;
    }

    private void addChildren(ListTreeNode parentTreeNode, int actualDepth) {
        if (actualDepth < TARGET_DEPTH) {
            for (int i = 0; i < NUMBER_OF_CHILDREN; i++) {
                ListTreeNode childTreeNode = parentTreeNode.addChild(getRanomValue());
                addChildren(childTreeNode, actualDepth + 1);
            }
        }
    }

    @Override
    protected void selectRoot() {
        ListTreeNode integer = gameTree.get(FIRST_NODE_INDEX);
    }

    @Override
    protected void selectRootChildren() {
        List<ListTreeNode> children = gameTree.get(FIRST_NODE_INDEX).children;
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
