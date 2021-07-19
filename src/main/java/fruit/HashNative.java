package fruit;

public class HashNative {
    private static final String[][] HASH_TABLE = {
            {},
            {"dragonfruit"},
            {"apricot"},
            {},
            {"fig", "plum"},
            {"banana"},
            {"cucumber"},
            {"blackberry"},
            {},
            {},
            {"apple"},
            {},
            {"blueberry"},
            {"passionfruit"},
            {},
            {}};

    private static int comparisons = 0;

    public static boolean isFruit(String s) {
        System.out.println("\n? " + s);
        int index = s.hashCode() & 0b1111;
        String[] bucket = HASH_TABLE[index];

        for (String fruit : bucket) {
            System.out.print(fruit + " ");
            comparisons += 1;
            if (s.equals(fruit)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        for (String fruit : LinearSearch.FRUITS) {
            int hash = fruit.hashCode();
            int index = hash & 0b1111;
            System.out.printf("%08x [%2d] %s %n", hash, index, fruit);
        }
        System.out.printf("%08x %n", ('f' * 31 + 'i') * 31 + 'g');
        System.out.printf("%08x %n", (('p' * 31 + 'l') * 31 + 'u') * 31 + 'm');

        for (String fruit : LinearSearch.FRUITS) {
            if (!isFruit(fruit)) throw new AssertionError();
        }
        if (isFruit("carrot")) throw new AssertionError();
        if (isFruit("brussels sprout")) throw new AssertionError();
        System.out.println("\n" + comparisons + " comparisons");
    }
}
