package br.com.projetologin.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.projetologin.dao.UsuarioDAO;
import br.com.projetologin.domain.Usuario;

@ManagedBean
@SessionScoped
public class AutenticacaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;
	private Usuario usuario = new Usuario();;

	public void autenticar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getNomeUsuario(), usuario.getSenha());
			if (usuarioLogado == null) {
				Messages.addGlobalError("Usuário ou senha incorreto");
				return;
			}

			else {
				Faces.redirect("./pages/usuarios.xhtml");

			}
		} catch (IOException erro) {
			erro.printStackTrace();
			Messages.addGlobalError(erro.getMessage());
		}

	}

	public void logout() throws IOException {
		HttpSession sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		sessao.invalidate();
		Faces.redirect("./pages/index.xhtml");
	}

	// M E T O D O S G E T T E R S E S E T T E R S

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

//	public List<Metodos> getTodosOsMetodos() {
//		return todosOsMetodos;
//	}
//
//	public void setTodosOsMetodos(List<Metodos> todosOsMetodos) {
//		this.todosOsMetodos = todosOsMetodos;
//	}
//
//	public Metodos getMetodoSelecionado() {
//		return metodoSelecionado;
//	}
//
//	public void setMetodoSelecionado(Metodos metodoSelecionado) {
//		this.metodoSelecionado = metodoSelecionado;
//	}

//	public void preencherLista() {
//	todosOsMetodos.add(new Metodos(0L, "Pirmeira pausa"));
//	todosOsMetodos.add(new Metodos(1L, "Intervalo"));
//	todosOsMetodos.add(new Metodos(2L, "Ultima pausa"));
//}

}