package com.si1.lab03.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.si1.lab03.model.Usuario;

@Repository
public class UsuarioDAO {
	@PersistenceContext
	private EntityManager entityManager;

	public Usuario verificarUsuario(String email, String senha) {
		TypedQuery<Usuario> query = entityManager.createQuery("SELECT new Usuario(u.email, u.nome, u.id) FROM Usuario u WHERE u.email=:email AND u.senha=:senha", Usuario.class);
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			throw new RuntimeException("Usuário ou senha inválidos.");
		}
	}

	public void cadastrarUsuario(Usuario usuario) {
		this.entityManager.persist(usuario);
	}

}
