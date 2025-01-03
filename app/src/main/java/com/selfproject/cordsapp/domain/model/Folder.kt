package com.selfproject.cordsapp.domain.model

import java.sql.Date

data class Folder(
    val folderId: Int? = null,
    val name: String,
    val description: String,
    val createdOn: Date,
    val layers: List<Layer>
)