package com.jetbrains.kmpapp.di.useCases

/**
 * A UseCase is the communicator between the VM and the data-layer
 */
interface UseCase<I, O> {
    suspend fun execute(input: I): O
}