//  Class created to manage customers statuses
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nfq_internship_task.CustomersManagment;

/**
 *
 * @author Rengetsu
 */
public class dbCustomersManagment {
    
    public void updateCustomerStatus(int customerNumber, String newStatus){
        try{
            //  Connect to db
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
                
                //  Choosing specialist name
                String specName;
                
                String sql = "UPDATE `visit` SET `status`='" + newStatus +"' WHERE id_visit = ?";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, customerNumber);
                
                pstmt.execute();
                
                con.close();
                updateCustomersBoard();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void updateCustomersBoard(){
            try{
                //  Connect to db
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfqdb", "root", "");
                
                Statement st = con.createStatement();
                // MySQL Query
                String sql = "select * from visit";
                ResultSet rs = st.executeQuery(sql);
                
                //  Delete old data from the table
                DefaultTableModel dm = (DefaultTableModel) CustomersManagment.customersTable.getModel();
                int rowCount = dm.getRowCount();
                //Remove rows one by one from the end of the table
                for (int i = rowCount - 1; i >= 0; i--) {
                    dm.removeRow(i);
                }
                
                //  Add new data in table
                while(rs.next()){
                    // data will be added until finish
                    String id       = "000" + String.valueOf(rs.getInt("id_visit"));    //conversion due to int value
                    String specid   = String.valueOf(rs.getInt("spec_id"));    //conversion due to int value
                    String date     = String.valueOf(rs.getTimestamp("datetime"));
                    String spcnam   = rs.getString("spec_name");
                    String cusnam   = rs.getString("customer_name");
                    String status   = rs.getString("status");
                    
                    // String array for store data into jtable
                    
                    String tbData[] = {id, specid, date, spcnam, cusnam, status};
                    DefaultTableModel tblModel = (DefaultTableModel)CustomersManagment.customersTable.getModel();
                    tblModel.addRow(tbData);
                }
                con.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    
    public void getCustomerVisit(int visitId)
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
                    
                    CustomersManagment.dateRez.setText(datetm);
                    CustomersManagment.specRez.setText(specnm);
                    CustomersManagment.nameRez.setText(custnm);
                    CustomersManagment.statusRez.setText(status);
                }
                
                con.close();
        
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
