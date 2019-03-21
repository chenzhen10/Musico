package by.epam.kimbar.util;

import by.epam.kimbar.entity.News;
import by.epam.kimbar.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagConverter {
    private TagConverter(){}

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

   public static List<String> getNameOfTag(List<News> newsList){
        List<String> tagList = new ArrayList<>();
        for(News news : newsList){
            tagList.add(TagConverter.convert(news.getIdTag()));
        }
        return tagList;
    }

}
