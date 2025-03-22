package testes;

import dao.PessoaDao;
import model.Pessoa;

import java.util.List;

public class TestaPessoaDAO {


    private static void inserirPessoa(Pessoa pessoa){
        PessoaDao pessoaDao = new PessoaDao();
        pessoaDao.inserir(pessoa);
    }

    private static void alterarPessoa(Pessoa pessoa){
        PessoaDao pessoaDao = new PessoaDao();
        pessoaDao.alterar(pessoa);
    }

    private static void listarPessoas(){
        PessoaDao pessoaDao = new PessoaDao();
        pessoaDao.listar();
    }

    private static void ConsultarPessoa(Long id){
        PessoaDao pessoaDao = new PessoaDao();
        pessoaDao.consultar(id);
    }

    private static void ExcluirPessoa(Long id){
        PessoaDao pessoaDao = new PessoaDao();
        pessoaDao.remover(id);
    }



    public static void main(String[] args) {
//        Pessoa pessoa = new Pessoa("Alysson","alysson@gmail.com","92 98324234");
//        inserirPessoa(pessoa);
//        listarPessoas();
        ConsultarPessoa(2l);
    }
}
