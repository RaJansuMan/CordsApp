package com.selfproject.cordsapp.domain.coordinateModel


val validZoneLetters = listOf(
    'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
    'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X'
)
val validZoneNumbers = 1..60

fun isValidZoneNumber(number: Int): Boolean {
    return number in validZoneNumbers
}

fun isValidZoneLetter(letter: Char): Boolean {
    return letter in validZoneLetters
}

data class UTMCoordinate(
    val easting: Double,
    val northing: Double,
    val zoneNumber: Int,
    val zoneLetter: Char
) {
    init {
        require(isValidZoneNumber(zoneNumber)) { "Invalid zone number: $zoneNumber. Must be between 1 and 60." }
        require(isValidZoneLetter(zoneLetter)) { "Invalid zone letter: $zoneLetter. Must be one of $validZoneLetters." }
    }

    override fun toString(): String {
        return "$zoneNumber$zoneLetter"
    }

//    companion object {
//        fun fromString(zone: String): UTMZone? {
//            if (zone.length < 2) return null
//
//            val numberPart = zone.dropLast(1).toIntOrNull() ?: return null
//            val letterPart = zone.lastOrNull() ?: return null
//
//            return if (isValidZoneNumber(numberPart) && isValidZoneLetter(letterPart)) {
//                UTMZone(numberPart, letterPart)
//            } else {
//                null
//            }
//        }
//    }
}