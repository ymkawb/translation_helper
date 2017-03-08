package com.github.ymkawb.translation_helper.model
import scala.collection.mutable.ListBuffer

class Diff(val added : List[Resource],val removed : List[Resource],val changed : List[Pair[Resource,Resource]]) {
	lazy val isEmpty = added.isEmpty && removed.isEmpty && changed.isEmpty
}

object Diff {
	def calcDiff(base : List[Resource], income : List[Resource]) : Diff = {				
		val mappedBase = (base map (x => (x.name,x))).toMap
		var mappedIncome = (income map (x => (x.name,x))).toMap
		val removed : ListBuffer[Resource] = ListBuffer()
		val changed : ListBuffer[Pair[Resource,Resource]] = ListBuffer()
		for( (k,v) <- mappedBase ) {
			mappedIncome get k match {
				case Some(x) if x != v => changed += Pair(v,x) 
				case None => removed +=  v 
			}	
			mappedIncome = mappedIncome - k 
		}
		new Diff(mappedIncome.values.toList,removed.toList,changed.toList)
	}
}