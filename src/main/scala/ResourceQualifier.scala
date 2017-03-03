 package com.github.ymkawb.translation_helper.model
 import scala.util.parsing.combinator._

 //See https://developer.android.com/guide/topics/resources/providing-resources.html Table 2 for up-to-date list of variation
 case class ResourceQualifier(
 	val mcc : Option[String] = None,
 	val mnc : Option[String] = None,
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
 		case class layoutDirection(str:String) extends Token
 		case class smallestWidth(str:String) extends Token
 		case class avalibleWidth(str:String) extends Token
 		case class screenSize(str:String) extends Token
 		case class roundScreen(str:String) extends Token 
 		case class screenAspect(str:String) extends Token
 		case class screenOrientation(str:String) extends Token
 		case class uiMode(str:String) extends Token
 		case class nightMode(str:String) extends Token
 		case class dpi(str:String) extends Token
 		case class tst(str:String) extends Token
 		case class ka(str:String) extends Token
 		case class pti(str:String) extends Token
 		case class pnt(str:String) extends Token
 		case class platformVersion(str:String) extends Token

 		// private val MCC_MNC_RE = """(mcc-\d+)(-mnc\d+)?""".r;

 		private val MCC_MNC_RE = "(mcc\\d+)(-mnc10)?".r;


 		def separator : Parser[SEPARATOR] = "-".r ^^ {_ => SEPARATOR()}

 		def mcc_mnc : Parser[Token] = MCC_MNC_RE  ^^ { _ match {
 			case MCC_MNC_RE(mcc,null) => MCC_MNC(mcc,None)
 			case MCC_MNC_RE(mcc,mnc) => MCC_MNC(mcc,Some(mnc))
 			case x => MCC_MNC(x,None)
 			}
 		}
		def locale : Parser[Token] = "\\w\\w(-r\\w\\w)?" ^^ {LOCALE(_)}}

 		def apply(input:String) = ???

 	}
