package v_builders.examples

fun todoTask38(): Nothing = TODO(
    """
        Task 38.
        The previous examples can be rewritten with the library function 'apply' (see examples below).
        Write your own implementation of the function 'apply' named 'myApply'.
    """
)

//类型为T的扩展函数myApply,传入lambda表达式(形参列表为空，返回值为空)
//最后该扩展函数的返回值为T
fun <T> T.myApply(f: T.() -> Unit): T {
    this.f()
    return  this

    //这里不能按照上一题这样写，因为泛型参数T无法实例化
//    var tInstance:T
//    tInstance.f()
//    return tInstance

}

fun buildString(): String {
    return StringBuilder().myApply {
        append("Numbers: ")
        for (i in 1..10) {
            append(i)
        }
    }.toString()
}

fun buildMap(): Map<Int, String> {
    return hashMapOf<Int, String>().myApply {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}