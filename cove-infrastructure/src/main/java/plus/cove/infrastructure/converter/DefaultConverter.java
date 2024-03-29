package plus.cove.infrastructure.converter;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import plus.cove.infrastructure.utils.AssertHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 基础转换器
 *
 * @author jimmy.zhang
 * @date 2019-04-02
 */
public class DefaultConverter implements BaseConverter {
    protected static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().mapNulls(false).build();
    protected static final MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    public static final DefaultConverter INSTANCE = new DefaultConverter();

    protected DefaultConverter() {
    }

    @Override
    public <O, T> T convert(O origin, Class<T> targetClass) {
        AssertHelper.assertNotNull(origin);
        AssertHelper.assertNotNull(targetClass);

        return MAPPER_FACADE.map(origin, targetClass);
    }

    @Override
    public <O, T> void convert(O origin, T target) {
        AssertHelper.assertNotNull(origin);
        AssertHelper.assertNotNull(target);

        MAPPER_FACADE.map(origin, target);
    }

    public <O, T> List<T> convertAll(List<O> origins, Class<T> targetClass) {
        List<T> targets = new ArrayList<>();
        if (origins == null) {
            return targets;
        }

        for (O origin : origins) {
            T target = convert(origin, targetClass);
            targets.add(target);
        }
        return targets;
    }
}
