package ieee754;

public enum Fractionals {
    TWO {
        @Override
        int cut(char[] chars) {
            return chars.length;
        }
    }, ONE_TWO {
        @Override
        int cut(char[] chars) {
            int right = chars.length - 1;
            while (chars[right] == '0') --right;
            if (chars[right] == ',') ++right;
            return right + 1;
        }
    }, ZERO_ONE_TWO {
        @Override
        int cut(char[] chars) {
            int right = chars.length - 1;
            while (chars[right] == '0') --right;
            if (chars[right] == ',') --right;
            return right + 1;
        }
    };

    abstract int cut(char[] chars);
}
