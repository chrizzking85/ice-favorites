package com.example.demo.service

import com.example.demo.model.Song
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.time.Year
import java.time.format.DateTimeParseException

fun validateSong(song: Song) {
    try {
        Year.parse(song.year.toString())
    } catch (e: DateTimeParseException) {
        throw ValidationException("year cannot be parsed")
    }
}

@ResponseStatus(HttpStatus.BAD_REQUEST)
class ValidationException(msg: String): Exception(msg)