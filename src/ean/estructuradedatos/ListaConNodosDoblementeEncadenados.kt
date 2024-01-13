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

//-------------------------------------------------
// Esta clase implementa las operaciones de un nodo
// de la lista doblemente encadenada
//-------------------------------------------------
private class Nodo<T>(var info: T) {
    // Atributos
    var sig: Nodo<T>? = null
    var ant: Nodo<T>? = null
}

/**
 * Implementación de Listas con el uso de nodos doblemente encadenados
 */
class ListaConNodosDoblementeEncadenados<T>() : Lista<T> {


    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    private var prim: Nodo<T>? = null
    private var ult: Nodo<T>? = null
    private var numNodos = 0

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

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
    // Propiedades y métodos
    // -----------------------------------------------------------------

    override val vacia: Boolean
        get() = numNodos == 0
    override val noVacia: Boolean
        get() = numNodos > 0
    override val tam: Int
        get() = numNodos
    override val primero: T
        get() = prim!!.info
    override val ultimo: T
        get() = ult!!.info
    override val ultimaPosicion: Int
        get() = numNodos - 1

    private fun obtenerNodoPosicion(pos: Int): Nodo<T>? {
        var p: Nodo<T>? = prim
        for (i in 0 ..< pos) {
            p = p!!.sig
        }
        return p
    }

    override fun get(posicion: Int): T {
        require(posicion in 0 ..< numNodos) { "La posición $posicion no es válida!!" }

        return obtenerNodoPosicion(posicion)!!.info
    }

    override fun copiar(): Lista<T> {
        val result = ListaConNodosDoblementeEncadenados<T>()
        var p: Nodo<T>? = prim
        while (p != null) {
            result.agregarAlFinal(p.info)
            p = p.sig
        }
        return result
    }

    override fun copiar(desde: Int, hasta: Int): Lista<T> {
        var fin = hasta
        if (fin < 0) {
            fin = ultimaPosicion
        }
        require(desde in 0..fin && fin <= ultimaPosicion)
        var p = obtenerNodoPosicion(desde)
        var i = desde
        val result = ListaConNodosDoblementeEncadenados<T>()
        while (p != null && i <= fin) {
            result.agregarAlFinal(p.info)
            i++
            p = p.sig
        }
        return result
    }

    override fun eliminarPrimero() {
        require(noVacia) { "No se puede eliminar de una lista vacía" }
        prim = prim!!.sig
        if (prim!!.sig == null) {
            ult = null
        }
        else {
            prim!!.ant = null
        }
        numNodos--
    }

    override fun eliminarUltimo() {
        require(noVacia) { "No se puede eliminar de una lista vacía" }
        ult = ult!!.ant
        if (ult == null) {
            prim = null
        }
        else {
            ult!!.sig = null
        }
        numNodos--
    }

    override fun eliminarElementoEnPosicion(posicion: Int) {
        require(posicion in 0 .. ultimaPosicion) { "La posición $posicion es inválida!" }
        if (posicion == 0) {
            eliminarPrimero()
        }
        else if (posicion == ultimaPosicion) {
            eliminarUltimo()
        }
        else {
            var p: Nodo<T>? = obtenerNodoPosicion(posicion)
            val q = p?.ant
            val r = p?.sig

            q?.sig = r
            r?.ant = q

            p?.sig = null
            p?.ant = null
            p = null

            numNodos--
        }
    }

    override fun eliminarTodosLosElementos() {
        prim = null
        ult = null
        numNodos = 0
    }

    override fun component1(): T = get(0)

    override fun component2(): T = get(1)

    override fun component3(): T = get(2)

    override fun component4(): T = get(3)

    override fun component5(): T = get(4)

    override fun iterador(): Iterador<T> {
        return object : Iterador<T> {
            private var posActual: Int = -1
            private var actual: Nodo<T>? = null

            override var info: T
                get() {
                    if (actual != null) {
                        return actual!!.info
                    }
                    throw IllegalArgumentException()
                }
                set(value) {
                    if (actual != null) {
                        actual!!.info = value
                    }
                    else {
                        throw IllegalArgumentException()
                    }
                }
            override val posicionActual: Int
                get() = if (posActual in indices) posActual else -1

            override fun ubicarAlPrincipio() {
                if (vacia) {
                    posActual = -1
                    actual = null
                }
                else {
                    posActual = 0
                    actual = prim
                }
            }

            override fun ubicarAlFinal() {
                if (vacia) {
                    posActual = -1
                    actual = null
                }
                else {
                    posActual = ultimaPosicion
                    actual = ult
                }
            }

            override fun ubicarEnLaPosicion(posicion: Int) {
                if (vacia || posicion !in indices) {
                    posActual = -1
                    actual = null
                }
                else {
                    posActual = 0
                    actual = prim
                    while (posActual < posicion) {
                        posActual++
                        actual = actual!!.sig
                    }
                }
            }

            override fun tieneSiguiente(): Boolean = actual != null

            override fun tieneAnterior(): Boolean = actual != null

            override fun avanzar(): Iterador<T> {
                if (actual != null) {
                    actual = actual!!.sig
                    posActual++
                }
                return this
            }

            override fun retroceder(): Iterador<T> {
                if (actual != null) {
                    actual = actual!!.ant
                    posActual--
                }
                return this
            }

            override fun eliminar() {
                if (actual != null) {
                    when (actual) {
                        prim -> {
                            eliminarPrimero()
                            actual = prim
                            posActual = if (actual == null) -1 else 0
                        }
                        ult -> {
                            eliminarUltimo()
                            actual = null
                            posActual = -1
                        }
                        else -> {
                            val ant = actual!!.ant
                            actual = actual!!.sig
                            ant!!.sig = actual
                            actual!!.ant = ant
                            numNodos--
                        }
                    }
                }
                else {
                    throw IllegalArgumentException()
                }
            }

            override fun cambiar(nuevoElemento: T) {
                if (actual != null) {
                    actual!!.info = nuevoElemento
                }
                else {
                    throw IllegalArgumentException()
                }
            }

            override fun agregarAntes(elemento: T) {
                if (actual != null) {
                    if (actual == prim) {
                        agregarAlPrincipio(elemento)
                    }
                    else {
                        val nodo = Nodo(elemento)
                        val p: Nodo<T>? = actual!!.ant

                        p?.sig = nodo
                        nodo.ant = p

                        actual!!.ant = nodo
                        nodo.sig = actual

                        ++numNodos
                    }
                }
                else {
                    throw NoSuchElementException()
                }
            }

            override fun agregarDespues(elemento: T) {
                if (actual != null) {
                    if (actual == ult) {
                        agregarAlFinal(elemento)
                    }
                    else {
                        val nodo = Nodo(elemento)
                        val p: Nodo<T>? = actual!!.sig

                        actual!!.sig = nodo
                        nodo.ant = actual

                        p!!.ant = nodo
                        nodo.sig = p

                        ++numNodos
                    }
                }
                else {
                    throw NoSuchElementException()
                }
            }

        }
    }

