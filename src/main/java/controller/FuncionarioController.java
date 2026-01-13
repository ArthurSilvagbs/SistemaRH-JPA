package controller;

import dao.FuncionarioDAOJPA;
import jakarta.persistence.EntityManager;
import model.Funcionario;
import util.JPAUtil;

import java.math.BigDecimal;
import java.util.List;

public class FuncionarioController {

    public void contratarFuncionario(Funcionario funcionario) {

        EntityManager em = JPAUtil.getEntityManager();
        FuncionarioDAOJPA dao = new FuncionarioDAOJPA(em);

        try{
            em.getTransaction().begin();
            dao.cadastrar(funcionario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            dao.fechar();
        }
    }

    public void demitirFuncionario(Funcionario funcionario) {

        EntityManager em = JPAUtil.getEntityManager();
        FuncionarioDAOJPA dao = new FuncionarioDAOJPA(em);

        try{
            em.getTransaction().begin();
            dao.excluir(funcionario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            dao.fechar();
        }
    }

    public void atualizarDadosFuncioanrio(Funcionario funcionario) {

        EntityManager em = JPAUtil.getEntityManager();
        FuncionarioDAOJPA dao = new FuncionarioDAOJPA(em);

        try{
            em.getTransaction().begin();
            dao.atualizar(funcionario);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            dao.fechar();
        }
    }

    public Funcionario buscarFuncionarioPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        FuncionarioDAOJPA dao = new FuncionarioDAOJPA(em);

        try{
            return dao.buscarPorId(id);
        } finally {
            dao.fechar();
        }
    }

    public List<Funcionario> verListaFuncionarios() {

        EntityManager em = JPAUtil.getEntityManager();
        FuncionarioDAOJPA dao = new FuncionarioDAOJPA(em);

        try{
            return dao.buscarTodos();
        } finally {
            dao.fechar();
        }
    }

    public BigDecimal obterSomatorioSalarios() {
        EntityManager em = JPAUtil.getEntityManager();
        FuncionarioDAOJPA dao = new FuncionarioDAOJPA(em);

        try{
            return dao.obterSalarios();
        } finally {
            dao.fechar();
        }

    }


}
