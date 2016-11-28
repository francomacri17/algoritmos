package sistema;

// Interfaz del sistema
import Listas.abbEmpresa;
import Modelo.Punto;
import Modelo.ciudad;
import Modelo.data_center;
import java.util.ArrayList;

// No modificar esta clase!!!!!!!!!
public interface ISistema {

    Retorno inicializarSistema(int cantPuntos);

    Retorno destruirSistema();

    Retorno registrarEmpresa(String nombre, String direccion, String pais, String email_contacto, String color);

    Retorno registrarCiudad(String nombre, Double coordX, Double coordY);

    Retorno registrarDC(String nombre, Double coordX, Double coordY,
            String empresa, int capacidadCPUenHoras, int costoCPUporHora);

    Retorno registrarTramo(int indexOrigen, int indexDestino, int peso);

    Retorno eliminarTramo(int indexOrigen, int indexDestino);

    Retorno eliminarPunto(int indexPunto);

    Retorno mapaEstado();

    Retorno procesarInformacion(int indexDC, int esfuerzoCPUrequeridoEnHoras);

    Retorno listadoRedMinima();

    Retorno listadoEmpresas();

    abbEmpresa getListaEmpresa();

    Punto[] getCantPuntos();

    ciudad getCiudad(Double x, Double y);

    ciudad getCiudad(String nombre);

    data_center getDC(Double x, Double y);

    data_center getDC(String nombre);
    void cargarDatosPrueba();

    public ArrayList<String> getColores();

    public void cargarColores();
}
