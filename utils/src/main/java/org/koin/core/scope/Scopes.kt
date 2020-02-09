package org.koin.core.scope

import org.koin.core.Koin

/**
 * Created by 23alot on 01.02.2020.
 */
fun Koin.isScopeOpen(scopeName: String): Boolean {
    val scope = scopeRegistry.getScopeInstanceOrNull(scopeName)
    return scope != null
}
