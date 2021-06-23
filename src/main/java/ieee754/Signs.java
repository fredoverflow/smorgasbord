package ieee754;

public enum Signs {
    MINUS {
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : NONE;
        }
    }, MINUS_PLUS {
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : '+';
        }
    }, MINUS_NONE_PLUS {
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : (x > 0) ? '+' : NONE;
        }
    }, MINUS_SPACE_PLUS {
        @Override
        char sign(double x) {
            return (x < 0) ? '-' : (x > 0) ? '+' : ' ';
        }
    };

    static char NONE = 0;

    abstract char sign(double x);
}
