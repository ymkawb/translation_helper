package com.github.ymkawb.translation_helper.model
{
import enumeratum._
    //Resource model
    sealed trait PluralKey extends EnumEntry


    object PluralKey extends Enum[PluralKey] {
     val values = findValues
     case object zero extends PluralKey
     case object one  extends PluralKey
     case object two  extends PluralKey
     case object few  extends PluralKey
     case object many extends PluralKey
     case object other extends PluralKey
   }

   abstract class Resource(name : String)

    /*
    
    */
    case class StringResource(name: String, value:String) extends Resource(name)    
    case class StringArray(name: String, items: Array[String]) extends Resource(name)
    case class Plurals(name: String, values: Map[PluralKey,String]) extends Resource(name)
  }
