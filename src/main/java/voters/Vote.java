package voters;

import avm.Address;
import avm.Blockchain;
import org.aion.avm.tooling.abi.Callable;
import org.aion.avm.userlib.AionMap;

public class Vote {

    public static AionMap<Address, Proposal> postVoted  = new AionMap<>();

    /**
     * Get the post ID
     * Check if the voter is validated - has amount in the wallet
     * Vote as per the amount in the wallet ratio 1:1
     * i.e
     * 10aions = 10 votes
     * if the voter votes for a specific post. blacklist the address
     * */

    @Callable
    public static void vote(int proposalId, boolean isLiked, Address address) {
        Blockchain.require(Posts.proposals.get(proposalId) != postVoted.get(address) && Posts.proposals.containsKey(proposalId));

        Proposal votedProposal = Posts.proposals.get(proposalId);

        Blockchain.log("Voted".getBytes(), Integer.toString(proposalId).getBytes(), Blockchain.getCaller().unwrap());

        double count = 0;

        if(!isLiked){
           count = Proposal.setIsNotLiked(address);
        }else{
            count = Proposal.setIsLiked(address);
        }

        postVoted.put(address, votedProposal);
        Proposal.isVoter(address);
        countVotes(votedProposal,count);
    }


    /**
     * Check if the number of voters (size) != 1/2 + 1
     * Check if the number of voters (size) < 1/2 + 1
     * add the number of votes to a map container key{proposal} value{total count/ votes}
     */
    @Callable
    public static void countVotes(Proposal votedProposal, double count){
        if (votedProposal.voters.size() != Posts.minimumQuorum || votedProposal.voters.size() < Posts.minimumQuorum) {
            votedProposal.isPassed = true;
            Proposal.finalCount.put(votedProposal, (int) count);
            Blockchain.log("ProposalPassed".getBytes(), String.valueOf( (int) count).getBytes());
        }
    }
}
