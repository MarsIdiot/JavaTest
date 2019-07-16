
/**
 * 枚举开发规范参考
 */
/**
 * 1、枚举类名建议带上Enum后缀，枚举成员名称需要全大写，单词间用下划线隔开
 * 2、强制：所有的枚举类型字段必须有注释，说明每个数据项的用途。除非如:CodeEnum带message的。
 * 3、枚举对象之间的比较可以用==
 *   说明：枚举类其实就是特殊的常量类，且构造方法被默认强制私有，无法被new出来
 */