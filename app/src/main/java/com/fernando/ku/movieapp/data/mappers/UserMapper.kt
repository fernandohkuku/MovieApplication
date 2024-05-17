package com.fernando.ku.movieapp.data.mappers

import com.fernando.ku.movieapp.data.local.models.UserModel
import com.fernando.ku.movieapp.domain.entities.UserEntity
import com.google.firebase.auth.AdditionalUserInfo
import com.google.firebase.auth.AuthResult

fun AuthResult.toEntity() = UserEntity(
    username = additionalUserInfo?.username
)

fun UserEntity.toModel() = UserModel(
    uuid = uuid,
    userName = username
)

fun UserModel.toEntity() = UserEntity(
    uuid = uuid,
    username = userName
)