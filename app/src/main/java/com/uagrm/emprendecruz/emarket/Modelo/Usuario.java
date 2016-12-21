package com.uagrm.emprendecruz.emarket.Modelo;

/**
 * Created by Cosio on 12/12/2016.
 */
public class Usuario {

    int id;
    String name;
    String email;
    String password;
    String provider_user_id;

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider_user_id() {
        return provider_user_id;
    }

    public void setProvider_user_id(String provider_user_id) {
        this.provider_user_id = provider_user_id;
    }
}
