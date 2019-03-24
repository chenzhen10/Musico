package by.epam.kimbar.util;

import by.epam.kimbar.entity.News;
import by.epam.kimbar.entity.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains method w can convert from id tag to tag name and list of all available tag
 */
public class TagConverter {

    /** private empty constructor  don't need to create an entity  */
    private TagConverter(){}

    /**
     *Method convert id tag to tag name
     * @param idTag
     *          Id tag which must be converted
     * @return name of the tag
     */
    public static String convert(int idTag){
        String result = null ;
        Tag[] all = Tag.values();
        --idTag;
        for(Tag tag : all ){
            if(idTag == tag.ordinal()){
                result = tag.name();
                break;
            }
        }
        return result;
    }

    /**
     * Method return all available tags on base which we can create news
     * @param newsList
     * @return list of all tags
     */
   public static List<String> getNameOfTag(List<News> newsList){
        List<String> tagList = new ArrayList<>();
        for(News news : newsList){
            tagList.add(TagConverter.convert(news.getIdTag()));
        }
        return tagList;
    }

}
