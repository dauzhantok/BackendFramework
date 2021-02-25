import scala.io.StdIn.readLine

object Hello extends App {
  def ex1(a:Int,b:Int):Int={
    a+b
  }
  //ex(1,2)
  def ex2(a:Int,b:Int):Unit={
    if (a>b) {
      println("a is bigger")
    }else println("b is bigger")
  }
  //ex2(2,3)
  def ex3(i:Int): String ={
    val result = i match {
      case 1 => "one"
      case 2 => "two"
      case _ => "not 1 or 2"
    }
    result
  }
  //println(ex3(2))
  def ex4(a:Int)={
    val arr=for(i<- 1 to a) yield i*i
    println(arr)
  }
  //ex4(5)
  def ex5(a:Int)={
    var i=0;
    while (i<a){
      i+=1
      println(i)

    }
  }
  //ex5(5)
  def ex6(a:Int)={
    var i=0
    do{
      i+=1
      println(i)
    } while (i<a)
  }
  //ex6(5)
  def ex7(): Unit ={
    val speech = """Four score and
               |seven years ago
               |our fathers ...""".stripMargin

    print(speech)
  }
  //ex7()
  def ex8(): Unit ={
    print("Enter your first name: ")
    val firstName = readLine()

    print("Enter your last name: ")
    val lastName = readLine()

    println(s"Your name is $firstName $lastName")
  }
  //ex8()
  def ex9(): Unit ={
    val people = List(
      "Bill",
      "Candy",
      "Karen",
      "Leo",
      "Regina"
    )
    //for (p <- people) println(p)
    people.foreach(println)
  }

  //ex9()
  //hashMap
  def ex10(): Unit ={
    val ratings =Map(
      "Brat2"->0.3,
      "Madagaskar"->4.3,
      "RED"->4.0
    )
    //for((name,rat)<-ratings) println(name,rat)
    ratings.foreach{
      case(name,rat)=> println(name,rat)
    }
  }
  //ex10()
  def ex11(): List[String] ={
    val namesM =List("_dauzhan","_bolik","_daulet")
    //val names = for(n<-namesM) yield n.drop(1).capitalize
    val names = for(n <-namesM) yield{
      val nameD = n.drop(1)
      val name = nameD.capitalize
      name
    }
    names
  }
  //println(ex11())
  def ex12(n:Int) = n match {
    case 1|3|5|7|9 => println("odd")
    case 0|2|4|6|8 => println("even")
  }
  //ex12(2)
  def ex13(n:Int) = n match{
    case 1 =>{
      //doChtoNibut
      println("1")
    }
    case x if x==2||x==3 =>{
      //doChtoNibut
      println("2 or 3")
    }
    case x if x>3 =>{
      println("n>3")
      //doChtoNibut
    }
    case _=>{
      //doChtoNibut
      println("0")
    }
  }
  //ex13(0)
  def ex14(stock:String) = stock match {
    case x if (x == "ZXC") => ex12(x.length)
    case x if (x=="QWER" ) => ex12(x.length)
    case x => println("ZXC or QWER")
  }
  //ex14("ZXC")

  def sortdub(arr: Array[Int]): Array[Int] = {
    var n = arr.length-1
    var p=2
    for(i <- 1 to n ) {
    {
      if(arr(i) != arr(i-1))
      {
        p+=1
      }
    }
    println(p)
    }
    var nums = new Array[Int](p)
    nums(0)=arr(0)
    var t=1
    for (i <- 1 to n){
      if(arr(i) != arr(i-1))
      {
        nums(t)=arr(i)
        t+=1
      }
    }
    nums
  }
  def sortin(arr: Array[Int]): Array[Int] = {
    for (i <- 0 to (arr.length - 1)){
      for (j <- 0 to (arr.length - 2)){
        var b=arr(j)
        var c=arr(j+1)
        if(b>c){
          var a=arr(j)
          arr(j)=arr(j+1)
          arr(j+1)=a
        }
      }
    }
    arr
  }
  def findPairs(nums: Array[Int], k: Int): Int = {
    var s = 0
    val arr=sortin(nums)
    if (k < 0)
      0
    else if (k == 0){
      for ( i <- 0 to (arr.length - 1)) {
        for ( j <- i+1 to (arr.length - 1)) {
          if(arr(i)-arr(j)==0){
            s+=1
          }
        }
      }
      s
    }
    else {
      var ar=sortdub(arr)
      for ( i <- 0 to (ar.length - 1)) {
        for ( j <- i+1 to (ar.length - 1)) {
          var p=ar(j)-ar(i)
          if(p==k){
            s+=1
          }
        }
      }
      s
    }
  }

  var nums=Array(1,2,4,4,3,3,0,9,2,3)
  println(findPairs(nums,3))
}
