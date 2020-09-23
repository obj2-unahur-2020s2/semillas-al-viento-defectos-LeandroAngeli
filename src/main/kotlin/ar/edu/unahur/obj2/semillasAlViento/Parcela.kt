package ar.edu.unahur.obj2.semillasAlViento


import kotlin.Exception

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()


  fun superficie() = ancho * largo

  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }

  fun cantidadMaximaPlantas() =
    if (ancho > largo) superficie() / 5 else superficie() / 3 + largo


  fun plantar(planta: Planta) {
    if (plantas.size == this.cantidadMaximaPlantas()) {
      throw Exception("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      throw Exception("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      }
  }

  fun esSemillera(self: Parcela) = self.plantas.all{planta -> planta.daSemillas()}
}


class Agricultora(val parcelas: List<Parcela>) {


  fun parcelasSemilleras() =
    parcelas.filter {
      parcela -> parcela.esSemillera(parcela)
    }


  fun ubicacionEstrategica() = parcelas.maxBy { it.cantidadMaximaPlantas() - it.plantas.size }!!

  fun plantarEstrategicamente(planta: Planta) {
    ubicacionEstrategica().plantar(planta)
  }
}
