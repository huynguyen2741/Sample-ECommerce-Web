package com.ecommerce.email;

import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ecommerce.model.Address;
import com.ecommerce.model.Orders;
import com.ecommerce.model.Product;
import com.ecommerce.model.Role;
import com.ecommerce.model.User;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc(addFilters = false)
class EmailServiceTest {
	
	@MockBean
	private EmailService emailService;
	
	@Test
	void testEmailSend() throws MessagingException {
		List<Orders> orders = new ArrayList<>();
		Set<Role> roles = new HashSet<>();
		List<Address> addresses = new ArrayList<>();
		User user = new User(1L, "Taylor", "Joostema", "TaylorJ1208@yahoo.com", "tay", "123", "919", "8604",
				orders, roles, addresses, null);
		
		emailService.sendEmail(user);
		verify(emailService).sendEmail(user);
	}
	
	@Test
	void testEmailReceipt() throws MessagingException {
		Orders order = new Orders();
		User user = new User();
		List<Product> products = new ArrayList<>();
		java.sql.Date date = new java.sql.Date(1500);
		order = new Orders(1L, new BigDecimal(15.00), date, true, user, "913 Bridlemine Dr.", "913 Bridlemine Dr.",
				products);
		
		emailService.sendReceipt(order);
		verify(emailService).sendReceipt(order);
	}

}
