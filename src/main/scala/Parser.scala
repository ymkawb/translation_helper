package com.github.ymkawb.translation_helper

import java.io.InputStream;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;
import scala.collection.mutable.ListBuffer
import scala.xml._;

object Parser {

	val logger = Logger("Resource model parser")

	private val parseMap = Map(
		"string"	-> ((x : Node) =>  StringResource((x \ "@name").text,x.text)),
		"plurals"	-> ((x : Node) => readPlural(x)),
		"string-array" -> ((x : Node) => readArray(x))
		)

	def readModel(in : InputStream) : List[Resource] = {
		if( in == null ){
			return Nil
		}
		val root = XML.load(in);
		(for( (k,v) <- parseMap) yield  ((root \ k ) map v)).toList.flatten				
	}

	def readPlural(n:Node) : Plurals = {		
		val tupleList = for( e <- n \ "item") yield  (PluralKey.withNameInsensitive((e \ "@quantity").text),e.text)
		Plurals((n \ "@name").text,tupleList.toMap)
	} 

	def readArray(n:Node) : StringArray =  {
		val name = (n \ "@name" ).text
		val array : Array[String] = (for ( e <- n \ "item" ) yield {e.text}).toArray
		StringArray(name,array)
	}
	
}