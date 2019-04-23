package voters;

import java.util.List;

public class TypeBResponseModel {

    public static String description;
    public static List<String> ansewrs;
    public static int id;

    TypeBResponseModel(String description, List<String>ansewrs, int id){
        this.description = description;
        this.id = id;
        this.ansewrs = ansewrs;
    }
}
