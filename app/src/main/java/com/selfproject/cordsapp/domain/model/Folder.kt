package com.selfproject.cordsapp.domain.model

import java.sql.Date

data class Folder(
    val folderId: Int = 0,
    val name: String,
    val description: String,
    val createdOn: Date,
    val layersId: List<Layer>
)