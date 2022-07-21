import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.ninhaoshijie.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import java.lang.Math.max
import java.lang.Runnable

fun main() {
  fun chubuDayin() {
    println("您好 您好")
    println(getResultMsg(Success("成功消息")))
    println(getResultMsg(Failure(Exception("失败消息"))))
  }

  fun tongjiZimu() {
    println(StringUtil.lettersCount("ABC123xyz!@#"))
  }

  fun tongjiZimu2() {
    println("ABC123xyz!@#H".lettersCount())
  }

  fun duixiangJiaDuixiang() {
    val obj = Obj() + Obj(1)
    println(obj.n)
  }

  fun qianJiaQian() {
    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    println(money3.value)
  }

  fun qianJiaZhengshu() {
    val money1 = Money(5)
    val money2 = Money(10)
    val money3 = money1 + money2
    val money4 = money3 + 20
    println(money4.value)
  }

  fun dayinZifuchuanContains() {
    println("hello".contains("he"))
  }

  fun dayinZifuchuanIn() {
    println("he" in "hello")
  }

  fun zifuchuanBeishu(z: String,b: Int) {
    val str = z * b
    println(str)
  }
  fun f(s:String,i:Int){
    println("$s $i")
  }

  /*example{
    s,i-> println("$s $i")
  }*/

  /*inlineTest({
    println("1")
  },{
    println("2")
  })*/

  /*if ("Hello Kotlin"  beginsWith "Hello") {
    println("已包括了")
  }*/
  /*val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
  if (list has "Banana") {
    println("使用infix后列表还是已包括指定的元素了")
  }*/
  /*val map = mapOf("Apple" with 1, "Banana" with 2, "Orange" with 3, "Pear" with 4, "Grape" with 5)
  println("映射表的键和值$map")*/
  /*val result1 = getGenericType<String>()
  val result2 = getGenericType<Int>()
  println("result1 is $result1")
  println("result2 is $result2")*/

  //协变
//  val student = Student("Tom", 19)
//  val data = SimpleData<Student>(student)
//  data.set(student)
//  handleSimpleData(data) // 实际上这行代码会报错，这里假设它能编译通过
//  val studentData = data.get()


  //逆变
//  val trans = object : Transformer<Person> {
//    override fun transform(name: String, age: Int): Person {
//      return Teacher(name, age)
//    }
//  }
//  handleTransformer(trans) // 这行代码会报错

  /*GlobalScope.launch {
    println("codes run in coroutine scope")
  }*/
  /*runBlocking {
    println("codes run in coroutine scope")
    delay(1500)
    println("codes run in coroutine scope finished")
  }*/
//  Thread.sleep(1000)
  /*runBlocking {
    launch {
      println("launch1")
      delay(1000)
      println("launch1 finished")
    }
    launch {
      println("launch2")
      delay(1000)
      println("launch2 finished")
    }
  }*/
  /*val start = System.currentTimeMillis()
  runBlocking {
    repeat(100000) {
      launch {
        println(".")
      }
    }
  }
  val end = System.currentTimeMillis()
  println(end - start)*/
  /*runBlocking {
    coroutineScope {
      launch {
        for (i in 1..10) {
          println(i)
          delay(1000)
        }
      }
    }
    println("coroutineScope finished")
  }
  println("runBlocking finished")*/
  /*runBlocking {
    val result = async {
      5 + 5
    }.await()
    println(result)
  }*/
  /*runBlocking {
    val start = System.currentTimeMillis()
    val result1 = async {
      delay(1000)
      5 + 5
    }.await()
    val result2 = async {
      delay(1000)
      4 + 6
    }.await()
    println("result is ${result1 + result2}.")
    val end = System.currentTimeMillis()
    println("cost ${end - start} ms.")
  }*/
  /*runBlocking {
    val start = System.currentTimeMillis()
    val deferred1 = async {
      delay(1000)
      5 + 5
    }
    val deferred2 = async {
      delay(1000)
      4 + 6
    }
    println("result is ${deferred1.await() + deferred2.await()}.")
    val end = System.currentTimeMillis()
    println("cost ${end - start} milliseconds.")
  }*/
  /*runBlocking {
    val result = withContext(Dispatchers.Default) {
      5 + 5
    }
    println(result)
  }*/
  /*val a = 10
  val b = 15
  val c = 5*/
  /*println(max(max(a, b), c))*/
  /*println(max(a, b, c))*/
  /*val a = 3.5
  val b = 3.8
  val c = 4.1*/
  /*println(max(a, b, c))*/
  /*println(kotlin.math.min(kotlin.math.min(a, b), c))*/
  /*println(min(a, b, c))*/
  /*val libraries = dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.squareup.retrofit2:converter-gson:2.6.1")
  }
  for (lib in libraries) {
    println(lib)
  }*/

  /*val html = table {
    tr {
      td { "Apple" }
      td { "Grape" }
      td { "Orange" }
    }
    tr {
      td { "Pear" }
      td { "Banana" }
      td { "Watermelon" }
    }
  }*/
  val html = table {
    repeat(2) {
      tr {
        val fruits = listOf("Apple", "Grape", "Orange")
        for (fruit in fruits) {
          td { fruit }
        }
      }
    }
  }
  println(html)

}

