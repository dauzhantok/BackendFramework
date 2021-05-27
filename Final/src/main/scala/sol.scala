import javax.swing.tree.TreeNode

object Solution extends Node{
  def isAnagram(s: String, t: String): Boolean = {
    if (s.length == t.length) {
      var ss = s.sorted
      var tt =t.sorted
      var lis: Map[Char, Int] = Map()
      for (i <- ss) {
        if (lis.contains(i)){
          var n = lis(i) + 1
          lis = lis + (i -> n)
        }else
          lis += (i -> 1)
      }
      println(lis)
      for (j <- tt) {
        if (lis.contains(j)){
          if(lis(j)>0){
            var n = lis(j) - 1
            lis = lis + (j -> n)
          }else return false
        } else return false
      }

      true
    } else false
  }
  println(isAnagram("aacc","ccac"))
  def maxDepth(root: TreeNode): Int = {
  /*  if (root == null) return 0
    val leftDepth = maxDepth(root.left)
    val rightDepth = maxDepth(root.right)

    if (leftDepth > rightDepth)
      leftDepth + 1
    else rightDepth + 1*/
    1
  }
  def canMakeArithmeticProgression(arr: Array[Int]): Boolean = {
    arr.sorted
    val a=arr(1) - arr(0)
    for(i <- 2 to arr.length-1){
      if (arr(i) - arr(i - 1) != a)
        return false
    }
    true
  }
  def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    var arr: Array[Int] = Array()
    var t =0
    for(i<- 0 to nums1.length){
      for(j<- 0 to nums2.length){
        if(nums1(i) == nums2(j)){
          arr(t)=nums1(i)
          t+=1
        }
      }
    }
    arr
  }

  import java.util

  def largestPerimeter(nums: Array[Int]): Int = {
    var num = nums.sorted
    var p = 0
    for (i <- 0 to num.length - 3) {
      val b = check(num(i), num(i + 1), num(i + 2))
      if (b > p) p = b
    }
    p
  }

  def check(a: Int, b: Int, c: Int): Int = {
    if (a + b > c && a + c > b && c + b > a) {
      a + b + c
    } else -1
  }

  def maxDepth(root: Node): Int = {
    if (root == null) return 0
    var max = 1

    for (x <- root.children) {
      max = Math.max(maxDepth(x) + 1, max)
    }
    max
  }

  def intersection(nums1: Array[Int], nums2: Array[Int]): Array[Int] = {
    var arr1 = nums1.sorted
    var arr2 = nums2.sorted
    var arr: Array[Int] = Array()
    var lis1: Map[Int, Int] = Map()
    for (i <- 0 to arr1.length-1) {
      var n = arr1(i)
      if (lis1.contains(n)){
        //    lis1 = lis1 + (i -> arr1(i))
      }else{
        lis1 += (n -> n )
      }
    }
    println(lis1)

    var t = -1
    for(i<- 0 to arr2.length-1){
      var n = arr2(i)
      if(lis1.contains(n)){

        if(n != t){
          arr = arr ++ Array(n)
          t= n
        }

      }

    }
    println(t)
    arr
  }
class Node(var _value: Int) {
     var value: Int = _value
     var children: List[Node] = List()
}