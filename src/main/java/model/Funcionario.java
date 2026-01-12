package model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "funcionario_nome", nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cargo;

    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal salario;

    public Funcionario() {
    }

    public Funcionario(String nome, String cargo, BigDecimal salario) {
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
