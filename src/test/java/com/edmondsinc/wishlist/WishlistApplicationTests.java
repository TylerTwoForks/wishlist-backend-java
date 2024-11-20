package com.edmondsinc.wishlist;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class WishlistApplicationTests {

	@Autowired
	WishlistApplication wishlistApplication;


	@Test
	void contextLoads() {
		Assertions.assertThat(wishlistApplication).isNotNull();
	}


}
