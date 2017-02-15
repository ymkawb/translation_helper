package com.github.ymkawb.translation_helper

import java.io.InputStream;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;
import scala.collection.mutable.ListBuffer
import scala.xml._;

object Parser {

	val logger = Logger("Resource model parser")

	def readModel(in : InputStream) : List[Resource] = {
		if( in == null ){
			return Nil
		}
		val root = XML.load(in);
		val result : ListBuffer[Resource] = ListBuffer()
		result ++= (for( e <- root \ "string" ) yield StringResource((e \ "@name").text,e.text))
		result ++= (for( e <- root \ "plurals" ) yield 	readPlural(e))
		result ++= (for( e <- root \ "string-array") yield readArray(e))
		result.toList
	}

	def readPlural(n:Node) : Plurals = {		
		val tupleList = for( e <- n \ "item") yield  (PluralKey.withNameInsensitive((e \ "@quantity").text),e.text)
		Plurals((n \ "@name").text,tupleList.toMap)
	} 

	def readArray(n:Node) : StringArray =  {
		val name = (n \ "@name" ).text
		val array : Array[String] = (for ( e <- n \ "item" ) yield {
				e.text
			}).toArray
		StringArray(name,array)
	}
	
}