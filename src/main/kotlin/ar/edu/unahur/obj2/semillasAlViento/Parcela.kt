package ar.edu.unahur.obj2.semillasAlViento


import kotlin.Exception

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0  /*Cantidad de plantas se puede conseguir contando los elementos de la lista plantas.
                           Tiene redundancia.*/

  fun superficie() = ancho * largo

  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  /*En las secciones de codigo donde dice ancho * largo podria reemplazarse por la
  funcion ya implementada superficie, que hace exactamente lo mismo.
  No cumple con la Consistencia.*/
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo

  /*Problema de Robustez. El sistema muestra los errores de por que no se puede hacer una accion.
  Pero no da opcion para seguir con el proceso.*/
  fun plantar(planta: Planta) {
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      throw Exception("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      throw Exception("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }
  }
}


  /*La variable ahorrosEnPesos, y la funcion comprarParcela no deberian estar. Ya que no son pedidas.
  No cumple con la simplicidad del codigo.*/
class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  /*Aca se le esta diciendo a una parcela, que agregue algo a su lista de plantas.
  Pero deberia utilizar la funcion plantar.
  Al hacerlo como lo hace agrega una planta a la lista, pero no la suma al a cantidad de plantas.*/
  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
    laElegida.plantas.add(planta)
  }
}
