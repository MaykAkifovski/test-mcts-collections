import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MctsTreeMap extends MctsTest {

    private TreeMap<String, Integer> gameTree;

    public MctsTreeMap() {
        gameTree = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] o1_items = o1.split(DELIMETER);
                String[] o2_items = o2.split(DELIMETER);
                if (o1_items.length < o2_items.length) {
                    return -1;
                } else if (o1_items.length > o2_items.length) {
                    return 1;
                } else {
                    return o1.compareTo(o2);
                }
            }

            @Override
            public boolean equals(Object obj) {
                return false;
            }
        });

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
        Map.Entry<String, Integer> rootNode = gameTree.firstEntry();
    }

    @Override
    protected void selectRootChildren() {
        String fromKey = generateTreeMapKey(FIRST_NODE_KEY, FIRST_NODE_INDEX);
        String toKey = generateTreeMapKey("01", FIRST_NODE_INDEX);
        SortedMap<String, Integer> rootChildren = gameTree.tailMap(fromKey, true).headMap(toKey);
    }

    @Override
    protected void selectLeaf() {
        Map.Entry<String, Integer> leaf = gameTree.lastEntry();
    }

    @Override
    protected void selectLeafChildren() {
        String fromKey = IntStream.range(0, TARGET_DEPTH).mapToObj(i -> FIRST_NODE_KEY).collect(Collectors.joining(DELIMETER));
        String toKey = gameTree.lastKey();
        SortedMap<String, Integer> stringIntegerSortedMap = gameTree.tailMap(fromKey, true).headMap(toKey);
    }
}
