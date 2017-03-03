package com.github.ymkawb.translation_helper.model

import enumeratum._
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

    abstract class Resource(val name : String){}


    /*
    
    */
    case class StringResource(name_ : String,val value:String) extends Resource(name_)    
    case class StringArray(name_ : String,val items: Array[String]) extends Resource(name_)
    case class Plurals(name_ : String,val values: Map[PluralKey,String]) extends Resource(name_)
