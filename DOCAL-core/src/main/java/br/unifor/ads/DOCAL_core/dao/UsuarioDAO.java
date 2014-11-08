package br.unifor.ads.DOCAL_core.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.unifor.ads.DOCAL_core.dao.EntityManager;
import br.unifor.ads.DOCAL_core.entity.Usuario;

public class UsuarioDAO {

	private static EntityManager em = new EntityManager() {
		@Override
		public Object trataResultSet(ResultSet result) throws SQLException {
			Usuario usuario = null;
			if (result.next()) {
				usuario = new Usuario();
				usuario.setId(result.getInt("id"));
				usuario.setNome(result.getString("nome"));
				usuario.setAltura(result.getFloat("altura"));
				usuario.setPeso(result.getFloat("peso"));
				usuario.setLogin(result.getString("login"));
				usuario.setSenha(result.getString("senha"));
			}
			return usuario;
		}
	};

	public static void inserir(Usuario usuario) {
		String sql = "insert into usuario (nome, altura, peso, login, senha) values (?, ?, ?, ?, ?)";
		em.execute(sql, usuario.getNome(), usuario.getAltura(),
				usuario.getPeso(), usuario.getLogin(), usuario.getSenha());
	}


	public static Usuario buscarPorNome(String nome) {
		String sql = "select id, nome, altura, peso, login, senha from usuario where nome = ?";
		return (Usuario) em.getSingleResult(sql, nome);
	}

	public static Usuario findById(Integer Id) {
		String sql = "select id, nome, altura, peso, login, senha from usuario where id = ?";
		return (Usuario) em.getSingleResult(sql, Id);
	}
	
	public static Usuario findByLogin(String login) {
		String sql = "select id, nome, altura, peso, login, senha from usuario where login = ?";
		return (Usuario) em.getSingleResult(sql, login);
	}

	public static List<Object> buscarTodos() {
		String sql = "select id, nome, altura, peso, login, senha from usuario";
		return em.resultList(sql);
	}

	public static void excluir(Usuario usuario) {
		String sql = "delete from usuario where id = ?";
		em.execute(sql, usuario.getId());
	}

}
