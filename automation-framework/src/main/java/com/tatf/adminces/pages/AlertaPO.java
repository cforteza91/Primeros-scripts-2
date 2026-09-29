package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class AlertaPO {

    private final IBrowser browser;

    private static final String MENSAJE =
            "swal2-html-container";

    private static final String BOTON_OK =
            "//button[normalize-space()='OK']";

    private static final String BOTON_SI =
            "//button[normalize-space()='Sí']";


    public AlertaPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperar() {

        browser.wait(MENSAJE).id();
    }


    public String obtenerMensaje() {

        esperar();

        return browser.find()
                .id(MENSAJE)
                .getText();
    }


    public void cerrar() {

        browser.wait(BOTON_OK).xpath();

        browser.find()
                .xpath(BOTON_OK)
                .click();
    }


    public void confirmar() {

        browser.wait(BOTON_SI).xpath();

        browser.find()
                .xpath(BOTON_SI)
                .click();
    }
}