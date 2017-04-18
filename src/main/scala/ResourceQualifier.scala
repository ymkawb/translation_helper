package com.github.ymkawb.translation_helper.model

 import com.typesafe.scalalogging._;
 //See https://developer.android.com/guide/topics/resources/providing-resources.html Table 2 for up-to-date list of variation
 case class ResourceQualifier(
 	val mcc_mnc : Option[String] = None,
 	val locale : Option[String] = None, // TODO: 
 	val layoutDirection : Option[String] = None,
 	val smallestWidth : Option[String] = None,
 	val avalibleWidth : Option[String] = None,
 	val avalibleHeight : Option[String] = None,
 	val screenSize : Option[String] = None,
 	val screenAspect : Option[String] = None,
 	val roundScreen : Option[String] = None,
 	val wideColorGamut : Option[String] = None,
 	val hdr : Option[String] = None,
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
 	def apply(input:String) : ResourceQualifier = Tokenizer.build(input)
 }
