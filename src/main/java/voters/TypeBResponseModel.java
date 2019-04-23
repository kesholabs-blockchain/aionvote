package voters;

import org.aion.avm.userlib.AionSet;

import java.util.List;

public class TypeBResponseModel {

    public static String description;
    public static AionSet<String> ansewrs;
    public static int id;

    TypeBResponseModel(String description, AionSet<String>ansewrs, int id){
        this.description = description;
        this.id = id;
        this.ansewrs = ansewrs;
    }
}
