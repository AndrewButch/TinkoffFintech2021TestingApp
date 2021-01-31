package com.andrewbutch.androiddevelopertinkofffintech2021.ui.latest

import com.andrewbutch.androiddevelopertinkofffintech2021.repository.PostsRepository
import com.andrewbutch.androiddevelopertinkofffintech2021.ui.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

@ExperimentalCoroutinesApi
class LatestPostsViewModel(private val postsRepository: PostsRepository) : BaseViewModel() {
    override fun getNextPost() {
        increasePage()
        val source = postsRepository.getNextLatestPost()
        source.onEach {
            withContext(Dispatchers.Main) {
                handleResult(it)
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }

    override fun getPreviousPost() {
        decreasePage()
        val source = postsRepository.getPreviousLatestPost()
        source.onEach {
            withContext(Dispatchers.Main) {
                handleResult(it)
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))

    }

    override fun retry() {
        val source = postsRepository.getNextLatestPost()
        source.onEach {
            withContext(Dispatchers.Main) {
                handleResult(it)
            }
        }.launchIn(CoroutineScope(Dispatchers.IO))
    }
}