abstract class TextWatcher2 : TextWatcher {

  override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
  }

  override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
  }

}

fun EditText.addTextChangedListener2(tw:TextWatcher2) {
  addTextChangedListener(tw)
}

class Sky (val info: String, val icon: Int, val bg: Int)

private val sky = mapOf(
  "CLEAR_DAY" to Sky("晴", R.drawable.ic_clear_day, R.drawable.bg_clear_day),
  "CLEAR_NIGHT" to Sky("晴", R.drawable.ic_clear_night, R.drawable.bg_clear_night),
  "PARTLY_CLOUDY_DAY" to Sky("多云", R.drawable.ic_partly_cloud_day,R.drawable.bg_partly_cloudy_day),
  "PARTLY_CLOUDY_NIGHT" to Sky("多云", R.drawable.ic_partly_cloud_night,
    R.drawable.bg_partly_cloudy_night),
  "CLOUDY" to Sky("阴", R.drawable.ic_cloudy, R.drawable.bg_cloudy),
  "WIND" to Sky("大风", R.drawable.ic_cloudy, R.drawable.bg_wind),
  "LIGHT_RAIN" to Sky("小雨", R.drawable.ic_light_rain, R.drawable.bg_rain),
  "MODERATE_RAIN" to Sky("中雨", R.drawable.ic_moderate_rain, R.drawable.bg_rain),
  "HEAVY_RAIN" to Sky("大雨", R.drawable.ic_heavy_rain, R.drawable.bg_rain),
  "STORM_RAIN" to Sky("暴雨", R.drawable.ic_storm_rain, R.drawable.bg_rain),
  "THUNDER_SHOWER" to Sky("雷阵雨", R.drawable.ic_thunder_shower, R.drawable.bg_rain),
  "SLEET" to Sky("雨夹雪", R.drawable.ic_sleet, R.drawable.bg_rain),
  "LIGHT_SNOW" to Sky("小雪", R.drawable.ic_light_snow, R.drawable.bg_snow),
  "MODERATE_SNOW" to Sky("中雪", R.drawable.ic_moderate_snow, R.drawable.bg_snow),
  "HEAVY_SNOW" to Sky("大雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
  "STORM_SNOW" to Sky("暴雪", R.drawable.ic_heavy_snow, R.drawable.bg_snow),
  "HAIL" to Sky("冰雹", R.drawable.ic_hail, R.drawable.bg_snow),
  "LIGHT_HAZE" to Sky("轻度雾霾", R.drawable.ic_light_haze, R.drawable.bg_fog),
  "MODERATE_HAZE" to Sky("中度雾霾", R.drawable.ic_moderate_haze, R.drawable.bg_fog),
  "HEAVY_HAZE" to Sky("重度雾霾", R.drawable.ic_heavy_haze, R.drawable.bg_fog),
  "FOG" to Sky("雾", R.drawable.ic_fog, R.drawable.bg_fog),
  "DUST" to Sky("浮尘", R.drawable.ic_fog, R.drawable.bg_fog)
)
fun getSky(skycon: String): Sky {
  return sky[skycon] ?: sky["CLEAR_DAY"]!!
}

fun table(block: Table.() -> Unit): String {
  val table = Table()
  table.block()
  return table.html()
}

class Table {
  private val children = ArrayList<Tr>()
  fun tr(block: Tr.() -> Unit) {
    val tr = Tr()
    tr.block()
    children.add(tr)
  }
  fun html(): String {
    val builder = StringBuilder()
    builder.append("<table>")
    for (childTag in children) {
      builder.append(childTag.html())
    }
    builder.append("\n</table>")
    return builder.toString()
  }
}

class Tr {
  private val children = ArrayList<Td>()
  fun td(block: Td.() -> String) {
    val td = Td()
    td.content = td.block()
    children.add(td)
  }
  fun html(): String {
    val builder = StringBuilder()
    builder.append("\n\t<tr>")
    for (childTag in children) {
      builder.append(childTag.html())
    }
    builder.append("\n\t</tr>")
    return builder.toString()
  }
}

class Td {
  var content = ""
  fun html() = "\n\t\t<td>$content</td>"
}

fun dependencies(block: Dependency.() -> Unit): List<String> {
  val dependency = Dependency()
  dependency.block()
  return dependency.libraries
}

class Dependency {
  val libraries = ArrayList<String>()
  fun implementation(lib: String) {
    libraries.add(lib)
  }
}

fun View.showSnackbar(text: String, actionText: String? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
  val snackbar = Snackbar.make(this, text, duration)
  if (actionText != null && block != null) {
    snackbar.setAction(actionText) {
      block()
    }
  }
  snackbar.show()
}

fun View.showSnackbar(resId: Int, actionResId: Int? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
  val snackbar = Snackbar.make(this, resId, duration)
  if (actionResId != null && block != null) {
    snackbar.setAction(actionResId) {
      block()
    }
  }
  snackbar.show()
}

fun View.showSnackbar(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
  Snackbar.make(this, text, duration).show()
}

fun View.showSnackbar(resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
  Snackbar.make(this, resId, duration).show()
}

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
  Toast.makeText(context, this, duration).show()
}

