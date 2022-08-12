package todolist;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ControleToDoList {
    
    ConexaoBD conex = new ConexaoBD();
    ModeloToDoList mod = new ModeloToDoList();
    
    public void SalvarTask(ModeloToDoList mod){
        conex.conexao();

        try {
            PreparedStatement pst = conex.con.prepareStatement(
            "insert into list(titulo, task) values(?, ?)");
            
            pst.setString(1, mod.getTitulo());
            pst.setString(2, mod.getTask());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Inseridos com Sucesso!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao Inserir Dados!");
        }

        conex.desconecta();
    }
    
    public void EditarTask(ModeloToDoList mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement(
            "update list set titulo=?, genero=? where titulo=?");
            
            pst.setString(1, mod.getTitulo());
            pst.setString(2, mod.getTask());
            pst.setString(3, mod.getTitulo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao Alterar Dados: "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao Alterar Dados!");
        }
        
        conex.desconecta();
    }
    
    public ModeloToDoList buscaTask(ModeloToDoList mod){
        conex.conexao();
        
        conex.executaSql("select * from list where titulo like'%" +mod.getPesquisa() +"%'");
        try {
            conex.rs.first();
            mod.setTitulo(conex.rs.getString("titulo"));
            mod.setTask(conex.rs.getString("task"));
        } catch (SQLException ex) {
            System.out.println("Erro ao Buscar Task:\n"+ ex);
            JOptionPane.showMessageDialog(null, "Task não cadastrada.");
        }
        
        conex.desconecta();
        return mod;
    }
    
    public void ExcluirTask(ModeloToDoList mod){
        conex.conexao();
        try {
            PreparedStatement pst = conex.con.prepareStatement("delete from list where titulo=?");
            pst.setString(1, mod.getTitulo());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Dados Excluidos com Sucesso!");
        } catch (SQLException ex) {
            System.out.println("Erro ao Deletar Dados: "+ ex.getMessage());
            JOptionPane.showMessageDialog(null, "Erro ao Deletar Dados!");
        }
        
        
        conex.desconecta();
    }
    
}
