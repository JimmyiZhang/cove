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
                .setTakeTime(LocalDateTime.now());

        int count = photoRep.insert(photo);
        Assert.isTrue(count == 1, "照片插入");
    }
}
