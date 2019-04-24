package voters;

import avm.Address;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;
import org.aion.avm.userlib.AionSet;

public class TypeA {

    /**
     * This is yes/No Polls
     * */

    public static String description;
    public static boolean isPassed;
    public static AionSet<Address> voters = new AionSet<>();

    TypeA(String description, Boolean isPassed) {
        this.description = description;
        this.isPassed = isPassed;
    }

    public static String checkDescriptionLength(String description){
        if(description == null ){
            return "Invalid Length, Input Characters";
        }
        return description;
    }

}
