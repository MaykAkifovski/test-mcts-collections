import java.util.Random;
import java.util.function.Function;
import java.util.regex.Pattern;

public abstract class MctsTest {

    protected static final int FIRST_NODE_INDEX = 0;
    protected static final String FIRST_NODE_KEY = "00";
    protected static final String DELIMETER = ", ";
    protected static final Function<Integer, String> indexToString = index -> (index < 10 ? "0" : "") + index;
    protected static final Pattern p = Pattern.compile(", [0-9]*$");

    protected final Random randomInstance = new Random(123L);
    protected static final Integer NUMBER_OF_CHILDREN = 10;
    protected static Integer TARGET_DEPTH = 9;

    protected abstract void expand();

    protected abstract int getNumberOfNodes();

    protected abstract Object getGameTree();

    protected Integer getRanomValue() {
        return randomInstance.nextInt(100_000_000);
    }

    private double benchmarkExpand() {
        long startTime = System.nanoTime();
        expand();
//        double elapsedTimeInSeconds = elapsedTime(startTime);
        return elapsedTime(startTime);
//        System.out.printf(
//                "Hinzufügen (Knoten=%d, Tiefe=%d, Kinder=%d) hat %f s gedauert.%n",
//                getNumberOfNodes(), TARGET_DEPTH, NUMBER_OF_CHILDREN, elapsedTimeInSeconds
//        );
    }

    private double elapsedTime(long startTime) {
        long endTime = System.nanoTime();
        long elapsedTime = endTime - startTime;
        return (double) elapsedTime / 1_000_000_000;
    }


    private void benchmarkSelect() {
        benchmarkSelectRoot();
        benchmarkSelectRootChildren();
        benchmarkSelectLeaf();
        benchmarkSelectLeafChildren();
    }

    private void benchmarkSelectRoot() {
        long startTime = System.nanoTime();
        selectRoot();
        double elapsedTime = elapsedTime(startTime);
        System.out.printf(
                "selectRoot (Knoten=%d, Tiefe=%d, Kinder=%d) hat %f s gedauert.%n",
                getNumberOfNodes(), TARGET_DEPTH, NUMBER_OF_CHILDREN, elapsedTime
        );
    }

    private void benchmarkSelectRootChildren() {
        long startTime = System.nanoTime();
        selectRootChildren();
        double elapsedTime = elapsedTime(startTime);
        System.out.printf(
                "selectRootChildren (Knoten=%d, Tiefe=%d, Kinder=%d) hat %f s gedauert.%n",
                getNumberOfNodes(), TARGET_DEPTH, NUMBER_OF_CHILDREN, elapsedTime
        );
    }

    private void benchmarkSelectLeaf() {
        long startTime = System.nanoTime();
        selectLeaf();
        double elapsedTime = elapsedTime(startTime);
        System.out.printf(
                "selectLeaf (Knoten=%d, Tiefe=%d, Kinder=%d) hat %f s gedauert.%n",
                getNumberOfNodes(), TARGET_DEPTH, NUMBER_OF_CHILDREN, elapsedTime
        );
    }

    private void benchmarkSelectLeafChildren() {
        long startTime = System.nanoTime();
        selectLeafChildren();
        double elapsedTime = elapsedTime(startTime);
        System.out.printf(
                "selectLeafChildren (Knoten=%d, Tiefe=%d, Kinder=%d) hat %f s gedauert.%n",
                getNumberOfNodes(), TARGET_DEPTH, NUMBER_OF_CHILDREN, elapsedTime
        );
    }

    protected abstract void selectRoot();

    protected abstract void selectRootChildren();

    protected abstract void selectLeaf();

    protected abstract void selectLeafChildren();

    public static void main(String[] args) {
        int numberOfRuns = 10;
        int maxDepth = 10;
        for (int i = 1; i <= maxDepth; i++) {
            TARGET_DEPTH = i;
            double elapsedTime = 0;
            for (int j = 0; j < numberOfRuns; j++) {
                MctsTest underTest = new MctsTreeMap();
//        System.out.printf("GameTreeSize before expand %d bytes.%n", GraphLayout.parseInstance(underTest.getGameTree()).totalSize());
                elapsedTime += underTest.benchmarkExpand();
//        System.out.printf("GameTreeSize after expand %d bytes.%n", GraphLayout.parseInstance(underTest.getGameTree()).totalSize());
//                underTest.benchmarkSelect();
            }
            System.out.println(elapsedTime / numberOfRuns);
        }

    }
}
