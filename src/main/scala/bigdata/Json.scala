package bigdata

//import org.apache.commons.net.ntp.TimeStamp
//import scala.util.matching.Regex

/**
  * Created by bigdata on 6/6/17.
  *
  * {
  *   "id":"590892d45b251e0004476a2a",
  *   "value":{
  *             "voltage":"10",
  *             "current":"5",
  *             "timestamp":1496716624572
  *           }
  * }
  */

class Json(string: String) {

  private def DELTA_TIME: Int = 5

  private val primeiro: Int = string.indexOfSlice("\"id\":\"")
  private val segundo: Int = string.indexOfSlice("\",\"sensorData\"")
  private val terceiro: Int = string.indexOfSlice("\"voltage\":\"")
  private val quarto: Int = string.indexOfSlice("\",\"current\"")
  private val quinto: Int = string.indexOfSlice("\"current\":\"")
  private val sexto: Int = string.indexOfSlice("\",\"timestamp\"")
  private val setimo: Int = string.indexOfSlice("\"timestamp\":")
  private val oitavo: Int = string.indexOfSlice("}}")

  private val id: String = string.slice(primeiro + 6, segundo)
  private val voltage: Float = string.slice(terceiro + 11, quarto).toFloat
  private val current: Float = string.slice(quinto + 11, sexto).toFloat
  private val timestamp: Long = string.slice(setimo + 12, oitavo).toLong
  private val power:Float = instantPower

  def getId: String = return id
  def getVoltage: Float = return voltage
  def getCurrent: Float = return current
  def getTimestamp: Long = return timestamp

  def instantPower: Float ={
    return getVoltage * getCurrent
  }

  def instantEnergy: Float ={
    return instantPower * DELTA_TIME
  }

  override def toString: String = {
    return s"""{"id":"$id","sensorData":{"voltage":"$voltage","current":"$current","timestamp":$timestamp}}"""
  }
}