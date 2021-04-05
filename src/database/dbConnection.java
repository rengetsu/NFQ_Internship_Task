//  Class created for connection to database with login window
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Rengetsu
 */
public class dbConnection {
    
    //  Main connection to database function
    public void connectionStart(String usernameText, String passwordText)
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
            String sql = "Select * from login where username=? and password=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usernameText);
            pst.setString(2, passwordText);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                //  If OK show message that everything is OK
                JOptionPane.showMessageDialog(null, "Username and Password OK");
            }
            else{
                JOptionPane.showMessageDialog(null, "Error! Username and Password do not match.");
            }
            con.close();
        }
        catch(Exception e){
            //  Show error message to user
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
