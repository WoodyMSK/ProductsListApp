package com.example.productslistapp.di

import com.example.productslistapp.data.ProductListRepositoryImpl
import com.example.productslistapp.domain.ProductListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindProductListRepository(impl: ProductListRepositoryImpl): ProductListRepository
}