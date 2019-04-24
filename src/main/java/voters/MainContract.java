package voters;

import avm.Address;
import avm.Blockchain;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;
import org.aion.avm.userlib.AionSet;
import org.aion.avm.userlib.abi.ABIDecoder;

import java.math.BigInteger;
import java.util.List;

public class MainContract {

    private static AionSet<Address> members = new AionSet<>();
    private static int minimumQuorum;
    private static int counter;
    private static Address owner;
    public static AionMap<String, Address> memberVote = new AionMap<>();
    public static AionMap<Integer, TypeA> quizA = new AionMap<>();
    public static AionMap<Integer, TypeB> quizB = new AionMap<>();
    public static AionMap<Integer, TypeC> quizC = new AionMap<>();


    @Callable
    public static String createPoll(String type, String poll){
        String validate = TypeA.checkDescriptionLength(poll);
        if(validate.startsWith("Invalid")){
            return "Failed To Create Post";
        }

        switch (type){
            case "typeA":
                return createTypeAPoll(poll);
            case "typeB":
                return createTypeBPoll(poll);
            case "typeC":
                return createTypeCPoll(poll);
            default:
                return "No type found";
        }
    }

    public static String createTypeAPoll(String description){
        Blockchain.println("CREATE TYPE A POLL");
        TypeA proposal = new TypeA(description, false);
        int proposalId = quizA.size();
        quizA.put(proposalId, proposal);
        Blockchain.log("NewProposalAdded".getBytes(), Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap(), description.getBytes());
        return description;
    }

    public static String createTypeBPoll(String description){
        Blockchain.println("CREATE TYPE B POLL");
        TypeB proposal = new TypeB(description, false);
        int proposalId = quizB.size();
        quizB.put(proposalId, proposal);
        Blockchain.log("NewProposalAdded".getBytes(), Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap(), description.getBytes());
        return description;
    }

    public static String createTypeCPoll(String description){
        Blockchain.println("CREATE TYPE C POLL");
        TypeC proposal = new TypeC(description, false);
        int proposalId = quizC.size();
        quizC.put(proposalId, proposal);
        Blockchain.log("NewProposalAdded".getBytes(), Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap(), description.getBytes());
        return description;
    }

    @Callable
    public static String getProposalDescription(int proposalId, String type) {
        switch (type){
            case "typeA":
                return quizA.containsKey(proposalId) ? quizA.get(proposalId).description : null;
            case "typeB":
                return quizB.containsKey(proposalId) ? quizB.get(proposalId).description : null;
            case "typeC":
                return quizC.containsKey(proposalId) ? quizC.get(proposalId).description : null;
            default:
                return "No type found";
        }
    }

    @Callable
    public static  boolean isMember(Address address) {
        return members.contains(address);
    }

    @Callable
    public static void addMember(Address newMember) {
        members.add(newMember);
        Blockchain.println("MemberAdded "+ newMember.unwrap());

    }

    @Callable
    public static int getBalance(Address address){
        int balance = Blockchain.getBalance(address).byteValue();
        return balance;
    }

    @Callable
    public static void vote(int proposalId, Address member, String status, int answers){
        int totalVotes = getBalance(member)/answers;
        addMember(member);  //All members

        switch (status){
            case "typeA":
                TypeA votedProposal = quizA.get(proposalId);
                votedProposal.voters.add(Blockchain.getCaller());
                memberVote.put(status,member);
                counter += totalVotes;
                Blockchain.log("Voted".getBytes(), Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap());
                break;
            case "typeB":
                break;
            case "typeC":
                break;
            default:
                Blockchain.println("Status doesnt EXIST");
                break;
        }
    }

    @Callable
    public static int getVotes() {
        return counter;
    }
}
