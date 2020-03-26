package plus.cove.infrastructure.generator;

/**
 * 主键构造器
 * 用来生成一个主键或多个主键
 * KeyGeneratorBuilder.INSTANCE.build()
 *
 * @author jimmy.zhang
 * @date 2019-04-19
 */
public class KeyGeneratorBuilder {
    /** 
    * 单例模式
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-09-06 
    */ 
    public static final KeyGeneratorBuilder INSTANCE = new KeyGeneratorBuilder();

    private KeyGenerator generator;

    private KeyGeneratorBuilder() {
        this.generator = new SnowflakeKeyGenerator(0);
    }

    /** 
    * 创建
    * @param  
    * @return  
    * @author jimmy.zhang 
    * @date 2019-09-06 
    */ 
    public long build() {
        return this.generator.generateKey();
    }
}
