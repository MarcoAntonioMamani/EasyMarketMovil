package com.uagrm.emprendecruz.emarket.Util;

import com.uagrm.emprendecruz.emarket.Modelo.Usuario;

/**
 * Created by Cosio on 12/12/2016.
 */
public class UsuarioFacebook {


    public static String nameUsuario = "";
    public static String correoUsuario = "";
    public static String idFacebookUsuario = "";
    public static Usuario user;

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        UsuarioFacebook.user = user;
    }

    public static String getNameUsuario() {
        return nameUsuario;
    }

    public static void setNameUsuario(String nameUsuario) {
        UsuarioFacebook.nameUsuario = nameUsuario;
    }

    public static String getCorreoUsuario() {
        return correoUsuario;
    }

    public static void setCorreoUsuario(String correoUsuario) {
        UsuarioFacebook.correoUsuario = correoUsuario;
    }

    public static String getIdFacebookUsuario() {
        return idFacebookUsuario;
    }

    public static void setIdFacebookUsuario(String idFacebookUsuario) {
        UsuarioFacebook.idFacebookUsuario = idFacebookUsuario;
    }

}
