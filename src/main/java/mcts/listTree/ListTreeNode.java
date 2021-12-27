package mcts.listTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListTreeNode {
    Integer value;
    List<ListTreeNode> children;

    public ListTreeNode(Integer value) {
        this.value = value;
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
