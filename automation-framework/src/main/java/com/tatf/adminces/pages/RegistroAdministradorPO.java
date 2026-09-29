package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class RegistroAdministradorPO {

    private final IBrowser browser;

    private static final String CAMPO_NOMBRE = "inputFirstName";
    private static final String CAMPO_APELLIDO = "inputLastName";
    private static final String CAMPO_EMAIL = "inputEmail";
    private static final String CAMPO_PASSWORD = "inputPassword";
    private static final String CAMPO_REPETIR_PASSWORD = "inputRepeatPassword";
    private static final String CAMPO_PAIS = "inputCountry";
    private static final String BOTON_REGISTRARSE = "btnRegister";


    public RegistroAdministradorPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperarCarga() {
        browser.wait(CAMPO_NOMBRE).name();
    }


    public void ingresarNombre(String nombre) {
        browser.find()
                .name(CAMPO_NOMBRE)
                .write(nombre);
    }


    public void ingresarApellido(String apellido) {
        browser.find()
                .name(CAMPO_APELLIDO)
                .write(apellido);
    }


    public void ingresarEmail(String email) {
        browser.find()
                .name(CAMPO_EMAIL)
                .write(email);
    }


    public void ingresarPassword(String password) {
        browser.find()
                .name(CAMPO_PASSWORD)
                .write(password);
    }


    public void repetirPassword(String password) {
        browser.find()
                .name(CAMPO_REPETIR_PASSWORD)
                .write(password);
    }


    public void ingresarPais(String pais) {
        browser.find()
                .name(CAMPO_PAIS)
                .write(pais);
    }


    public void clickRegistrarse() {
        browser.find()
                .id(BOTON_REGISTRARSE)
                .click();
    }


    public void registrarAdministrador(
            String nombre,
            String apellido,
            String email,
            String password,
            String pais
    ) {

        ingresarNombre(nombre);
        ingresarApellido(apellido);
        ingresarEmail(email);
        ingresarPassword(password);
        repetirPassword(password);
        ingresarPais(pais);
        clickRegistrarse();
    }
}