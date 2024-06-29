package pers.jamestang.ktokr.system.util

data class Resp(val code: Int, val data: Any?, val msg: String) {

    companion object {
        @JvmStatic
        fun success(): Resp = Resp(200, null, "ok")

        @JvmStatic
        fun data(data: Any): Resp = Resp(200, data, "ok")

        @JvmStatic
        fun error(code: Int, msg: String): Resp = Resp(code, null, msg)

        @JvmStatic
        fun error(msg: String): Resp = Resp(500, null, msg)
    }

}
