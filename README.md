Task Manager (Java + JDBC + MySQL)

A simple console-based Task Manager that demonstrates core JDBC concepts: connecting to a MySQL database, creating a database and table, and performing CRUD operations (Create, Read, Update, Delete) on a to-do list using PreparedStatement.

Features
Reusable database connection utility
Automatic database creation (Task)
Automatic table creation (TOdolist)
Insert, update and delete tasks using parameterized queries (prevents SQL injection)
Tech Stack
Component	Details
Language	Java (JDK 15+ — uses text blocks """)
Database	MySQL 8.x
Driver	MySQL Connector/J (com.mysql.cj.jdbc.Driver)
API	JDBC (java.sql.*)
Project Structure
TaskManager/
├── DBConnection.java     # Provides a reusable MySQL connection
├── CreateDataBase.java   # Creates the "Task" database
├── CreateTable.java      # Creates the "TOdolist" table
└── AllOperations.java    # Add / Update / Delete task operations
File Overview
File	Purpose
DBConnection.java	Loads the MySQL driver and returns a Connection via getConnection().
CreateDataBase.java	Runs CREATE DATABASE IF NOT EXISTS Task.
CreateTable.java	Creates the TOdolist table if it doesn't exist.
AllOperations.java	Contains AddTask(), UpdateTask() and DeleteTask().
Database Schema

Database: Task Table: TOdolist

Column	Type	Constraint
id	INT	Primary Key
name	VARCHAR(50)	—
status	VARCHAR(50)	—
Sample Data (inserted by AddTask())
id	name	status
101	Intership Application	InProgress
102	Java Certification Course	Not Completed
103	Registration for Hackthon	Completed
Prerequisites
JDK 15 or higher installed
MySQL Server running on localhost:3306
MySQL Connector/J JAR added to your project's classpath (or as a Maven dependency):
xml
   <dependency>
       <groupId>com.mysql</groupId>
       <artifactId>mysql-connector-j</artifactId>
       <version>8.4.0</version>
   </dependency>
Configuration

Open DBConnection.java and set your MySQL credentials:

java
private static final String URL = "jdbc:mysql://localhost:3306/";
private static final String User = "root";
private static final String password = "<your-mysql-password>";

Important: CreateTable and AllOperations need the connection to point at the Task database. After running CreateDataBase once, update the URL to:

java
private static final String URL = "jdbc:mysql://localhost:3306/Task";

Otherwise MySQL will throw a No database selected error.

How to Run

Run the classes in this order:

Create the database — run CreateDataBase.java Output: Database Created
Switch the URL in DBConnection.java to jdbc:mysql://localhost:3306/Task
Create the table — run CreateTable.java Output: Table Created
Perform operations — in AllOperations.java, call the method you want from main:
java
   public static void main(String[] args) {
       AddTask();        // insert 3 sample rows
       // UpdateTask();  // mark task 102 as "Completed"
       // DeleteTask();  // delete task 103
   }

Then run AllOperations.java.

Command line (optional)
bash
javac -cp ".;mysql-connector-j-8.4.0.jar" TaskManager/*.java
java  -cp ".;mysql-connector-j-8.4.0.jar" TaskManager.CreateDataBase
java  -cp ".;mysql-connector-j-8.4.0.jar" TaskManager.CreateTable
java  -cp ".;mysql-connector-j-8.4.0.jar" TaskManager.AllOperations

On Linux/macOS, use : instead of ; as the classpath separator.

Operations Summary
Method	SQL	Effect
AddTask()	INSERT INTO TOdolist VALUES (?,?,?)	Adds tasks 101, 102, 103
UpdateTask()	UPDATE TOdolist SET status=? WHERE id=?	Sets task 102 to Completed
DeleteTask()	DELETE FROM TOdolist WHERE id=?	Removes task 103
Notes & Known Limitations
Running AddTask() twice will fail with a duplicate primary key error (IDs 101–103 already exist).
Task values are currently hard-coded; there is no user input or menu yet.
There is no SELECT / view operation yet.
Database credentials are stored in source code — avoid committing real passwords to GitHub.
Future Improvements
 Add a ViewTasks() method (SELECT) to list all tasks
 Accept user input via Scanner for a menu-driven console app
 Use AUTO_INCREMENT for the id column
 Load credentials from environment variables or a config.properties file
 Close Connection, Statement and PreparedStatement using try-with-resources
 Build a GUI (Swing/JavaFX) or a web front end
Author

Riddhika — B.E. Computer Science (AI & ML)
