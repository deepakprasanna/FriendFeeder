package models

import java.net.{URLConnection, URL}
import scala.xml._
import scala.collection.mutable.{Queue, HashMap}

object Feeder {
  var url = new URL("http://friendfeed.com/api/feed/public?format=xml&num=100")
  var feedXml = XML.load(url.openConnection.getInputStream)

  def entries = {
    var entries = new Queue[Node]
    feedXml\"entry" foreach{(entry) =>
      entries +=entry
    }
    entries
  }

  def usernames = {
    var usernames = new Queue[String]
    entries.foreach{ (entry) =>
      usernames += (entry\"user"\"nickname").text
    }
    usernames
  }
}
