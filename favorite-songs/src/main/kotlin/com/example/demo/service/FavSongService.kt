package com.example.demo.service

import com.example.demo.model.Favorite
import com.example.demo.model.Song
import com.example.demo.model.SongDTO
import com.example.demo.repository.FavSongRepository
import com.example.demo.repository.SongRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ResponseStatus
import java.util.*

@Service
class FavSongService(
    val songRepository: SongRepository,
    val favSongRepository: FavSongRepository,
    val userService: UserService
) {
    fun findAll(
        pageable: Pageable = PageRequest.of(0, 20),
        year: Int? = null
    ): Page<SongDTO> {
        val userName = userService.getCurrentUserName()

        return songRepository.findAllFavoriteSongsByFilters(pageable, userName, year).map { it.toDTO() }
    }

    fun addFavSong(id: UUID): Favorite {
        val song = findSongById(id)
        val userName = userService.getCurrentUserName()

        return favSongRepository.save(Favorite(userName, song))
    }

    fun addSong(payload: Song): Song {
        validateSong(payload)

        return songRepository.save(payload)
    }

    fun findSongById(id: UUID): Song {
        return songRepository.findById(id).orElseThrow { NotFoundException("Song not found") }
    }

    fun getAllSongs(): List<Song> {
        return songRepository.findAll()
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException(msg: String): Exception(msg)