package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class InicioPO {

    private final IBrowser browser;

    private static final String LINK_REGISTRARSE =
            "Registrarse";

    private static final String LINK_INICIAR_SESION =
            "Iniciar sesión";

    private static final String TARJETA_REINICIAR_CONTRASENA =
            "//a[@href='/adminces/forgot-password' " +
                    "and .//div[normalize-space()='Reiniciar contraseña']]";


    public InicioPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperarCarga() {

        browser.wait(LINK_REGISTRARSE).link();
    }


    public RegistroAdministradorPO irARegistroAdministrador() {

        browser.find()
                .link(LINK_REGISTRARSE)
                .click();

        RegistroAdministradorPO registro =
                new RegistroAdministradorPO(browser);

        registro.esperarCarga();

        return registro;
    }


    public LoginPO irAInicioSesion() {

        browser.find()
                .link(LINK_INICIAR_SESION)
                .click();

        LoginPO login =
                new LoginPO(browser);

        login.esperarCarga();

        return login;
    }


    public ReiniciarContrasenaPO irAReiniciarContrasena() {

        browser.find()
                .xpath(TARJETA_REINICIAR_CONTRASENA)
                .click();

        ReiniciarContrasenaPO reiniciar =
                new ReiniciarContrasenaPO(browser);

        reiniciar.esperarCarga();

        return reiniciar;
    }
}