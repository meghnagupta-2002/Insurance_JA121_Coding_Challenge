package org.insurance.main;

import java.util.Collection;
import java.util.Scanner;

import org.insurance.dao.*;
import org.insurance.entity.Policy;
import org.insurance.exception.*;

public class MainModeule {
	//scan variable to get input from user
	static Scanner scan = new Scanner(System.in);
	static InsuranceServiceImp policy = new InsuranceServiceImp(); //upcasting
	
	
	public static void main(String[] args) {
		while(true){
			System.out.println("\n*** Policy Menu ***");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int opt = scan.nextInt();
            switch(opt) {
            //----------------------------CASE 1---------------------------
            case 1: 
            	System.out.println("Enter Policy ID:");
            	int policyID = scan.nextInt();
            	System.out.println("Enter Policy Name:");
            	scan.nextLine(); 
            	String policyName = scan.nextLine();
            	System.out.println("Enter Policy Type:");
            	String policyType = scan.nextLine();
            	System.out.println("Enter Coverage Amount:");
            	double coverageAmount = scan.nextDouble();

            	Policy newPolicy = new Policy(policyID, policyName, policyType, coverageAmount);

            	if (policy.createPolicy(newPolicy)) {
            		System.out.println("Policy Created!");
            	} else {
            		System.out.println("Failed to Create Policy!");
            	}	
            	break;
            //-----------------------CASE 2--------------------------------------
            case 2:
            	try {
            		System.out.println("Enter Policy ID:");
            	     int pid = scan.nextInt();
            	     Policy getPolicy = policy.getPolicy(pid);
            	     if (getPolicy != null) {
            	         System.out.println("Extracted Policy: " + getPolicy);
            	     } else {
            	         System.out.println("Policy with ID " + pid + " not found.");
            	     }
            	}catch(PolicyNotFoundException err){
            		err.printStackTrace();
            	}
            	break;
            //----------------------------CASE 3------------------------------	
            case 3:
            	try {
            		Collection<Policy> allPolicies = policy.getAllPolicies();

            	     if (allPolicies != null && !allPolicies.isEmpty()) {
            	         System.out.println("Details of All Policies are:");
            	         for (Policy policy : allPolicies) {
            	             System.out.println(policy);
            	         }
            	     } else {
            	         System.out.println("No policies found!");
            	     }
            	}catch(PolicyNotFoundException err){
            		err.printStackTrace();
            	}
            	break;
            //---------------------CASE 4--------------------------	
            case 4:
            	try{System.out.println("Enter Policy ID:");
                int pid = scan.nextInt();
                Policy updatePolicy = policy.getPolicy(pid);

                if (updatePolicy != null) {
                    System.out.println("Enter New Policy Name:");
                    scan.nextLine(); 
                    String updatePolicyName = scan.nextLine();
                    updatePolicy.setPolicyName(updatePolicyName);
                    System.out.println("Enter New Policy Type");
                    String updatePolicyType= scan.nextLine();
                    updatePolicy.setPolicyType(updatePolicyType);
                    System.out.println("Enter New Coverage Amount:");
                    double updateCoverageAmount = scan.nextDouble();
                    updatePolicy.setCoverageAmount(updateCoverageAmount);

                    if (policy.updatePolicy(updatePolicy)) {
                        System.out.println("Policy updated!");
                    } else {
                        System.out.println("Failed to update Policy!");
                    }
                } else {
                    throw new PolicyNotFoundException("Policy with ID " + updatePolicy + " not found.");
                }
            	}catch(PolicyNotFoundException err){
            		err.printStackTrace();
            	}
            	break;
            //-------------------------CASE 5----------------------    
            case 5:
            	try {
            		System.out.println("Enter Policy ID:");
            	    int pid = scan.nextInt();

            	    if (policy.deletePolicy(pid)) {
            	        System.out.println("Policy deleted successfully!");
            	    } else {
            	        System.out.println("Policy with ID " + pid + " not found.");
            	    }
            	}catch(PolicyNotFoundException err){
            		err.printStackTrace();
            	}
            	break;
            //----------------CASE 6----------------------------------
            case 6:
            	System.out.println("Thank You. Please Visit Again");
            	scan.close();
            	System.exit(0);
            	break;
            //------------------------DEFAULT CASE-------------------------
            default:
            	System.out.println("Invalid Option. Please try Again!");
            }
		}
	}

}
