package com.github.ymkawb.translation_helper

import com.github.ymkawb.translation_helper.io._;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;
import org.scalatest._;


class FileWalkerSpec extends FlatSpec with Matchers with LazyLogging {
	info("Filewalker should walk given directory recursevly and build model according to locales")
	"Simple test" should " succeed " in {
		val name = """values(-(.+))?""".r	
		"values-it" match {
			case name(null,_) => {}
			case name(_,spec) => {logger.info(s"Spec=${spec}")}
			case _ => {}
		}
	}
}