package by.epam.kimbar.util.cheeker;


import by.epam.kimbar.entity.Tag;

public class TagChecker {

    private TagChecker() {
    }

    public static int check(String tag) {
        return Tag.valueOf(tag).ordinal() + 1;
    }
}
