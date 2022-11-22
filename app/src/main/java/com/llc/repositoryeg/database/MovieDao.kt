package com.llc.moviedb.database

import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavouriteMovie(item: MovieEntity)

    @Delete
    suspend fun delete(item: MovieEntity)

    @Query("SELECT * from movieEntity WHERE id = :id")
    fun getFavouriteMovieById(id: Long): MovieEntity?

    @Query("SELECT * from movieEntity ORDER BY id ASC")
    fun getAllFavouriteMovie(): List<MovieEntity>
}