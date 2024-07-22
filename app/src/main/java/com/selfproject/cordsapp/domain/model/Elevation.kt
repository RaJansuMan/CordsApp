package com.selfproject.cordsapp.domain.model

data class Elevation(
    val elevationType: ElevationType,
    val ellipsoidal: Double? = null,
    val egm96: Double? = null,
    val egm08: Double? = null
)

enum class ElevationType(val code: String) {
    ELLIPSOIDAL("Ellipsoidal"),
    EGM96("EGM-96"),
    EGM08("EGM-08"),
    ALL("ALL");

    companion object{
        fun fromCode(code: String):ElevationType?{
            return entries.find { it.code == code }
        }
    }
}