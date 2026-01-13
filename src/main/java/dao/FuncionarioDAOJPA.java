package dao;

import jakarta.persistence.EntityManager;
import model.Funcionario;

import java.math.BigDecimal;
import java.util.List;

public class FuncionarioDAOJPA implements FuncionarioDAO {

    private final EntityManager em;

    public FuncionarioDAOJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public void cadastrar(Funcionario funcionario) {
        this.em.persist(funcionario);
    }

    @Override
    public void excluir(Funcionario funcionario){
        this.em.remove(funcionario);
    }

    @Override
    public void atualizar(Funcionario funcionario) {
        this.em.merge(funcionario);
    }

    @Override
    public Funcionario buscarPorId(Long id) {
        return this.em.find(Funcionario.class, id);
    }

    @Override
    public List<Funcionario> buscarTodos() {
        String jpql = "SELECT f FROM Funcionario f";
        return em.createQuery(jpql, Funcionario.class).getResultList();
    }

    @Override
    public BigDecimal obterSalarios() {
        String jpql = "SELECT SUM(f.salario) FROM Funcionario f";
        BigDecimal total = em.createQuery(jpql, BigDecimal.class).getSingleResult();
        return total != null ? total : BigDecimal.ZERO;
    }

    public void fechar() {
        if (this.em != null && this.em.isOpen()) {
            this.em.close();
        }
    }
}
