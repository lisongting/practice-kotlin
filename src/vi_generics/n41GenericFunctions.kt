package vi_generics

import util.TODO
import java.util.*
import kotlin.collections.ArrayList

fun task41(): Nothing = TODO(
    """
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
        references = { l: List<Int> ->
            l.partition { it > 0 }
            l.toCollection(HashSet<Int>())
        }
)

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
    return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
}

//第一种解法：为List和Set分别添加泛型方法扩展
//List的泛型方法扩展
//fun <T>List<T>.partitionTo(list1:ArrayList<T>,list2:ArrayList<T>,predicate:(T)->Boolean)
//        :Pair<ArrayList<T>,ArrayList<T>>{
//    this.forEach {
//        if (predicate(it)) {
//            list1.add(it)
//        } else {
//            list2.add(it)
//        }
//    }
//    return Pair(list1,list2)
//}
//
////Set的泛型方法扩展
//fun <T>Set<T>.partitionTo(set1:HashSet<T>,set2:HashSet<T>,predicate:(T)->Boolean):Pair<Set<T>,Set<T>>{
//    this.forEach {
//        if (it.let(predicate)) {
//            set1.add(it)
//        } else {
//            set2.add(it)
//        }
//    }
//    return Pair(set1,set2)
//}


//第二种解法(更通用),为Collection添加泛型方法扩展。因为List和Set接口都继承自Collection接口
fun <T,C:MutableCollection<T>>Collection<T>.partitionTo(first:C,second:C,predicate:(T)->Boolean)
        : Pair<C,C>{
    this.forEach {
        if (predicate(it)) {
            first.add(it)
        } else {
            second.add(it)
        }
    }
    return Pair(first,second)
}