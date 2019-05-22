package com.carbybus.cove.test.repository;

import com.carbybus.cove.api.ApiApplication;
import com.carbybus.cove.domain.entity.coordinate.Coordinate;
import com.carbybus.cove.domain.entity.photograph.Photo;
import com.carbybus.cove.domain.principal.UserPrincipal;
import com.carbybus.cove.repository.PhotoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
public class PhotoRepositoryTest {
    @Autowired
    private PhotoRepository photoRep;

    @Test
    public void insert() {
        UserPrincipal user = UserPrincipal.init();
        user.setUserId(1L);

        Coordinate location = new Coordinate();
        location.setLatitude(38.9989752835);
        location.setLongitude(117.6972198486);

        Photo photo = Photo.create(user);
        photo.setDescription("TTTTTTest")
                .setLocation(location)
                .setTag("tianjin")
                .setUrl("https://cove-1259284616.cos.ap-beijing.myqcloud.com/photos/IMG_1325.JPG?q-sign-algorithm=sha1&q-ak=AKIDOFjQJX6VMcNpCs4xtjBG9uItrLx7RPgi&q-sign-time=1558501623;1558503423&q-key-time=1558501623;1558503423&q-header-list=&q-url-param-list=&q-signature=eecb541e4fbc6d7af0eb4ed2ba8944bc0bedbb49&x-cos-security-token=cda0887df6238b19bbe0e77e3a62e41996bb74d610001")
                .setTakeTime(LocalDateTime.now());

        int count = photoRep.insert(photo);
        Assert.isTrue(count == 1, "照片插入");
    }
}
