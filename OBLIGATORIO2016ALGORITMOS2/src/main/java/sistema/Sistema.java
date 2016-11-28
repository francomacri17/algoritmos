package sistema;

import Listas.CiudadLista;

import Listas.DCLista;

import Listas.abbEmpresa;

import Listas.Dijkstra;

import Modelo.Punto;

import Modelo.ciudad;

import Modelo.data_center;

import Modelo.empresa;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import sistema.Retorno.Resultado;

public class Sistema implements ISistema {

    static abbEmpresa listaEmpresa = new abbEmpresa();
    static ArrayList<String> colores = new ArrayList<String>();

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

    /**
     *
     * @return
     */
    @Override
    public ArrayList<String> getColores() {
        return colores;
    }

    public ArrayList<Integer> getPosDataCenters() {
        ArrayList<Integer> dataCentersPos = new ArrayList<>();
        int cantidad = contarPuntos();
        int j = 0;
        for (int i = 0; i < cantidad; i++) {
            if (cantPuntos[i] instanceof data_center) {
                dataCentersPos.add(i);
            }
        }
        return dataCentersPos;
    }

    public static void setCantPuntos(int cantPuntos) {

        if (Sistema.cantPuntos == null) {

            Sistema.cantPuntos = new Punto[cantPuntos];

            dijkstra.setMAX(cantPuntos);

        }

    }

    @Override
    public void cargarColores() {
        colores.add("blue");
        colores.add("red");
        colores.add("purple");
        colores.add("brown");
        colores.add("black");
        colores.add("gray");
        colores.add("white");
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

        dijkstra = new Dijkstra(contarPuntos());

    }

    public void eliminarPuntoYsusTramos(int indexPunto) {

        Punto p = cantPuntos[indexPunto];

        int i = 0;

        while (i < cantPuntos.length) {

            if (cantPuntos[i].getCoordX() == p.getCoordX() && cantPuntos[i].getCoordY() == p.getCoordY()) {

                cantPuntos[i] = null;

                i = cantPuntos.length;

            } else {

                i++;

            }

        }

        dijkstra = new Dijkstra(contarPuntos());
        dijkstra.removeEdge(indexPunto);
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
        int cant = 0;
        if (cantPuntos != null) {

            while (i < cantPuntos.length) {

                if (cantPuntos[i] != null) {

                    cant++;

                }

                i++;

            }

        }

        return cant;

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
        int i = 0;
        while (i < colores.size()) {
            if (colores.get(i).equals(color)) {
                colores.remove(i);
                i = colores.size();
            }
            i++;
        }
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

        } else if (!dijkstra.existsEdge(indexOrigen, indexDestino)) {

            dijkstra.addEdge(indexOrigen, indexDestino, peso, false);

            r = new Retorno(Resultado.OK);

        } else {

            r = new Retorno(Resultado.ERROR_3, "Ya existe un tramo registrado entre los vertices", 3);

        }

