package br.edu.ifpb.infra;

import br.edu.ifpb.pratica.DAO.interfaces.IntegrantesInterface;
import br.edu.ifpb.pratica.DAO.connect.ConFactory;
import br.edu.ifpb.pratica.DAO.connect.DataBase;
import br.edu.ifpb.pratica.model.CPF;
import br.edu.ifpb.pratica.model.Integrante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import javax.annotation.Resource;

@Stateless
@Remote(IntergrantesInterface.class)
public class IntegranteImplDAOBanco implements IntegrantesInterface{
    
    @Resource(lookup = "java:app/jdbc/sessionbeans")
    private DataSource dataSource;

    @Override
    public boolean salvar(Integrante integrantes) {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement createStatement = conn.prepareStatement(
                    "INSERT INTO Integrante VALUES (?,?,?);"
            );
            createStatement.setString(1, integrantes.getNome());
            createStatement.setString(2, integrantes.getDataDeNascimento()+"");
            createStatement.setString(3, integrantes.getCpf()+"");
            
            createStatement.executeUpdate();
            createStatement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean excluir(int id) {
       try (Connection conn = dataSource.getConnection()){
            Statement createStatement = conn.createStatement();
            ResultSet result = createStatement.executeQuery("DELETE CASCADE "
                    + "FROM Integrante WHERE id = ?;"+id);
            createStatement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean atualizar(Integrante integrante) {
        try (Connection conn = dataSource.getConnection()){
            PreparedStatement createStatement = conn.prepareStatement("UPDATE Banda SET localDeorigem = ?, "
                            + "nomeFantasia = ? WHERE id = ?");
            createStatement.setString(1, integrante.getNome());
            createStatement.setString(2, integrante.getDataDeNascimento()+"");
            createStatement.setString(3, integrante.getCpf()+"");
            createStatement.executeUpdate();
            createStatement.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Integrante> listarIntegrantes() {
        List<Integrante> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection()){
            Statement createStatement = conn.createStatement();
            ResultSet result = createStatement.executeQuery("SELECT * FROM Integrante;");
            iterarEmIntegrantes(result, lista);
            createStatement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            Logger.getLogger(IntegranteImplDAOBanco.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    private void iterarEmIntegrantes(ResultSet result, List<Integrante> lista) 
            throws SQLException, ParseException {
        while (result.next()) {
            lista.add(
                    criarNovoIntegrante(result)
            );
        }
    }

    private Integrante criarNovoIntegrante(ResultSet result) throws SQLException, ParseException {
        String nome = result.getString("nome");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dataDeNasc = result.getString("dataDeNascimento");
        LocalDate dataDeNascimento = LocalDate.parse(dataDeNasc, formatter);
        
        CPF cpf = new CPF(result.getString("cpf"));
        int id = result.getInt("id");

        return new Integrante(id, nome, dataDeNascimento, cpf);
}