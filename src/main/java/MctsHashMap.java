import java.util.*;
import java.util.stream.Collectors;

public class MctsHashMap extends MctsTest {

    private HashMap<String, Integer> gameTree;

    public MctsHashMap() {
        gameTree = new HashMap<>();

        gameTree.put(FIRST_NODE_KEY, getRanomValue());
    }

    @Override
    protected void expand() {
        addChildren(FIRST_NODE_KEY);
    }

    @Override
    protected int getNumberOfNodes() {
        return gameTree.size();
    }

    @Override
    protected Object getGameTree() {
        return gameTree;
    }

    private void addChildren(String parentKey) {
        if (parentKey.split(DELIMETER).length < TARGET_DEPTH) {
            for (int i = 0; i < NUMBER_OF_CHILDREN; i++) {
                String childKey = generateTreeMapKey(parentKey, i);
                gameTree.put(childKey, getRanomValue());
                addChildren(childKey);
            }
        }
    }


    private String generateTreeMapKey(String parentNodeKey, int childNodeIndex) {
        return parentNodeKey + DELIMETER + indexToString.apply(childNodeIndex);
    }

    @Override
    protected void selectRoot() {
        Integer integer = gameTree.get(FIRST_NODE_KEY);
    }

    @Override
    protected void selectRootChildren() {
        List<Map.Entry<String, Integer>> collect = gameTree
                .entrySet()
                .parallelStream()
                .filter(entry -> entry.getKey().split(DELIMETER).length == 2)
                .collect(Collectors.toList());
    }

    @Override
    protected void selectLeaf() {
        Map.Entry<String, Integer> stringIntegerEntry = gameTree.
                entrySet()
                .parallelStream()
                .min(Map.Entry.comparingByKey())
                .orElse(null);
    }

    @Override
    protected void selectLeafChildren() {
        List<Map.Entry<String, Integer>> collect = gameTree
                .entrySet()
                .parallelStream()
                .filter(entry -> entry.getKey().split(DELIMETER).length == TARGET_DEPTH)
                .collect(Collectors.toList());
    }
}
