package ieee754;

public enum Signs {
    MINUS {
        // -1, 0, 1
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : NONE;
        }
    }, MINUS_PLUS {
        // -1, +0, +1
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : '+';
        }
    }, MINUS_NONE_PLUS {
        // -1, 0, +1
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : (x > 0) ? '+' : NONE;
        }
    }, MINUS_SPACE_PLUS {
        // -1,  0, +1
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : (x > 0) ? '+' : ' ';
        }
    };

    static char NONE = 0;

    abstract char sign(double x);
}
