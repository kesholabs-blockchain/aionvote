package voters;

import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;

import java.util.List;

public class TypeC {

    /**
     * This is multiple answer Polls
     */

    public static String description;
    public static List<String> answers;
    public static AionMap<Integer, TypeC> quiz = new AionMap<>();

    TypeC(String description, List<String> answers ) {
        this.description = description;
        this.answers = answers;
    }
}
