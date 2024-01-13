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
 * Los iteradores son objetos que permiten recorrer una colección de objetos
 * (una estructura de datos). Un iterador sigue una ruta y da acceso a elementos
 * de datos del contenedor, pero no realiza iteración
 */
interface Iterador<T> {
    // -----------------------------------------------------------------
    // Propiedades
    // -----------------------------------------------------------------

    /**
     * Permite obtener el valor actual almacenado en la posición
     * actual del iterador
     */
    var info: T

    /**
     * Permite obtener la posición actual del iterador. Esta
     * posición es -1 si el iterador está por fuera del contenedor
     */
    val posicionActual: Int

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Coloca el iterador en el primer elemento de la colección
     */
    fun ubicarAlPrincipio(): Unit

    /**
     * Coloca el iterador en el último elemento de la colección
     */
    fun ubicarAlFinal(): Unit

    /**
     * Coloca el iterador en la posición especificada dentro de la colección
     */
    fun ubicarEnLaPosicion(posicion: Int): Unit

    /**
     * Permite saber si el iterador puede avanzar al siguiente
     * elemento de la colección
     */
    fun tieneSiguiente(): Boolean

    /**
     * Permite saber si el iterador puede retroceder al anterior
     * elemento de la colección
     */
    fun tieneAnterior(): Boolean

    /**
     * Avanza el cursor del iterador al siguiente elemento de la colección
     */
    fun avanzar(): Iterador<T>
    operator fun inc(): Iterador<T> = avanzar()

    /**
     * Retrocede el cursor del iterador al anterior elemento de la colección
     */
    fun retroceder(): Iterador<T>
    operator fun dec(): Iterador<T> = retroceder()

    // Operaciones modificadoras

    /**
     * Agrega un nuevo elemento después de la posición actual del iterador
     */
    fun agregarDespues(elemento: T): Unit

    /**
     * Agrega un nuevo elemento antes de la posición ac
     */
    fun agregarAntes(elemento: T): Unit

    /**
     * Elimina el elemento actual que está siendo referenciado por el iterador
     * Este iterador avanzará después de eliminar el objeto.
     */
    fun eliminar(): Unit

    /**
     * Modifica el valor del objeto almacenado bajo el iterador
     */
    fun cambiar(nuevoElemento: T): Unit
}