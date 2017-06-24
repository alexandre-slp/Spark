package bigdata.bigdata

/**
  * Created by bigdata on 6/20/17.
  */
case class Sensor(id: String, voltage: Float, current: Float, timestamp: Long) {

  private val _id: String = id
  private val _voltage: Float = voltage
  private val _current: Float = current
  private val _timestamp: Long = timestamp

  def getId: String = return _id
  def getVoltage: Float = return _voltage
  def getCurrent: Float = return _current
  def getTimestamp: Long = return _timestamp

}
