//  Class created for connection to database with login window
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import nfq_internship_task.Login;

/**
 *
 * @author Rengetsu
 */
public class dbConnection {
    
    //  Main connection to database function
    public boolean connectionStart(String usernameText, String passwordText)
    {
        boolean back = false;
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
                JOptionPane.showMessageDialog(null, "Username and Password OK. Now you login!");
                //  Give access as a specialist to a user to enter specialist GUI
                back = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "Error! Username and Password do not match.");
                back = false;
            }
            con.close();
        }
        catch(Exception e){
            //  Show error message to user
            JOptionPane.showMessageDialog(null, e);
            back = false;
        }
        //  Returning true-false to give access or not
        return back;
    }
}