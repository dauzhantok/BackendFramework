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
  ex6(5)
}