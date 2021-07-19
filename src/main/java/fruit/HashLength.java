package fruit;

public class HashLength {
    private static final String[] FRUITS = {
            null,
            null,
            null,
            "fig",
            "plum",
            "apple",
            "banana",
            "apricot",
            "cucumber",
            "blueberry",
            "watermelon",
            "dragonfruit",
            "passionfruit"};

    private static int comparisons = 0;

    public static boolean isFruit(String s) {
        System.out.println("\n? " + s);
        int index = s.length();
        if (index < FRUITS.length) {
            String fruit = FRUITS[index];
            System.out.print(fruit + " ");
            comparisons += 1;
            return s.equals(fruit);
        }
        return false;
    }

    public static void main(String[] args) {
        for (int i = 3; i < FRUITS.length; i++) {
            String fruit = FRUITS[i];
            if (!isFruit(fruit)) throw new AssertionError();
        }
        if (isFruit("carrot")) throw new AssertionError();
        if (isFruit("brussels sprout")) throw new AssertionError();
        System.out.println("\n" + comparisons + " comparisons");
    }
}
