package fruit;

import java.util.Arrays;
import java.util.Comparator;

public class BinarySearch {
    private static final String[] FRUITS = {
            "apple",
            "apricot",
            "banana",
            "blackberry",
            "blueberry",
            "cucumber",
            "dragonfruit",
            "fig",
            "passionfruit",
            "plum"};

    private static int comparisons = 0;

    public static boolean isFruit(String s) {
        System.out.println("\n? " + s);
        return Arrays.binarySearch(FRUITS, s, printingComparator) >= 0;
    }

    private static final Comparator<String> printingComparator = (fruit, s) -> {
        System.out.print(fruit + " ");
        comparisons += 1;
        return fruit.compareTo(s);
    };

    public static void main(String[] args) {
        for (String fruit : FRUITS) {
            if (!isFruit(fruit)) throw new AssertionError();
        }
        if (isFruit("carrot")) throw new AssertionError();
        if (isFruit("brussels sprout")) throw new AssertionError();
        System.out.println("\n" + comparisons + " comparisons");
    }

    //    1 fruit  ->  1 comparison
    //    3 fruits ->  2 comparisons
    //    7 fruits ->  3 comparisons
    //   15 fruits ->  4 comparisons
    //   31 fruits ->  5 comparisons
    //   63 fruits ->  6 comparisons
    //  127 fruits ->  7 comparisons
    //  255 fruits ->  8 comparisons
    //  511 fruits ->  9 comparisons
    // 1023 fruits -> 10 comparisons
}