fun String.showToast(context: Context) {
  Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun Int.showToast(context: Context) {
  Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

fun <T : Comparable<T>> min(vararg nums: T): T {
  if (nums.isEmpty()) throw RuntimeException("Params can not be empty.")
  var minNum = nums[0]
  for (num in nums) {
    if (num < minNum) {
      minNum = num
    }
  }
  return minNum
}

fun <T : Comparable<T>> max(vararg nums: T): T {
  if (nums.isEmpty()) throw RuntimeException("Params can not be empty.")
  var maxNum = nums[0]
  for (num in nums) {
    if (num > maxNum) {
      maxNum = num
    }
  }
  return maxNum
}

fun max(vararg nums: Int): Int {
  var maxNum = Int.MIN_VALUE
  for (num in nums) {
    maxNum = kotlin.math.max(maxNum, num)
  }
  return maxNum
}

fun handleTransformer(trans: Transformer<Student>) {
  val result = trans.transform("Tom", 19)
}

interface Transformer<in T> {
  fun transform(name: String, age: Int): @UnsafeVariance T
}

open class Person(val name: String, val age: Int)
class Student(name: String, age: Int) : Person(name, age)
class Teacher(name: String, age: Int) : Person(name, age)

class SimpleData<out T> (val data: T?){

  /*fun set(t: T?) {
    data = t
  }*/
  fun get(): T? {
    return data
  }
}

fun handleSimpleData(data: SimpleData<Person>) {
  val personData = data.get()
}

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
  val intent = Intent(context, T::class.java)
  intent.block()
  context.startActivity(intent)
}

inline fun <reified T> startActivity(context: Context) {
  val intent = Intent(context, T::class.java)
  context.startActivity(intent)
}

inline fun <reified T> getGenericType() = T::class.java

infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)

infix fun <T> Collection<T>.has(element: T) = contains(element)

infix fun String.beginsWith(prefix: String) = startsWith(prefix)

inline fun runRunnable(crossinline block: () -> Unit) {
  val runnable = Runnable {
    block()
  }
  runnable.run()
}

inline fun printString2(str: String, block: (String) -> Unit) {
  println("printString begin")
  block(str)
  println("printString end")
}

fun dayin3ceng2() {
  println("main start")
  val str = ""
  printString2(str) { s ->
    println("lambda start")
    if (s.isEmpty()) return
    println(s)
    println("lambda end")
  }
  println("main end")
}

fun printString(str: String, block: (String) -> Unit) {
  println("printString begin")
  block(str)
  println("printString end")
}

fun dayin3ceng() {
  println("main start")
  val str = ""
  printString(str) { s ->
    println("lambda start")
    if (s.isEmpty()) return@printString
    println(s)
    println("lambda end")
  }
  println("main end")
}

inline fun inlineTest(block1: () -> Unit, noinline block2: () -> Unit) {
  block1()
  block2()
}

fun chiShuiguo() {
  val list = listOf("Apple", "Banana", "Orange", "Pear", "Grape")
  val result = StringBuilder().build {
    append("Start eating fruits.\n")
    for (fruit in list) {
      append(fruit).append("\n")
    }
    append("Ate all fruits.")
  }
  println(result.toString())
}

fun dayinNum1AndNum22() {
  val num1 = 100
  val num2 = 80
  val result1 = num1AndNum2(num1, num2) { n1, n2 ->
    n1 + n2
  }
  val result2 = num1AndNum2(num1, num2) { n1, n2 ->
    n1 - n2
  }
  println("result1 is $result1")
  println("result2 is $result2")
}

fun dayinNum1AndNum2() {
  val num1 = 100
  val num2 = 80
  val result1 = num1AndNum2(num1, num2, ::plus)
  val result2 = num1AndNum2(num1, num2, ::minus)
  println("result1 is $result1")
  println("result2 is $result2")
}

fun example(func: (String, Int) -> Unit) {
  func("hello", 123)
}

class Obj(val n: Int = 0) {
  operator fun plus(obj: Obj): Obj {
    return Obj(n + obj.n)
  }
}

object StringUtil {
  fun lettersCount(str: String): Int {
    var count = 0
    for (char in str) {
      if (char.isLetter()) {
        count++
      }
    }
    return count
  }
}

sealed class Result
class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()
class Unknown: Result()

fun getResultMsg(result: Result) = when (result) {
  is Success -> result.msg
  is Failure -> "Error is ${result.error.message}"
  is Unknown -> "不知道"
  /*else -> throw IllegalArgumentException()*/
}

