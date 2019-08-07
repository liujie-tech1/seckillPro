package io.github.seckillPro.serializer

import java.lang.reflect.Type

import com.google.gson._
import io.github.seckillPro.entity.SeckillUser
import io.github.seckillPro.util.ImplicitUtils

/**
 * 自定义SeckillUser的序列化
 *
 * @author 梦境迷离
 * @time 2019-08-07
 * @version v2.0
 */
class SeckillUserSerialize extends JsonSerializer[SeckillUser] with JsonDeserializer[SeckillUser] {
  override def serialize(seckill: SeckillUser, typeOfSrc: Type, context: JsonSerializationContext): JsonElement = {
    val data = new JsonObject
    val id = seckill.id.getOrElse(-1).asInstanceOf[Number]
    val nickname = seckill.nickname
    val password = seckill.password
    val salt = seckill.salt
    val head = seckill.head
    val loginCount = seckill.loginCount
    val registerDate = ImplicitUtils.toLong(seckill.registerDate)
    val lastLoginDate = ImplicitUtils.toLong(seckill.lastLoginDate)
    data.addProperty("id", id)
    data.addProperty("nickname", nickname)
    data.addProperty("password", password)
    data.addProperty("salt", salt)
    data.addProperty("head", head)
    data.addProperty("loginCount", loginCount)
    data.addProperty("registerDate", registerDate)
    data.addProperty("lastLoginDate", lastLoginDate)
    data
  }

  override def deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): SeckillUser = {
    import com.google.gson.JsonObject
    val jsonObject: JsonObject = json.getAsJsonObject
    SeckillUser(
      Option(jsonObject.get("id").getAsLong),
      jsonObject.get("nickname").getAsString,
      jsonObject.get("password").getAsString,
      jsonObject.get("salt").getAsString,
      jsonObject.get("head").getAsString,
      jsonObject.get("loginCount").getAsInt,
      ImplicitUtils.toLocalDateTime(Option(jsonObject.get("registerDate").getAsLong)),
      ImplicitUtils.toLocalDateTime(Option(jsonObject.get("lastLoginDate").getAsLong))
    )
  }
}

object SeckillUserSerialize extends SeckillUserSerialize