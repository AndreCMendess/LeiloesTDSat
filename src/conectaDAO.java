
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class conectaDAO {
    
        String url = "jdbc:mysql://localhost/leilao";
        String user = "root";
        String password = "dede1234.";
    
    public Connection connectDB(){
        Connection conn = null;
        
        try {
        
            conn = DriverManager.getConnection(url,user,password);
            
        } catch (SQLException erro){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }
    
    public void desconectDB(Connection conn){
        
        if(conn != null ){
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(conectaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
