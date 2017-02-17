package com.github.ymkawb.translation_helper

import org.scalatest._;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;

class DiffCalcSpec extends FlatSpec with Matchers{
	import Diff._
	info("Calculatin diff should support addition, removal, and changing of resource values")

	"Diff of empty lists " should " return empty list " in {
	  (calcDiff(List(),List())) shouldBe 'empty
	}

	"Diff of non-empty base list and empty incoming" should " result in removal  of all elements" in {
		val l = List(StringResource("a","b"))
		val diff = calcDiff(l,Nil)
		diff.removed should have size 1
	}


	"Diff of non-empty incoming list and empty base" should " result in addition  of all elements" in {
		val l = List(StringResource("a","b"))
		val diff = calcDiff(Nil,l)
		diff.added should have size 1
		diff.changed should have size 0
		diff.removed should have size 0
	}

	"Diff of resource with same key and differnt value" should " result in chaanged diff"  in {
		val s1 = StringResource("key","value")
		val s2 = StringResource("key","value1")
		val diff = calcDiff(List(s1),List(s2));
		diff.changed should have size 1
		diff.changed(0)._1 shouldBe s1
		diff.changed(0)._2 shouldBe s2
	}
}