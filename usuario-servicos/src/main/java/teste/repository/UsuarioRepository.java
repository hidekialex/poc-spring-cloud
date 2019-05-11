package teste.repository;

import org.springframework.stereotype.Repository;
import teste.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UsuarioRepository {

	@PersistenceContext
	private EntityManager em;

	public Usuario registrar(Usuario novoUsuario) {
		em.persist(novoUsuario);

		return novoUsuario;
	}

	public Usuario buscarPorID(int id) {
		return em.find(Usuario.class, id);
	}

	public Optional<Usuario> getByEmail(String email) {
		TypedQuery<Usuario> query = em.createQuery(
			"select u from Usuario u where u.credenciais.email = :email",
			Usuario.class);

		query.setParameter("email", email);

		try {
			Usuario usuario = query.getSingleResult();
			return Optional.of(usuario);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public Optional<Usuario> getById(Integer id) {
		TypedQuery<Usuario> query = em.createQuery(
				"select u from Usuario u where u.id = :id",
				Usuario.class);

		query.setParameter("id", id);

		try {
			Usuario usuario = query.getSingleResult();
			return Optional.of(usuario);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	public void atualizar(Usuario usuario) {
		em.persist(usuario);
	}

}
