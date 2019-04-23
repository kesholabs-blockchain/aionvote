package voters;


import org.aion.avm.userlib.AionSet;

import java.util.List;

public class TypeCResponseModel {

    public static String description;
    public static AionSet<String> ansewrs;
    public static int id;

    TypeCResponseModel(String description, AionSet<String>ansewrs, int id){
        this.description = description;
        this.id = id;
        this.ansewrs = ansewrs;
    }
}
