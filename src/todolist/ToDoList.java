package todolist;

import javax.swing.JOptionPane;

public class ToDoList {
    
    ConexaoBD conex = new ConexaoBD();
    
    public static void main(String[] args) {
        int opcao;
        
        do{
            
            opcao = Integer.parseInt(JOptionPane.showInputDialog(null, "[ 1 ] Criar nova task\n"
                    + "[ 2 ] Editar Task\n"
                    + "[ 3 ] Excluir task\n"
                    + "[ 4 ] Buscar task\n"
                    + "[ 5 ] Sair"));
        
        if(opcao == 1)
            inserirTask(opcao);
        else if(opcao == 2)
            inserirTask(opcao);
        else if(opcao == 3)
            deletarTask();
        else if(opcao == 4)
            buscarTask();
        }while(opcao != 5);
        
	
    }
    
    public static void inserirTask(int opcao){
	ModeloToDoList mod = new ModeloToDoList();
        ControleToDoList con = new ControleToDoList();
      
            mod.setTitulo(JOptionPane.showInputDialog(null, "Titulo: "));
            mod.setTask(JOptionPane.showInputDialog(null, "Descricao: "));
        if(opcao == 1)
            con.SalvarTask(mod);
        else
            con.EditarTask(mod);
    }
    
    public static void deletarTask(){
	ModeloToDoList mod = new ModeloToDoList();
        ControleToDoList con = new ControleToDoList();
        
        String task = JOptionPane.showInputDialog(null, "Qual task deseja deletar? ");
        String descTask = "000";
        
        mod.setTask(task);
        con.ExcluirTask(mod);
    }
    
    public static void buscarTask(){
	ModeloToDoList mod = new ModeloToDoList();
        ControleToDoList con = new ControleToDoList();
        
        String pesquisa = JOptionPane.showInputDialog(null, "Titulo da task: ");
        
        mod.setPesquisa(pesquisa);
        
        mod = con.buscaTask(mod);
    }
}
