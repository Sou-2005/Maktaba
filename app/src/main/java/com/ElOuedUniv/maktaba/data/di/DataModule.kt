package com.ElOuedUniv.maktaba.data.di

import com.ElOuedUniv.maktaba.data.repository.BookRepository
import com.ElOuedUniv.maktaba.data.repository.SupabaseBookRepositoryImpl
import com.ElOuedUniv.maktaba.data.repository.CategoryRepository
import com.ElOuedUniv.maktaba.data.repository.SupabaseCategoryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideCategoryRepository(
        supabaseCategoryRepositoryImpl: SupabaseCategoryRepositoryImpl
    ): CategoryRepository {
        return supabaseCategoryRepositoryImpl
    }

    @Provides
    @Singleton
    fun provideBookRepository(
        supabaseBookRepositoryImpl: SupabaseBookRepositoryImpl
    ): BookRepository {
        return supabaseBookRepositoryImpl
    }
}
