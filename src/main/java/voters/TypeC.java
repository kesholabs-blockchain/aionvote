package voters;

import avm.Address;
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
//    public static List<String> answers;
    public static boolean isPassed;
    AionSet<Address> voters = new AionSet<>();

    TypeC(String description, boolean isPassed ) {
        this.description = description;
        this.isPassed = isPassed;
    }
}
