package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class InicioAdministradorPO {

    private final IBrowser browser;

    private static final String LINK_CREAR_USUARIO = "Crear usuario";
    private static final String LINK_VER_USUARIOS = "Ver usuarios";


    public InicioAdministradorPO(IBrowser browser) {
        this.browser = browser;
    }


    public void esperarCarga() {
        browser.wait(LINK_CREAR_USUARIO).link();
    }


    public String obtenerTextoCrearUsuario() {

        esperarCarga();

        return browser.find()
                .link(LINK_CREAR_USUARIO)
                .getText();
    }


    public CrearTesterPO irACrearUsuario() {

        browser.find()
                .link(LINK_CREAR_USUARIO)
                .click();

        CrearTesterPO crearTesterPO =
                new CrearTesterPO(browser);

        crearTesterPO.esperarCarga();

        return crearTesterPO;
    }


    public UsuariosPO irAVerUsuarios() {

        browser.wait(LINK_VER_USUARIOS).link();

        browser.find()
                .link(LINK_VER_USUARIOS)
                .click();

        return new UsuariosPO(browser);
    }
}