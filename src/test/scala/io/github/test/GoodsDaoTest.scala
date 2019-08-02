package io.github.test

import io.github.seckillPro.dao.GoodsDao
import io.github.seckillPro.db.DatabaseSupport
import io.github.seckillPro.entity.SeckillGoods

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * 目前不引入测试框架
 * 后期改用scalatest plus
 *
 * @author 梦境迷离
 * @time 2019-08-02
 * @version v2.0
 */
object GoodsDaoTest extends App {

  DatabaseSupport.init()

  val id = 1
  val goodsId = 1
  val stockCount = 2
  val price = 2
  val seckillGoods = SeckillGoods(id, goodsId, stockCount, price)

  val listGoodsVo = Await.result(GoodsDao.listGoodsVo(), Duration.Inf)
  listGoodsVo.foreach(x => println(x))

  val goodsVo = Await.result(GoodsDao.getGoodsVoByGoodsId(1), Duration.Inf)
  goodsVo match {
    case Some(goodsVo) => println(goodsVo)
    case None => println("Not found")
  }

  val reduceCount = Await.result(GoodsDao.reduceStock(seckillGoods), Duration.Inf)
  println(reduceCount)

  val resetStockCount = Await.result(GoodsDao.resetStock(seckillGoods), Duration.Inf)
  println(resetStockCount)

}