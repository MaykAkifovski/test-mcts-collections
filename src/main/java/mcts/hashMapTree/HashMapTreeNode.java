package mcts.hashMapTree;

import java.util.HashMap;

public class HashMapTreeNode {
    Integer value;
    HashMap<Integer, HashMapTreeNode> children;

    public HashMapTreeNode(Integer value) {
        this.value = value;
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
