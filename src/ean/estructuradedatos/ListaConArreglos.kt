/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas
 * Facultad de Ingeniería
 *
 * Proyecto EAN Kotlin Estructura de Datos
 * @author Luis Cobo (lacobo@universidadean.edu.co)
 * Fecha: Feb 05, 2024
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.estructuradedatos

// -----------------------------------------------------------------
// Constantes
// -----------------------------------------------------------------
const val TAMAÑO_POR_DEFECTO = 100
const val NO_EXISTE = -1

/**
 * Implementación de la Interface Lista a través de arreglos
 */
class ListaConArreglos<T>() : Lista<T> {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    private var info: Arreglo<T>
    private var tamLista: Int = 0

    // -----------------------------------------------------------------
    // Constructor
    // -----------------------------------------------------------------
    init {
        info = Arreglo(TAMAÑO_POR_DEFECTO)
    }

    /**
     * Permite crear la lista con los elementos que se pasan como parámetro
     */
    constructor(vararg elementos: T) : this() {
        for (elem in elementos) {
            this.agregarAlFinal(elem)
        }
    }

    /**
     * Crea una lista a partir de la lista que se recibe como parámetro
     */
    constructor(otraLista: Lista<T>) : this() {
        this.agregarLista(otraLista)
    }

    /**
     * Crea una lista a partir del arreglo que se recibe como parámetro
     */
    constructor(arreglo: Arreglo<T>) : this() {
        for (i in arreglo.indices) {
            val elem = arreglo[i]
            this.agregarAlFinal(elem)
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------
    override val vacia: Boolean
        get() = tamLista == 0
    override val noVacia: Boolean
        get() = tamLista > 0
    override val tam: Int
        get() = tamLista
    override val primero: T
        get() = info[0]
    override val ultimo: T
        get() = info[ultimaPosicion]
    override val ultimaPosicion: Int
        get() = tam - 1

    override fun get(posicion: Int): T = info[posicion]

    override fun copiar(): Lista<T> {
        val result = ListaConArreglos<T>()
        for (i in info.indices) {
            result.agregarAlFinal(this[i])
        }
        return result
    }

    override fun copiar(desde: Int, hasta: Int): Lista<T> {
        var fin = hasta
        if (fin < 0) {
            fin = tam - 1
        }
        val result = ListaConArreglos<T>()
        for (i in desde..fin) {
            result.agregarAlFinal(this[i])
        }
        return result
    }

    override fun eliminarPrimero() {
        if (vacia) {
            throw IndexOutOfBoundsException("La lista está vacía")
        }

        for (pos in 1..<tam) {
            info[pos - 1] = info[pos]
        }
        tamLista--
    }

    override fun eliminarUltimo() {
        if (vacia) {
            throw IndexOutOfBoundsException("La lista está vacía")
        }

        tamLista--
    }

    override fun eliminarElementoEnPosicion(posicion: Int) {
        if (posicion in 0..<tam) {
            if (posicion == ultimaPosicion) {
                eliminarUltimo()
            }
            else {
                for (pos in posicion..< tamLista - 1) {
                    info[pos] = info[pos + 1]
                }
                tamLista--
            }
        }
        else {
            throw IndexOutOfBoundsException("La posición $posicion no es válida!")
        }
    }

    override fun eliminarTodosLosElementos() {
        tamLista = 0
    }

    override fun component1(): T = get(0)

    override fun component2(): T = get(1)

    override fun component3(): T = get(2)

    override fun component4(): T = get(3)

    override fun component5(): T = get(4)

    override fun ultimaPosicionDe(elemento: T): Int {
        for (i in ultimaPosicion downTo 0) {
            if (info[i] == elemento) {
                return i
            }
        }
        return NO_EXISTE
    }

    override fun posicionDe(elemento: T): Int {
        for (i in indices) {
            if (info[i] == elemento) {
                return i
            }
        }
        return NO_EXISTE
    }

    override fun eliminarElemento(elemento: T) {
        val posicion = posicionDe(elemento)
        if (posicion == NO_EXISTE) {
            throw NoSuchElementException("Elemento inexistente!")
        }
        this.eliminarElementoEnPosicion(posicion)
    }

    override fun agregarArreglo(unArreglo: Arreglo<T>) {
        for (i in unArreglo.indices) {
            val elem = unArreglo[i]
            this.agregarAlFinal(elem)
        }
    }

    override fun agregarLista(otraLista: Lista<T>) {
        for (i in otraLista.indices) {
            val elem = otraLista[i]
            this.agregarAlFinal(elem)
        }
    }

    override fun agregarEnPosicion(posicion: Int, elemento: T) {
        if (posicion in 0 ..< tamLista) {
            if (tam >= info.tam) {
                info.expandirTamaño()
            }
            for (pos in tamLista downTo posicion + 1) {
                info[pos] = info[pos - 1]
            }
            info[posicion] = elemento
            tamLista++
        }
        else if (posicion >= tamLista) {
            this.agregarAlFinal(elemento)
        }
        else {
            throw IndexOutOfBoundsException("La posicion $posicion es incorrecta!")
        }
    }

    override fun agregarAlFinal(elemento: T) {
        if (tam >= info.tam) {
            info.expandirTamaño()
        }
        info[tamLista++] = elemento
    }

    override fun agregarAlPrincipio(elemento: T) {
        if (tam >= info.tam) {
            info.expandirTamaño()
        }
        for (pos in tamLista downTo 1) {
            info[pos] = info[pos - 1]
        }
        info[0] = elemento
        tamLista++
    }

    override fun plus(elementos: Arreglo<out T>): Lista<T> {
        val lista = ListaConArreglos<T>(this)
        for (i in elementos.indices) {
            val elem = elementos[i]
            lista.agregarAlFinal(elem)
        }
        return lista
    }

    override fun plus(elementos: Lista<out T>): Lista<T> {
        val lista = ListaConArreglos<T>(this)
        for (i in elementos.indices) {
            val elem = elementos[i]
            lista.agregarAlFinal(elem)
        }
        return lista
    }

    override fun plus(elemento: T): Lista<T> {
        val lista = ListaConArreglos<T>(this)
        lista.agregarAlFinal(elemento)
        return lista
    }

    override fun set(posicion: Int, valor: T) {
        if (posicion in 0 ..< tamLista) {
            info[posicion] = valor
        }
        else {
            throw IndexOutOfBoundsException("La posicion $posicion es incorrecta!!")
        }
    }

    override fun contains(elemento: T): Boolean {
        for (i in indices) {
            if (info[i]!! == elemento) {
                return true
            }
        }
        return false
    }

    override fun toString(): String {
        val str = StringBuilder("[")

        for (i in this.indices) {
            str.append(info[i].toString())

            if (i != tam - 1) {
                str.append(", ")
            }
        }
        str.append("]")
        return str.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ListaConArreglos<*>

        if (tamLista != other.tamLista) return false
        if (info != other.info) return false

        return true
    }

    override fun hashCode(): Int {
        var result = info.hashCode()
        result = 31 * result + tamLista
        return result
    }

}