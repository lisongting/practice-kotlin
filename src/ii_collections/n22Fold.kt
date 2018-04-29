package ii_collections

fun example9() {
    val result = listOf(1, 2, 3, 4).fold(1, { partResult, element -> element * partResult })
    result == 24
}

// The same as
fun whatFoldDoes(): Int {
    var result = 1
    listOf(1, 2, 3, 4).forEach { element -> result = element * result}
    return result
}

//返回那些被每一个客户都订购过的商品
fun Shop.getSetOfProductsOrderedByEachCustomer(): Set<Product> {
    //得到所有被下单过的商品
    val allProducts = customers.flatMap {
        it.orders.flatMap { it.products }
    }.toSet()

    return customers.fold(allOrderedProducts, {
        orderedByAll, customer ->
        orderedByAll.intersect(
                customer.orders.flatMap { it.products }.toSet())
    })
}
