package voters;

import avm.Address;
import org.aion.avm.userlib.AionMap;
import org.aion.avm.userlib.AionSet;

import java.util.List;

public class TypeB {
    /**
     * This is single answer Polls
     */

    public static String description;
    public static String answers;
    public static boolean isPassed;
    AionSet<Address> voters = new AionSet<>();


    TypeB(String description, boolean isPassed ) {
        this.description = description;
        this.isPassed = isPassed;
    }
}
