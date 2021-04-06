//  Class created to take data from database to update Display Board Screen
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import nfq_internship_task.DisplayBoard;

/**
 *
 * @author Rengetsu
 */
public class dbDisplayBoard {
        //  Update Display Board Table (Our Service Department Screen)
        public void updateDisplayBoardTable(){
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
                    DefaultTableModel tblModel = (DefaultTableModel)DisplayBoard.screenTable.getModel();
                    tblModel.addRow(tbData);
                }
                con.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    
}