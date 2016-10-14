package DP.ED;

/**
 * Implementacion de arbol binario de busqueda.
 *
 * @version 1.0
 * @author <br>Asignatura Desarrollo de Programas
 * <b> Profesores DP </b><br>
 * Curso 14/15
 */
public class Arbol<tipoDato extends Comparable<tipoDato>> {

    /**
     * Dato almacenado en cada nodo del árbol.
     */
    private tipoDato datoRaiz;

    /**
     * Atributo que indica si el árbol está vacío.
     */
    boolean esVacio;

    /**
     * Hijo izquierdo del nodo actual
     */
    private Arbol<tipoDato> hIzq;

    /**
     * Hijo derecho del nodo actual
     */
    private Arbol<tipoDato> hDer;

    /**
     * Constructor por defecto de la clase. Inicializa un árbol vacío.
     */
    public Arbol() {
        this.esVacio = true;
        this.hIzq = null;
        this.hDer = null;
    }

    /**
     * Crea un nuevo árbol a partir de los datos pasados por parámetro.
     *
     * @param hIzq El hijo izquierdo del árbol que se está creando
     * @param datoRaiz Raíz del árbol que se está creando
     * @param hDer El hijo derecho del árbol que se está creando
     */
    public Arbol(Arbol<tipoDato> hIzq, tipoDato datoRaiz, Arbol<tipoDato> hDer) {
        this.esVacio = false;
        this.datoRaiz = datoRaiz;
        this.hIzq = hIzq;
        this.hDer = hDer;
    }

    /**
     * Devuelve el hijo izquierdo del árbol
     *
     * @return El hijo izquierdo
     */
    public Arbol<tipoDato> getHijoIzq() {
        return hIzq;
    }

    /**
     * Devuelve el hijo derecho del árbol
     *
     * @return Hijo derecho del árbol
     */
    public Arbol<tipoDato> getHijoDer() {
        return hDer;
    }

    /**
     * Devuelve la raíz del árbol
     *
     * @return La raíz del árbol
     */
    public tipoDato getRaiz() {
        return datoRaiz;
    }

    /**
     * Comprueba si el árbol está vacío.
     *
     * @return verdadero si el árbol está vacío, falso en caso contrario
     */
    public boolean vacio() {
        return esVacio;
    }

    /**
     * Inserta un nuevo dato en el árbol.
     *
     * @param dato El dato a insertar
     * @return verdadero si el dato se ha insertado correctamente, falso en caso
     * contrario
     */
    public boolean insertar(tipoDato dato) {
        boolean resultado = true;
        if (vacio()) {
            datoRaiz = dato;
            esVacio = false;
        } else if (!(this.datoRaiz.equals(dato))) {
            Arbol<tipoDato> aux;
            if (dato.compareTo(this.datoRaiz) < 0) { // dato < datoRaiz
                if ((aux = getHijoIzq()) == null) {
                    hIzq = aux = new Arbol<tipoDato>();
                }
            } else // dato > datoRaiz
            {
                if ((aux = getHijoDer()) == null) {
                    hDer = aux = new Arbol<tipoDato>();
                }
            }
            resultado = aux.insertar(dato);
        } else {
            resultado = false;
        }
        return resultado;
    }

    /**
     * Comprueba si un dato se encuentra almacenado en el árbol
     *
     * @param dato El dato a buscar
     * @return verdadero si el dato se encuentra en el árbol, falso en caso
     * contrario
     */
    public boolean pertenece(tipoDato dato) {
        Arbol<tipoDato> aux = null;
        boolean encontrado = false;
        if (!vacio()) {
            if (this.datoRaiz.equals(dato)) {
                encontrado = true;
            } else {
                if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
                {
                    aux = getHijoIzq();
                } else // dato > datoRaiz
                {
                    aux = getHijoDer();
                }
                if (aux != null) {
                    encontrado = aux.pertenece(dato);
                }
            }
        }
        return encontrado;
    }

    /**
     * Borrar un dato del árbol.
     *
     * @param dato El dato que se quiere borrar
     */
    public void borrar(tipoDato dato) {
        if (!vacio()) {
            if (dato.compareTo(this.datoRaiz) < 0 && hIzq != null) { // dato<datoRaiz
                hIzq = hIzq.borrarOrden(dato);
            } else if (dato.compareTo(this.datoRaiz) > 0 && hDer != null) { // dato>datoRaiz
                hDer = hDer.borrarOrden(dato);
            } else // En este caso el dato es datoRaiz
            {
                if (hIzq == null && hDer == null) {
                    esVacio = true;
                } else {
                    borrarOrden(dato);
                }
            }
        }
    }

