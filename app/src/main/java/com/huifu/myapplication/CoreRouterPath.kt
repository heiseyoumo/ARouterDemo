package com.huifu.myapplication

/**
 * @author pengkuanwang
 * @date 2019-12-23
 */
interface CoreRouterPath {
    companion object {
        /**
         * the path must be start with '/' and contain more than 2 '/'
         */
        const val MAIN: String = "/app/MainActivity"
        const val SIMPLE: String = "/app/SimpleActivity"
    }
}