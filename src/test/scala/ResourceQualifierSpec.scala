package com.github.ymkawb.translation_helper

import scala.util.parsing.combinator._
import org.scalatest._;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;

class ResourceQualifierSpec extends FlatSpec with Matchers with LazyLogging{

	info("""Resource quilifier should be able to parse and build all supported android resource quilifiers.
		See https://developer.android.com/guide/topics/resources/providing-resources.html table 2""")

	"ResourceQualifier " should " parse mcc and optional mnc " in {
		var mcc = "mcc310"
		var rq = ResourceQualifier(mcc)
		rq.mcc_mnc shouldBe Some(mcc)
		mcc = "mcc310-mnc004"
		rq = ResourceQualifier(mcc)
		rq.mcc_mnc shouldBe Some(mcc)
	}

	"ResourceQualifier " should " parse locale" in {
		var mcc = "en"
		var rq = ResourceQualifier(mcc)
		rq.locale shouldBe Some("en")		
		logger.debug(s"${rq}")		
	}


	"ResourceQualifier " should " parse layout direction in order" in {
		var mcc = "ldrtl"
		var rq = ResourceQualifier(mcc)
		rq.layoutDirection shouldBe Some("ldrtl")				
	}



	"ResourceQualifier " should " parse two quilifiers in order" in {
		var mcc = "mcc310-en-rUS"
		var rq = ResourceQualifier(mcc)
		rq.mcc_mnc shouldBe Some("mcc310")		
		rq.locale shouldBe Some("en-rUS")		
	}

	"ResourceQualifier " should " parse two quilifiers with one missed " in {
		var mcc = "mcc310-ldrtl"
		var rq = ResourceQualifier(mcc)
		rq.mcc_mnc shouldBe Some("mcc310")		
		rq.layoutDirection shouldBe Some("ldrtl")		
	}
}
