package voters;

import avm.Address;

public class CountHolder {

    int proposalID;
    Object description;
    Address address;
    boolean answer;

    CountHolder( int proposalID,Object description,boolean answer, Address address){
        this.proposalID = proposalID;
        this.description = description;
        this.answer = answer;
        this.address = address;
    }


}
