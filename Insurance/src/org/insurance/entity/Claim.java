package org.insurance.entity;

import java.util.Date;

public class Claim {
	
	
	private int claimID;
    private String claimNumber;
    private Date dateFiled;
    private double claimAmount;
    private String status;
    private Policy policy;   
    private Client client;
    
    
    public Claim() {
    	super();
    }
    
    
    public Claim(int claimID, String claimNumber, Date dateFiled, double claimAmount, String status, Policy policy, Client client) {
        this.claimID = claimID;
        this.claimNumber = claimNumber;
        this.dateFiled = dateFiled;
        this.claimAmount = claimAmount;
        this.status = status;
        this.policy = policy;
        this.client = client;
    }
    
    
    public int getclaimID() {
        return claimID;
    }
    public void setclaimID(int claimID) {
        this.claimID = claimID;
    }

    public String getClaimNumber() {
        return claimNumber;
    }
    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public Date getDateFiled() {
        return dateFiled;
    }
    public void setDateFiled(Date dateFiled) {
        this.dateFiled = dateFiled;
    }

    public double getClaimAmount() {
        return claimAmount;
    }
    public void setClaimAmount(double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Policy getPolicy() {
        return policy;
    }
    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

  
    @Override
    public String toString() {
        return "Claim [claimID=" + claimID + ", claimNumber=" + claimNumber + ", dateFiled=" + dateFiled 
            + ", claimAmount=" + claimAmount + ", status=" + status + ", policy=" + policy + ", client=" + client + "]";
    }
}
