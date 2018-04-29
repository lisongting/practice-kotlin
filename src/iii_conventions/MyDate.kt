package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)
    :Comparable<MyDate>{

    override fun compareTo(other:MyDate):Int{
        val r:Int
        if(year<other.year){
            r = -1
        }else if(year > other.year){
            r = 1
        }else{
            if(month<other.month){
                r = -1
            }else if (month > other.month) {
                r = 1
            }else{
                if (dayOfMonth < other.dayOfMonth) {
                    r = -1
                }else if (dayOfMonth > other.dayOfMonth) {
                    r = 1
                } else {
                    r = 0
                }
            }
        }
        return r
    }

    //把+运算符重载
    operator fun plus(interval:TimeInterval):MyDate{
        return when (interval) {
            TimeInterval.DAY -> this.nextDay()
            TimeInterval.WEEK -> MyDate(year,month,dayOfMonth+7)
            TimeInterval.YEAR->MyDate(year+1,month,dayOfMonth)
        }
    }



    //这个方法实际重载了操作符..   重载写在类内部也可以
//    operator fun rangeTo(other:MyDate):DateRange{
//        return DateRange(this,other)
//    }
}

//重载操作符..  作为扩展函数写在类外部也可以
operator fun MyDate.rangeTo(other: MyDate): DateRange{
    return DateRange(this,other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    //重载 * 运算符，创建一个新的类
    operator fun times(number:Int) = RepeatedTimeInterval(this,number)
}

class RepeatedTimeInterval(val t: TimeInterval, val num: Int)

//再次重载 + 运算符。之前的重载是对TimeInterval，现在是对RepeatedTimeInterval
operator fun MyDate.plus(proxy : RepeatedTimeInterval):MyDate{
    return addTimeIntervals(proxy.t,proxy.num)
}

class DateRange(val start: MyDate, val endInclusive: MyDate):Iterable<MyDate>{
    var current:MyDate = start

    //调用的时候是date in DateRange(first, last)
    //而对in操作重载却是写在DateRange中
    operator fun contains(date:MyDate):Boolean{
        return date>=start&&date<=endInclusive
    }

    override fun iterator(): Iterator<MyDate> {
        //返回一个对象表达式
        return object:Iterator<MyDate>{
            override fun hasNext():Boolean{
                return  current<=endInclusive

            }
            override fun next():MyDate{
                var result = current.copy()
                current = current.nextDay()
                return result
            }
        }
    }
}
