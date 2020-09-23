package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  /*Este metodo (parcelaTieneComplicaciones) es de la parcela. No deberia estar dentro de una planta.
  No se respeta la cohesion.*/


  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}


/*No cumple con el desacoplamiento.
Soja transgenica deberia ser una clase aparte que herede Soja.*/
open class Soja(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return horasBase
  }


  override fun daSemillas(): Boolean  {
    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }

class SojaTransgenica(anioObtencionSemilla: Int, altura: Float) : Soja(anioObtencionSemilla, altura){

  override fun horasDeSolQueTolera(): Int{
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }
    return horasBase * 2
  }

  override fun daSemillas(): Boolean  = false



  }
}
