package com.rage.weatherapp.presentation.base

import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

inline fun <reified T> BaseFragment.injectByScope(qualifier: Qualifier? = null,
                                                  noinline parameters: ParametersDefinition? = null) : Lazy<T>{
    return currentScope.inject(qualifier, parameters)
}
inline fun <reified T> BaseFragment.getByScope(qualifier: Qualifier? = null,
                                               noinline parameters: ParametersDefinition? = null) : T{
    return currentScope.get(qualifier, parameters)
}
inline fun <reified T> BaseActivity.injectByScope(qualifier: Qualifier? = null,
                                                  noinline parameters: ParametersDefinition? = null) : Lazy<T>{
    return currentScope.inject(qualifier, parameters)
}
inline fun <reified T> BaseActivity.getByScope(qualifier: Qualifier? = null,
                                                  noinline parameters: ParametersDefinition? = null) : T{
    return currentScope.get(qualifier, parameters)
}