package com.carbybus.cove.domain.converter;

import com.carbybus.cove.domain.entity.photograph.Photo;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.PhotoCreateInput;
import com.carbybus.infrastructure.converter.DefaultConverter;

/**
 * 照片转换器
 *
 * @author jimmy.zhang
 * @date 2019/5/20
 */
public class PhotoConverter extends DefaultConverter {
    public static final PhotoConverter INSTANCE = new PhotoConverter();

    private PhotoConverter() {
        super();
        MAPPER_FACTORY.classMap(PhotoCreateInput.class, Photo.class)
                .field("latitude", "location.latitude")
                .field("longitude", "location.longitude")
                .byDefault()
                .register();
    }

    public Photo convertFrom(PhotoCreateInput input, UserPrincipal user) {
        Photo out = super.convert(input, Photo.class);
        out.setOwnerId(user.getUserId());
        return out;
    }
}
