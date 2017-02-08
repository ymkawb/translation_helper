package com.github.ymkawb.translation_helper
import enumeratum._


package model {

    //Resource model
    sealed trait PluralKey extends EnumEntry


    object PluralKey extends Enum[PluralKey] {
           val values = findValues
           case object Zero extends PluralKey
           case object One  extends PluralKey
           case object Two  extends PluralKey
           case object Few  extends PluralKey
           case object Many extends PluralKey
           case object Other extends PluralKey
    }

    abstract class Resource(name : String)

    /*
    
    */
    case class StringResource(name: String, value:String) extends Resource(name)
    case class StringArray(name: String, items: Array[String]) extends Resource(name)
    case class Plurals(name: String, values: Map[PluralKey,String]) extends Resource(name)


}