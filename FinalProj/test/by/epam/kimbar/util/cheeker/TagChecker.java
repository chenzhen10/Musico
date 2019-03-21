package by.epam.kimbar.util.cheeker;


import by.epam.kimbar.entity.Tag;

public class TagChecker {
    private static final int CORRECT_NUM_OF_SEQENCES  = 1;

    private TagChecker() {
    }

    public static int check(String tag) {
        return Tag.valueOf(tag).ordinal() + CORRECT_NUM_OF_SEQENCES;
    }
}
