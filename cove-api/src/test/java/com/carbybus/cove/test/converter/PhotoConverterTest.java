package com.carbybus.cove.test.converter;

import com.carbybus.cove.domain.converter.PhotoConverter;
import com.carbybus.cove.domain.entity.photograph.Photo;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.domain.view.PhotoCreateInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
public class PhotoConverterTest {
    @Test
    public void convert() {
        PhotoCreateInput input = new PhotoCreateInput();
        input.setTakeTime(LocalDateTime.now())
                .setDescription("TTTTTest")
                .setLatitude(38.9989752835)
                .setLongitude(117.6972198486)
                .setTag("tianjin");

        UserPrincipal user = UserPrincipal.init();
        user.setUserId(1L);

        Photo entity = PhotoConverter.INSTANCE.convertFrom(input, user);
        System.out.println(entity);
        Assert.notNull(entity, "照片装换");
        Assert.notNull(entity.getLocation(), "照片坐标");
    }
}
