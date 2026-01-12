package dao;

import model.Funcionario;

import java.util.List;

public interface FuncionarioDAO {

    void cadastrar(Funcionario funcionario);
    void excluir(Funcionario funcionario);
    void atualizar(Funcionario funcionario);
    Funcionario buscarPorId(Long id);
    List<Funcionario> buscarTodos();
    void fechar();
}
