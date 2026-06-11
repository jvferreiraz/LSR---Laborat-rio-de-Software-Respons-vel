package model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Criptografa a senha usando Bcrypt
     * @param senha Senha em texto plano
     * @return Senha criptografada
     */
    public static String criptografarSenha(String senha) {
        return encoder.encode(senha);
    }

    /**
     * Verifica se a senha em texto plano corresponde ao hash armazenado
     * @param senhaPlana Senha em texto plano
     * @param senhaHash Senha criptografada armazenada no banco
     * @return true se correspondem, false caso contrário
     */
    public static boolean verificarSenha(String senhaPlana, String senhaHash) {
        return encoder.matches(senhaPlana, senhaHash);
    }
}