    override fun ultimaPosicionDe(elemento: T): Int {
        var pos = ultimaPosicion
        var p: Nodo<T>? = ult

        while (p != null) {
            if (p!!.info == elemento) {
                return pos
            }
            pos--
            p = p.ant
        }

        return NO_EXISTE
    }

    override fun posicionDe(elemento: T): Int {
        var pos = 0
        var p: Nodo<T>? = prim

        while (p != null) {
            if (p.info == elemento) {
                return pos
            }
            pos++
            p = p.sig
        }

        return NO_EXISTE
    }

    override fun eliminarElemento(elemento: T) {
        var p = prim
        if (p!!.info == elemento) {
            eliminarPrimero()
        }
        else {
            while (p != null && p.info != elemento) {
                p = p.sig
            }
            if (p != null) {
                if (p == ult) {
                    eliminarUltimo()
                }
                else {
                    val q = p.ant
                    val r = p.sig

                    q?.sig = r
                    r?.ant = q

                    p.sig = null
                    p.ant = null
                    p = null
                    numNodos--
                }
            }
        }
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
        require(posicion >= 0) { "La posición $posicion es inválida!!"}

        if (posicion == 0) {
            agregarAlPrincipio(elemento)
        }
        else if (posicion >= numNodos) {
            agregarAlFinal(elemento)
        }
        else {
            val nodo = Nodo(elemento)
            val p: Nodo<T>? = obtenerNodoPosicion(posicion)
            val q: Nodo<T>? = p?.ant

            q?.sig = nodo
            nodo.ant = q

            p?.ant = nodo
            nodo.sig = p

            ++numNodos
        }
    }

    override fun agregarAlFinal(elemento: T) {
        val nodo = Nodo(elemento)
        if (prim == null) {
            prim = nodo
        }
        else {
            ult!!.sig = nodo
            nodo.ant = ult
        }
        ult = nodo
        ++numNodos
    }

    override fun agregarAlPrincipio(elemento: T) {
        val nodo = Nodo(elemento)
        if (prim == null) {
            ult = nodo
        }
        else {
            prim!!.ant = nodo
            nodo.sig = prim
        }
        prim = nodo
        ++numNodos
    }

    override fun plus(elementos: Arreglo<out T>): Lista<T> {
        val lista = ListaConNodosDoblementeEncadenados(this)
        for (i in elementos.indices) {
            val elem = elementos[i]
            lista.agregarAlFinal(elem)
        }
        return lista
    }

    override fun plus(elementos: Lista<out T>): Lista<T> {
        val lista = ListaConNodosDoblementeEncadenados(this)
        for (i in elementos.indices) {
            val elem = elementos[i]
            lista.agregarAlFinal(elem)
        }
        return lista
    }

    override fun plus(elemento: T): Lista<T> {
        val lista = ListaConNodosDoblementeEncadenados<T>(this)
        lista.agregarAlFinal(elemento)
        return lista
    }

    override fun set(posicion: Int, valor: T) {
        require(posicion in 0 ..< numNodos) { "La posición $posicion está incorrecta" }
        val p = obtenerNodoPosicion(posicion)
        p!!.info = valor
    }

    override fun contains(elemento: T): Boolean {
        var p: Nodo<T>? = prim
        while (p != null) {
            if (p.info == elemento) {
                return true
            }
            p = p.sig
        }
        return false
    }

    override fun toString(): String {
        val str = StringBuilder("[")
        var p = prim

        for (i in this.indices) {
            str.append(p!!.info.toString())

            if (i != tam - 1) {
                str.append(", ")
            }
            p = p.sig
        }
        str.append("]")
        return str.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Lista<T>

        if (numNodos != other.tam) return false

        var p = prim
        var i = 0
        while (p != null) {
            if (p.info != other[i]) {
                return false
            }
            p = p.sig
            i++
        }

        return true
    }

    override fun hashCode(): Int {
        var result = prim.hashCode()
        result = 31 * result + numNodos
        return result
    }

}