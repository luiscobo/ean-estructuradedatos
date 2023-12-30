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


/**
 * Una lista es una estructura lineal compuesta por una secuencia de 0 o más elementos.
 * Esta interface representa las operaciones que las diversas implementaciones de la
 * lista debe realizar.
 * @param <T> Tipo de datos a almacenar en la lista. Los objetos de tipo T deben tener bien definidos el
 * método <b>equals</b>
 * @author LACOBO
 */
interface Lista<T> {

    /**
     * Retorna `true` si la lista está vacía (no contiene elementos), `false` si tiene al menos un elemento
     */
    val vacia: Boolean

    /**
     * Retorna `false` si la lista está vacía (no contiene elementos), `true` si tiene al menos un elemento
     */
    val noVacia: Boolean

    /**
     * Retona el número de elementos que hacen parte de la lista
     */
    val tam: Int

    /**
     * Verifica si el elemento especificado hace parte de la lista o no
     */
    operator fun contains(elemento: T): Boolean

    /**
     * Obtiene el primer elemento de la lista
     */
    val primero: T

    /**
     * Obtiene el último elemento de la lista
     */
    val ultimo: T

    /**
     * Obtiene la posición del último elemento de la lista
     */
    val ultimaPosicion: Int

    /**
     * Permite obtener el objeto ubicado en la posición indicada
     */
    operator fun get(posicion: Int): T

    /**
     * Permite cambiar el objeto que se encuentra ubicado en la posición dada
     */
    operator fun set(posicion: Int, valor: T)

    /**
     * Obtiene los diversos índices del arreglo
     */
    val indices: IntRange
        get() = IntRange(0, tam - 1)

    /**
     * Retorna una nueva lista idéntica a la lista que recibe el mensaje
     */
    fun copiar(): Lista<T>

    /**
     * Retorna una copia de la lista, desde la posición dada hasta la otra posición (incluye)
     */
    fun copiar(desde: Int, hasta: Int = -1): Lista<T>

    /**
     * Retorna una lista que contiene todos los elementos de la lista
     * original y el elemento dado al final
     */
    operator fun plus(elemento: T): Lista<T>

    /**
     * Retorna una lista conteniendo todos los elementos de la
     * lista original  y luego todos los elementos de la lista dada
     */
    operator fun plus(elementos: Lista<out T>): Lista<T>

    /**
     * Retorna una lista conteniendo todos los elementos de la
     * lista original  y luego todos los elementos de la lista dada
     */
    operator fun plus(elementos: Arreglo<out T>): Lista<T>

    /**
     * Agrega un nuevo elemento al inicio de la  lista
     */
    fun agregarAlPrincipio(elemento: T)

    /**
     * Agrega un nuevo elemento al final de la lista
     */
    fun agregarAlFinal(elemento: T)

    /**
     * Agrega un nuevo element a la lista en la posición dadad
     */
    fun agregarEnPosicion(posicion: Int, elemento: T)

    /**
     * Agrega todos los elementos de la lista al final de esta lista
     */
    fun agregarLista(otraLista: Lista<T>)

    /**
     * Agrega un arreglo al final de la lista
     */
    fun agregarArreglo(unArreglo: Arreglo<T>)

    /**
     * Elimina el primer elemento de la lista
     */
    fun eliminarPrimero()

    /**
     * Elimina el último elemento de la lista
     */
    fun eliminarUltimo()

    /**
     * Elimina la primera ocurrencia del elemento que se pasa como parámetro
     */
    fun eliminarElemento(elemento: T)

    /**
     * Elimina el elemento que se encuentra en la posición especificada
     */
    fun eliminarElementoEnPosicion(posicion: Int)

    /**
     * Elimina todos los elementos de la lista
     */
    fun eliminarTodosLosElementos()


    /**
     * Retorna la posición de la primera ocurrencia del elemento
     */
    fun posicionDe(elemento: T): Int

    /**
     * Retorna la posición de la última ocurrencia del elemento dado
     */
    fun ultimaPosicionDe(elemento: T): Int

    /**
     * Permite saber si las dos listas poseen los mismos elemento sin importar el orden
     */
    fun similar(otraLista: Lista<T>): Boolean {
        if (this.tam != otraLista.tam) {
            return false
        }
        for (i in indices) {
            if (get(i) !in otraLista) {
                return false
            }
        }
        return true
    }

    /**
     * Obtiene los componentes de la lista
     */
    operator fun component1(): T
    operator fun component2(): T
    operator fun component3(): T
    operator fun component4(): T
    operator fun component5(): T

    /**
     * Operaciones con listas
     */
    companion object {
        private const val IMPLEMENTACION_LISTA_ARREGLOS = "ListasConArreglos"
        private const val IMPLEMENTACION_LISTA_NODOS = "ListasConNodos"

        private var implementacionListaPorDefecto: String? = IMPLEMENTACION_LISTA_NODOS

        fun configurarImplementacionLista(implementacion: String) {
            implementacionListaPorDefecto = implementacion
        }

        private fun <T> crearListaDeAcuerdoConImplementacion(): Lista<T>? {
            when (implementacionListaPorDefecto) {
                IMPLEMENTACION_LISTA_ARREGLOS -> return ListaConArreglos<T>()
                IMPLEMENTACION_LISTA_NODOS -> return ListaConNodosDoblementeEncadenados<T>()
            }
            return null
        }


        fun <T> crear(vararg elements: T): Lista<T> {
            val result: Lista<T>? = crearListaDeAcuerdoConImplementacion()
            if (result != null) {
                for (elem in elements) {
                    result.agregarAlFinal(elem)
                }
                return result
            }
            throw IllegalArgumentException("No hay una implementación por defecto para la lista!")
        }
    }

}

fun <T> recorrer(lista: Lista<T>, accion: (T) -> Unit): Unit {
    for (i in lista.indices) {
        val elemento = lista[i]
        accion(elemento)
    }
}

fun <T> recorrerConIndice(lista: Lista<T>, accion: (Int, T) -> Unit): Unit {
    for (i in lista.indices) {
        val elemento = lista[i]
        accion(i, elemento)
    }
}