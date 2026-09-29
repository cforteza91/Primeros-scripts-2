package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class UsuariosPO {

    private final IBrowser browser;


    public UsuariosPO(IBrowser browser) {
        this.browser = browser;
    }


    private String xpathFilaUsuario(String email) {

        return "//tr[.//td[normalize-space()='" + email + "']]";
    }


    public String obtenerEmailUsuario(String email) {

        String xpathEmail =
                "//td[normalize-space()='" + email + "']";

        browser.wait(xpathEmail).xpath();

        return browser.find()
                .xpath(xpathEmail)
                .getText();
    }


    public boolean existeUsuario(String email) {

        String xpathFila =
                xpathFilaUsuario(email);

        return !browser.find()
                .xpathList(xpathFila)
                .isEmpty();
    }


    public void eliminarUsuario(String email) {

        String xpathFila =
                xpathFilaUsuario(email);

        browser.wait(xpathFila).xpath();

        browser.find()
                .xpath(xpathFila + "//button")
                .click();
    }
}