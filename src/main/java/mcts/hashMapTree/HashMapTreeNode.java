package mcts.hashMapTree;

import java.util.HashMap;

public class HashMapTreeNode {
    Integer score;
    HashMap<Integer, HashMapTreeNode> children;

    public HashMapTreeNode(Integer score) {
        this.score = score;
    }

    public HashMapTreeNode addChild(Integer childKey, Integer value) {
        if (children == null) {
            children = new HashMap<>();
        }
        HashMapTreeNode childTreeNode = new HashMapTreeNode(value);
        children.put(childKey, childTreeNode);
        return childTreeNode;
    }
}
