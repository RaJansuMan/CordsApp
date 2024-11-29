package com.selfproject.cordsapp.domain.coordinateModel

data class Elevation(
    val elevationType: ElevationType,
    val ellipsoidal: Double? = null,
    val egm96: Double? = null,
    val egm08: Double? = null
)

enum class ElevationType(val code: String, val codeInt: Int) {
    ALL("ALL", 1),
    ELLIPSOIDAL("Ellipsoidal", 1),
    EGM96("EGM-96", 2),
    EGM08("EGM-08", 3);


    companion object {
        fun fromCode(code: String): ElevationType? {
            return entries.find { it.code == code }
        }
    }
}