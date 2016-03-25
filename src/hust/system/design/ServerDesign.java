package hust.system.design;

/**
 * 由若干服务器组成的集群一起提供服务，共同承担访问压力，请设计集群的增减策略，使得当集群增加或减少机器时，数据移动的代价尽量小。 
 * 
 * 解答：一致性哈希算法（将当前IP根据hash算法映射到不同缓冲区，解决路由问题），负载均衡（轮询，比重，流量比例，服务类别等）
 * 
 * 参考：
 * http://blog.csdn.net/yq76034150/article/details/6776044
 * http://www.cnblogs.com/yanghuahui/p/3755460.html
 * 
 * @since 2015-11-30
 *
 */

public class ServerDesign {

}
