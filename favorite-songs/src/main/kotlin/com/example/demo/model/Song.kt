package com.example.demo.model

import jakarta.persistence.*
import java.util.*

@Entity
data class Song(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val artist: String,
    val album: String,
    val year: Int, // release year
    val songLengthSecs: Int,
    val genre: String
) {
    fun toDTO(): SongDTO = SongDTO(id, name, artist, year)
}

// reduced version of a song for the purpose of presenting
data class SongDTO(val id: UUID, val name: String, val artist: String, val year: Int)

@Entity
@IdClass(FavoriteId::class)
data class Favorite(
    @Id
    val userName: String,
    @Id
    @OneToOne
    val song: Song
)
data class FavoriteId(val userName: String? = null, val song: UUID? = null) : java.io.Serializable
