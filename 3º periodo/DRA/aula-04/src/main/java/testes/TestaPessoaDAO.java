package testes;

import dao.PessoaDao;
import exception.DaoException;
import model.Pessoa;

public class TestaPessoaDAO {

    private static void inserirPessoaDao(){
        Pessoa pessoa = new Pessoa("João","joao@email.com","3234-5678");

        PessoaDao pessoaDao = new PessoaDao();

        try {
            pessoaDao.inserir(pessoa);
        }catch(DaoException e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        inserirPessoaDao();
    }

}