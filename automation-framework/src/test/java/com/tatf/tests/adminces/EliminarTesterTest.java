package com.tatf.tests.adminces;

import com.tatf.adminces.pages.AccesoPO;
import com.tatf.adminces.pages.AlertaPO;
import com.tatf.adminces.pages.CrearTesterPO;
import com.tatf.adminces.pages.InicioAdministradorPO;
import com.tatf.adminces.pages.InicioPO;
import com.tatf.adminces.pages.LoginPO;
import com.tatf.adminces.pages.RegistroAdministradorPO;
import com.tatf.adminces.pages.UsuariosPO;

import com.tatf.core.browser.BrowserFactory;
import com.tatf.core.browser.IBrowser;
import com.tatf.core.verification.IVerify;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class EliminarTesterTest {

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
    void eliminarCuentaTester() {

        // ==================================================
        // DATOS
        // ==================================================

        String claveAcceso =
                "3)ea60e0be3ba12c6ecd%7297868%5c4";

        String emailAdministrador =
                "admin.eliminar."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordAdministrador = "Clave123!";

        String nombreTester = "Tester";
        String apellidoTester = "Eliminar";

        String emailTester =
                "tester.eliminar."
                        + System.currentTimeMillis()
                        + "@gmail.com";

        String passwordTester = "Tester123!";
        String paisTester = "Uruguay";


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
        // 2. CREAR ADMINISTRADOR AUXILIAR
        // ==================================================

        RegistroAdministradorPO registroPO =
                inicioPO.irARegistroAdministrador();

        registroPO.registrarAdministrador(
                "Admin",
                "Eliminar",
                emailAdministrador,
                passwordAdministrador,
                "Uruguay"
        );


        // ==================================================
        // 3. VALIDAR CREACIÓN DEL ADMINISTRADOR
        // ==================================================

        String mensajeAdministrador =
                alertaPO.obtenerMensaje();

        verify.verify(
                "Usuario creado.",
                mensajeAdministrador,
                "No se pudo crear el Administrador necesario para la prueba."
        );

        alertaPO.cerrar();


        // ==================================================
        // 4. INICIAR SESIÓN
        // ==================================================

        LoginPO loginPO =
                inicioPO.irAInicioSesion();

        loginPO.iniciarSesion(
                emailAdministrador,
                passwordAdministrador
        );

        alertaPO.cerrar();


        // ==================================================
        // 5. VALIDAR INGRESO
        // ==================================================

        InicioAdministradorPO administradorPO =
                new InicioAdministradorPO(browser);

        String textoCrearUsuario =
                administradorPO.obtenerTextoCrearUsuario();

        verify.verify(
                "Crear usuario",
                textoCrearUsuario,
                "No se pudo iniciar sesión con el Administrador."
        );


        // ==================================================
        // 6. CREAR TESTER
        // ==================================================

        CrearTesterPO crearTesterPO =
                administradorPO.irACrearUsuario();

        crearTesterPO.crearTesterSenior(
                nombreTester,
                apellidoTester,
                emailTester,
                paisTester,
                passwordTester
        );


        // ==================================================
        // 7. VALIDAR CREACIÓN DEL TESTER
        // ==================================================

        String mensajeTester =
                alertaPO.obtenerMensaje();

        verify.verify(
                "Usuario creado.",
                mensajeTester,
                "No se pudo crear el Tester necesario para la prueba."
        );

        alertaPO.cerrar();


        // ==================================================
        // 8. IR A VER USUARIOS
        // ==================================================

        UsuariosPO usuariosPO =
                administradorPO.irAVerUsuarios();


        // ==================================================
        // 9. VALIDAR QUE EL TESTER EXISTE
        // ==================================================

        boolean existeAntes =
                usuariosPO.existeUsuario(emailTester);

        verify.verifyTrue(
                existeAntes,
                "El Tester que se desea eliminar no aparece en el listado."
        );


        // ==================================================
        // 10. ELIMINAR TESTER
        // ==================================================

        usuariosPO.eliminarUsuario(emailTester);


        // ==================================================
        // 11. VALIDAR MENSAJE DE CONFIRMACIÓN
        // ==================================================

        String mensajeConfirmacion =
                alertaPO.obtenerMensaje();

        String mensajeEsperado =
                "¿Eliminar usuario: " + emailTester + "?";

        verify.verify(
                mensajeEsperado,
                mensajeConfirmacion,
                "El mensaje de confirmación no corresponde al Tester seleccionado."
        );


        // ==================================================
        // 12. CONFIRMAR ELIMINACIÓN
        // ==================================================

        alertaPO.confirmar();


        // ==================================================
        // 13. VALIDAR MENSAJE FINAL
        // ==================================================

        String mensajeEliminacion =
                alertaPO.obtenerMensaje();

        verify.verify(
                "Usuario eliminado.",
                mensajeEliminacion,
                "No se mostró el mensaje esperado de eliminación."
        );

        alertaPO.cerrar();


        // ==================================================
        // 14. VALIDAR QUE EL TESTER YA NO EXISTE
        // ==================================================

        boolean existeDespues =
                usuariosPO.existeUsuario(emailTester);

        verify.verifyFalse(
                existeDespues,
                "El Tester continúa apareciendo luego de eliminarlo."
        );
    }
}