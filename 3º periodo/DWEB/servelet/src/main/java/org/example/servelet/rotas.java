package org.example.servelet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet(urlPatterns = {"/home", "/conta" , "/configuracoes"})
public class rotas extends HttpServlet {

    public rotas(){
        super();
    }


}
