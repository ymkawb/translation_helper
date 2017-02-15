package com.github.ymkawb.translation_helper

import org.scalatest._;
import com.github.ymkawb.translation_helper.model._;

class ParserSpec extends FlatSpec with Matchers{

	info("Parser should be able to parse all variants of Android string resources")

	"Empty stream " should "return empty list" in {
		assert(Parser.readModel(null) == Nil)
	}

	"String resource in \"string_resource.xml\"" should " result in single resource instance" in {
		val i = getClass().getResourceAsStream("string_resource.xml");
		val list = Parser.readModel(i)
		list should have size 1
		list(0) shouldBe a [StringResource]

	}

	"String array resource in \"string_array_resource.xml\"" should " result in single resource instance" in {
		val i = getClass().getResourceAsStream("string_array_resource.xml");
		val list : List[Resource] = Parser.readModel(i)
		list should have size 1
		list(0) shouldBe a [StringArray]
		val ar = list(0).asInstanceOf[StringArray]
		ar.items should have size 5
	}

	"Plurals resource in \"string_plurals_resource.xml\"" should " result in single plurals instance" in {
		val i = getClass().getResourceAsStream("string_plurals_resource.xml");
		val list : List[Resource] = Parser.readModel(i)
		list should have size 1
		list(0) shouldBe a [Plurals]
		val p = list(0).asInstanceOf[Plurals];
		p.name shouldEqual "plural_name"
		p.values should have size PluralKey.values.size
	}
}	