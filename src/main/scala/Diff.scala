package com.github.ymkawb.translation_helper.model

class Diff(val added : List[StringResource],val removed : List[StringResource],val changed : List[StringResource]) {
	lazy val isEmpty = added.isEmpty && removed.isEmpty && changed.isEmpty
}

object Diff {
	
	def apply() = new Diff(Nil,Nil,Nil)

	def calcDiff(base : List[StringResource], income : List[StringResource]) : Diff = {
		if(base.isEmpty){
			if (income.isEmpty) Diff() else new Diff(income,Nil,Nil)
		}else if(income.isEmpty){
			new Diff(Nil,base,Nil)
		}
		else {
			Diff()
		}
	}
}