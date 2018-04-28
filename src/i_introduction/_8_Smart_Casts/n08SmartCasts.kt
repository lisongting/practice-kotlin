package i_introduction._8_Smart_Casts

import util.TODO
import util.doc8

// 'sealed' modifier restricts the type hierarchy:
// all the subclasses must be declared in the same file
sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int =
        when (e) {
            is Num -> e.value
            is Sum -> eval(e.left)+ eval(e.right)
            //这里不需要加else,因为密封类的个数是有限的，只能定义在密封类的同一个文件中
//            else -> throw  IllegalArgumentException("Unknown expression")
        }

fun todoTask8(expr: Expr): Nothing = TODO(
    """
        Task 8.
        Complete the implementation of the 'eval' function above using smart casts and 'when' expression.
        The 'JavaCode8.eval' method provides the similar functionality written in Java.
    """,
    documentation = doc8(),
    references = { JavaCode8().eval(expr) })

