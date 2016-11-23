package sistema;

import Listas.CiudadLista;
import Listas.DCLista;
import Listas.abbEmpresa;
import Listas.Dijkstra;
import Modelo.Punto;
import Modelo.ciudad;
import Modelo.data_center;
import Modelo.empresa;
import java.util.Objects;
import sistema.Retorno.Resultado;

public class Sistema implements ISistema {

    static abbEmpresa listaEmpresa = new abbEmpresa();
    static CiudadLista listaCiudad = new CiudadLista();
    static DCLista listaDC = new DCLista();
    static Punto[] cantPuntos;
    static Dijkstra dijkstra = new Dijkstra();
    @Override
    public abbEmpresa getListaEmpresa() {
        return listaEmpresa;
    }

    @Override
    public Punto[] getCantPuntos() {
        return cantPuntos;
    }

    public static void setCantPuntos(int cantPuntos) {
        if (Sistema.cantPuntos == null) {
            Sistema.cantPuntos = new Punto[cantPuntos];
            dijkstra.setMAX(cantPuntos);
        }
    }

    public boolean pertence(Double x, Double y) {
        boolean b = false;
        int i = 0;
        while (i < cantPuntos.length && b == false) {
            if (cantPuntos[i] != null) {
                if (cantPuntos[i].getCoordX().equals(x) && cantPuntos[i].getCoordY().equals(y)) {
                    b = true;
                }
            }
            i++;
        }
        return b;
    }

    public Punto darPunto(Double x, Double y) {
        int i = 0;
        Punto p = new Punto();
        while (i < cantPuntos.length) {
            if (cantPuntos[i].getCoordX().equals(x) && cantPuntos[i].getCoordY().equals(y)) {

                p = cantPuntos[i];
                i = cantPuntos.length;

            }
            i++;
        }
        return p;
    }

    public void agregarPunto(Punto p) {
        int i = 0;
        while (i < cantPuntos.length) {
            if (cantPuntos[i] == null) {
                cantPuntos[i] = p;
                i = cantPuntos.length;
            } else {
                i++;
            }
        }
        dijkstra = new Dijkstra(cantPuntos);
    }

    public Boolean hayLugar() {
        Boolean b = false;
        if (cantPuntos != null) {
            int i = 0;
            while (i < cantPuntos.length) {
                if (cantPuntos[i] == null) {
                    b = true;
                    i = cantPuntos.length;
                }
                i++;
            }
        } else {
            b = true;
        }
        return b;
    }
    public int contarPuntos() {
        int i = 0;
        if (cantPuntos != null) {
            
            while (i < cantPuntos.length) {
                if (cantPuntos[i] == null) {
                    i = cantPuntos.length;
                }
                i++;
            }
        }
        return i;
    }
    @Override
    public Retorno inicializarSistema(int cantPuntos) {
        // TODO Auto-generated method stub
        setCantPuntos(cantPuntos);
        return new Retorno(Resultado.OK);
    }

