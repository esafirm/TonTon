package nolambda.stream.tonton.chat.model

import java.util.UUID

data class Profile(
    val avatar: String,
    val name: String,
    val drawMe: String,
)

data class ChatModel(
    val id: String = UUID.randomUUID().toString(),
    val profile: Profile,
    val matched: Boolean
)