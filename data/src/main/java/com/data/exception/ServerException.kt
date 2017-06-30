package com.data.exception

import java.lang.Exception

/**
 * 描    述：自定义服务器异常
 * 作    者：xul@13322.com
 * 时    间：2017/6/7
 */
class ServerException : Exception {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)

}