    /**
     * Borrar un dato. Este método es utilizado por el método borrar anterior.
     *
     * @param dato El dato a borrar
     * @return Devuelve el árbol resultante después de haber realizado el
     * borrado
     */
    private Arbol<tipoDato> borrarOrden(tipoDato dato) {
        tipoDato datoaux;
        Arbol<tipoDato> retorno = this;
        Arbol<tipoDato> aborrar, candidato, antecesor;

        if (!vacio()) {
            if (dato.compareTo(this.datoRaiz) < 0 && hIzq != null) { // dato<datoRaiz
                hIzq = hIzq.borrarOrden(dato);
            } else if (dato.compareTo(this.datoRaiz) > 0 && hDer != null) { // dato>datoRaiz
                hDer = hDer.borrarOrden(dato);
            } else {
                aborrar = this;
                if ((hDer == null) && (hIzq == null)) {
                    /* si es hoja */
                    retorno = null;
                } else {
                    if (hDer == null) {
                        /* Solo hijo izquierdo */
                        aborrar = hIzq;
                        datoaux = this.datoRaiz;
                        datoRaiz = hIzq.getRaiz();
                        hIzq.datoRaiz = datoaux;
                        hIzq = hIzq.getHijoIzq();
                        hDer = aborrar.getHijoDer();

                        retorno = this;
                    } else if (hIzq == null) {
                        /* Solo hijo derecho */
                        aborrar = hDer;
                        datoaux = datoRaiz;
                        datoRaiz = hDer.getRaiz();
                        hDer.datoRaiz = datoaux;
                        hDer = hDer.getHijoDer();
                        hIzq = aborrar.getHijoIzq();

                        retorno = this;
                    } else {
                        /* Tiene dos hijos */
                        candidato = this.getHijoIzq();
                        antecesor = this;
                        while (candidato.getHijoDer() != null) {
                            antecesor = candidato;
                            candidato = candidato.getHijoDer();
                        }

                        /* Intercambio de datos de candidato */
                        datoaux = datoRaiz;
                        datoRaiz = candidato.getRaiz();
                        candidato.datoRaiz = datoaux;
                        aborrar = candidato;
                        if (antecesor == this) {
                            hIzq = candidato.getHijoIzq();
                        } else {
                            antecesor.hDer = candidato.getHijoIzq();
                        }
                    } // Eliminar solo ese nodo, no todo el subarbol
                    aborrar.hIzq = null;
                    aborrar.hDer = null;
                }
            }
        }
        return retorno;
    }

    /**
     * Recorrido inOrden del árbol.
     */
    public void inOrden() {
        Arbol<tipoDato> aux = null;
        if (!vacio()) {
            if ((aux = getHijoIzq()) != null) {
                aux.inOrden();
            }

            System.out.println(this.datoRaiz);

            if ((aux = getHijoDer()) != null) {
                aux.inOrden();
            }
        }
    }

    /**
     * Calcula la profundiad del árbol
     *
     * @return Profundidad del árbol
     */
    public Integer profundidad() {
        Integer prof = 1;
        Integer prof2 = 1;
        Arbol<tipoDato> aux, aux2;
        if (!vacio()) {
            aux = getHijoIzq();
            aux2 = getHijoDer();
            if (aux != null) {
                prof = 1 + aux.profundidad();
            }
            if (aux2 != null) {
                prof2 = 1 + aux2.profundidad();
            }
            if (prof2.compareTo(prof) > 0) {
                prof = prof2;
            }
        }
        return prof;
    }

    /**
     * Cuenta los nodos por tipos
     *
     * @param nodos Nodos contados. Nodos[0] contiene las hojas, Nodos[1] los
     * internos
     */
    public void tiposNodos(Integer[] nodos) {
        if (!vacio()) {
            boolean sumado = false;
            if (hIzq == null && hDer == null) {
                nodos[0]++;
            } else {
                if (hIzq != null) {
                    nodos[1]++;
                    sumado = true;
                    hIzq.tiposNodos(nodos);
                }
                if (hDer != null) {
                    if (!sumado) {
                        nodos[1]++;
                    }
                    hDer.tiposNodos(nodos);
                }
            }
        }
    }

    /**
     * Cuenta los nodos
     *
     * @return Número de nodos del árbol
     */
    public Integer nodos() {
        Integer count = 0;
        if (!vacio()) {
            count++;
            if (hIzq != null) {
                count += hIzq.nodos();
            }
            if (hDer != null) {
                count += hDer.nodos();
            }
        }
        return count;
    }

}
