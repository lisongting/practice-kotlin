package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> {
    // Return the set of customers who ordered the specified product

    return customers.filter {
        it.orders.flatMap {
            it.products
        }.any {
            it==product
        }
    }.toSet()


}

fun Customer.getMostExpensiveDeliveredProduct(): Product? {
    // Return the most expensive product among all delivered products
    // (use the Order.isDelivered flag)
    return orders.filter {
        //先过滤出已被派送的订单
        it.isDelivered
    }.flatMap {
        //将每个订单中的所有产品取出到一个集合中
        it.products
    }.maxBy {
        //根据价格排序拿到最大的那个商品
        it.price
    }
}

//返回：指定商品被购买了几次
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    // Return the number of times the given product was ordered.
    // Note: a customer may order the same product for several times.
    return this.customers.flatMap {
        it.orders.flatMap {
            it.products
        }
    }.count {
        it == product
    }
}

//要注意区分map和flatMap，
//不同点：
//map中传入的lambda表达式：返回值是一个指定的R类型
//flatMap中传入的lambda表达式：返回值是可迭代的R(如集合，列表，数组等)
//相同点：
//map和flatMap的调用者，最后得到的都是一个List<R>
//public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R> {
//    return mapTo(ArrayList<R>(collectionSizeOrDefault(10)), transform)
//}

//public inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R> {
//    return flatMapTo(ArrayList<R>(), transform)
//}
