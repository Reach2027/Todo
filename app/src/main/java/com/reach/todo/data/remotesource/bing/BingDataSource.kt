package com.reach.todo.data.remotesource.bing

import com.reach.todo.data.remotesource.bing.bean.BingResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 2022/2/5  Reach
 */
@Singleton
class BingDataSource @Inject constructor(
    private val serviceCreator: BingServiceCreator
) {

    private val bingService by lazy {
        serviceCreator.create<BingService>()
    }

    suspend fun getImageUrl(): Flow<BingResult> = flow {
        emit(bingService.getImageUrl())
    }.flowOn(Dispatchers.IO)
        .catch { e ->
            e.printStackTrace()
        }

}