package br.com.projetologin.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.projetologin.dao.UsuarioDAO;
import br.com.projetologin.domain.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private List<Usuario> usuarios;

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaCriptografada());
			usuario.setSenha(hash.toHex());
			if (usuarioDAO.buscar(usuario.getNomeUsuario()) == null) {
				usuarioDAO.salvar(usuario);
				Messages.addGlobalInfo("Usuario salvo com sucesso");
				novo();
				System.out.println("Metodo SALVAR CHAMADO");
			} else {
				Messages.addGlobalInfo("Usuario ja cadastrado");
				novo();
				return;
			}

		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo usuario");
			erro.printStackTrace();
		}
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
			System.out.println("metodo LISTAR CHAMADO");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os usuarios");
			erro.printStackTrace();
		}

	}

	public void novo() {
		usuario = new Usuario();
		System.out.println("Metodo NOVO CHAMADO USUARIO BEAN");
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
