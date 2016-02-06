package br.cms.Senha;

import java.math.BigInteger;
import java.security.SecureRandom;

public class GeradorSenha {

    public static String gerarSenha() {
        SecureRandom random = new SecureRandom();

        return new BigInteger(50, random).toString(32);
    }
}
