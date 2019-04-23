package voters;

import avm.Address;
import avm.Blockchain;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;
import org.aion.avm.userlib.AionSet;

import java.math.BigInteger;
import java.util.Date;

public  class Proposal {

    public static String description;
    public static Address owner;
    public static double isLiked;
    public static double isNotLiked;
    public static int votes;
    public static boolean isPassed;
//    public static Date dateCreated;
//    public static Date endDate;
    public static AionSet<Address> voters = new AionSet<>();
    public static AionMap<Proposal, Integer> finalCount = new AionMap<>();

    Proposal(String description, Address owner) {
        this.description = description;
        this.owner = owner;
    }


    @Callable
    public static double getIsLiked() {
        return isLiked;
    }

    @Callable
    public static double setIsLiked(Address votersAdd){
        BigInteger walletAmt = validateWallet(votersAdd);

        if(walletAmt.byteValue() <= 0){
            return 0;
        }

        voters.add(votersAdd);
        return isLiked += walletAmt.byteValueExact();
    }

    @Callable
    public static double getIsNotLikedLiked() {
        return isNotLiked;
    }

    @Callable
    public static double setIsNotLiked(Address votersAdd){
        BigInteger walletAmt = validateWallet(votersAdd);

        if(walletAmt.byteValue() <= 0){
            return 0;
        }
        voters.add(votersAdd);
        return isNotLiked += walletAmt.byteValueExact();
    }

    @Callable
    public static boolean isVoter(Address address) {
        return voters.contains(address);
    }

//    public Date getDateCreated(){
//        return new Date();
//    }

    private static BigInteger validateWallet(Address address){
        return Blockchain.getBalance(address);
    }

}


