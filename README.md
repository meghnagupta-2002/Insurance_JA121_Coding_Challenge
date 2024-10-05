# Insurance Management System
A Java-based console application for managing insurance policies, using a MySQL database and JDBC for database connectivity.

## Features
- **Create Policy**: Add new insurance policies.
- **Retrieve Policy**: Fetch a policy by its ID.
- **View All Policies**: List all available policies.
- **Update Policy**: Modify existing policy details.
- **Delete Policy**: Remove policies by ID.

## Requirements
- **Java**: JDK 8+
- **MySQL**: Database `insurance` with table `Policy`.
- **JDBC Driver**: MySQL Connector/J (add `mysql-connector-java.jar` to your classpath).

## SQL Table Setup:
- Refer to the "insurance_database.sql" file.

## How to Run
1. Set up MySQL with the database and table as shown above.
2. Update `db.properties` with your database credentials.
3. Add the MySQL JDBC Driver (`mysql-connector-java.jar`) to your project classpath.
4. Compile and run `MainModeule`:
   - `javac -cp .:mysql-connector-java.jar org/insurance/main/MainModeule.java`
   - `java -cp .:mysql-connector-java.jar org.insurance.main.MainModeule`
5. Use the interactive menu to create, view, update, or delete policies. Enter the option number to choose an action.
