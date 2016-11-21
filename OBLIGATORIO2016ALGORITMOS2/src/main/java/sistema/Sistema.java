package sistema;

import Listas.CiudadLista;
import Listas.DCLista;
import Listas.abbEmpresa;
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
    
    
    
@Override
    public abbEmpresa getListaEmpresa() {
        return listaEmpresa;
    }
    private static Punto[] cantPuntos;

    public static Punto[] getCantPuntos() {
        return cantPuntos;
    }

    public static void setCantPuntos(int cantPuntos) {
        if (getCantPuntos() == null) {
            Sistema.cantPuntos = new Punto[cantPuntos];
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
            } else {
                if (!pertence(coordX, coordY)) {
                empresa e = listaEmpresa.buscarEmpresa(empresa);
                if (e != null) {
                    data_center dc = new data_center(nombre,coordX,coordY,e,capacidadCPUenHoras,costoCPUporHora,e.getColor());
                    listaDC.agregarNodoAlFinal(dc);
                    agregarPunto(dc);
                    r = new Retorno(Resultado.OK);
                }else{
                    r = new Retorno(Resultado.ERROR_4, "La empresa no existe en el sistema", 4);
                }

            } else {
                r = new Retorno(Resultado.ERROR_3, "El punto ya existe", 3);
            }
        } 
    
    }else {
            r = new Retorno(Resultado.ERROR_1, "Ya hay registrados cantPuntos puntos", 1);
        }
        return r;
    }

    @Override
    public Retorno registrarTramo(Double coordXi, Double coordYi,
            Double coordXf, Double coordYf, int peso) {
        // TODO Auto-generated method stub
        return new Retorno(Resultado.NO_IMPLEMENTADA);
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
}
