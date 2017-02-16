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
}