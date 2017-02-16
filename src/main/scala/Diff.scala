package com.github.ymkawb.translation_helper.model

class Diff(val added : List[StringResource],val removed : List[StringResource],val changed : List[StringResource]) {
	lazy val isEmpty = added.isEmpty && removed.isEmpty && changed.isEmpty
}

object Diff {
	def calcDiff(base : List[StringResource], income : List[StringResource]) : Diff = {
		if(base.isEmpty && income.isEmpty) new Diff(Nil,Nil,Nil)
		else {
			base map (_,_)
		}
		
	}
}