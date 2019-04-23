package voters;

import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionList;
import org.aion.avm.userlib.AionMap;
import org.aion.avm.userlib.AionSet;

import java.util.List;

public class TypeC {

    /**
     * This is multiple answer Polls
     */

    public static String description;
    public static AionSet<String> answers;
    public static AionMap<Integer, TypeC> quiz = new AionMap<>();

    TypeC(String description, AionSet<String> answers ) {
        this.description = description;
        this.answers = answers;
    }
}
