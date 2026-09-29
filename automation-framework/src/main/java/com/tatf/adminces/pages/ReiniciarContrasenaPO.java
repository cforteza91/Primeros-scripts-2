package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class ReiniciarContrasenaPO {

    private final IBrowser browser;

    private static final String CAMPO_EMAIL =
            "//input[@type='email' and @placeholder='Email']";

    private static final String CAMPO_PASSWORD =
            "inputPassword";

    private static final String CAMPO_REPETIR_PASSWORD =
            "inputRepeatPassword";

    private static final String BOTON_REINICIAR =
            "btnReset";


    public ReiniciarContrasenaPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperarCarga() {

        browser.wait(CAMPO_EMAIL).xpath();
    }


    public void ingresarEmail(String email) {

        browser.find()
                .xpath(CAMPO_EMAIL)
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


    public void clickReiniciar() {

        browser.find()
                .id(BOTON_REINICIAR)
                .click();
    }


    public void reiniciarContrasena(
            String email,
            String nuevaPassword
    ) {

        ingresarEmail(email);
        ingresarPassword(nuevaPassword);
        repetirPassword(nuevaPassword);
        clickReiniciar();
    }
}