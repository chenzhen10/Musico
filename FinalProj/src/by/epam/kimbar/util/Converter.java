package by.epam.kimbar.util;

import by.epam.kimbar.entity.Tag;

public class Converter {
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
}
