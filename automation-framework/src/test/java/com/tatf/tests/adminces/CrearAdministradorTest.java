package com.tatf.tests.adminces;

import com.tatf.adminces.pages.AccesoPO;
import com.tatf.adminces.pages.AlertaPO;
import com.tatf.adminces.pages.InicioAdministradorPO;
import com.tatf.adminces.pages.InicioPO;
import com.tatf.adminces.pages.LoginPO;
import com.tatf.adminces.pages.RegistroAdministradorPO;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CrearAdministradorTest {

    private static IBrowser browser;
    private static IVerify verify;


    @BeforeAll
    static void beforeAll() {
        browser = BrowserFactory.getBrowser(true);
        verify = IVerify.create();
    }


    @AfterAll
    static void afterAll() {
        BrowserFactory.quitBrowser();
    }


    @Test
    void crearCuentaAdministrador() {

        // ==================================================
        // DATOS DE PRUEBA
        // ==================================================

        String claveAcceso =
                "3)ea60e0be3ba12c6ecd%7297868%5c4";

        String nombre = "Juan";
        String apellido = "Perez";

        String email =
                "juan.perez."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String password = "Clave123!";
        String pais = "Uruguay";


        // ==================================================
        // PAGE OBJECTS
        // ==================================================

        AccesoPO accesoPO =
                new AccesoPO(browser);

        AlertaPO alertaPO =
                new AlertaPO(browser);


        // ==================================================
        // 1. ACCEDER A ADMINCES
        // ==================================================

        InicioPO inicioPO =
                accesoPO.acceder(claveAcceso);


        // ==================================================
        // 2. IR AL REGISTRO
        // ==================================================

        RegistroAdministradorPO registroPO =
                inicioPO.irARegistroAdministrador();


        // ==================================================
        // 3. CREAR ADMINISTRADOR
        // ==================================================

        registroPO.registrarAdministrador(
                nombre,
                apellido,
                email,
                password,
                pais
        );


        // ==================================================
        // 4. VALIDAR CREACIÓN
        // ==================================================

        String mensaje =
                alertaPO.obtenerMensaje();

        verify.verify(
                "Usuario creado.",
                mensaje,
                "No se mostró el mensaje esperado de creación."
        );

        alertaPO.cerrar();


        // ==================================================
        // 5. INICIAR SESIÓN
        // ==================================================

        LoginPO loginPO =
                inicioPO.irAInicioSesion();

        loginPO.iniciarSesion(
                email,
                password
        );

        alertaPO.cerrar();


        // ==================================================
        // 6. VALIDAR ACCESO
        // ==================================================

        InicioAdministradorPO inicioAdministradorPO =
                new InicioAdministradorPO(browser);

        String textoCrearUsuario =
                inicioAdministradorPO.obtenerTextoCrearUsuario();

        verify.verify(
                "Crear usuario",
                textoCrearUsuario,
                "No se pudo iniciar sesión con el Administrador creado."
        );
    }
}