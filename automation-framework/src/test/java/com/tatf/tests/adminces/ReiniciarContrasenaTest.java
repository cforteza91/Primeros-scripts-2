package com.tatf.tests.adminces;

import com.tatf.adminces.pages.AccesoPO;
import com.tatf.adminces.pages.AlertaPO;
import com.tatf.adminces.pages.InicioAdministradorPO;
import com.tatf.adminces.pages.InicioPO;
import com.tatf.adminces.pages.LoginPO;
import com.tatf.adminces.pages.RegistroAdministradorPO;
import com.tatf.adminces.pages.ReiniciarContrasenaPO;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReiniciarContrasenaTest {

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
    void reiniciarContrasena() {

        // ==================================================
        // DATOS
        // ==================================================

        String claveAcceso =
                "3)ea60e0be3ba12c6ecd%7297868%5c4";

        String email =
                "reset.test."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordInicial = "Clave123!";
        String passwordNueva = "NuevaClave456!";


        // ==================================================
        // PAGE OBJECTS
        // ==================================================

        AccesoPO accesoPO =
                new AccesoPO(browser);

        AlertaPO alertaPO =
                new AlertaPO(browser);


        // ==================================================
        // 1. ACCEDER
        // ==================================================

        InicioPO inicioPO =
                accesoPO.acceder(claveAcceso);


        // ==================================================
        // 2. CREAR ADMINISTRADOR PARA LA PRUEBA
        // ==================================================

        RegistroAdministradorPO registroPO =
                inicioPO.irARegistroAdministrador();

        registroPO.registrarAdministrador(
                "Usuario",
                "Reset",
                email,
                passwordInicial,
                "Uruguay"
        );


        // ==================================================
        // 3. VALIDAR CREACIÓN
        // ==================================================

        String mensajeCreacion =
                alertaPO.obtenerMensaje();

        verify.verify(
                "Usuario creado.",
                mensajeCreacion,
                "No se pudo crear el Administrador necesario para la prueba."
        );

        alertaPO.cerrar();


        // ==================================================
        // 4. REINICIAR CONTRASEÑA
        // ==================================================

        ReiniciarContrasenaPO reiniciarPO =
                inicioPO.irAReiniciarContrasena();

        reiniciarPO.reiniciarContrasena(
                email,
                passwordNueva
        );


        // ==================================================
        // 5. VALIDAR RESPUESTA
        // ==================================================

        alertaPO.obtenerMensaje();

        alertaPO.cerrar();


        // ==================================================
        // 6. INICIAR SESIÓN CON NUEVA CONTRASEÑA
        // ==================================================

        LoginPO loginPO =
                inicioPO.irAInicioSesion();

        loginPO.iniciarSesion(
                email,
                passwordNueva
        );

        alertaPO.cerrar();


        // ==================================================
        // 7. VALIDAR ACCESO
        // ==================================================

        InicioAdministradorPO administradorPO =
                new InicioAdministradorPO(browser);

        String textoCrearUsuario =
                administradorPO.obtenerTextoCrearUsuario();

        verify.verify(
                "Crear usuario",
                textoCrearUsuario,
                "No fue posible iniciar sesión utilizando la nueva contraseña."
        );
    }
}