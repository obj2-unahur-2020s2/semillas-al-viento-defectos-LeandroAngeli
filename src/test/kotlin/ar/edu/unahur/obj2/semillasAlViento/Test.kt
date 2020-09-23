package ar.edu.unahur.obj2.semillasAlViento


import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe


class SemillasAlViento : DescribeSpec({
    describe("Una parcela") {


        /* Configuracion de Plantas */
        val menta1 = Menta(1996,1.15F)
        val menta2 = Menta(2001,0.9F)

        /*Para crear las sojas y las sojas transgenicas
        es necesario desacoplarlas.*/

        val soja1 = Soja(1988,0.4F)
        val soja2 = Soja(2009,1.27F)
        val soja3 = Soja(2010,1.40F)

        val sojaTrans1 = Soja.SojaTransgenica(2012,0.9F)
        val sojaTrans2 = Soja.SojaTransgenica(2000,1.72F)
        val sojaTrans3 = Soja.SojaTransgenica(2008,1.53F)


        /* Configuracion de Parcelas */

        val parcela1 = Parcela(3,5,10)
        val parcela2 = Parcela(9,5,7)
        val parcela3 = Parcela(8,4,5)

        parcela1.plantar(soja2)
        parcela1.plantar(sojaTrans1)
        parcela1.plantar(sojaTrans2)

        parcela2.plantar(soja2)
        parcela2.plantar(sojaTrans1)
        parcela2.plantar(sojaTrans2)

        parcela3.plantar(menta1)
        parcela3.plantar(menta2)
        parcela3.plantar(soja2)

        /* Configuracion de Agricultora */
        val agricultora1 = Agricultora(mutableListOf(parcela1, parcela2, parcela3))


        /* Comienzo de Testeo del codigo */
        menta1.horasDeSolQueTolera().shouldBe(6)
        soja1.horasDeSolQueTolera().shouldBe(6)
        soja2.horasDeSolQueTolera().shouldBe(9)
        sojaTrans1.horasDeSolQueTolera().shouldBe(14)
        sojaTrans2.horasDeSolQueTolera().shouldBe(18)

        menta1.esFuerte().shouldBe(expected = false)
        menta2.esFuerte().shouldBe(expected = false)
        soja1.esFuerte().shouldBe(expected = false)
        soja2.esFuerte().shouldBe(expected = false)
        sojaTrans1.esFuerte().shouldBe(true)
        sojaTrans2.esFuerte().shouldBe(expected = true)

        menta1.daSemillas().shouldBe(true)
        menta2.daSemillas().shouldBe(true)
        soja1.daSemillas().shouldBe(false)
        soja2.daSemillas().shouldBe(true)
        sojaTrans1.daSemillas().shouldBe(false)
        sojaTrans2.daSemillas().shouldBe(false)



        parcela1.superficie().shouldBe(15)
        parcela2.superficie().shouldBe(45)
        parcela3.superficie().shouldBe(32)
        parcela1.cantidadMaximaPlantas().shouldBe(10)
        parcela2.cantidadMaximaPlantas().shouldBe(9)
        parcela3.cantidadMaximaPlantas().shouldBe(6)

        /* Para hacer el test de complicaciones hubo que mover
        el codigo que estaba puesto dentro de planta a Parcela. */
        parcela1.parcelaTieneComplicaciones(parcela = parcela1).shouldBe(true)
        parcela2.parcelaTieneComplicaciones(parcela = parcela2).shouldBe(false)

        /* Para poder probar si se puede plantar o no hubo que corregir
            la funcion de plantar para que de una excepcion en vez del print. */
        shouldThrowAny { parcela1.plantar(menta1) }
        shouldThrowAny { parcela1.plantar(soja1) }
        parcela2.plantar(sojaTrans3)
        parcela2.plantas.shouldContain(sojaTrans3)


        agricultora1.parcelasSemilleras().shouldContainAll(listOf(parcela3))
        agricultora1.plantarEstrategicamente(soja3)
        agricultora1.parcelas.filter { it.plantas.contains(soja3) }.shouldContainAll(listOf(parcela1))



    }
})

