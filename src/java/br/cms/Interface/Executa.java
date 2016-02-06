package br.cms.Interface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Executa {

    String executa(HttpServletRequest req,
            HttpServletResponse res) throws Exception;
}
