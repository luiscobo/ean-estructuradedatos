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

import java.util.*

/**
 * Un arreglo es una estructura de datos que permite almacenar una colección de elementos del mismo
 * tipo. Los elementos de un arreglo son accesibles a través de un índice numérico, que comienza en
 * cero para el primer elemento. El tamaño del arreglo es fijo.
 *
 */

/* $Version = GitHub(1.0) */
class Arreglo<T>(tamañoInicial: Int) {

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    private var datos: Array<T?> = arrayOfNulls<Any?>(tamañoInicial) as Array<T?>

    // -----------------------------------------------------------------
    // Constructor Secundario
    // -----------------------------------------------------------------
    constructor(elemento: T, vararg elementos: T) : this(elementos.size + 1) {
        this.datos[0] = elemento
        for (i in elementos.indices) {
            this.datos[i + 1] = elementos[i]
        }
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Permite obtener el tamaño del arreglo
     */
    val tam: Int
        get() = datos.size

    /**
     * Permite obtener el objeto ubicado en la posición indicada
     */
    operator fun get(posicion: Int): T = datos[posicion]!!

    /**
     * Permite cambiar el objeto que se encuentra ubicado en la posición dada
     */
    operator fun set(posicion: Int, valor: T) {
        datos[posicion] = valor
    }

    /**
     * Obtiene los diversos índices del arreglo
     */
    val indices: IntRange
        get() = IntRange(0, tam - 1)

    /**
     * Obtiene el primer elemento del arreglo
     */
    val primero: T
        get() = datos[0]!!

    /**
     * Obtiene el último elemento del arreglo
     */
    val ultimo: T
        get() = datos.last()!!

    /**
     * Obtiene la última posición dentro del arreglo
     */
    val ultimaPosicion: Int
        get() = datos.lastIndex

    /**
     * Retorna un nuevo arreglo idéntico al arreglo que recibe el mensaje
     */
    fun copiar(): Arreglo<T> {
        val result = Arreglo<T>(this.tam)
        result.datos = datos.copyOf()
        return result
    }

    /**
     * Retorna un nuevo arreglo, el cuál es una copia del arreglo original, pero con
     * el tamaño dado. La copia es o truncada o expandida de acuerdo al valor correspondiente
     */
    fun copiar(nuevoTamaño: Int): Arreglo<T> {
        val result = Arreglo<T>(this.tam)
        result.datos = datos.copyOf(nuevoTamaño)
        return result
    }

    /**
     * Expande el tamañor del arreglo al dado como parámetro
     */
    fun copiar(desde: Int, hasta: Int): Arreglo<T> {
        val result = Arreglo<T>(hasta - desde)
        result.datos = Arrays.copyOfRange(this.datos, desde, hasta)
        return result
    }

    /**
     * Llena el arreglo con el valor especificado
     */
    fun llenarCon(elemento: T?, desde: Int = 0, hasta: Int = tam): Unit {
        Arrays.fill(this.datos, desde, hasta, elemento)
    }

    /**
     * Llena el arreglo con el resultado de aplicar la
     * función a cada índice del arreglo
     */
    fun llenarCon(valorInicial: (Int) -> T?): Unit {
        for (i in indices) {
            this.datos[i] = valorInicial(i)
        }
    }

    /**
     * Hace que el tamaño del arreglo cambien de acuerdo al tamaño
     * especificado como parámetro
     */
    fun expandirTamaño(nuevoTamaño: Int = 0): Unit {
        var tamFinal = nuevoTamaño
        if (tamFinal <= 0) {
            tamFinal = 2 * tam
        }
        this.datos = this.datos.copyOf(tamFinal)
    }

    /**
     * Retorna un arreglo que contiene todos los elementos del arreglo
     * original y el elemento dado al final
     */
    operator fun plus(elemento: T): Arreglo<T> {
        val pos = tam
        val result = Arreglo<T>(pos + 1)
        result.datos = this.datos.copyOf(pos + 1)
        result[pos] = elemento
        return result
    }

    /**
     * Retorna un arreglo conteniendo todos los elementos del
     * arreglo origina  y luego todos los elementos del arreglo dado
     */
    operator fun plus(elementos: Arreglo<out T>): Arreglo<T> {
        val esteTam = this.tam
        val otroTam = elementos.tam
        val result = Arreglo<T>(esteTam + otroTam)
        result.datos = this.datos.copyOf(esteTam + otroTam)
        System.arraycopy(elementos.datos, 0, result.datos, esteTam, otroTam)
        return result
    }

    /**
     * Retorna una representación como String de este arreglo
     */
    override fun toString(): String {
        val str = StringBuilder("[")

        for (i in datos.indices) {
            str.append(datos[i].toString())

            if (i != datos.size - 1) {
                str.append(", ")
            }
        }
        str.append("]")
        return str.toString()
    }

    /**
     * Retorna un código de Hash para este arreglo
     */
    override fun hashCode(): Int {
        return Arrays.hashCode(datos)
    }

    /**
     * Permite saber si dos arreglos son iguales
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }

        other as Arreglo<T>

        if (!Arrays.equals(datos, other.datos)) {
            return false
        }

        return true
    }

    /**
     * Funciones para descomponer el arreglo en sus componentes
     */
    operator fun component1(): T {
        return get(0)
    }

    operator fun component2(): T {
        return get(1)
    }

    operator fun component3(): T {
        return get(2)
    }

    operator fun component4(): T {
        return get(3)
    }

    operator fun component5(): T {
        return get(4)
    }

}