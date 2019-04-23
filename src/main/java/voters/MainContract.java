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
    private static Address owner;
    public static AionMap<Integer, CountHolder> countHolder = new AionMap<>();


    @Callable
    public TypeAResponseModel createTypeAPoll(String description){
        Blockchain.println("CREATE TYPECAPOLL");
        Blockchain.require(!description.isEmpty());
        TypeA proposal = new TypeA(description, false);
        int proposalId = TypeA.quiz.size();
        TypeA.quiz.put(proposalId, proposal);
        return new TypeAResponseModel(description,false,true, proposalId);
    }

    @Callable
    public TypeBResponseModel createTypeBPoll(String description, AionSet<String> answers){
        Blockchain.println("CREATE TYPEB POLL");
        Blockchain.require(!description.isEmpty() && !answers.isEmpty());
        TypeA proposal = new TypeA(description, false);
        int proposalId = TypeA.quiz.size();
        TypeA.quiz.put(proposalId, proposal);
        return new TypeBResponseModel(description,answers, proposalId);
    }

    @Callable
    public TypeCResponseModel createTypeCPoll(String description, AionSet<String> answers){
        Blockchain.println("CREATE TYPEC POLL");
        Blockchain.require(!description.isEmpty() && !answers.isEmpty());
        TypeC proposal = new TypeC(description, answers);
        int proposalId = TypeC.quiz.size();
        TypeC.quiz.put(proposalId, proposal);
        return new TypeCResponseModel(description,answers, proposalId);
    }

    @Callable
    public static boolean isMember(Address address) {
        return members.contains(address);
    }

    @Callable
    public static void addMember(Address newMember) {
        members.add(newMember);
        Blockchain.println("MemberAdded"+ newMember.unwrap());

    }

    @Callable
    public void vote(int proposalId, Address member, String status, List<String> answers, boolean feedback){

        switch (status){
            case "typeA":
                votetypeA(proposalId, feedback, member);
                break;
            case "typeB":
                votetypeB(proposalId, feedback, member);
                break;
            case "typeC":
                votetypeC(proposalId, feedback, member);
                break;
            default:
                Blockchain.println("Status doesnt EXIST");
                break;
        }
    }

    @Callable
    public int getBalance(Address address){
        return Blockchain.getBalance(address).byteValue();
    }

    public void votetypeA(int id, boolean feedback, Address memberAdd){
        addMember(memberAdd);
        int bal = getBalance(memberAdd);
        TypeA typeA = TypeA.quiz.get(id);
        countHolder.put(bal,new CountHolder(id, typeA ,feedback, memberAdd));
    }

    public void votetypeB(int id, boolean feedback, Address memberAdd){
        addMember(memberAdd);
        int bal = getBalance(memberAdd);
        TypeC typeC = TypeC.quiz.get(id);
        countHolder.put(bal,new CountHolder(id, typeC ,feedback, memberAdd));
    }

    public void votetypeC(int id, boolean feedback, Address memberAdd){
        addMember(memberAdd);
        int bal = getBalance(memberAdd);
        TypeC typeC = TypeC.quiz.get(id);
        countHolder.put(bal,new CountHolder(id, typeC ,feedback, memberAdd));
    }

    private static void onlyOwner() {
        Blockchain.require(owner.equals(Blockchain.getCaller()));
    }

}
