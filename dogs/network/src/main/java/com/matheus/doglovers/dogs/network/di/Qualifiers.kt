package com.matheus.doglovers.dogs.network.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DogsRetrofit

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DogsOkHttpClient

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class InterceptorLogging