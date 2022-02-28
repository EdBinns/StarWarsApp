package com.edbinns.starwarsapp.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.edbinns.starwarsapp.models.Character
import com.edbinns.starwarsapp.repository.CharactersRepository
import javax.inject.Inject

class CharactersSource @Inject constructor(private  val repository: CharactersRepository) : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return  try {

            val page = params.key ?: 1

            when(val charactersResponse = repository.getAllCharacters(page)){
                is Resource.Success -> {
                    LoadResult.Page(
                        data = charactersResponse.data!!,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = page.plus(1)
                    )
                }

                is Resource.Error ->{
                    LoadResult.Error(Exception("Error paging"))
                }
            }
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        TODO("Not yet implemented")
    }
}