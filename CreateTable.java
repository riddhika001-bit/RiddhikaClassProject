CreateTable:

package TaskManager;
import java.sql.*;
public class CreateTable {
   public static void main(String[]args) throws Exception{
    Connection c=DBConnection.getConnection();
    String table="""
    Create table if not exists TOdolist(
    id int primary key,
    name varchar(50),
    status VARCHAR(50)
    )
    """;
    Statement s=c.createStatement();
    System.out.println("Table Created");
    s.executeUpdate(table);



}

}
