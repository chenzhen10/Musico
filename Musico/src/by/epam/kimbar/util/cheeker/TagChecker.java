package by.epam.kimbar.util.cheeker;


import by.epam.kimbar.entity.Tag;

/**
 * This class convert name of the tag to id when we creating news
 */
public class TagChecker {
    /** Enum start count from 0 and then we should add +1 to get right sequences of the enum  */
    private static final int CORRECT_NUM_OF_SEQENCES  = 1;

    /** private empty constructor  don't need to create an entity  */
    private TagChecker() {
    }

    /**
     * Method convert from tag name to id tag to create new news
     * @param tag
     *      Name of the tag
     * @return
     */
    public static int check(String tag) {
        return Tag.valueOf(tag).ordinal() + CORRECT_NUM_OF_SEQENCES;
    }
}
