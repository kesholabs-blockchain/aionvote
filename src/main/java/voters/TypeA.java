package voters;

import avm.Address;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;

public class TypeA {

    /**
     * This is yes/No Polls
     * */

    public static String description;
    public static Address owner;
    public static boolean isPassed;
    public static AionMap<Integer, TypeA> quiz = new AionMap<>();

    TypeA(String description, Boolean isPassed) {
        this.description = description;
        this.isPassed = isPassed;
    }

}
