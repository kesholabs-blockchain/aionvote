package voters;


import java.util.List;

public class TypeCResponseModel {

    public static String description;
    public static List<String> ansewrs;
    public static int id;

    TypeCResponseModel(String description, List<String>ansewrs, int id){
        this.description = description;
        this.id = id;
        this.ansewrs = ansewrs;
    }
}
