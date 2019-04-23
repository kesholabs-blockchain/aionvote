package voters;

public class TypeAResponseModel {

    public static String description;
    public static Boolean decline;
    public static Boolean accept;
    public static int id;

    TypeAResponseModel(String description, Boolean decline, Boolean accept, int id){
        this.description = description;
        this.decline = decline;
        this.accept = accept;
        this.id = id;
    }

}
