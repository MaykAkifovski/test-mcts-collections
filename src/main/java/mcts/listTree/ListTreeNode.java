package mcts.listTree;

import java.util.ArrayList;
import java.util.List;

public class ListTreeNode {
    Integer score;
    List<ListTreeNode> children;

    public ListTreeNode(Integer score) {
        this.score = score;
    }

    public ListTreeNode addChild(Integer value) {
        if (children == null) {
            children = new ArrayList<>();
        }
        ListTreeNode childTreeNode = new ListTreeNode(value);
        children.add(childTreeNode);
        return childTreeNode;
    }
}
