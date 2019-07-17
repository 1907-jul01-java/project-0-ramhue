package com.revature;

public class Ac{
 
    int id;
    int acctnum;
    int custId;

    public Ac(int acctnum, int custId) {
        this.acctnum = acctnum;
        this.custId = custId;
    }
    public Ac() {
        super();
	}
    public int getAcctnum() {
        return acctnum;
    }

    public void setAcctnum(int acctnum) {
        this.acctnum = acctnum;
    }

    public int getcustId() {
        return custId;
    }

    public void setcustId(int custId) {
        this.custId = custId;
    }
    public int getId(){
        return id;
    }
    public void setId(int  id){
        this.id = id;
    }
    @Override
    public String toString() {
        return "Ac [acctnum=" + acctnum + ", custId=" + custId + "]";
    }

    
}

