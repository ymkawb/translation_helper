package com.github.ymkawb.translation_helper
import scala.util.parsing.combinator._
import org.scalatest._;
import com.github.ymkawb.translation_helper.model._;
import com.typesafe.scalalogging._;

class ResourceQualifierSpec extends FlatSpec with Matchers with LazyLogging{
	info("""Resource quilifier should be able to parse and build all supported android resource quilifiers.
	See https://developer.android.com/guide/topics/resources/providing-resources.html table 2""")

	"Resource quilifier parser" should " parse mcc-mnc scheme with both values " in {
		import ResourceQualifier.QualifierParser._
		parse(mcc_mnc,"mcc10-mnc10") match {			
			case Success(_,_) => {}
			case x => fail(s"Result = ${x.getClass}")
		}	
	}

	"Resource quilifier parser" should " parse mcc-mnc scheme with mcc only " in {
		import ResourceQualifier.QualifierParser._
		parse(mcc_mnc,"mcc10") match {			
			case Success(_,_) => {}
			case x => fail(s"Result = ${x.getClass}")
		}	
	}


	"Resource quilifier parser" should " parse local values and save hole value  " in {
		import ResourceQualifier.QualifierParser._
		parse(locale,"en-rUS") match {			
			case Success(_,_) => {}
			case x => {
				logger.error(x.toString)
				fail(s"Result = ${x.getClass}")
			}
		}	
	}
}
