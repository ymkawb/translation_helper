 package com.github.ymkawb.translation_helper.model
 import scala.util.parsing.combinator._

 //See https://developer.android.com/guide/topics/resources/providing-resources.html Table 2 for up-to-date list of variation
 case class ResourceQualifier(
 	val mcc_mnc : Option[String] = None,
 	val locale : Option[String] = None, // TODO: 
 	val layoutDirection : Option[String] = None,
 	val smallestWidth : Option[String] = None,
 	val avalibleWidth : Option[String] = None,
 	val screenSize : Option[String] = None,
 	val screenAspect : Option[String] = None,
 	val roundScreen : Option[String] = None,
 	val screenOrientation : Option[String] = None,
 	val uiMode : Option[String] = None,
 	val nightMode : Option[String] = None,
 	val dpi : Option[String] = None,
 	val touchScreenType : Option[String] = None,
 	val keyboardAvailibility : Option[String] = None,
 	val primaryTextInput : Option[String] = None,
 	val primaryNonTouch : Option[String] = None,
 	val platformVersion : Option[String] = None
 	)


 object ResourceQualifier {

 	object QualifierParser extends RegexParsers {
 		sealed trait Token {}
 		case class SEPARATOR() extends Token
 		case class MCC_MNC(mcc:String,mnc:Option[String] = None) extends Token
 		case class LOCALE(value:String) extends Token
 		case class LAYOUTDIRECTION(str:String) extends Token
 		case class SMALLESTWIDTH(str:String) extends Token
 		case class AVALIBLEWIDTH(str:String) extends Token
 		case class SCREENSIZE(str:String) extends Token
 		case class ROUNDSCREEN(str:String) extends Token 
 		case class SCREENASPECT(str:String) extends Token
 		case class SCREENORIENTATION(str:String) extends Token
 		case class UIMODE(str:String) extends Token
 		case class NIGHTMODE(str:String) extends Token
 		case class DPI(str:String) extends Token
 		case class TST(str:String) extends Token
 		case class KA(str:String) extends Token
 		case class PTI(str:String) extends Token
 		case class PNT(str:String) extends Token
 		case class PLATFORMVERSION(str:String) extends Token

 		implicit def tokenToOptionString(t:Option[Token]) : Option[String] = t match {
 			case None => None
 			case Some(x) => Some("SOME")
 		}

 		// private val MCC_MNC_RE = """(mcc-\d+)(-mnc\d+)?""".r;

 		private val MCC_MNC_RE = "(mcc\\d+)(-mnc\\d+)?".r;


 		def separator : Parser[Token] = "-".r ^^ {_ => SEPARATOR()}

 		def mcc_mnc : Parser[Token] = MCC_MNC_RE  ^^ { _ match {
 			case MCC_MNC_RE(mcc,null) => MCC_MNC(mcc,None)
 			case MCC_MNC_RE(mcc,mnc) => MCC_MNC(mcc,Some(mnc))
 			case x => MCC_MNC(x,None)
 			}
 		}
		def locale : Parser[Token] = "\\w\\w(-r\\w\\w)?".r ^^ {LOCALE(_)}

		def layoutDirection : Parser[Token] = "(ldrtl)|(ldltr)".r ^^ {LAYOUTDIRECTION(_)}

		def smallestWidth : Parser[Token] = "sw\\d+dp".r ^^ {SMALLESTWIDTH(_)} // Use groups for extrating actual value for dp

		def avalibleWidth  : Parser[Token] = "w\\d+dp".r ^^ {AVALIBLEWIDTH(_)}

		def quilifier : Parser[ResourceQualifier] = mcc_mnc.? ~ separator.? ~> locale.? ^^ { case mcc_mnc ~ locale  => {new ResourceQualifier(
			mcc_mnc = mcc_mnc,
			locale = locale
			)} }

	}

	

 		def apply(input:String) = ???

 	}
