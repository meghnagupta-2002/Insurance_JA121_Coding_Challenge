package org.insurance.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.insurance.entity.Policy;
import org.insurance.exception.*;
import org.insurance.util.PropertyUtil;

public class InsuranceServiceImp implements IPolicyService{
	
	//etract connection string from DBPropertyUtil
	static String connectionString = PropertyUtil.getPropertyString();
	
	
	public InsuranceServiceImp() {
		super();
	}
	
	//-----------------------------create new policy-------------------------
	public boolean createPolicy(Policy policy) {
		try (Connection connection = DriverManager.getConnection(connectionString);
	            //sql query to insert new policy into Policy Table 
				PreparedStatement preparedStatement = connection.prepareStatement(
	                     "insert into Policy (policyID, policyName, policyType,coverageAmount) values(?, ?, ?, ?)"
	             )) {
			// set values into sql query for inserting data
	            preparedStatement.setInt(1, policy.getPolicyID());
	            preparedStatement.setString(2, policy.getPolicyName());
	            preparedStatement.setString(3, policy.getPolicyType());
	            preparedStatement.setDouble(4, policy.getCoverageAmount());
	            // run and return row count
	            int rowsAffected = preparedStatement.executeUpdate();
	            //retrun true is row is inserted otherwise return false
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	}

	//--------------------get policy using policyID--------------------------
	@Override
	public Policy getPolicy(int policyID) throws PolicyNotFoundException {
		try (Connection connection = DriverManager.getConnection(connectionString);
	            //sql query to get policy details from Policy Table using policyID 
				PreparedStatement preparedStatement = connection.prepareStatement(
	                     "select * from Policy where policyID = ?"
	             )) {
				//set value into sql query for getting policy details
	            preparedStatement.setInt(1, policyID);
	            //run and return output produced by sql query
	            ResultSet resultSet = preparedStatement.executeQuery();

	            if (resultSet.next()) {
	            	// extract data from output
	                int policyId=resultSet.getInt("policyId");
	                String policyName=resultSet.getString("policyName");
	                String policyType=resultSet.getString("policyType");
	                double coverageAmount=resultSet.getDouble("coverageAmount");
	                Policy policy=new Policy(policyId,policyName,policyType,coverageAmount);
	                //return policy object
	                return policy;
	            } else {
	            	throw new PolicyNotFoundException("Policy with ID " + policyID + " not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new PolicyNotFoundException("Error while retrieving policy with ID " + policyID);
	        }
	}

	//------------------------------get details of all policies--------------------------------
	@Override
	public Collection<Policy> getAllPolicies() throws PolicyNotFoundException {
		List<Policy> policies = new ArrayList<>();
		
        try (Connection connection = DriverManager.getConnection(connectionString);
        		//sql query to get details of all policies which exist in Policy Table 
				PreparedStatement preparedStatement = connection.prepareStatement(
						"select * from Policy"
	             )){
        	//run and return output produced by sql query
            ResultSet resultSet = preparedStatement.executeQuery(); 
            // extract data from output by iterating over output obtained
            while (resultSet.next()) {
            	int policyId=resultSet.getInt("policyId");
                String policyName=resultSet.getString("policyName");
                String policyType=resultSet.getString("policyType");
                double coverageAmount=resultSet.getDouble("coverageAmount");
                Policy policy=new Policy(policyId,policyName,policyType,coverageAmount);
                policies.add(policy);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PolicyNotFoundException("Error while retrieving all policies");
        }
        //return collection of policies
        return policies;
	}

	//update policy using policyID
	@Override
	public boolean updatePolicy(Policy policy) {
		try (Connection connection = DriverManager.getConnection(connectionString);
				//sql query to update policy details in Policy Table using policyID
				PreparedStatement preparedStatement = connection.prepareStatement(
	                     "update Policy set policyName = ?, policyType = ?, coverageAmount = ? WHERE policyID = ?"
	             )) {
				// set values into sql query for updateing data
	            preparedStatement.setString(1, policy.getPolicyName());
	            preparedStatement.setString(2, policy.getPolicyType());
	            preparedStatement.setDouble(3, policy.getCoverageAmount());
	            preparedStatement.setInt(4, policy.getPolicyID());
	         // run and return row count
	            int rowsAffected = preparedStatement.executeUpdate();
	            return rowsAffected > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            //return false when error occurs
	            return false;
	        }
	}

	// delete policy using policyID
	@Override
	public boolean deletePolicy(int policyId) throws PolicyNotFoundException {
		try (Connection connection = DriverManager.getConnection(connectionString);
	            //sql query to delete policy details  from Policy Table using policyID 
				PreparedStatement preparedStatement = connection.prepareStatement(
	                     "delete from Policy where policyId = ?"
	             )) {
				// set value into sql query for deleting data
	            preparedStatement.setInt(1, policyId);
	            // run and return row count
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                // return true if policy gets deleted
	            	return true; 
	            } else {
	                throw new PolicyNotFoundException("Policy with ID " + policyId + " not found.");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new PolicyNotFoundException("Error while deleting policy with ID " + policyId);
	        }
	}

}
