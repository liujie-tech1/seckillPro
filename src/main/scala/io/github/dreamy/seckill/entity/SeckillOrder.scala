package io.github.dreamy.seckill.entity

/**
 * 秒杀订单
 *
 * @author 梦境迷离
 * @since 2019年8月1日
 * @version v2.0
 */
case class SeckillOrder(id: Option[Long], userId: Option[Long], orderId: Option[Long], goodsId: Option[Long])