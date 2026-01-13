package dao;

import model.Funcionario;

import java.math.BigDecimal;
import java.util.List;

public interface FuncionarioDAO {

    void cadastrar(Funcionario funcionario);
    void excluir(Funcionario funcionario);
    void atualizar(Funcionario funcionario);
    Funcionario buscarPorId(Long id);
    List<Funcionario> buscarTodos();
    BigDecimal obterSalarios();
    void fechar();
}
