package br.com.projetologin.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.projetologin.dao.UsuarioDAO;
import br.com.projetologin.domain.Usuario;

public class UsuarioDAOTest {

	@Test
	@Ignore
	public void salvar() {

		Usuario usuario = new Usuario();

		usuario.setNomeUsuario("cryanfigueiredo");
		usuario.setNome("César Ryan");
		usuario.setSenhaCriptografada("123456");
		usuario.setEmail("ryanfigueiredo@hotmail.com");

		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaCriptografada());
		usuario.setSenha(hash.toHex());

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);

	}

	@Ignore
	@Test
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.listar();

		System.out.println(usuarios.size());
	}

	@Test
	@Ignore
	public void buscar() {
		String nomeUsuario = "cryanfigueiredo";

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(nomeUsuario);

		System.out.println("Nome do colaborador : " + usuario.getNome());
		System.out.println();

	}

	@Test
	@Ignore
	public void excluir() {
		String nomeUsuario = "cryanfigueiredo";

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(nomeUsuario);

		usuarioDAO.excluir(usuario);
		System.out.println("Usuario removido!");
		System.out.println("Nome do colaborador excluido: " + usuario.getNome());
		System.out.println();

	}

	@Test
	@Ignore
	public void editar() {
		String nomeUsuario = "cryanfigueiredo";

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(nomeUsuario);

		usuario.setNome("César Ryan Damasceno Figueiredo");

		usuarioDAO.editar(usuario);
		System.out.println("Usuario editado: ");
		System.out.println("Nome do usuario " + usuario.getNome());
		System.out.println("Senha do usuario: " + usuario.getSenha());
		System.out.println();

	}

	@Test
	@Ignore
	public void autenticar() {

		String nomeUsuario = "cryanfigueiredo";
		String senha = "123456";

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(nomeUsuario, senha);

		System.out.println("nome: " + usuario.getNome());
		System.out.println("senha: " + usuario.getSenha());
		System.out.println("senha Sem cripftografia: " + usuario.getSenhaCriptografada());
	}

	@Test
	@Ignore
	public void merge() {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar("emanuelferreira");
		usuario.setNome("Emanuel Ferreira");
		usuarioDAO.merge(usuario);

	}

}
