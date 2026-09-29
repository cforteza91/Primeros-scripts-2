package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class AccesoPO {

    private final IBrowser browser;

    private static final String URL_ADMINCES =
            "http://cestore.ces.com.uy/adminces/";

    private static final String CAMPO_CLAVE =
            "input[type='password']";

    private static final String BOTON_INGRESAR =
            "button[type='submit']";


    public AccesoPO(IBrowser browser) {
        this.browser = browser;
    }


    public void abrir() {

        browser.interaction()
                .navigateTo(URL_ADMINCES);
    }


    public void ingresarClave(String clave) {

        browser.find()
                .css(CAMPO_CLAVE)
                .clear()
                .write(clave);
    }


    public void clickIngresar() {

        browser.find()
                .css(BOTON_INGRESAR)
                .click();
    }


    public InicioPO acceder(String clave) {

        abrir();

        ingresarClave(clave);

        clickIngresar();

        InicioPO inicioPO = new InicioPO(browser);

        inicioPO.esperarCarga();

        return inicioPO;
    }
}