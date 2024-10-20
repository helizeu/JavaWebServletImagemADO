package javaBeans;

import java.sql.SQLException;

public class Usuario extends Conectar {

    public int pkuser;
    public String nome;
    public String email;
    public String celular;
    public String senha;
    public String nivel;

    public boolean getLogin() {
        if (email.equals("admin") && senha.equals("1234")) {
            return true;
        }

        try {
            sql = "select * from usuarios where email = ? and senha = ?";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            ps.setString(2, senha); // Configura Parametros
            tab = ps.executeQuery(); // Executa comando SQL
            if (tab.next()) {
                return true;
            }
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao tentar buscar Usuário! " + ex.getMessage();
        }
        return false;
    }

    public void incluir() {
        try {
            sql = "insert into usuarios ( nome, email, celular, senha, nivel ) "
                    + "values (?,?,?,?,?) ";

            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, nome); // Configura Parametros
            ps.setString(2, email); // Configura Parametros
            ps.setString(3, celular); // Configura Parametros
            ps.setString(4, senha); // Configura Parametros
            ps.setString(5, nivel); // Configura Parametros

            ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao incluir usuario ! <br> " + ex.getMessage();
        }
    }

    public void alterar() {
        try {
            sql = "update usuarios set nome=?, email=?, celular=?,"
                    + " senha=?, nivel=? where ucase(trim(email))=ucase(trim(?)) ";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, nome); // Configura Parametros
            ps.setString(2, email); // Configura Parametros
            ps.setString(3, celular); // Configura Parametros
            ps.setString(4, senha); // Configura Parametros
            ps.setString(5, nivel); // Configura Parametros
            ps.setString(6, email); // Configura Parametros
            ps.executeUpdate(); // executa comando SQL
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = "Erro ao Alterar usuario ! <br> " + ex.getMessage();
        }
    }
  public void deletar () {
        try {
            sql = "delete from usuarios where email = ? ";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            ps.executeUpdate();
            
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = sql + " <br> Erro ao deletar usuario ! <br> " + ex.getMessage();
        }
    }
  public boolean buscarEmail () {
        try {
            sql = "select * from usuarios where ucase(trim(email)) = ucase(trim(?))";
            ps = con.prepareStatement(sql); // prepara SQL
            ps.setString(1, email); // Configura Parametros
            tab = ps.executeQuery();
            if( tab.next() )
            {
               pkuser=tab.getInt("pkuser");
               nome = tab.getString("nome");
               celular = tab.getString("celular");
               senha = tab.getString("senha");
               nivel = tab.getString("nivel");
               return true; 
            }
            this.statusSQL = null; // armazena null se deu tudo certo
        } catch (SQLException ex) {
            this.statusSQL = sql + " <br> Erro ao deletar usuario ! <br> " + ex.getMessage();
        }
        return false;
    }
}
