package fruit;

public class LinearSearch {
    public static final String[] FRUITS = {
            "passionfruit",
            "plum",
            "apple",
            "blackberry",
            "blueberry",
            "fig",
            "apricot",
            "cucumber",
            "banana",
            "dragonfruit"};

    private static int comparisons = 0;

    public static boolean isFruit(String s) {
        System.out.println("\n? " + s);

        for (String fruit : FRUITS) {
            System.out.print(fruit + " ");
            comparisons += 1;
            if (s.equals(fruit)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        for (String fruit : FRUITS) {
            if (!isFruit(fruit)) throw new AssertionError();
        }
        if (isFruit("carrot")) throw new AssertionError();
        if (isFruit("brussels sprout")) throw new AssertionError();
        System.out.println("\n" + comparisons + " comparisons");
    }
}
