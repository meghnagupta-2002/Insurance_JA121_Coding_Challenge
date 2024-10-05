package org.insurance.dao;

import java.util.Collection;

import org.insurance.entity.Policy;
import org.insurance.exception.*;

public interface IPolicyService {
	/*non implemented methods for 
	 * creating new policy
	 * getting policy by policyID
	 * getting details of all policies
	 * updating policy by policyID
	 * deleting policy by policyID
	*/
	boolean createPolicy(Policy policy);
    Policy getPolicy(int policyId) throws PolicyNotFoundException;
    Collection<Policy> getAllPolicies() throws PolicyNotFoundException;
    boolean updatePolicy(Policy policy);
    boolean deletePolicy(int policyId) throws PolicyNotFoundException ;
}
