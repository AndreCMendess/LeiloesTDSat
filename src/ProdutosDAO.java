
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProdutosDAO {
    
    Connection conn = new conectaDAO().connectDB();
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public boolean cadastrarProduto (ProdutosDTO produto){
        
        
      
        
        try{
            
            String sql = "INSERT INTO produtos (nome,valor,status) VALUES (?,?,?) ";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getValor());
            ps.setString(3, produto.getStatus());
            ps.executeUpdate();
            ps.close();
            
              
           
            return true;
            
        }catch(Exception e){
            
            System.out.println("Erro ao cadastrar produto no banco de dados" + e.getMessage());
            e.printStackTrace();
        }finally{
            new conectaDAO().desconectDB(conn);
        }
        return false;
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        
        String sql = "SELECT * FROM produtos";
        
        try{
            
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                ProdutosDTO p = new ProdutosDTO();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setValor(rs.getInt("valor"));
                p.setStatus(rs.getString("status"));
              
                listagem.add(p);
            }
            
            ps.close();
            rs.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            new conectaDAO().desconectDB(conn);
        }
        
        return listagem;
    }

    public boolean venderProduto(String id) {

        String sql = "UPDATE Produtos SET status = ? WHERE id = ?";

        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);

            ps.setString(1, "Vendido");
            ps.setInt(2, Integer.parseInt(id));
            ps.executeUpdate();
            ps.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

  
        
    }

}

