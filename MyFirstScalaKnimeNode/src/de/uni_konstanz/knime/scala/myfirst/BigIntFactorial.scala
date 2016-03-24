package de.uni_konstanz.knime.scala.myfirst
 
/**
 * @author Oliver Sampson, University of Konstanz
 */

class BigIntFactorial {
  def calcFactorial(f : Int) : BigInt = {
     var fac : BigInt = 1
     var i : Int = 1
     for(i <- 1 to f)
       fac = fac * i
     return fac
  }
}