package ean.estructuradedatos

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ArregloTest {
    @Test
    fun prueba1() {
        val arr = Arreglo<Int>(10)

        assertEquals(10, arr.tam)
        for (i in arr.indices) {
            arr[i] = i + 1
        }
        println(arr)

        arr.llenarCon(0)
        println(arr)
        assertEquals(0, arr.primero)
        assertEquals(0, arr.ultimo)
        assertEquals(9, arr.ultimaPosicion)

        val arr2 = arr.copiar()
        arr[0] = -11
        println(arr)
        println(arr2)

        val arr3 = arr.copiar(4)
        arr3.llenarCon(4)
        println(arr3)

        var a = Arreglo<Int>(5)
        a.llenarCon { it * 2 }
        println(a)
        a += 16
        println(a)
        val b = a + arr3
        println(b)
        val c = b.copiar(6, 10)
        println(c)
        assertEquals(arr3, c)

        val (x, y, z) = a
        println("$x, $y, $z, $a")
        println("Prueba superada 👍")
    }
}