    @Override
    public Retorno destruirSistema() {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno registrarEmpresa(String nombre, String direccion,
            String pais, String email_contacto, String color) {
        // TODO Auto-generated method stub
        empresa e = new empresa();
        listaEmpresa.insertar(new empresa(nombre, direccion, pais, email_contacto, color));

        return new Retorno(Resultado.OK);
    }

    @Override
    public Retorno registrarCiudad(String nombre, Double coordX, Double coordY) {
        // TODO Auto-generated method stub
        Retorno r;
        if (hayLugar()) {
            if (!pertence(coordX, coordY)) {
                ciudad c = new ciudad(nombre, coordX, coordY);
                listaCiudad.agregarNodoAlFinal(c);
                agregarPunto(c);
                r = new Retorno(Resultado.OK);
            } else {
                r = new Retorno(Resultado.ERROR_2, "El punto ya existe", 2);
            }

        } else {
            r = new Retorno(Resultado.ERROR_1, "Ya hay registrados cantPuntos puntos", 1);
        }
        return r;
    }

    @Override
    public Retorno registrarDC(String nombre, Double coordX, Double coordY,
            String empresa, int capacidadCPUenHoras, int costoCPUporHora) {
        // TODO Auto-generated method stub
        Retorno r;
        if (hayLugar()) {
            if (capacidadCPUenHoras <= 0) {
                r = new Retorno(Resultado.ERROR_2, "Capacidad es menor o igual a 0", 2);
            } else if (!pertence(coordX, coordY)) {
                empresa e = listaEmpresa.buscarEmpresa(empresa);
                if (e != null) {
                    data_center dc = new data_center(nombre, coordX, coordY, e, capacidadCPUenHoras, costoCPUporHora, e.getColor());
                    listaDC.agregarNodoAlFinal(dc);
                    agregarPunto(dc);
                    r = new Retorno(Resultado.OK);
                } else {
                    r = new Retorno(Resultado.ERROR_4, "La empresa no existe en el sistema", 4);
                }

            } else {
                r = new Retorno(Resultado.ERROR_3, "El punto ya existe", 3);
            }

        } else {
            r = new Retorno(Resultado.ERROR_1, "Ya hay registrados cantPuntos puntos", 1);
        }
        return r;
    }

    @Override
    public Retorno registrarTramo(int indexOrigen, int indexDestino, int peso) {
        // TODO Auto-generated method stub
        Retorno r;
        if (peso <= 0) {
                r = new Retorno(Resultado.ERROR_1, "El peso es menor o igual a 0", 1);
            } else{
            if (!dijkstra.existsEdge(indexOrigen, indexDestino, peso, false)) {
                dijkstra.addEdge(indexOrigen, indexDestino, peso, false);
                r = new Retorno(Resultado.OK);
            }else{
                r = new Retorno(Resultado.ERROR_3, "Ya existe un tramo registrado entre los vertices", 3);
            }
        }
        return r;
    }


    @Override
    public Retorno eliminarTramo(Double coordXi, Double coordYi,
            Double coordXf, Double coordYf) {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno eliminarPunto(Double coordX, Double coordY) {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno mapaEstado() {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno procesarInformacion(Double coordX, Double coordY,
            int esfuerzoCPUrequeridoEnHoras) {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listadoRedMinima() {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
    }

    @Override
    public Retorno listadoEmpresas() {
        // TODO Auto-generated method stub
        StringBuilder string = new StringBuilder();
        String s = listaEmpresa.inOrder(string).toString();
        return new Retorno(Resultado.OK, s, 1);
    }

    @Override
    public ciudad getCiudad(Double x, Double y) {
        return listaCiudad.darCiudad(x, y);
    }
    @Override
    public ciudad getCiudad(String nombre) {
        return listaCiudad.darCiudad(nombre);
    }
    @Override
    public data_center getDC(Double x, Double y) {
        return listaDC.darDC(x, y);
    }
    @Override
    public data_center getDC(String nombre) {
        return listaDC.darDC(nombre);
    }
    
    @Override
    public void cargarDatosPrueba(){
        
        ciudad c = new ciudad("Punta del Este",-34.9368789,-54.9281496);
        listaCiudad.agregarNodoAlFinal(c);
        agregarPunto(c);
        
        ciudad c1 = new ciudad("Silicon Valley",12.8771089,77.5106465);
        listaCiudad.agregarNodoAlFinal(c1);
        agregarPunto(c1);
        
        ciudad c2 = new ciudad("Rio de Janeiro",-22.9068467,-43.1728965);
        listaCiudad.agregarNodoAlFinal(c2);
        agregarPunto(c2);
        
        ciudad c3 = new ciudad("Buenos Aires",-34.6036844,-58.3815591);
        listaCiudad.agregarNodoAlFinal(c3);
        agregarPunto(c3);
        
        ciudad c4 = new ciudad("Rocha",-34.4790141,-54.3352997);
        listaCiudad.agregarNodoAlFinal(c4);
        agregarPunto(c4);
        
        ciudad c5 = new ciudad("Montevideo",-34.9011127,-56.1645314);
        listaCiudad.agregarNodoAlFinal(c5);
        agregarPunto(c5);
                
        listaEmpresa.insertar(new empresa("Google", "Calle corrientes 1225, La Plata","Argentina", "googleArgentina@gmail.com", "AZUL"));
        listaEmpresa.insertar(new empresa("Antel", "18 de juli y ejido 1230, Canelones","Uruguay", "antelsupourtDC@antel.com", "AZUL"));
        listaEmpresa.insertar(new empresa("Movistar", "Uruguay 1520, Brasilia","Brasil", "movistarDC@Movistar.com", "AZUL"));
        listaEmpresa.insertar(new empresa("Claro", "AV. Simon Bolivar 1239, Asuncion","Paraguay", "claroDcSupourt@claro.com", "AZUL"));
        
        empresa e = new empresa("Intel", "BL. Freedom, Silicom Valley, Los Angeles","EEUU", "windowsDC@hotmail.com", "AZUL");
        
        listaEmpresa.insertar(e);
        data_center dc = new data_center("UnknownDC", 12.8771089, 77.5106465, e, 100, 250, e.getColor());
        listaDC.agregarNodoAlFinal(dc);
        
        agregarPunto(dc);
    }
}
