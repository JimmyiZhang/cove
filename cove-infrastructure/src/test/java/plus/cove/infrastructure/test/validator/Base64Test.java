package plus.cove.infrastructure.test.validator;

import org.junit.Test;
import plus.cove.infrastructure.component.ActionResult;
import plus.cove.infrastructure.validator.Base64;
import plus.cove.infrastructure.validator.ValidatorUtils;

public class Base64Test {
    @Test
    public void base64() {
        String base64 = "data:image/jpeg;base64,/9j/4AAQSkZJRg";
        Base64Object obj = new Base64Object();
        obj.setImage(base64);

        ActionResult result = ValidatorUtils.valid(obj, javax.validation.groups.Default.class);
        System.out.println(result);
        org.junit.Assert.assertTrue(result.isSuccess());
    }

    public static class Base64Object {
        @Base64(format = "jpg", message = "无效的图片格式")
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
