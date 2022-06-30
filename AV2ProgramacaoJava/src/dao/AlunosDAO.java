package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bancoDeDados.ConnectionFactory;
import model.Aluno;



public class AlunosDAO {
	private final Connection con;

	public AlunosDAO() throws SQLException {
		
		this.con = ConnectionFactory.getConnection();
	}
	//LISTAR
	public List<Aluno> getLista() throws SQLException{ //puxa todos os alunos da tabela
		String sql = "select * from alunos";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		while(result.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(result.getLong("id"));
			aluno.setNome(result.getString("nome"));
			aluno.setEmail(result.getString("email"));
			aluno.setDataDeNascimento(result.getString("dataNascimento"));
			aluno.setCpf(result.getInt("cpf"));
			aluno.setNaturalidade(result.getString("naturalidade"));
			aluno.setEndereco(result.getString("endereco"));
			alunos.add(aluno);
		}
		
		result.close();
		stmt.close();
		return alunos;
	}
	//INSERIR
	public void inserir(Aluno aluno) throws SQLException {
		String sql = "insert into alunos(nome,email,dataNascimento ,cpf,naturalidade,endereco) values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, aluno.getNome());
		stmt.setString(2, aluno.getEmail());
		stmt.setString(3, aluno.getDataDeNascimento());
		stmt.setInt(4, aluno.getCpf());
		stmt.setString(5, aluno.getNaturalidade());
		stmt.setString(6, aluno.getEndereco());
		stmt.execute();
		stmt.close();
		con.close();
		
	}
	//ATUALIZAR
	public void atualizar(Aluno aluno,int id) throws SQLException {
		String sql = "update alunos set nome = ?, email = ?, dataNascimento = ? ,cpf = ?,naturalidade = ?,endereco = ? where id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		stmt.setString(1, aluno.getNome());
		stmt.setString(2, aluno.getEmail());
		stmt.setString(3, aluno.getDataDeNascimento());
		stmt.setInt(4, aluno.getCpf());
		stmt.setString(5, aluno.getNaturalidade());
		stmt.setString(6, aluno.getEndereco());
		stmt.setLong(7, id);
			
		stmt.execute();
		stmt.close();
		con.close();
	}
	//EXCLUIR
	public void excluir(int id) {
		String sql = "delete from alunos where id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			stmt.execute();
			stmt.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	//BUSCA FILTRADA PELA LETRA
	public List<Aluno> getListaFiltrado(String letra) throws SQLException{
		String sql = "select * from alunos where nome like '"+letra+"%'";
		PreparedStatement stmt = con.prepareStatement(sql);
		
		
		ResultSet result = stmt.executeQuery();
		
		List<Aluno> alunos = new ArrayList<Aluno>();
		
		while(result.next()) {
			Aluno aluno = new Aluno();
			aluno.setId(result.getLong("id"));
			aluno.setNome(result.getString("nome"));
			aluno.setEmail(result.getString("email"));
			aluno.setDataDeNascimento(result.getString("dataNascimento"));
			aluno.setCpf(result.getInt("cpf"));
			aluno.setNaturalidade(result.getString("naturalidade"));			
			aluno.setEndereco(result.getString("endereco"));
			alunos.add(aluno);
		}
		
		result.close();
		stmt.close();
		return alunos;
	}
	
	
	
	

	
	
	
}