        return r;

    }

    @Override

    public Retorno eliminarTramo(int indexOrigen, int indexDestino) {

        // TODO Auto-generated method stub
        Retorno r;
        if (dijkstra.existsEdge(indexOrigen, indexDestino)) {
            dijkstra.removeEdge(indexOrigen, indexDestino);
            r = new Retorno(Resultado.OK);
        } else {
            r = new Retorno(Resultado.ERROR_2, "No existe un tramo con esos puntos", 2);
        }
        return r;
    }

    @Override

    public Retorno eliminarPunto(int indexPunto) {

        // TODO Auto-generated method stub
        eliminarPuntoYsusTramos(indexPunto);
        return new Retorno(Resultado.OK);

    }

    @Override

    public Retorno mapaEstado() {

        // TODO Auto-generated method stub
        Retorno r;
        if (contarPuntos() > 0) {
            StringBuilder url = new StringBuilder();
            url = url.append("http://maps.googleapis.com/maps/api/staticmap?center=-14.2350040,-51.9252800&zoom=3&size=640x640");
            String string = url.toString();
            int cantidad = contarPuntos();
            for (int i = 0; i < cantidad; i++) {
                if (cantPuntos[i] instanceof ciudad) {
                    string = string + "&markers=color:yellow%7Clabel:none%7C" + cantPuntos[i].getCoordX() + "," + cantPuntos[i].getCoordY() + "";
                }
                if (cantPuntos[i] instanceof data_center) {
                    string = string + "&markers=color:" + cantPuntos[i].getColor() + "%7Clabel:none%7C" + cantPuntos[i].getCoordX() + "," + cantPuntos[i].getCoordY() + "";
                }
            }

            if (java.awt.Desktop.isDesktopSupported()) {
                try {
                    Desktop dk = Desktop.getDesktop();
                    dk.browse(new URI(string));
                } catch (Exception e) {
                    System.out.print("Error al abrir URL: " + e.getMessage());
                }
            }
            r = new Retorno(Resultado.OK);
        } else {
            r = new Retorno(Resultado.ERROR_1);
        }
        return r;
    }

    @Override

    public Retorno procesarInformacion(int indexDC,
            int esfuerzoCPUrequeridoEnHoras) {

        // TODO Auto-generated method stub
        Retorno r = new Retorno(Resultado.ERROR_2, "error 2", 2);
        boolean b = false;
        int costo = 0;
        Punto p = cantPuntos[indexDC];
        data_center dc = listaDC.darDC(p.getCoordX(), p.getCoordY());
        data_center dcCercano = new data_center();
        if (dc.getCpuUsado() + esfuerzoCPUrequeridoEnHoras <= dc.getCapacidadCPUenHoras()) {
            costo = dc.getCostoCPUporHora() * esfuerzoCPUrequeridoEnHoras;
            r = new Retorno(Resultado.OK, dc.getNombre(), costo);
            dc.setCpuUsado(esfuerzoCPUrequeridoEnHoras);
            b = true;
        }
        if (b == false) {
            if (getPosDataCenters() != null) {
                ArrayList<Integer> pos = getPosDataCenters();
                Dijkstra d = new Dijkstra();
                d.dijkstra(indexDC);
                for (int i = 0; i < pos.size(); i++) {
                    p = cantPuntos[pos.get(i)];
                    dc = listaDC.darDC(p.getCoordX(), p.getCoordY());
                    if (d.masCercano(pos.get(i)) < 1000000000) {
                        if ((dc.getCpuUsado() + esfuerzoCPUrequeridoEnHoras) <= dc.getCapacidadCPUenHoras()) {
                            if (costo != 0) {
                                int costoAux = d.masCercano(pos.get(i)) + (dc.getCostoCPUporHora() * esfuerzoCPUrequeridoEnHoras);
                                if (costo > costoAux) {
                                    costo = costoAux;
                                    r = new Retorno(Resultado.OK, dc.getNombre(), costo);
                                    dcCercano = dc;
                                }

                            }
                            if (costo == 0) {
                                costo = d.masCercano(pos.get(i)) + (dc.getCostoCPUporHora() * esfuerzoCPUrequeridoEnHoras);
                                r = new Retorno(Resultado.OK, dc.getNombre(), costo);
                            }

                        }
                    }

                }
                if(dcCercano!=null){
                    dcCercano.setCapacidadCPUenHoras(esfuerzoCPUrequeridoEnHoras);
                }
            }

        }
        return r;
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

    public void cargarDatosPrueba() {

        ciudad c = new ciudad("Punta del Este", -34.9368789, -54.9281496);

        listaCiudad.agregarNodoAlFinal(c);

        agregarPunto(c);

        ciudad c1 = new ciudad("Silicon Valley", -12.8771089, -77.5106465);

        listaCiudad.agregarNodoAlFinal(c1);

        agregarPunto(c1);

        ciudad c2 = new ciudad("Rio de Janeiro", -22.9068467, -43.1728965);

        listaCiudad.agregarNodoAlFinal(c2);

        agregarPunto(c2);

        ciudad c3 = new ciudad("Buenos Aires", -34.6036844, -58.3815591);

        listaCiudad.agregarNodoAlFinal(c3);

        agregarPunto(c3);

        ciudad c4 = new ciudad("Rocha", -34.4790141, -54.3352997);

        listaCiudad.agregarNodoAlFinal(c4);

        agregarPunto(c4);

        ciudad c5 = new ciudad("Montevideo", -34.9011127, -56.1645314);

        listaCiudad.agregarNodoAlFinal(c5);

        agregarPunto(c5);

        registrarEmpresa("Google", "Calle corrientes 1225, La Plata", "Argentina", "googleArgentina@gmail.com", "blue");

        registrarEmpresa("Antel", "18 de juli y ejido 1230, Canelones", "Uruguay", "antelsupourtDC@antel.com", "red");

        empresa e = new empresa("Claro", "AV. Simon Bolivar 1239, Asuncion", "Paraguay", "claroDcSupourt@claro.com", "purple");

        registrarEmpresa(e.getNombre(), e.getDireccion(), e.getPais(), e.getEmail_contacto(), e.getColor());

        registrarDC("SaltaDC", -24.7997688, -65.4150367, e.getNombre(), 50, 199);

        empresa e1 = new empresa("Intel", "BL. Freedom, Silicom Valley, Los Angeles", "EEUU", "windowsDC@hotmail.com", "white");

        registrarEmpresa(e1.getNombre(), e1.getDireccion(), e1.getPais(), e1.getEmail_contacto(), e1.getColor());

        registrarDC("UnknownDC", -13.8771089, -75.5106465, e1.getNombre(), 100, 250);

        empresa e2 = new empresa("Movistar", "Uruguay 1520, Brasilia", "Brasil", "movistarDC@Movistar.com", "orange");

        registrarEmpresa(e2.getNombre(), e2.getDireccion(), e2.getPais(), e2.getEmail_contacto(), e2.getColor());

        registrarDC("Brasilia", -15.7942287, -47.8821658, e2.getNombre(), 75, 99);

        registrarTramo(0, 1, 1);
        registrarTramo(1, 2, 1);
        registrarTramo(2, 3, 2);
        registrarTramo(3, 1, 1);
        registrarTramo(1, 6, 7);
        registrarTramo(6, 3, 10);
        registrarTramo(6, 5, 3);
        registrarTramo(0, 5, 100);
        registrarTramo(0, 6, 7);
    }

}
