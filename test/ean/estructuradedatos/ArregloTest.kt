package ean.estructuradedatos

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.random.Random

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

    @Test
    fun prueba2() {
        val lst1: Lista<String> = ListaConArreglos("Hola", "Mundo", "Comienza", "Con", "Vocal")
        println(lst1)

        lst1.eliminarPrimero()
        assertEquals(4, lst1.tam)
        assertEquals("Mundo", lst1.primero)
        println(lst1)

        val lst2: Lista<Int> = ListaConArreglos(3, 11, -2, 14, 1, 44)
        lst2.eliminarUltimo()
        assertTrue(-2 in lst2)
        assertFalse(0 in lst2)
        assertEquals(5, lst2.tam)
        assertEquals(1, lst2.ultimo)
        assertEquals(4, lst2.ultimaPosicion)
        println(lst2)

        // Llenar con aleatorios
        val random = Random(45)
        val lst3 = ListaConArreglos<Int>()
        repeat(120) {
            lst3.agregarAlFinal(random.nextInt())
        }
        println(lst3.tam)
        println(lst3.primero)
        println(lst3.ultimaPosicion)
        println("Elemento 50 -> ${lst3[50]}")
        println("Elemento 51 -> ${lst3[51]}")
        lst3.eliminarElementoEnPosicion(50)
        println("Tam => ${lst3.tam}")
        println("Ultima posicion -> ${lst3.ultimaPosicion}")
        println("Elemento 50 -> ${lst3[50]}")
        println("Elemento 51 -> ${lst3[51]}")

        lst3.eliminarTodosLosElementos()
        println("Tam => ${lst3.tam}")
        assertTrue(lst3.vacia)
        println(lst3)

        val lst4 = ListaConArreglos<Int>(6, 1, 2, 11, 8, 6, 4, 3, 6, 11, 2)
        println(lst4.posicionDe(2))
        println(lst4.ultimaPosicionDe(2))
        lst4.eliminarElemento(11)
        println(lst4.ultimaPosicionDe(11))
        println(lst4.posicionDe(11))

        val lst5 = ListaConArreglos<Int>()
        for (i in lst4.indices) {
            lst5.agregarAlPrincipio(lst4[i])
        }
        println(lst4)
        println(lst5)
        lst5.agregarEnPosicion(2, 7)
        lst5.agregarEnPosicion(5, -120)
        println(lst5)
        lst5.agregarEnPosicion(0, -20)
        lst5.agregarEnPosicion(120, 8)
        println(lst5)

        var lst6 = lst5 + 12
        println(lst6)
        lst6 += 23
        println(lst6)

        val arreglos = Arreglo<Int>(1, 2, 3, 4, 5)
        val lst7 = lst6 + arreglos
        println(lst7)

        var sum = 0
        recorrer(lst7) { elem ->
            sum += elem
            println(sum)
        }

        val l1 = ListaConArreglos(1, 4, 2, 3)
        val l2 = ListaConArreglos(1, 2, 3, 4)
        assertTrue(l1.similar(l2))
    }

    @Test
    fun prueba3() {
        // Llenar con aleatorios
        val random = Random(45)
        val lst3 = ListaConNodosDoblementeEncadenados<Int>()
        val l4 = ListaConArreglos<Int>()
        repeat(120) {
            val x = random.nextInt()
            lst3.agregarAlFinal(x)
            l4.agregarAlFinal(x)
        }
        println(lst3.tam)
        println(lst3.primero)
        println(lst3.ultimaPosicion)
        println("Elemento 50 -> ${lst3[50]}")
        println("Elemento 51 -> ${lst3[51]}")
        assertTrue(lst3 == l4)

        val l1 = ListaConNodosDoblementeEncadenados(1, 4, 2, 3)
        val l2 = ListaConArreglos(1, 2, 3, 4)
        assertTrue(l1.similar(l2))

        val lst4 = ListaConNodosDoblementeEncadenados(6, 1, 2, 11, 8, 6, 4, 3, 6, 11, 2)
        println(lst4.posicionDe(2))
        println(lst4.ultimaPosicionDe(2))
        lst4.eliminarElemento(11)
        println(lst4.ultimaPosicionDe(11))
        println(lst4.posicionDe(11))
        println(lst4)
        lst4.eliminarUltimo()
        println(lst4)
        println(lst4.posicionDe(2))
        println(lst4.ultimaPosicionDe(2))
        lst4[1] = 11
        println(lst4)
        assertTrue(3 in lst4)

        val lst5 = ListaConNodosDoblementeEncadenados<Int>()
        for (i in lst4.indices) {
            lst5.agregarAlPrincipio(lst4[i])
        }
        println(lst4)
        println(lst5)
        lst5.agregarEnPosicion(2, 7)
        lst5.agregarEnPosicion(5, -120)
        println(lst5)
        lst5.agregarEnPosicion(0, -20)
        lst5.agregarEnPosicion(120, 8)
        println(lst5)

        var lst6 = lst5 + 12
        println(lst6)
        lst6 += 23
        println(lst6)
        val (a, b) = lst6
        println("$a, $b")
    }

    @Test
    fun prueba4() {
        val miLista: Lista<Int> = Lista.crear()
        miLista.agregarAlFinal(10)
        miLista.agregarAlPrincipio(11)
        miLista.agregarEnPosicion(1, 25)
        println(miLista)
    }
}