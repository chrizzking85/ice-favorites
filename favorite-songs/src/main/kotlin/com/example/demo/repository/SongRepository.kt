package com.example.demo.repository

import com.example.demo.model.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SongRepository : JpaRepository<Song, UUID> {
    @Query(
        nativeQuery = true,
        value = """
            SELECT song.id, song.name, song.artist, song.album, song.year as year, song.song_length_secs, song.genre 
            FROM favorite
            JOIN song ON favorite.song_id = song.id AND (year = :year OR :year IS NULL)
            WHERE favorite.user_name = :userName
        """,
    )
    fun findAllFavoriteSongsByFilters(pageable: Pageable, userName: String, year: Int? = null): Page<Song>
}

@Repository
interface FavSongRepository : JpaRepository<Favorite, FavoriteId>