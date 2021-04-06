//  Class created to add and edit visits from visit table in database
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nfq_internship_task.DisplayBoard;
import nfq_internship_task.Reservation;
import nfq_internship_task.VisitManagement;

/**
 *
 * @author Rengetsu
 */
public class dbVisit {
    
    dbDisplayBoard  dbdb    = new dbDisplayBoard();
    
    //  Function return how many visits we have now to know what id add to database
    public int howManyVisits()
    {
        //dbdb.updateDisplayBoardTable();
        
        try{
            //  Connect to db
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
                
                Statement st = con.createStatement();
                // MySQL Query
                String sql = "select * from visit";
                ResultSet rs = st.executeQuery(sql);
                
                //  Delete old data from the table
                DefaultTableModel dm = (DefaultTableModel) DisplayBoard.screenTable.getModel();
                int rowCount = dm.getRowCount();
                con.close();
                return rowCount;
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
                return 0;
            }
    }
    
    public void addNewVisit(int custNumber, String customerName, int specNumber, String visitStatus, String visitDateTime){
        try{
            //  Connect to db
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
                
                //  Choosing specialist name
                String specName;
                if( specNumber == 1){specName = "Dr. Kabajashi Bashi";}
                else{specName = "Kakashi Hatake";}
             
                String sql = "insert into visit values (?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, custNumber);
                pstmt.setInt(2, specNumber);
                pstmt.setString(3, visitDateTime);
                pstmt.setString(4, specName);
                pstmt.setString(5, customerName);
                pstmt.setString(6, visitStatus);
                
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Your visit has been successfully registered! ");
                con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void getVisit(int visitId)
    {
        try{
            //  Connect to db
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
                
                //  Choosing specialist name
                String specName;
                
                String sql = "SELECT `datetime`, `spec_name`, `customer_name`, `status` FROM `visit` WHERE id_visit = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, visitId);
                
                ResultSet rs = pstmt.executeQuery();
                
                if( rs.next() )
                {
                    String datetm   = rs.getString("datetime");
                    String specnm   = rs.getString("spec_name");
                    String custnm   = rs.getString("customer_name");
                    String status   = rs.getString("status");
                    
                    VisitManagement.dateRez.setText(datetm);
                    VisitManagement.specRez.setText(specnm);
                    VisitManagement.nameRez.setText(custnm);
                    VisitManagement.statusRez.setText(status);
                    VisitManagement.cancelButton.setEnabled(true);
                }
                
                con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void deleteVisit(int visitId)
    {
        String sql = "DELETE FROM `visit` WHERE `id_visit` = ?";
        try{
            //  Connect to db
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
                
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setInt(1, visitId);
                
                pst.execute();
                JOptionPane.showMessageDialog(null, "Your visit has been successfully deleted! ");
                
                con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}