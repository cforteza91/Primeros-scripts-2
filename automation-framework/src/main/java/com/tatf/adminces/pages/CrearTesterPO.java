package com.tatf.adminces.pages;

import com.tatf.core.browser.IBrowser;

public class CrearTesterPO {

    private final IBrowser browser;

    private static final String CAMPO_NOMBRE = "inputFirstName";
    private static final String CAMPO_APELLIDO = "inputLastName";

    private static final String CAMPO_EMAIL =
            "//input[@type='email' and @placeholder='Email']";

    private static final String CAMPO_PAIS = "inputCountry";
    private static final String CAMPO_PASSWORD = "inputPassword";

    private static final String PERFIL_TESTER_SENIOR = "testerSenior";

    private static final String BOTON_CREAR = "btnRegister";


    public CrearTesterPO(IBrowser browser) {
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
                .xpath(CAMPO_EMAIL)
                .write(email);
    }


    public void seleccionarPais(String pais) {
        browser.find()
                .name(CAMPO_PAIS)
                .selectValue(pais);
    }


    public void ingresarPassword(String password) {
        browser.find()
                .name(CAMPO_PASSWORD)
                .write(password);
    }


    public void seleccionarTesterSenior() {
        browser.find()
                .id(PERFIL_TESTER_SENIOR)
                .click();
    }


    public void clickCrearCuenta() {
        browser.find()
                .id(BOTON_CREAR)
                .click();
    }


    public void crearTesterSenior(
            String nombre,
            String apellido,
            String email,
            String pais,
            String password
    ) {

        ingresarNombre(nombre);
        ingresarApellido(apellido);
        ingresarEmail(email);
        seleccionarPais(pais);
        ingresarPassword(password);
        seleccionarTesterSenior();
        clickCrearCuenta();
    }
}