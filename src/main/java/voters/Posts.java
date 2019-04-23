package voters;

import avm.Address;
import avm.Blockchain;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;
import org.aion.avm.userlib.AionSet;
import org.aion.avm.userlib.abi.ABIDecoder;

public class Posts {


    public static Address owner;
    public static int minimumQuorum;
    public static AionMap<Integer, Proposal> proposals = new AionMap<>();

    static {
        ABIDecoder decoder = new ABIDecoder(Blockchain.getData());

        Address[] arg = decoder.decodeOneAddressArray();
        for (Address addr:arg) {
            Proposal.voters.add(addr);
        }
        updateQuorum();
        owner = Blockchain.getCaller();
    }

    @Callable
    public static String addPost(String description) {
        Blockchain.require(owner.equals(Blockchain.getCaller()));

        Proposal proposal = new Proposal(description, Blockchain.getCaller());
        int proposalId = proposals.size();
        proposals.put(proposalId, proposal);

        Blockchain.log("NewPostlAdded".getBytes(), Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap(), description.getBytes());
        return String.format("NewPostlAdded "+Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap(), description.getBytes());
    }

    @Callable
    public static String getProposals() {
         return proposals.toString();
    }

    @Callable
    public static int getMinimumQuorum() {
        return minimumQuorum;
    }

    private static void updateQuorum() {
        minimumQuorum = Proposal.voters.size() / 2 + 1;
    }

//    private static void onlyOwner() {
//         Blockchain.require(owner.equals(Blockchain.getCaller()));
//    }
}




