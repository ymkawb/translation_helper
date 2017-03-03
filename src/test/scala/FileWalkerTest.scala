package com.github.ymkawb.translation_helper

import com.github.ymkawb.translation_helper.io._;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;
import org.scalatest._;


class FileWalkerSpec extends FlatSpec with Matchers with LazyLogging {
	info("Filewalker should walk given directory recursevly and build model according to locales")
	"Simple test" should " succeed " in {
		val name = """values(-(\w+))?""".r
		// val name(filename,locale) = "valuess"
		// logger.info(s"Filename:${filename}, locale:${locale}")
		"values-it" match {
			case name(null,_) => logger.info("Default values")
			case name(_,loc) => logger.info(s"Values with locale ${loc}")
			case _ => logger.info("No match")
		}
	}
}