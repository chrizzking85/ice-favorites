package com.example.demo.controller

import com.example.demo.model.Favorite
import com.example.demo.model.Song
import com.example.demo.model.SongDTO
import com.example.demo.service.FavSongService
import org.springdoc.core.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/api")
@CrossOrigin
class FavSongController(
    private val favSongService: FavSongService,
) {
    @GetMapping("/favsongs")
    fun getAllFavSongs(
        @ParameterObject pageable: Pageable,
        @RequestParam("year", required = false) year: Int? = null,
    ): Page<SongDTO> = favSongService.findAll(pageable, year)

    @PostMapping("/favsongs/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun addFavSong(@PathVariable("id") id: UUID): Favorite = favSongService.addFavSong(id)

    @PostMapping("/songs")
    @ResponseStatus(HttpStatus.CREATED)
    fun addSong(@RequestBody payload: Song): Song = favSongService.addSong(payload)

    // TODO: pageable?
    @GetMapping("/songs")
    fun getAllAvailableSongs(): List<Song> = favSongService.getAllSongs()

    @GetMapping("/songs/{id}")
    fun getSongDetails(@PathVariable("id") id: UUID): Song = favSongService.findSongById(id)
}
