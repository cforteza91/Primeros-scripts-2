package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class LoginPO {

    private final IBrowser browser;

    private static final String CAMPO_EMAIL = "inputEmail";
    private static final String CAMPO_PASSWORD = "inputPassword";

    private static final String BOTON_INICIAR_SESION =
            "//button[contains(text(),'Iniciar Sesión')]";


    public LoginPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperarCarga() {
        browser.wait(CAMPO_EMAIL).name();
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


    public void clickIniciarSesion() {
        browser.find()
                .xpath(BOTON_INICIAR_SESION)
                .click();
    }


    public void iniciarSesion(
            String email,
            String password
    ) {

        ingresarEmail(email);
        ingresarPassword(password);
        clickIniciarSesion();
    }
}