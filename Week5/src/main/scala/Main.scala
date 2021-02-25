object Solution {
  // Task1
  def maxProduct(nums: Array[Int]): Int = {
    val b=sortMax2(nums)
    b
  }
  def sortMax2(nums: Array[Int]): Int ={
    var mx= -1
    var mn= -1
    for (a<-nums) {
      if (a > mx) {
        mn = mx;
        mx = a;
      } else if (a > mn)mn = a;
    }
    (mn-1)*(mx-1)
  }
  // Task2
  def average(salary: Array[Int]): Double = {
    var mx= 0.0
    var mn= 10000000.0
    var sum=0.0
    var i=0.0
    for (a<-salary) {
      i+=1.0
      sum+=a
      if (a > mx) {
        mx = a
      }
      if (a < mn) mn = a
    }
    (sum-mx-mn)/(i-2)
  }
  // Task 3
  def dayOfTheWeek(day: Int, month: Int, year: Int): String = {
    val arr = Array(0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334)
    var s = 0
    var y = 1971
    while(year>y){
      s += 365
      if(y%4==0 && y%100!=0 || y%400==0) {
        s+=1
      }

      y+=1
    }
    s += arr(month-1) + day;
    if(month>2 && (y%4==0 && y%100!=0 || y%400==0) ) s+=1

    val w=(s+5)%7
    w match {
      case 0 => "Saturday"
      case 1 => "Sunday"
      case 2 => "Monday"
      case 3 => "Tuesday"
      case 4 => "Wednesday"
      case 5 => "Thursday"
      case 6 => "Friday"
    }
  }
  println(dayOfTheWeek(1,1,2001))

  //Task4
  def findPairs(nums: Array[Int], k: Int): Int = {
    var s = 0
    var arr=sortin(nums)
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

          var p=ar(i)-ar(j)
          if(p<0){
            p=ar(j)-ar(i)
          }
          if(p==k){
            s+=1
          }
        }
      }
      s
    }
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
  def sortdub(arr: Array[Int]): Array[Int] = {
    var n = arr.length-1
    var p=2
    for(i <- 1 to n )
    {
      if(arr(i) != arr(i-1))
      {
        p+=1
      }
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
}
